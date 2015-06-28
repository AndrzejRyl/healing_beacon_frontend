package sithlords.com.healingbeacon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

import sithlords.com.healingbeacon.rest.TestSerializer;

@JsonSerialize(using = TestSerializer.class)
public class Test implements Serializable {
    private String takenTime;
    private String testType;
    private String result;

    public String getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(String takenTime) {
        this.takenTime = takenTime;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
