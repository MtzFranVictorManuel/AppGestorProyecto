package sgpca.domain;

import java.sql.Date;

public class ResearchProyect {
    private String proyectTitle;
    private String proyectDescription;
    private Date startDate;
    private Date endDate;

    public ResearchProyect() {
    }

    public ResearchProyect(String proyectTitle, String proyectDescription, Date startDate, Date endDate) {
        this.proyectTitle = proyectTitle;
        this.proyectDescription = proyectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProyectTitle() {
        return proyectTitle;
    }

    public void setProyectTitle(String proyectTitle) {
        this.proyectTitle = proyectTitle;
    }

    public String getProyectDescription() {
        return proyectDescription;
    }

    public void setProyectDescription(String proyectDescription) {
        this.proyectDescription = proyectDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

   
}
