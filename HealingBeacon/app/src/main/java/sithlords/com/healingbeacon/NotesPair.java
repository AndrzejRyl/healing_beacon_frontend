package sithlords.com.healingbeacon;

/**
 * @author FleenMobile at 2015-06-28
 */
public class NotesPair {
    private long firstID;
    private long secondID;
    private String firstContent;
    private String secondContent;

    public NotesPair(long firstID, long secondID, String firstContent, String secondContent) {
        this.firstID = firstID;
        this.secondID = secondID;
        this.firstContent = firstContent;
        this.secondContent = secondContent;
    }

    public long getFirstID() {
        return firstID;
    }

    public void setFirstID(long firstID) {
        this.firstID = firstID;
    }

    public long getSecondID() {
        return secondID;
    }

    public void setSecondID(long secondID) {
        this.secondID = secondID;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }
}
