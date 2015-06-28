package sithlords.com.healingbeacon.service;

import sithlords.com.healingbeacon.model.BloodMeasurement;
import sithlords.com.healingbeacon.model.PrescribedDrug;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;
import sithlords.com.healingbeacon.model.Test;

public interface ExternalService {

    /**
     *
     * @param beaconID ID of the beacon connected to the patient
     */
    void getPatientCard(long beaconID);

    void addBloodPressureMeasurement(long beaconID, BloodMeasurement measurement);

    void addTemperatureMeasurement(long beaconID, TemperatureMeasurement measurement);

    void prescribeDrug(long beaconID, PrescribedDrug prescribedDrug);

    void addTest(long beaconID, Test test);
}
