package sithlords.com.healingbeacon.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.List;

import sithlords.com.healingbeacon.rest.PatientCardJsonDeserializer;

/**
 * @author FleenMobile at 2015-06-27
 */
@JsonDeserialize(using = PatientCardJsonDeserializer.class)
public class PatientCard implements Serializable {
    private String visitStart;
    private String visitEnd;
    private Patient patient;
    private List<TemperatureMeasurement> temperatureMeasurements;
    private List<BloodMeasurement> bloodPressureMeasurements;
    private List<DrugDose> drugDoses;
    private List<Test> tests;

    public String getVisitStart() {
        return visitStart;
    }

    public void setVisitStart(String visitStart) {
        this.visitStart = visitStart;
    }

    public String getVisitEnd() {
        return visitEnd;
    }

    public void setVisitEnd(String visitEnd) {
        this.visitEnd = visitEnd;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<TemperatureMeasurement> getTemperatureMeasurements() {
        return temperatureMeasurements;
    }

    public void setTemperatureMeasurements(List<TemperatureMeasurement> temperatureMeasurements) {
        this.temperatureMeasurements = temperatureMeasurements;
    }

    public List<BloodMeasurement> getBloodPressureMeasurements() {
        return bloodPressureMeasurements;
    }

    public void setBloodPressureMeasurements(List<BloodMeasurement> bloodPressureMeasurements) {
        this.bloodPressureMeasurements = bloodPressureMeasurements;
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
