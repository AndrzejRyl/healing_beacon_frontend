package sithlords.com.healingbeacon.service;

public interface ExternalService {

    /**
     *
     * @param beaconID ID of the beacon connected to the patient
     */
    void getPatient(long beaconID);

}
