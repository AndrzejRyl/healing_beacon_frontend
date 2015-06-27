package sithlords.com.healingbeacon.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

import sithlords.com.healingbeacon.rest.PatientCardJsonDeserializer;

/**
 * @author FleenMobile at 2015-06-27
 */
@JsonDeserialize(using = PatientCardJsonDeserializer.class)
public class PatientCard implements Serializable{
    private String visit_start;
    private String visit_end;
    private Patient patient;
    private List<TemparatureMeasurement> temperatureMeasurements;
    private List<BloodMeauserement> bloodPreasureMeasurements;
    private List<DrugDose> drugDoses;
    private List<Test> tests;

    public PatientCard() {
    }

    public String getVisit_start() {
        return visit_start;
    }

    public void setVisit_start(String visit_start) {
        this.visit_start = visit_start;
    }

    public String getVisit_end() {
        return visit_end;
    }

    public void setVisit_end(String visit_end) {
        this.visit_end = visit_end;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<TemparatureMeasurement> getTemperatureMeasurements() {
        return temperatureMeasurements;
    }

    public void setTemperatureMeasurements(List<TemparatureMeasurement> temperatureMeasurements) {
        this.temperatureMeasurements = temperatureMeasurements;
    }

    public List<BloodMeauserement> getBloodPreasureMeasurements() {
        return bloodPreasureMeasurements;
    }

    public void setBloodPreasureMeasurements(List<BloodMeauserement> bloodPreasureMeasurements) {
        this.bloodPreasureMeasurements = bloodPreasureMeasurements;
    }

    public List<DrugDose> getDrugDoses() {
        return drugDoses;
    }

    public void setDrugDoses(List<DrugDose> drugDoses) {
        this.drugDoses = drugDoses;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
