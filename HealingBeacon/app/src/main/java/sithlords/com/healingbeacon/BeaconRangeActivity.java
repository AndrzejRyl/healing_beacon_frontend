package sithlords.com.healingbeacon;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class BeaconRangeActivity extends Activity {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;

    private BeaconManager beaconManager;
    private List<String> beaconsInfo;
    private Map<Integer,BeaconDevice> ourBeacons;
    private List<Integer> ourMinors;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_range);

        beaconsInfo = newArrayList();
        ourBeacons = newHashMap();
        ourMinors = newArrayList(1,2,3,4,5,6);

        // Find list view
        listView = (ListView) findViewById(R.id.beacons_list);

        // Set default adapter with beacons
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, beaconsInfo);
        listView.setAdapter(adapter);


        beaconManager = BeaconManager.newInstance(this);
        beaconManager.registerRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(final Region region, final List<BeaconDevice> beacons) {
                BeaconRangeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update our map of beacons
                        for (BeaconDevice beacon : beacons) {
                            if (beacon.getMajor() == 6969 && ourMinors.contains(beacon.getMinor())) {
                                ourBeacons.put(beacon.getMinor(), beacon);
                            }
                        }

                        // Update adapter
                        adapter.clear();
                        for (BeaconDevice beacon : ourBeacons.values()) {
                            adapter.add("Address: " + beacon.getAddress() +
                                    ", name: " + beacon.getName() +
                                    ", major: " + beacon.getMajor() +
                                    ", minor: " + beacon.getMinor() +
                                    ", proximity: " + beacon.getProximity());
                        }
                        adapter.notifyDataSetChanged();

                    }});
                };

        });
    }

        @Override
        protected void onStart () {
            super.onStart();
            if (!beaconManager.isBluetoothEnabled()) {
                final Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
            } else if (beaconManager.isConnected()) {
                try {
                    beaconManager.startRanging();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else {
                connect();
            }
        }

        @Override
        protected void onStop () {
            super.onStop();
            beaconManager.stopRanging();
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();
            beaconManager.disconnect();
            beaconManager = null;
        }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){

            if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
                if (resultCode == Activity.RESULT_OK) {
                    connect();
                } else {
                    Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
                    getActionBar().setSubtitle("Bluetooth not enabled");
                }
                return;
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

        private void connect () {
            try {
                beaconManager.connect(new OnServiceBoundListener() {
                    @Override
                    public void onServiceBound() throws RemoteException {
                        beaconManager.startRanging(newHashSet(Region.EVERYWHERE));
                    }
                });
            } catch (RemoteException e) {

            }
        }
    }