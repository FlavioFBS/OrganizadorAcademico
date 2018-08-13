package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class JdbcHelper {
    
    //para read 
    public ResultSet realizarConsulta(String query){
        ConexionMySql conexionMySql = new ConexionMySql();
        Connection conn = conexionMySql.conectar();
        ResultSet rs = null;
        PreparedStatement ps;
        Statement stQuery;
        try{
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
//            stQuery = conn.createStatement();
//            rs = stQuery.executeQuery(query);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al ejecutar "+query+": "+ex);
        }
        return rs;
    }
    
    //para insert, update y delete
    public boolean ejecutarQuery(String query){
        ConexionMySql conexionMySql = new ConexionMySql();
        Connection conn = conexionMySql.conectar();
        boolean exito = false;
        try{
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            if(affectedRows!=0){
                exito = true;
            }
            else{
                exito = false;
            }
            System.out.println("Affected rows: "+affectedRows+"Ejecutar-Query--Consulta: "+query);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al ejecutar "+query+": "+ex);
            exito = false;
        }
        return exito;
    }
}