package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;

public class PatientCardJsonDeserializer extends JsonDeserializer<PatientCard> {

    @Override
    public PatientCard deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        PatientCard patientCard = new PatientCard();

        JsonNode node = jp.readValueAsTree();
        JsonNode patientNode = node.get("patient");

        Patient patient = new Patient();
        patient.setId(patientNode.get("id").asLong());
        patient.setFirstName(patientNode.get("first_name").asText());
        patient.setLastName(patientNode.get("last_name").asText());
        patient.setPhotoUrl(patientNode.get("photo_url").asText());
        patientCard.setPatient(patient);

        return patientCard;
    }
}
