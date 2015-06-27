package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sithlords.com.healingbeacon.model.PatientCard;

public class PatientCardRequestTask extends AsyncTask<Long, Void, PatientCard> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    private final PatientCardResponseListener listener;

    public PatientCardRequestTask(PatientCardResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected PatientCard doInBackground(Long... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
            SimpleModule module = new SimpleModule("LongDeserializerModule", new Version(1, 0, 0, null));
            module.addDeserializer(PatientCard.class, new PatientCardJsonDeserializer());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(module);
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
