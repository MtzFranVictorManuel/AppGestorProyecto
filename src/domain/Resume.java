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
public class Resume {
    private String nameResume;
    private String mission;
    private String vision;
    private String generalObjetive;
    private int fkMember;
    

    public Resume() {
    }

    public Resume(String nameResume, String mission, String vision, String generalObjetive, int fkMember) {
        this.nameResume = nameResume;
        this.mission = mission;
        this.vision = vision;
        this.generalObjetive = generalObjetive;
        this.fkMember = fkMember;
    }

    public String getNameResume() {
        return nameResume;
    }

    public String getMission() {
        return mission;
    }

    public String getVision() {
        return vision;
    }

    public String getGeneralObjetive() {
        return generalObjetive;
    }

    public int getFkMember() {
        return fkMember;
    }

    public void setNameResume(String nameResume) {
        this.nameResume = nameResume;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public void setGeneralObjetive(String generalObjetive) {
        this.generalObjetive = generalObjetive;
    }

    public void setFkMember(int fkMember) {
        this.fkMember = fkMember;
    }
}
