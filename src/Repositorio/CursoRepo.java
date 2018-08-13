package Repositorio;

import entidades.Curso;
import db.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
/**
 *
 * @author flavio
 */
public class CursoRepo {
    
    public boolean insertar(Curso curso){
        String query="INSERT INTO Asignatura (Asi_codigo,Asi_nombre,Asi_horarioT"
                + ",Asi_horarioP,Asi_Escuela,Asi_Universidad,Asi_Facultad,"
                + "Asi_plan,Asi_ciclo,Asi_credito)"
                + "VALUES"
                + "("
                + "'"+curso.getCodigo()+"',"
                + "'"+curso.getNombre()+"',"
                + "'"+curso.getHorarioT()+"',"
                + "'"+curso.getHorarioP()+"',"
                + "'"+curso.getEap()+"',"
                + "'"+curso.getUniversidad()+"',"
                + "'"+curso.getFacultad()+"',"
                + "'"+curso.getPlan()+"',"
                + ""+curso.getCiclo()+","
                + ""+curso.getCreditos()+")";
        JdbcHelper jdbc=new JdbcHelper();
        //probar si la consulta se podra realizar:
        boolean exito=jdbc.ejecutarQuery(query);
        return exito;
    }
    
    
    
    public boolean guardar(Curso curso) throws SQLException{
        if(!validar(curso))
            return false;
        boolean exito;
        
        exito=insertar(curso);
        
        return exito;
    }
    
    
    public boolean validar(Curso curso) throws SQLException{
        boolean esValido=true;
        StringBuilder sb=new StringBuilder();
        //comprobar cruce de horario:
        String query1="SELECT Asi_horarioT FROM Asignatura WHERE Asi_codigo='"+curso.getCodigo()+"'";
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs1=jdbc.realizarConsulta(query1);
        int cantidadResultados=0;
        while(rs1.next()){
            cantidadResultados++;
        }
        if(cantidadResultados>0){ //entonces existe otro curso con ese horario
            esValido=false;
            sb.append("El horario de teoria se cruza con otro curso");
        }
        //con el horario de practica:
        String query2="SELECT Asi_horarioP FROM Asignatura WHERE Asi_codigo='"+curso.getCodigo()+"'";
        JdbcHelper jdbc2=new JdbcHelper();
        ResultSet rs2=jdbc.realizarConsulta(query2);
        int cantidadResultados2=0;
        while(rs2.next()){
            cantidadResultados++;
        }
        if(cantidadResultados>0){
            esValido=false;
            sb.append("El horario de practica se cruza con otro curso");
        }
        
       if(!esValido){
           JOptionPane.showMessageDialog(null,"Se encontraron los siguientes errores:\n"
                   + ""+sb.toString(),"Error-Validacion",JOptionPane.WARNING_MESSAGE);
       }
       return esValido;
        
    }
    
    public ObservableList<Curso> todoCurso(){
        String query="SELECT * FROM Asignatura";
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs=jdbc.realizarConsulta(query);
        
        ObservableList<Curso> cursos=FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                String nombre=rs.getString("Asi_nombre");
                String codigo=rs.getString("Asi_codigo");
                String horarioT=rs.getString("Asi_horarioT");
                String horarioP=rs.getString("Asi_horarioP");
                String escuela=rs.getString("Asi_Escuela");
                String universidad=rs.getString("Asi_Universidad");
                String facultad=rs.getString("Asi_facultad");
                String plan=rs.getString("Asi_plan");
                int ciclo=rs.getInt("Asi_ciclo");
                int credito=rs.getInt("Asi_credito");
                //agregar a la lista:
                cursos.add(new Curso(nombre, codigo, ciclo, universidad, 
                        facultad, escuela, credito, plan, horarioT, horarioP));                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al cargas los cursos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return cursos;
    }
}
