package sithlords.com.healingbeacon;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kontakt.sdk.android.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.configuration.MonitorPeriod;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class BeaconMonitorActivity extends Activity {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;

    private BeaconManager beaconManager;
    private List<String> beacons;
    private ListView listView;
    private Set<Integer> beaconsIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_monitor);

        beacons = newArrayList();
        beaconsIDs = newHashSet();

        // Find list view
        listView = (ListView) findViewById(R.id.beacons_list);

        // Set default adapter with beacons
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, beacons);
        listView.setAdapter(adapter);

        beaconManager = BeaconManager.newInstance(this);
        beaconManager.setMonitorPeriod(MonitorPeriod.MINIMAL);
        beaconManager.setForceScanConfiguration(ForceScanConfiguration.DEFAULT);
        beaconManager.registerMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onMonitorStart() {
            } // active scan period starts

            @Override
            public void onMonitorStop() {
            } // passive scan period starts

            @Override
            public void onBeaconAppeared(final Region region, final BeaconDevice beacon) {
                if (!beaconsIDs.contains(beacon.getMinor())) {
                    adapter.add("Address: " + beacon.getAddress() +
                            ", name: " + beacon.getName() +
                            ", major: " + beacon.getMajor() +
                            ", minor: " + beacon.getMinor() +
                            ", proximity: " + beacon.getProximity());
                    beaconsIDs.add(beacon.getMinor());
                }

                adapter.notifyDataSetChanged();

            } // beacon appeared within desired region for the first time

            @Override
            public void onBeaconsUpdated(final Region venue, final List<BeaconDevice> beacons) {
            } // beacons that are visible within specified region are provided through this method callback. This method has the same

            @Override
            public void onRegionEntered(final Region venue) {
            } // Android device enters the Region for the first time

            @Override
            public void onRegionAbandoned(final Region venue) {
            } // Android device abandons the region
        });
    }

    @Override
    protected void onStart() {
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
    protected void onStop() {
        super.onStop();
        beaconManager.stopMonitoring();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

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

    private void connect() {
        try {
            beaconManager.connect(new OnServiceBoundListener() {
                @Override
                public void onServiceBound() {
                    try {
                        beaconManager.startMonitoring(newHashSet(Region.EVERYWHERE));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}