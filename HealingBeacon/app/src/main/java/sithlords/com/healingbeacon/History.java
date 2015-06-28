package sithlords.com.healingbeacon;

/**
 * @author FleenMobile at 2015-06-28
 */
public class History {
    private String date;
    private String content;

    public History(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
