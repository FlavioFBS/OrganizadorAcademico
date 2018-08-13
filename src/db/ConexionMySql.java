package db;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionMySql {
    public String database = "Proyecto_Aguilar_Barrantes_Bendezu_YonYong";
    public String url = "jdbc:derby://localhost:1527/"+database;
    public String user = "root";
    public String password = "1234";
    
    public Connection conectar(){
        Connection link = null;
        try{
            link = DriverManager.getConnection(this.url,this.user,this.password);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"ERROR 0 al conectar: "+ex
                    +"\nAsegúrese de que el servidor esté encendido.","ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        return link;
    }
    
}
