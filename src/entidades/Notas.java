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
public class Notas {
    
    private double promedio;
    private double ControlLectura;
    private double pesoControlLectura;
    private double Proyecto;
    private double pesoProyecto;
    private double Laboratorio;
    private double pesoLaboratorio;
    private double Parcial;
    private double pesoParcial;
    private double Final;
    private double pesoFinal;
    
    private String codAlumno;
    private String codAsignatura;
    private String codProfesor;

    public Notas(double pesoControlLectura, double pesoProyecto, double pesoLaboratorio, 
            double pesoParcial, double pesoFinal,String codAsignatura) {
        this.pesoControlLectura = pesoControlLectura;
        this.pesoProyecto = pesoProyecto;
        this.pesoLaboratorio = pesoLaboratorio;
        this.pesoParcial = pesoParcial;
        this.pesoFinal = pesoFinal;
        this.codAsignatura=codAsignatura;
        promedio=
                pesoControlLectura*ControlLectura+
                pesoFinal*Final+
                pesoLaboratorio*Laboratorio+
                pesoParcial*Parcial+
                pesoProyecto*Proyecto;
    }

    public Notas(double ControlLectura, double pesoControlLectura, double Proyecto,
            double pesoProyecto, double Laboratorio, double pesoLaboratorio,
            double Parcial, double pesoParcial, double Final, double pesoFinal,
            String codAlumno, String codAsignatura) {
        
        this.ControlLectura = ControlLectura;
        this.pesoControlLectura = pesoControlLectura;
        this.Proyecto = Proyecto;
        this.pesoProyecto = pesoProyecto;
        this.Laboratorio = Laboratorio;
        this.pesoLaboratorio = pesoLaboratorio;
        this.Parcial = Parcial;
        this.pesoParcial = pesoParcial;
        this.Final = Final;
        this.pesoFinal = pesoFinal;
        this.codAlumno = codAlumno;
        this.codAsignatura = codAsignatura;
        //calcular promedio:
        promedio=
                pesoControlLectura*ControlLectura+
                pesoFinal*Final+
                pesoLaboratorio*Laboratorio+
                pesoParcial*Parcial+
                pesoProyecto*Proyecto;
    }
    
    

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getControlLectura() {
        return ControlLectura;
    }

    public void setControlLectura(double ControlLectura) {
        this.ControlLectura = ControlLectura;
    }

    public double getPesoControlLectura() {
        return pesoControlLectura;
    }

    public void setPesoControlLectura(double pesoControlLectura) {
        this.pesoControlLectura = pesoControlLectura;
    }

    public double getProyecto() {
        return Proyecto;
    }

    public void setProyecto(double Proyecto) {
        this.Proyecto = Proyecto;
    }

    public double getPesoProyecto() {
        return pesoProyecto;
    }

    public void setPesoProyecto(double pesoProyecto) {
        this.pesoProyecto = pesoProyecto;
    }

    public double getLaboratorio() {
        return Laboratorio;
    }

    public void setLaboratorio(double Laboratorio) {
        this.Laboratorio = Laboratorio;
    }

    public double getPesoLaboratorio() {
        return pesoLaboratorio;
    }

    public void setPesoLaboratorio(double pesoLaboratorio) {
        this.pesoLaboratorio = pesoLaboratorio;
    }

    public double getParcial() {
        return Parcial;
    }

    public void setParcial(double Parcial) {
        this.Parcial = Parcial;
    }

    public double getPesoParcial() {
        return pesoParcial;
    }

    public void setPesoParcial(double pesoParcial) {
        this.pesoParcial = pesoParcial;
    }

    public double getFinal() {
        return Final;
    }

    public void setFinal(double Final) {
        this.Final = Final;
    }

    public double getPesoFinal() {
        return pesoFinal;
    }

    public void setPesoFinal(double pesoFinal) {
        this.pesoFinal = pesoFinal;
    }

    public String getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(String codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    public String getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(String codProfesor) {
        this.codProfesor = codProfesor;
    }
    
    
    
    
}
