package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sithlords.com.healingbeacon.model.Patient;

public class PatientRequestTask extends AsyncTask<Void, Void, Patient> {

    private static final String ENDPOINT = "http://example.com/patient/";

    private final PatientResponseListener listener;

    public PatientRequestTask(PatientResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected Patient doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.getForObject(ENDPOINT + params[0], Patient.class);
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
