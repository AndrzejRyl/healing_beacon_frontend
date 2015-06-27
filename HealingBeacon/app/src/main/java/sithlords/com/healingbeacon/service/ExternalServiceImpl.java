package sithlords.com.healingbeacon.service;

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
}
