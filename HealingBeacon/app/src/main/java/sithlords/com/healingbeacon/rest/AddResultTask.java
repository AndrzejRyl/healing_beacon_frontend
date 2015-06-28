package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sithlords.com.healingbeacon.model.PatientCard;

import static com.google.common.collect.Maps.newHashMap;

public class AddResultTask extends AsyncTask<Object, Void, Void> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    @Override
    protected Void doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            restTemplate.postForObject(ENDPOINT + "/beacons/" + params[0] + "/patient_card/" + params[1], params[2], Void.class, newHashMap());
        } catch (Exception e) {
            Log.e("[AddResultTask]", e.getMessage(), e);
        }
        return null;
    }
}
