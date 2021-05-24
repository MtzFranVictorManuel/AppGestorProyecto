/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.domain;

/**
 *
 * @author azul_
 */
public class Workplan {
    private static int idWorkplan;
    private static String title;
    private static String startDate;
    private static String endDate;
    private int idAcademicBody;

    public Workplan() {
    }

    public Workplan(int idWorkplan){
        this.idWorkplan = idWorkplan;
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

    public static int getIdWorkplan() {
        return idWorkplan;
    }

    public static String getTitle() {
        return title;
    }

    public static String getStartDate() {
        return startDate;
    }

    public static String getEndDate() {
        return endDate;
    }

    public int getIdAcademicBody() {
        return idAcademicBody;
    }

    public static void setIdWorkplan(int idWorkplan) {
        Workplan.idWorkplan = idWorkplan;
    }

    public static void setTitle(String title) {
        Workplan.title = title;
    }

    public static void setStartDate(String startDate) {
        Workplan.startDate = startDate;
    }

    public static void setEndDate(String endDate) {
        Workplan.endDate = endDate;
    }

    public void setIdAcademicBody(int idAcademicBody) {
        this.idAcademicBody = idAcademicBody;
    }   
}
