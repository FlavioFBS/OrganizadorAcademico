/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//para consultas:
import db.JdbcHelper;
import java.sql.ResultSet;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaPrincipalController implements Initializable {
    
    //variable de paquete que al seleccionar un curso, se inicialice y esto 
    //a su vez servirá para mostrar los registros de notas y asistencias segun sea el curso seleccionado    
    public static String cursoSeleccionado="";
    
    void setCursoSeleccionado(String curs){
        cursoSeleccionado=curs;
    }

    @FXML
    private Button btnAgregarCurso;
    @FXML
    private Button btnVerCurso;
    @FXML
    private Label txtNombreProfe;
    @FXML
    private Label txtApellidoProfe;
    @FXML
    private Label txtCodigoProfe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String query="SELECT * FROM Profesor WHERE Prof_codigo='1234'";
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs=jdbc.realizarConsulta(query);
        String nombre="-",apellido="-",codigo="-";
        
        try {
            while(rs.next()){
                nombre=rs.getString("Prof_Nombre");
                apellido=rs.getString("Prof_Apellido");
                codigo=rs.getString("Prof_codigo");
            }
            this.txtNombreProfe.setText("Nombre: "+nombre);
            this.txtApellidoProfe.setText("Apellido: "+apellido);
            this.txtCodigoProfe.setText("Codigo: "+codigo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema al cargar datos del profesor",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @FXML
    private void btnAgregarCurso_click(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        
        
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("pruebaScrollPanel.fxml"));
        Parent root= (Parent)fxmlLoader.load();
        
        
//        Parent root = FXMLLoader.load(getClass().getResource("pruebaScrollPanel.fxml")); //abrir ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Agregar curso");
        stage.show();

        //para que si se cierra esta ventana se cierre tambien la aplicacion:
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                //para que termine la aplicacion:
                System.exit(0);
            }
        });
    }

    @FXML
    private void btnVerCurso_click(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        Parent root2 = FXMLLoader.load(getClass().getResource("VerCursos.fxml")); //abrir ventana
        Stage stage2 = new Stage();
        Scene scene = new Scene(root2);
        stage2.setScene(scene);
        stage2.setTitle("Administrador de cursos-Lista de Cursos");
        stage2.show();
        stage2.setResizable(false);

        //para que si se cierra esta ventana se cierre tambien la aplicacion:
        stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                //para que termine la aplicacion:
                System.exit(0);
            }
        });
    }

}
