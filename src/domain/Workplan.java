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
    private String idWorkplan;
    private String startDate;
    private String endDate;

    public Workplan() {
    }

    public Workplan(String idWorkplan, String startDate, String endDate) {
        this.idWorkplan = idWorkplan;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getIdWorkplan() {
        return idWorkplan;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setIdWorkplan(String idWorkplan) {
        this.idWorkplan = idWorkplan;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    
}
