package sgpca.domain;

import java.sql.Date;

public class Blueprint {
    private String blueprintTitle;
    private String description;
    private String duration;
    private Date startDate;

    public Blueprint() {
    }

    public Blueprint(String blueprintTitle, Date startDate, String duration, String description) {
        this.blueprintTitle = blueprintTitle;
        this.description = description;
        this.duration = duration;
        this.startDate = startDate;
    }

    public String getBlueprintTitle() {
        return blueprintTitle;
    }

    public void setBlueprintTitle(String bluePrintTitle) {
        this.blueprintTitle = bluePrintTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
}

