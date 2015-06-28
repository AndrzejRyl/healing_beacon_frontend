package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AddResultTask extends AsyncTask<Object, Void, Void> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    @Override
    protected Void doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.set("Connection", "Close");
            HttpEntity<Object> httpEntity = new HttpEntity<>(params[2], requestHeaders);
            restTemplate.postForEntity(ENDPOINT + "/beacons/" + params[0] + "/patient_card/" + params[1], httpEntity, Void.class);
        } catch (Exception e) {
            Log.e("[AddResultTask]", e.getMessage(), e);
        }
        return null;
    }
}
