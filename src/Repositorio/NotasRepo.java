/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositorio;

import db.JdbcHelper;
import entidades.Notas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import proyecto.algoritmica2.VentanaPrincipalController;
/**
 *
 * @author flavio
 */
public class NotasRepo {
    
    public boolean agregarPesosNotas(Notas notas){
        
        String query="UPDATE Nota SET "
                + "Peso_ControlLectura="+notas.getPesoControlLectura()+","
                + "Peso_Laboratorio="+notas.getPesoLaboratorio()+","
                +"Peso_Parcial="+notas.getPesoParcial()+","
                +"Peso_Final="+notas.getPesoFinal()+","
                + "Peso_Proyecto="+notas.getPesoProyecto()
                +" WHERE Asi_codigo='"+ notas.getCodAsignatura()+"'";
        JdbcHelper jdbc=new JdbcHelper();
        boolean exito=jdbc.ejecutarQuery(query);
        return exito;
                
    }
    
    
    public boolean agregarNotas(Notas notas){
        String query="UPDATE Nota SET "
                + "Nota_ControlLectura="+notas.getControlLectura()+","
                + "Nota_Proyecto="+notas.getProyecto()+","
                + "Nota_Laboratorio="+notas.getLaboratorio()+","
                + "Nota_Parcial="+notas.getParcial()+","
                + "Nota_Final="+notas.getFinal()+", "
                + "Nota_Promedio="+notas.getPromedio()+
                " WHERE Alu_codigo='"+notas.getCodAlumno()+"' AND Asi_codigo='"+notas.getCodAsignatura()+"'";
        
        JdbcHelper jdbc=new JdbcHelper();
        boolean exito=jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean guardarPesosNotas(Notas notas) throws SQLException{
        if(!validarPesosNotas(notas))
            return false;
        boolean exito;
        
        exito=agregarPesosNotas(notas);
        
        return exito;
    }
    
    public boolean validarPesosNotas(Notas notas){
        //suma de pesos debe ser igual a 1
        boolean esValido=true;
        String mensaje="";
        if(notas.getPesoControlLectura()+
                notas.getPesoLaboratorio()+
                notas.getPesoProyecto()+
                notas.getPesoParcial()+
                notas.getPesoFinal()>1){
            JOptionPane.showMessageDialog(null,"La suma de los pesos de "
                    + "las notas es mayor a 1, debe ser igual a 1","error",
                    JOptionPane.WARNING_MESSAGE);
            esValido=false;
        }
        else{
            if(notas.getPesoControlLectura()+
                notas.getPesoLaboratorio()+
                notas.getPesoProyecto()+
                notas.getPesoParcial()+
                notas.getPesoFinal()<1){
                JOptionPane.showMessageDialog(null,"La suma de los pesos de las "
                        + "notas es menor a 1, debe ser igual a 1","Error",
                        JOptionPane.WARNING_MESSAGE);
                esValido=false;
            }
            else{
                //la suma es 1
                esValido=true;
            }
        }
        
        return esValido;
    }
    
    public boolean guardarNotas(Notas notas){
        if(!validarGuardadoNotas(notas)){
            return false;
        }
        boolean exito;
        
        exito=agregarNotas(notas);
        
        return exito;
        
    } 
    
    public boolean validarGuardadoNotas(Notas notas){
        return validarNota(notas.getControlLectura()) ||
                validarNota(notas.getLaboratorio()) ||
                validarNota(notas.getProyecto()) ||
                validarNota(notas.getParcial()) ||
                validarNota(notas.getFinal()) ;
//        if(validarNota(notas.getControlLectura()) ||
//                validarNota(notas.getLaboratorio()) ||
//                validarNota(notas.getProyecto()) ||
//                validarNota(notas.getParcial()) ||
//                validarNota(notas.getFinal())
//                )
//        {
//            JOptionPane.showMessageDialog(null,"Notas fuera de rango","Error",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        else
//            return true;
    }
    
    private boolean validarNota(double nota){
        if(nota<0 || nota>20)
            return false;
        else
            return true;
    }
        


    public ObservableList<Notas> BuscarNotas() {
        
        JOptionPane.showMessageDialog(null,"entra a buscar notas");
        
        String query="SELECT * FROM Nota WHERE Asi_codigo='"+VentanaPrincipalController.cursoSeleccionado+"'";
        
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs=jdbc.realizarConsulta(query);
        
        ObservableList<Notas> notas=FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                String AluCodigo=rs.getString("Alu_codigo");
                String AsiCodigo=rs.getString("Asi_codigo");
                //notas
                Double Nota_ctrlLect=rs.getDouble("Nota_ControlLectura");
                Double Nota_Proyecto=rs.getDouble("Nota_Proyecto");
                Double Nota_Laboratorio=rs.getDouble("Nota_Laboratorio");
                Double Nota_Parcial=rs.getDouble("Nota_Parcial");
                Double Nota_Final=rs.getDouble("Nota_Final");
                //peso de notas
                Double Peso_ctrolLect=rs.getDouble("Peso_ControlLectura");
                Double Peso_Proyecto=rs.getDouble("Peso_Proyecto");
                Double Peso_Laboratorio=rs.getDouble("Peso_Laboratorio");
                Double Peso_Parcial=rs.getDouble("Peso_Parcial");
                Double Peso_Final=rs.getDouble("Peso_Final");
                
                notas.add(new Notas(Nota_ctrlLect, Peso_ctrolLect, 
                        Nota_Proyecto, Peso_Proyecto, Nota_Laboratorio, Peso_Laboratorio,
                        Nota_Parcial, Peso_Parcial, Nota_Final, Peso_Final,
                        AluCodigo, AsiCodigo));
                JOptionPane.showMessageDialog(null,"entra a agregar notas");
                System.out.println("Notas-Repo---consulta: "+query);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al cargar las notas","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        return notas;
    }
    
}
