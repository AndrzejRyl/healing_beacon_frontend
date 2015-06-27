package sithlords.com.healingbeacon.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import sithlords.com.healingbeacon.model.BloodMeasurement;
import sithlords.com.healingbeacon.model.DrugDose;
import sithlords.com.healingbeacon.model.Patient;
import sithlords.com.healingbeacon.model.PatientCard;
import sithlords.com.healingbeacon.model.PrescribedDrug;
import sithlords.com.healingbeacon.model.TemperatureMeasurement;

import static com.google.common.collect.Lists.newArrayList;

public class PatientCardJsonDeserializer extends JsonDeserializer<PatientCard> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssZ");

    @Override
    public PatientCard deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        PatientCard patientCard = new PatientCard();

        JsonNode rootNode = jp.readValueAsTree();
        JsonNode patientNode = rootNode.get("patient");

        Patient patient = new Patient();
        patient.setId(patientNode.get("id").asLong());
        patient.setFirstName(patientNode.get("first_name").asText());
        patient.setLastName(patientNode.get("last_name").asText());
        patient.setPhotoUrl(patientNode.get("photo_url").asText());

        try {
            patient.setDateOfBirth(DATE_FORMAT.parse(patientNode.get("date_of_birth").asText()));
            patientCard.setVisitStart(DATE_FORMAT.parse(rootNode.get("visit_start").asText()));
            patientCard.setVisitEnd(DATE_FORMAT.parse(rootNode.get("visit_end").asText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient.setGender(patientNode.get("gender").asText());
        patient.setWeight(patientNode.get("weight").asDouble());
        patient.setHeight(patientNode.get("height").asDouble());

        patientCard.setPatient(patient);

        List<TemperatureMeasurement> temperatureMeasurements = newArrayList();
        JsonNode temperatureNodes = rootNode.get("temperature_measurements");
        if (temperatureNodes.isArray()) {
            for (JsonNode tempNode : temperatureNodes) {
                TemperatureMeasurement temperatureMeasurement = new TemperatureMeasurement();
                try {
                    temperatureMeasurement.setMeasurementTime(DATE_FORMAT.parse(tempNode.get("measurement_time").asText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                temperatureMeasurement.setDegreeCelcius(tempNode.get("degree_celcius").asDouble());
                temperatureMeasurements.add(temperatureMeasurement);
            }
        }
        patientCard.setTemperatureMeasurements(temperatureMeasurements);

        List<BloodMeasurement> bloodMeasurements = newArrayList();
        JsonNode bloodNodes = rootNode.get("blood_pressure_measurements");
        if (bloodNodes.isArray()) {
            for (JsonNode bloodNode : bloodNodes) {
                BloodMeasurement bloodMeasurement = new BloodMeasurement();
                try {
                    bloodMeasurement.setMeasurementTime(DATE_FORMAT.parse(bloodNode.get("measurement_time").asText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                bloodMeasurement.setSystole(bloodNode.get("systole").asInt());
                bloodMeasurement.setDiastole(bloodNode.get("diastole").asInt());
                bloodMeasurements.add(bloodMeasurement);
            }
        }
        patientCard.setBloodPressureMeasurements(bloodMeasurements);

        List<PrescribedDrug> prescribedDrugs = newArrayList();
        final JsonNode prescribedDrugsNode = rootNode.get("prescribed_drugs");
        for (JsonNode prescribedDrugNode : prescribedDrugsNode) {
            final PrescribedDrug prescribedDrug = new PrescribedDrug();
            prescribedDrug.setDrugName(prescribedDrugNode.get("drug_name").asText());
            prescribedDrug.setIntervalHours(prescribedDrugNode.get("interval_hours").asInt());
            prescribedDrug.setDoseMilligrams(prescribedDrugNode.get("dose_milligrams").asInt());
            prescribedDrugs.add(prescribedDrug);
        }
        patientCard.setPrescribedDrugs(prescribedDrugs);

        List<DrugDose> drugDoses = newArrayList();
        JsonNode drugNodes = rootNode.get("drug_doses");
        if (drugNodes.isArray()) {
            for (JsonNode drugNode : drugNodes) {
                DrugDose drugDose = new DrugDose();
                try {
                    drugDose.setDoseTime(DATE_FORMAT.parse(drugNode.get("dose_time").asText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                drugDose.setDrugName(drugNode.get("drug_name").asText());
                drugDose.setMilligrams(drugNode.get("milligrams").asInt());
                drugDoses.add(drugDose);
            }
        }
        patientCard.setDrugDoses(drugDoses);

        return patientCard;
    }
}
