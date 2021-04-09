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
public class Curriculum {
    private String institucionDependecia;
    private String facultad;
    private String grado;
    private String clave;
    private int numeroIntegrantes;
    private int numeroColaboladores;
    private String fechaRegistro;
    private String objetiviGeneral;
    private String mision;
    private String vision;

    public Curriculum() {
    }

    public Curriculum(String institucionDependecia, String facultad, 
            String grado, String clave, int numeroIntegrantes, int numeroColaboladores, 
            String fechaRegistro, String objetiviGeneral, String mision, String vision) {
        this.institucionDependecia = institucionDependecia;
        this.facultad = facultad;
        this.grado = grado;
        this.clave = clave;
        this.numeroIntegrantes = numeroIntegrantes;
        this.numeroColaboladores = numeroColaboladores;
        this.fechaRegistro = fechaRegistro;
        this.objetiviGeneral = objetiviGeneral;
        this.mision = mision;
        this.vision = vision;
    }

    public String getInstitucionDependecia() {
        return institucionDependecia;
    }

    public String getFacultad() {
        return facultad;
    }

    public String getGrado() {
        return grado;
    }

    public String getClave() {
        return clave;
    }

    public int getNumeroIntegrantes() {
        return numeroIntegrantes;
    }

    public int getNumeroColaboladores() {
        return numeroColaboladores;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getObjetiviGeneral() {
        return objetiviGeneral;
    }

    public String getMision() {
        return mision;
    }

    public String getVision() {
        return vision;
    }

    public void setInstitucionDependecia(String institucionDependecia) {
        this.institucionDependecia = institucionDependecia;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNumeroIntegrantes(int numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public void setNumeroColaboladores(int numeroColaboladores) {
        this.numeroColaboladores = numeroColaboladores;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setObjetiviGeneral(String objetiviGeneral) {
        this.objetiviGeneral = objetiviGeneral;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }
    
    
}
