/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import Repositorio.AlumnoRepo;
import entidades.Alumno;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VerMatriculadosController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAgregar_click(ActionEvent event) {
        String nombre=txtNombre.getText();
        String apellido=txtApellido.getText();
        String codigo=txtCodigo.getText();
        String codiAsig=VentanaPrincipalController.cursoSeleccionado;
        Alumno alumno=new Alumno(nombre, apellido, codigo,codiAsig);
        AlumnoRepo alumnoRepo=new AlumnoRepo();
        try {
            if(alumnoRepo.guardar(alumno)){
                
                //insertar tambien la info en la tabla notas:
                alumnoRepo.guardarALumnon_Notas(alumno);
               alumnoRepo.guardarAlumno_en_Asistencia(alumno); //en caso que no funciones el codigo de AsistenciasController
               vaciarCampos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Ocurrio algo al intentar agregar el alumno a la base de datos");
        }
    }

    @FXML
    private void btnCancelar_click(ActionEvent event) throws Exception{
         ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        Parent root = FXMLLoader.load(getClass().getResource("pruebaScrollPanel.fxml")); //abrir ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Agregar Curso");
        stage.setResizable(false);
        stage.show();

//        //para que si se cierra esta ventana se cierre tambien la aplicacion:
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                //para que termine la aplicacion:
                System.exit(0);
            }
        });
    }
    
    
    private void vaciarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCodigo.setText("");
    }
}
