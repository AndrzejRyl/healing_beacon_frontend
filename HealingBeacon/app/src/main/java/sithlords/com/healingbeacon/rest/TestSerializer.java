package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import sithlords.com.healingbeacon.model.TemperatureMeasurement;
import sithlords.com.healingbeacon.model.Test;

public class TestSerializer extends JsonSerializer<Test> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

    @Override
    public void serialize(Test value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectFieldStart("test");
        jgen.writeStringField("taken_time", DATE_FORMAT.format(value.getTakenTime()));
        jgen.writeStringField("test_type", value.getTestType());
        jgen.writeStringField("result", value.getResult());
        jgen.writeEndObject();
    }

    @Override
    public Class<Test> handledType() {
        return Test.class;
    }
}
