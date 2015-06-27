package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sithlords.com.healingbeacon.model.Patient;

public class PatientRequestTask extends AsyncTask<Long, Void, Patient> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    private final PatientResponseListener listener;

    public PatientRequestTask(PatientResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected Patient doInBackground(Long... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.getForObject(ENDPOINT + "/beacons/" + params[0] + "/patient_card", Patient.class);
        } catch (Exception e) {
            Log.e("[PatientRequestTask]", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Patient patient) {
        listener.onPatientResponse(patient);
    }
}
