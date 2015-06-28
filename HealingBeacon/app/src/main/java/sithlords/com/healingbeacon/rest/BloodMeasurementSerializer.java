package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import sithlords.com.healingbeacon.model.BloodMeasurement;

public class BloodMeasurementSerializer extends JsonSerializer<BloodMeasurement> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

    @Override
    public void serialize(BloodMeasurement value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectFieldStart("blood_pressure_measurement");
        jgen.writeStringField("measurement_time", DATE_FORMAT.format(value.getMeasurementTime()));
        jgen.writeNumberField("systole", value.getSystole());
        jgen.writeNumberField("diastole", value.getDiastole());
        jgen.writeEndObject();
    }

    @Override
    public Class<BloodMeasurement> handledType() {
        return BloodMeasurement.class;
    }
}
