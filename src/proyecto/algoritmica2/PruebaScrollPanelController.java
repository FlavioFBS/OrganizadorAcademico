/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import Repositorio.CursoRepo;
import Repositorio.NotasRepo;
import db.JdbcHelper;
import entidades.Curso;
import entidades.Notas;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PruebaScrollPanelController implements Initializable {

    @FXML
    private TextField txtAsignatura;
    @FXML
    private TextField txtUniversidad;
    @FXML
    private TextField txtFacultad;
    @FXML
    private TextField txtEAP;
    @FXML
    private TextField txtTeorInicio;
    @FXML
    private TextField txtTeorFin;
    @FXML
    private TextField txtLabInicio;
    @FXML
    private TextField txtLabFin;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField txtPesoPCL;
    @FXML
    private TextField txtPesoEP;
    @FXML
    private TextField txtPesoEF;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> cmbHora_T;
    @FXML
    private ComboBox<String> cmbHora_P;
    
    ObservableList<String> dias=FXCollections.observableArrayList(
        "Lu",
        "Ma",
        "Mi",
        "Ju",
        "Vi",
        "Sa"
    );
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCreditos;
    @FXML
    private TextField txtPlanEstudio;
    @FXML
    private TextField txtCiclo;
    @FXML
    private TextField txtPesoPLab;
    @FXML
    private TextField txtPesoProy;
    @FXML
    private Button btnAgregarAlumno;
    @FXML
    private Button btnAgregarPesoNota;
    
    boolean presionaAgregarPeso;
    boolean cancelarOperacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbHora_T.getItems().addAll(dias);
        cmbHora_T.setPromptText("Seleccione día");
        cmbHora_P.getItems().addAll(dias);
        btnAgregarAlumno.setDisable(true);
        cmbHora_P.setPromptText("Selecciones día");
        
        btnAgregarAlumno.setDisable(true);
        btnAceptar.setDisable(false);
        btnAgregarPesoNota.setDisable(false);
        cancelarOperacion=true;
    }    
    //variable de mayor ambito porque se usara para dos metodos
    String codigo="";
    
    @FXML
    private void btnAceptar_click(ActionEvent event) throws SQLException {
        //variables para la consulta del tabla curso:
        String nombre=this.txtAsignatura.getText();
        codigo=this.txtCodigo.getText();
        VentanaPrincipalController.cursoSeleccionado=codigo;
        
        String universidad=txtUniversidad.getText();
        String facultad=txtFacultad.getText();
        String escuela=txtEAP.getText();
        int credito=Integer.parseInt(txtCreditos.getText());
        int  ciclo=Integer.parseInt(txtCiclo.getText());
        String plan=txtPlanEstudio.getText();        
        String horaT=this.cmbHora_T.getValue()+this.txtTeorInicio.getText()+txtTeorFin.getText();
        String horaP=this.cmbHora_P.getValue()+txtLabInicio.getText()+txtLabFin.getText();
        
        
        
        //para comprobar registros
        boolean registrado=false;
        //codigo de consulta SQL
        //agregar curso:
        Curso curso=new Curso(nombre, codigo, ciclo, universidad, facultad,
                escuela,credito, plan, horaT, horaP);
        CursoRepo cursoRepo=new CursoRepo();
        if(cursoRepo.guardar(curso)){
            
            //para poder agregar los pesos de las notas
//            btnAgregarPesoNota.setDisable(false);
            cancelarOperacion=false;
            btnAgregarAlumno.setDisable(false);
        }
        
    }

    @FXML
    private void btnCancelar_click(ActionEvent event) throws Exception{
        if(presionaAgregarPeso || cancelarOperacion==true){
            //regresar a la ventana principal
            ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
            Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); //abrir ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Administrador de cursos");
            stage.setResizable(false);
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
        else{
            JOptionPane.showMessageDialog(null,"Falta agregar el peso de las notas");
        }
    }
    
    private void vaciarCampos(){
        this.txtAsignatura.setText("");
        this.txtCiclo.setText("");
        this.txtCodigo.setText("");
        this.txtCreditos.setText("");
        this.txtEAP.setText("");
        this.txtFacultad.setText("");
        this.txtLabFin.setText("");
        this.txtLabInicio.setText("");
        this.txtPlanEstudio.setText("");
        this.txtTeorFin.setText("");
        this.txtTeorInicio.setText("");
        this.txtUniversidad.setText("");
    }

    @FXML
    private void btnAgregarAlumno_click(ActionEvent event) throws Exception{
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        Parent root3 = FXMLLoader.load(getClass().getResource("VerMatriculados.fxml")); //abrir ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root3);
        stage.setScene(scene);
        stage.setTitle("Matricular Alumno");
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

    @FXML
    private void btnAgregarPesoNota_click(ActionEvent event) {
        presionaAgregarPeso=true;
        //variables para la consulta de la tabla notas(pesos de las notas, segun el codigo del curso)
        double P_ControlLect=Double.parseDouble(this.txtPesoPCL.getText());
        double P_Labo=Double.parseDouble(this.txtPesoPLab.getText());
        double P_Proy=Double.parseDouble(this.txtPesoProy.getText());
        double P_Parcial=Double.parseDouble(this.txtPesoEP.getText());
        double P_Final=Double.parseDouble(this.txtPesoEF.getText());
        codigo=VentanaPrincipalController.cursoSeleccionado;
        JOptionPane.showMessageDialog(null,"el codigo de curso es "+codigo);
//        codigo=txtCodigo.getText();
        Notas notas=new Notas(P_ControlLect, P_Proy, P_Labo, P_Parcial, P_Final,codigo);
        NotasRepo notasRepo=new NotasRepo();
        if(notasRepo.agregarPesosNotas(notas)){
            vaciarCampos();
            vaciarPesos();
            JOptionPane.showMessageDialog(null,"Se guardaron los pesos de las notas");
            btnAceptar.setDisable(false);
        }
        
    }
    
    public void vaciarPesos(){
                this.txtPesoEF.setText("");
        this.txtPesoEP.setText("");
        this.txtPesoPCL.setText("");
        this.txtPesoPLab.setText("");
        this.txtPesoProy.setText("");

    }
}
