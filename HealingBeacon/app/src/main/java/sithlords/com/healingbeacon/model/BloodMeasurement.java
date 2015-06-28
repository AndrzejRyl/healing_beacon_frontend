package sithlords.com.healingbeacon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

import sithlords.com.healingbeacon.rest.BloodMeasurementSerializer;

@JsonSerialize(using = BloodMeasurementSerializer.class)
public class BloodMeasurement implements Serializable, Comparable {
    private Date measurementTime;
    private int systole;
    private int diastole;

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
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

    @Override
    public int compareTo(Object another) {
        return measurementTime.compareTo(((BloodMeasurement)another).getMeasurementTime());
    }
}
