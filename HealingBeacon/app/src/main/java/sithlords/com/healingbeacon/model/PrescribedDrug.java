package sithlords.com.healingbeacon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

import sithlords.com.healingbeacon.rest.PrescribedDrugSerializer;

@JsonSerialize(using = PrescribedDrugSerializer.class)
public class PrescribedDrug implements Serializable {
    private String drugName;
    private Integer intervalHours;
    private Integer doseMilligrams;

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getIntervalHours() {
        return intervalHours;
    }

    public void setIntervalHours(Integer intervalHours) {
        this.intervalHours = intervalHours;
    }

    public Integer getDoseMilligrams() {
        return doseMilligrams;
    }

    public void setDoseMilligrams(Integer doseMilligrams) {
        this.doseMilligrams = doseMilligrams;
    }

}
