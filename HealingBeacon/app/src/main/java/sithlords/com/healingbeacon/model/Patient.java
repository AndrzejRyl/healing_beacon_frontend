package sithlords.com.healingbeacon.model;

/**
 * @author FleenMobile at 2015-06-27
 */
public class Patient {
    private long ID;
    private String firstName;
    private String lastName;
    private long beaconID;

    public Patient(long ID, String firstName, String lastName, long beaconID) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.beaconID = beaconID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public long getBeaconID() {
        return beaconID;
    }

    public void setBeaconID(long beaconID) {
        this.beaconID = beaconID;
    }
}
