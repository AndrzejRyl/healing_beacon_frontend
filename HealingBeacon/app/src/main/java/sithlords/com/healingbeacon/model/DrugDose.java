package sithlords.com.healingbeacon.model;

import java.io.Serializable;

/**
 * @author FleenMobile at 2015-06-27
 */
public class DrugDose implements Serializable {
    private String doseTime;
    private String drugName;
    private int milligrams;

    public DrugDose(String doseTime, String drugName, int milligrams) {
        this.doseTime = doseTime;
        this.drugName = drugName;
        this.milligrams = milligrams;
    }

    public String getDoseTime() {
        return doseTime;
    }

    public void setDoseTime(String doseTime) {
        this.doseTime = doseTime;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getMilligrams() {
        return milligrams;
    }

    public void setMilligrams(int milligrams) {
        this.milligrams = milligrams;
    }
}
