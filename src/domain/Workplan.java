/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author azul_
 */
public class Workplan {
    private int idWorkplan;
    private static String title;
    private String startDate;
    private String endDate;
    private int idAcademicBody;

    public Workplan() {
    }

    public Workplan(String title, String startDate, String endDate, int idAcademicBody) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idAcademicBody = idAcademicBody;
    }

    public Workplan(String title, String startDate, String endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdWorkplan() {
        return idWorkplan;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getIdAcademicBody() {
        return idAcademicBody;
    }

    public void setIdWorkplan(int idWorkplan) {
        this.idWorkplan = idWorkplan;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setIdAcademicBody(int idAcademicBody) {
        this.idAcademicBody = idAcademicBody;
    }

    
    
    
    
}
