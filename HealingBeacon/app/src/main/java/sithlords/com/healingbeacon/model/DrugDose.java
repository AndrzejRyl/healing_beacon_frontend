package sithlords.com.healingbeacon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author FleenMobile at 2015-06-27
 */
public class DrugDose implements Serializable {
    private Date doseTime;
    private String drugName;
    private int milligrams;

    public Date getDoseTime() {
        return doseTime;
    }

    public void setDoseTime(Date doseTime) {
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
