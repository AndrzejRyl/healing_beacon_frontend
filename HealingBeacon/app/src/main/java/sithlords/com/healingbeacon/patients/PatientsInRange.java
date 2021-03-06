package sithlords.com.healingbeacon.patients;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.kontakt.sdk.android.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.device.BeaconDevice;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;
import com.kontakt.sdk.core.Proximity;

import java.util.List;
import java.util.Map;

import sithlords.com.healingbeacon.R;
import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.rest.PatientCardResponseListener;
import sithlords.com.healingbeacon.service.ExternalServiceImpl;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;


public class PatientsInRange extends ActionBarActivity implements PatientCardResponseListener {
    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;
    public static final String PATIENT = "Patient ID";

    private BeaconManager beaconManager;
    private Map<Integer, BeaconDevice> ourBeacons;
    private List<Integer> beaconIDs;
    private ListView listView;
    private volatile Map<Integer,Patient> patients;
    private PatientListAdapter adapter;
    private ExternalServiceImpl API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patients_in_range);

        ourBeacons = newHashMap();

        // !! OMG !! This is hackaton so I hardcode this list. !! OMG !!
        beaconIDs = newArrayList(1, 2, 3, 4, 5, 6);
        patients = newHashMap();

        // Build a map of all out patients
        API = new ExternalServiceImpl(this);
        for (long beaconID : beaconIDs)
            API.getPatientCard(beaconID);

        // Find list view displaying patients in range
        listView = (ListView) findViewById(R.id.patients_list);

        // Set adapter displaying patient's name and surname
        List<Patient> test = newArrayList();
        adapter = new PatientListAdapter(this, R.layout.patient_list_item, test);
        listView.setAdapter(adapter);

        initializeBeaconManager();
    }

    private void initializeBeaconManager() {
        beaconManager = BeaconManager.newInstance(this);
        beaconManager.setForceScanConfiguration(new ForceScanConfiguration(700, 2000));
        beaconManager.registerRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(final Region region, final List<BeaconDevice> beacons) {
                PatientsInRange.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ourBeacons = newHashMap();
                        // Update our map of beacons (we take only our beacons with the proximity NEAR)
                        for (BeaconDevice beacon : beacons) {
                            if (beacon.getMajor() == 6969 && beaconIDs.contains(beacon.getMinor())
                                    && beacon.getProximity() == Proximity.NEAR) {
                                ourBeacons.put(beacon.getMinor(), beacon);
                            }
                        }

                        // Update adapter (our list of patients in range)
                        adapter.clear();
                        for (BeaconDevice beacon : ourBeacons.values()) {
                            Patient patient = patients.get(beacon.getMinor());
                            if (patient != null)
                                adapter.add(patient);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
            }


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
        beaconManager.stopRanging();
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
                public void onServiceBound() throws RemoteException {
                    beaconManager.startRanging(newHashSet(Region.EVERYWHERE));
                }
            });
        } catch (RemoteException e) {

        }
    }

    @Override
    public void onPatientResponse(PatientCard patientCard) {
        patients.put(patientCard.getPatient().getBeaconID(), patientCard.getPatient());
    }
}
