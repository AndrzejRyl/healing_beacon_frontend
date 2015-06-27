package sithlords.com.healingbeacon.service;

import sithlords.com.healingbeacon.model.Patient;

/**
 * @author FleenMobile at 2015-06-27
 */
public interface ExternalService {

    /**
     *
     * @param beaconID ID of the beacon
     * @return Patient object connected to the beacon with the given ID
     */
    public Patient getPatient(long beaconID);
}
