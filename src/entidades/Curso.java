/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author flavio
 */
public class Curso {
    
    private String nombre;
    private String codigo;
    private int ciclo;
    private String universidad;
    private String facultad;
    private String eap;
    private int creditos;
    private String plan;
    private String horarioT;
    private String horarioP;

    public Curso(String nombre, String codigo, int ciclo, String universidad, 
            String facultad, String eap, int creditos, String plan, 
            String horarioT, String horarioP) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.ciclo = ciclo;
        this.universidad = universidad;
        this.facultad = facultad;
        this.eap = eap;
        this.creditos = creditos;
        this.plan = plan;
        this.horarioT = horarioT;
        this.horarioP = horarioP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getEap() {
        return eap;
    }

    public void setEap(String eap) {
        this.eap = eap;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getHorarioT() {
        return horarioT;
    }

    public void setHorarioT(String horarioT) {
        this.horarioT = horarioT;
    }

    public String getHorarioP() {
        return horarioP;
    }

    public void setHorarioP(String horarioP) {
        this.horarioP = horarioP;
    }

    
    
    
}
