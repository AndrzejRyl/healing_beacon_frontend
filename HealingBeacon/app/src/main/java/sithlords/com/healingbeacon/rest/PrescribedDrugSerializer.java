package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import sithlords.com.healingbeacon.model.PrescribedDrug;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;

public class PrescribedDrugSerializer extends JsonSerializer<PrescribedDrug> {

    @Override
    public void serialize(PrescribedDrug value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectFieldStart("prescribed_drug");
        jgen.writeStringField("drug_name", value.getDrugName());
        jgen.writeNumberField("interval_hours", value.getIntervalHours());
        jgen.writeNumberField("dose_milligrams", value.getDoseMilligrams());
        jgen.writeEndObject();
    }

    @Override
    public Class<PrescribedDrug> handledType() {
        return PrescribedDrug.class;
    }
}
