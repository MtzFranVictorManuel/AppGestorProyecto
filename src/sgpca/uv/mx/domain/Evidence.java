package sgpca.uv.mx.domain;

import java.sql.Date;

public class Evidence {
    private String evidenceName;
    private String autor;
    private Date date;
    private String evidenceType;

    public Evidence() {
    }

    public Evidence(String evidenceName, String autor, Date date, String evidenceType) {
        this.evidenceName = evidenceName;
        this.autor = autor;
        this.date = date;
        this.evidenceType = evidenceType;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.evidenceName = evidenceName;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(String evidenceType) {
        this.evidenceType = evidenceType;
    }

    
}
