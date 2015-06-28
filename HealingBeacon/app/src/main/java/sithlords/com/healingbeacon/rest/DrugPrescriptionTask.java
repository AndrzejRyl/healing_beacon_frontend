package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sithlords.com.healingbeacon.model.PatientCard;

public class DrugPrescriptionTask extends AsyncTask<Long, Void, PatientCard> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    private final PatientCardResponseListener listener;

    public DrugPrescriptionTask(PatientCardResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected PatientCard doInBackground(Long... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
            restTemplate.getMessageConverters().add(messageConverter);

            return restTemplate.getForObject(ENDPOINT + "/beacons/" + params[0] + "/patient_card", PatientCard.class);
        } catch (Exception e) {
            Log.e("[PatientRequestTask]", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(PatientCard patient) {
        listener.onPatientResponse(patient);
    }
}
