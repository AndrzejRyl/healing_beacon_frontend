package sithlords.com.healingbeacon.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AddResultTask extends AsyncTask<Object, Void, Object> {

    private static final String ENDPOINT = "http://api.healing-beacon.cymerys.com:8080/api";

    @Override
    protected Object doInBackground(Object... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.set("Connection", "Close");
            HttpEntity<Object> httpEntity = new HttpEntity<>(params[2], requestHeaders);

            return restTemplate.exchange(ENDPOINT + "/beacons/" + params[0] + "/patient_card/" + params[1], HttpMethod.POST, httpEntity, Object.class);
        } catch (Exception e) {
            Log.e("[AddResultTask]", e.getMessage(), e);
        }
        return null;
    }
}
