/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import Repositorio.CursoRepo;
import entidades.Curso;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
//para el menu contextual:
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VerCursosController implements Initializable {
    
    public ObservableList<Curso> dataCurso=FXCollections.observableArrayList();
    
    @FXML
    private TableView<Curso> tablaCurso;
    @FXML
    private TableColumn<Curso, String> colCodigo;
    @FXML
    private TableColumn<Curso, String> colNombre;
    @FXML
    private TableColumn<Curso, String> colUniver;
    @FXML
    private TableColumn<Curso, String> colFacul;
    @FXML
    private TableColumn<Curso, String> colEAP;
    @FXML
    private Button btn_irPaginaPrincipal;
    
    @FXML
    private TableColumn<Curso,Integer> colCiclo;
    @FXML
    private TableColumn<Curso,Integer> colPlanEst;
    @FXML
    private TableColumn<Curso, String> colHT;
    @FXML
    private TableColumn<Curso, String> colHP;
    private Button btnMatricular;
    @FXML
    private Button btnAsistencia;
    @FXML
    private Button btnNotas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        this.btn_irPaginaPrincipal.setText(VentanaPrincipalController.cursoSeleccionado);
        configurarTabla();      
        rellenarTablaCurso();
        desactivarBotones();
    }    

    @FXML
    private void btn_irPaginaPrincipal_click(ActionEvent event) throws Exception{
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
            Parent root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); //abrir ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Inicio");
            stage.setResizable(false);
            stage.show();            
    }
    
    private void cambiarVentana(ActionEvent event,String nombreVentana,String tituloVentana) throws Exception{
        //regresar a la ventana principal
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        Parent root = FXMLLoader.load(getClass().getResource(nombreVentana)); //abrir ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(tituloVentana);
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
    
    public void configurarTabla(){
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUniver.setCellValueFactory(new PropertyValueFactory<>("universidad"));
        colFacul.setCellValueFactory(new PropertyValueFactory<>("facultad"));
        colEAP.setCellValueFactory(new PropertyValueFactory<>("eap"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        colPlanEst.setCellValueFactory(new PropertyValueFactory<>("plan"));
        colHT.setCellValueFactory(new PropertyValueFactory<>("horarioT"));
        colHP.setCellValueFactory(new PropertyValueFactory<>("horarioP"));
        
        tablaCurso.setItems(dataCurso);
    }
    
    public void rellenarTablaCurso(){
        dataCurso.clear();
        CursoRepo cursoRepo=new CursoRepo();
        
        //donde se guardara 
        ObservableList<Curso> cursos=cursoRepo.todoCurso();
        //llenar el contenido de dataCurso:
        dataCurso.setAll(cursos);
    }

    @FXML
    private void contextMenu_click(ContextMenuEvent event) {
        ContextMenu menu=new ContextMenu();
        MenuItem itemSeleccionar=new MenuItem("Seleccionar curso");
        
        itemSeleccionar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Curso curso=tablaCurso.getSelectionModel().getSelectedItem();
                if(curso==null){
                    JOptionPane.showMessageDialog(null,"Selecciona un curso");
                    return;
                }
                //indicar el curso seleccionado y guardarlo en una variable de mayor ambito
                VentanaPrincipalController.cursoSeleccionado=curso.getCodigo();
                try {
                    activarBotones();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(VerCursosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });

        menu.getItems().add(itemSeleccionar);
        tablaCurso.setContextMenu(menu);
    }

    
    @FXML
    private void btnAsistencia_click(ActionEvent event) throws Exception{
        cambiarVentana(event, "Asistencias.fxml", "Registro de Asistencia");
    }

    @FXML
    private void btnNotas_click(ActionEvent event) throws Exception{
        cambiarVentana(event, "VerRegistrarNotas.fxml", "Ver y registrar notas");
    }
    
    private void desactivarBotones(){
        this.btnAsistencia.setDisable(true);
        this.btnNotas.setDisable(true);
    }
    
    private void activarBotones(){
        this.btnAsistencia.setDisable(false);
        this.btnNotas.setDisable(false);
    }
}
