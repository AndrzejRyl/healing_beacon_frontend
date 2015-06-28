package sithlords.com.healingbeacon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

import sithlords.com.healingbeacon.rest.TemperatureMeasurementSerializer;

@JsonSerialize(using = TemperatureMeasurementSerializer.class)
public class TemperatureMeasurement implements Serializable,Comparable {
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

    @Override
    public int compareTo(Object another) {
        return measurementTime.compareTo(((TemperatureMeasurement)another).getMeasurementTime());
    }
}
