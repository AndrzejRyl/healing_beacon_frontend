package sithlords.com.healingbeacon.model;

import java.io.Serializable;

/**
 * @author FleenMobile at 2015-06-27
 */
public class TemparatureMeasurement implements Serializable {
    private String measurementTime;
    private double degreeCelcius;

    public TemparatureMeasurement(String measurementTime, double degreeCelcius) {
        this.measurementTime = measurementTime;
        this.degreeCelcius = degreeCelcius;
    }

    public String getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(String measurementTime) {
        this.measurementTime = measurementTime;
    }

    public double getDegreeCelcius() {
        return degreeCelcius;
    }

    public void setDegreeCelcius(double degreeCelcius) {
        this.degreeCelcius = degreeCelcius;
    }
}
