package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import sithlords.com.healingbeacon.model.TemperatureMeasurement;

public class TemperatureMeasurementSerializer extends JsonSerializer<TemperatureMeasurement> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

    @Override
    public void serialize(TemperatureMeasurement value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectFieldStart("temperature_measurement");
        jgen.writeStringField("measurement_time", DATE_FORMAT.format(value.getMeasurementTime()));
        jgen.writeNumberField("degree_celsius", value.getDegreeCelcius());
        jgen.writeEndObject();
    }

    @Override
    public Class<TemperatureMeasurement> handledType() {
        return TemperatureMeasurement.class;
    }
}
