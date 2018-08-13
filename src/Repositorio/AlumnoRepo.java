/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import db.JdbcHelper;
import entidades.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyecto.algoritmica2.VentanaPrincipalController;

/**
 *
 * @author flavio
 */
public class AlumnoRepo {

    private boolean insertar(Alumno alumno,String tabla) {
        JOptionPane.showMessageDialog(null,"entra a insertar alumno en "+tabla);
        String query = "INSERT INTO "+tabla+" (Alu_Nombre,Alu_Apellidos,Alu_codigo) "
                + "VALUES ('" + alumno.getNombre() + "','"
                + alumno.getApellido() + "','"
                + alumno.getCodigo() + "')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

    public boolean guardar(Alumno alumno) throws SQLException {
        if (validar(alumno,"Alumno") == false) {
            return false;
        }
        boolean exito;

        exito = insertar(alumno,"Alumno");
        return exito;
    }

    public boolean validar(Alumno alumno,String tabla) {
        JOptionPane.showMessageDialog(null,"entra a validar alumno en "+tabla);
        boolean esValido = true;
        String query1 = "SELECT Alu_codigo FROM "+tabla+" WHERE Alu_codigo='" + alumno.getCodigo() + "'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs1 = jdbc.realizarConsulta(query1);
        int cantidadResultados = 0;
        try {
            while (rs1.next()) {
                cantidadResultados++;
            }
            if (cantidadResultados > 0) { //entonces el alumno ya está registrado
                esValido = false;
                JOptionPane.showMessageDialog(null, "Código de alumno ya registro",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al comprobar existencia"
                    + "de alumno ", "Error al registrar", JOptionPane.ERROR_MESSAGE);
        }
        return esValido;
    }
    
    
    //para guardar la info de alumnos en tabla Nota:
    
    private boolean insertar_en_Nota(Alumno alumno) {
        String query = "INSERT INTO Nota (Asi_codigo,Alu_codigo) "
                + "VALUES ('" + alumno.getcodiAsig() + "','"
                + alumno.getCodigo() + "')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean guardarALumnon_Notas(Alumno alumno){
        JOptionPane.showMessageDialog(null,"entra a guardar alumno en nota");
        if (validar(alumno,"Nota") == false) {
            return false;
        }
        boolean exito;

        exito = insertar_en_Nota(alumno);
        return exito;
    }
    
    //para guardar la info de alumno y codAsig en la tabla asistencias:
    public boolean guardarAlumno_en_Asistencia(Alumno alumno){
        JOptionPane.showMessageDialog(null,"entra a guardar alumno en asistencia");
        if(validar(alumno,"Asistencias")==false){
            return false;
        }
        boolean exito=insertar_en_Asistencia(alumno);
        return exito;
    }
    
    private boolean insertar_en_Asistencia(Alumno alumno) {
        String query = "INSERT INTO Asistencias (asi_codigo,alu_codigo) "
                + "VALUES ('" + alumno.getcodiAsig() + "','"
                + alumno.getCodigo() + "')";
        JdbcHelper jdbc = new JdbcHelper();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }

}
