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
public class Asistencia {
    
    private String codAlum;
    private String codAsig;
    private String codAlumNombre;
    private String codAlumApell;
    private int N_sesion;
    private int AsisteT;
    private int AsisteP;

    public Asistencia(String codAlum, String codAsig, int N_sesion, int AsisteT, int AsisteP) {
        this.codAlum = codAlum;
        this.codAsig = codAsig;
        this.N_sesion = N_sesion;
        this.AsisteT = AsisteT;
        this.AsisteP = AsisteP;
    }
    
    

    public String getCodAlum() {
        return codAlum;
    }

    public void setCodAlum(String codAlum) {
        this.codAlum = codAlum;
    }

    public String getCodAsig() {
        return codAsig;
    }

    public void setCodAsig(String codAsig) {
        this.codAsig = codAsig;
    }

    public String getCodAlumNombre() {
        return codAlumNombre;
    }

    public void setCodAlumNombre(String codAlumNombre) {
        this.codAlumNombre = codAlumNombre;
    }

    public String getCodAlumApell() {
        return codAlumApell;
    }

    public void setCodAlumApell(String codAlumApell) {
        this.codAlumApell = codAlumApell;
    }

    public int getN_sesion() {
        return N_sesion;
    }

    public void setN_sesion(int N_sesion) {
        this.N_sesion = N_sesion;
    }

    public int getAsisteT() {
        return AsisteT;
    }

    public void setAsisteT(int AsisteT) {
        this.AsisteT = AsisteT;
    }

    public int getAsisteP() {
        return AsisteP;
    }

    public void setAsisteP(int AsisteP) {
        this.AsisteP = AsisteP;
    }
    
    
    
    
}
