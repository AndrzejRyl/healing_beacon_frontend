package sithlords.com.healingbeacon.model;

import java.io.Serializable;

/**
 * @author FleenMobile at 2015-06-27
 */
public class BloodMeauserement implements Serializable {
    private String measurementTime;
    private int systole;
    private int diastole;

    public BloodMeauserement(String measurementTime, int systole, int diastole) {
        this.measurementTime = measurementTime;
        this.systole = systole;
        this.diastole = diastole;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public int getSystole() {
        return systole;
    }

    public void setSystole(int systole) {
        this.systole = systole;
    }

    public int getDiastole() {
        return diastole;
    }

    public void setDiastole(int diastole) {
        this.diastole = diastole;
    }
}
