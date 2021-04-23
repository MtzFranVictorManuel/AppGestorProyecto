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
    private String nombre;
    private String mision;
    private String vision;
    private String objetivoGeneral;

    public Resume() {
    }

    public Resume(String nombre, String mision, String vision, String objetivoGeneral) {
        this.nombre = nombre;
        this.mision = mision;
        this.vision = vision;
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMision() {
        return mision;
    }

    public String getVision() {
        return vision;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }
    
}
