/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import db.ConexionMySql;
import db.JdbcHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entidades.Asistencia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import proyecto.algoritmica2.VentanaPrincipalController;
/**
 *
 * @author flavio
 */
public class AsistenciaRepo {
    
    
        public ObservableList<Asistencia> buscarAsistencias(){
        String query="SELECT * FROM Asistencias WHERE Asi_codigo='"+VentanaPrincipalController.cursoSeleccionado+"'";
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs=jdbc.realizarConsulta(query);
        JOptionPane.showMessageDialog(null,"Entra a buscar asistencias");
        ObservableList<Asistencia> asistencias=FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                String codAlum=rs.getString("Alu_codigo");
                String codCurso=rs.getString("Asi_codigo");
                int asistenciaT=rs.getInt("Asistencia_T");
                int asistenciaP=rs.getInt("Asistencia_P");
                int N_sesion=rs.getInt("Numero_sesion");
                //agregar a la lista:
                asistencias.add(new Asistencia(codAlum, codAlum, N_sesion, asistenciaT, asistenciaP));                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al cargar las asistencias","Error",JOptionPane.ERROR_MESSAGE);
        }
        return asistencias;
    }
        
        
    public boolean MarcarAsistencia_(Asistencia asistencia,String tipo) throws SQLException{
        
            String query="UPDATE Asistencias SET "
                    + tipo+"=1 ,"
                    + "Numero_sesion="+asistencia.getN_sesion() //para espeficar teoria o practica: tipo=Asistencia_T  || tipo=Asistencia_P
                    + " WHERE "
                    + "Alu_codigo='"+asistencia.getCodAlum()+"' AND"
                    + " Asi_codigo='"+VentanaPrincipalController.cursoSeleccionado+"'";
            
            JdbcHelper jdbc=new JdbcHelper();
            boolean exito=jdbc.ejecutarQuery(query);
            
        return exito;
    }
    
    public boolean validarAsistencia(Asistencia asistencia,String tipo){
        boolean esValido=true;

        String query1 = "SELECT Alu_codigo FROM Asistencias WHERE Alu_codigo='" + asistencia.getCodAlum()+"'"
                + " AND Numero_sesion="+asistencia.getN_sesion()+" AND "+
                tipo+"=1 AND Asi_codigo='"+asistencia.getCodAsig()+"'";
        JdbcHelper jdbc = new JdbcHelper();
        ResultSet rs1 = jdbc.realizarConsulta(query1);
        int cantidadResultados = 0;
        try {
            while (rs1.next()) {
                cantidadResultados++;
            }
            if (cantidadResultados > 0) { //entonces el alumno ya está registrado en esa N-Sesion y en ese tipo(T o P)
                esValido = false;
                JOptionPane.showMessageDialog(null, "Ya se registro la asistencia en "+tipo
                        + " del alumno de codigo "+asistencia.getCodAlum()+" de la sesion "+asistencia.getN_sesion(),
                        "Advertencia-Validar Asistencia", JOptionPane.WARNING_MESSAGE);
            }
            else{
                esValido=true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al comprobar existencia"
                    + "de alumno ", "Error al marcar asistencia", JOptionPane.ERROR_MESSAGE);
        }
        return esValido;
        
    }
    
    public boolean guardarAsistencia(Asistencia asistencia,String tipo) throws SQLException {
        if (!validarAsistencia(asistencia,tipo) ) {
            return false;
        }
        boolean exito;

        exito = MarcarAsistencia_(asistencia,tipo);
        return exito;
    }
    
}
