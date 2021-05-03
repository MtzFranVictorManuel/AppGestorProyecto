/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;

/**
 *
 * @author azul_
 */
public class AcademicBody {
    private String keyCode;
    private String institucionalFaculty;
    private int numberCollaborators;
    private Date registrationDate;
    private String degreeConsolidation;
    private String dependecyInstitution;
    private int numberParticipants;
    private int fkMember;

    public AcademicBody(String keyCode, String institucionalFaculty, int numberCollaborators, Date registrationDate, String degreeConsolidation, String dependecyInstitution, int numberParticipants, int fkMember) {
        this.keyCode = keyCode;
        this.institucionalFaculty = institucionalFaculty;
        this.numberCollaborators = numberCollaborators;
        this.registrationDate = registrationDate;
        this.degreeConsolidation = degreeConsolidation;
        this.dependecyInstitution = dependecyInstitution;
        this.numberParticipants = numberParticipants;
        this.fkMember = fkMember;
    }

    public AcademicBody(String keyCode, String institucionalFaculty, int numberCollaborators, Date registrationDate, String degreeConsolidation, String dependecyInstitution, int numberParticipants) {
        this.keyCode = keyCode;
        this.institucionalFaculty = institucionalFaculty;
        this.numberCollaborators = numberCollaborators;
        this.registrationDate = registrationDate;
        this.degreeConsolidation = degreeConsolidation;
        this.dependecyInstitution = dependecyInstitution;
        this.numberParticipants = numberParticipants;
    }
    
    

    public AcademicBody() {
    }

    public int getFkMember() {
        return fkMember;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public String getInstitucionalFaculty() {
        return institucionalFaculty;
    }

    public int getNumberCollaborators() {
        return numberCollaborators;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getDegreeConsolidation() {
        return degreeConsolidation;
    }

    public String getDependecyInstitution() {
        return dependecyInstitution;
    }

    public int getNumberParticipants() {
        return numberParticipants;
    }

    public void setFkMember(int fkMember) {
        this.fkMember = fkMember;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public void setInstitucionalFaculty(String institucionalFaculty) {
        this.institucionalFaculty = institucionalFaculty;
    }

    public void setNumberCollaborators(int numberCollaborators) {
        this.numberCollaborators = numberCollaborators;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setDegreeConsolidation(String degreeConsolidation) {
        this.degreeConsolidation = degreeConsolidation;
    }

    public void setDependecyInstitution(String dependecyInstitution) {
        this.dependecyInstitution = dependecyInstitution;
    }

    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }
    
    
}
