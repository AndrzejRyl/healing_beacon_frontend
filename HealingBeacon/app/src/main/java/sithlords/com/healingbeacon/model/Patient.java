package sithlords.com.healingbeacon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author FleenMobile at 2015-06-27
 */
public class Patient implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private Date dateOfBirth;
    private String gender;
    private Double weight;
    private Double height;

    public Patient(){

    }

    public Patient(long id, String firstName, String lastName, String photoUrl,
                   Date dateOfBirth, String gender, Double weight, Double height) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
