package sithlords.com.healingbeacon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author FleenMobile at 2015-06-27
 */
public class TemperatureMeasurement implements Serializable {
    private Date measurementTime;
    private double degreeCelcius;

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    public double getDegreeCelcius() {
        return degreeCelcius;
    }

    public void setDegreeCelcius(double degreeCelcius) {
        this.degreeCelcius = degreeCelcius;
    }
}
