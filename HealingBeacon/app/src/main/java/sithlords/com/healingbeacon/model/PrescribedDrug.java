package sithlords.com.healingbeacon.model;

import java.io.Serializable;

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
