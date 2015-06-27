package sithlords.com.healingbeacon.service;

import sithlords.com.healingbeacon.rest.PatientRequestTask;
import sithlords.com.healingbeacon.rest.PatientResponseListener;

public class ExternalServiceImpl implements ExternalService {

    private PatientResponseListener listener;

    private ExternalServiceImpl() {
    }

    public ExternalServiceImpl(PatientResponseListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    public void getPatient(long beaconID) {
        new PatientRequestTask(listener).execute();
    }
}
