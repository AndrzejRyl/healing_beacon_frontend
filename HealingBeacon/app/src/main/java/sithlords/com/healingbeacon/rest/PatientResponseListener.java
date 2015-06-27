package sithlords.com.healingbeacon.rest;

import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;

public interface PatientResponseListener {

    /**
     * Invoked on patient request execution completion.
     *
     * @param patient Patient returned from external service.
     */
    void onPatientResponse(PatientCard patient);
}
