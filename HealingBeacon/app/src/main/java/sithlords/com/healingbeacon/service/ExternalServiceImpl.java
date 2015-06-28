package sithlords.com.healingbeacon.service;

import sithlords.com.healingbeacon.model.BloodMeasurement;
import sithlords.com.healingbeacon.model.PrescribedDrug;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;
import sithlords.com.healingbeacon.model.Test;
import sithlords.com.healingbeacon.rest.AddResultTask;
import sithlords.com.healingbeacon.rest.PatientCardRequestTask;
import sithlords.com.healingbeacon.rest.PatientCardResponseListener;

public class ExternalServiceImpl implements ExternalService {

    private PatientCardResponseListener listener;

    public ExternalServiceImpl(PatientCardResponseListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public void getPatientCard(long beaconID) {
        new PatientCardRequestTask(listener).execute(beaconID);
    }

    @Override
    public void addBloodPressureMeasurement(long beaconID, BloodMeasurement measurement) {
        new AddResultTask().execute(beaconID, "blood_pressure_measurements", measurement);
    }

    @Override
    public void addTemperatureMeasurement(long beaconID, TemperatureMeasurement measurement) {
        new AddResultTask().execute(beaconID, "temperature_measurements", measurement);
    }

    @Override
    public void prescribeDrug(long beaconID, PrescribedDrug prescribedDrug) {
        new AddResultTask().execute(beaconID, "drug_doses", prescribedDrug);
    }

    @Override
    public void addTest(long beaconID, Test test) {
        new AddResultTask().execute(beaconID, "tests", test);
    }
}
