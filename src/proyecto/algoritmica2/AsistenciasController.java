/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import Repositorio.AsistenciaRepo;
import db.ConexionMySql;
import db.JdbcHelper;
import entidades.Asistencia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import java.util.List;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AsistenciasController implements Initializable {

    public ObservableList<Asistencia> dataAsistencia;// = FXCollections.observableArrayList();
    @FXML
    private TableView<Asistencia> tablaAsistencias;
    @FXML
    private TableColumn<Asistencia, String> colCod_Al;
    @FXML
    private TableColumn<Asistencia, Integer> colAsisten_T;
    @FXML
    private TableColumn<Asistencia, Integer> colAsisten_P;
    @FXML
    private Button btnAsiste_T;
    @FXML
    private Button btnAsiste_P;
    @FXML
    private TextField txtApellido_Al;
    @FXML
    private TextField txtNombre_Al;
    @FXML
    private Button btn_VerCursos;
    @FXML
    private TableColumn<Asistencia, Integer> colN_Sesion;
    @FXML
    private TextField txtN_Sesion;
    @FXML
    private Label lb_NombreCurso;

    private int posicionAsistenciaEnTabla;
    ObservableList<Asistencia> asistencias;
    
    String alumnoCod="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        rellenarTablaAsistencia();
    }

    @FXML
    private void btnAsiste_T_click(ActionEvent event) {
        
        String aluCod=alumnoCod;
        String asiCod=VentanaPrincipalController.cursoSeleccionado;
        int numSesion=Integer.parseInt(txtN_Sesion.getText());
        int asisteP=1;
        int asisteT=0;
        String tipo="Asistencia_T";
        Asistencia asistencia=new Asistencia(aluCod, asiCod, numSesion, asisteT, asisteP);
        AsistenciaRepo asistenciaRepo=new AsistenciaRepo();
        try {
            if(asistenciaRepo.guardarAsistencia(asistencia, tipo)){
                vaciarCampos();
                JOptionPane.showMessageDialog(null,"Asistencia registrada");
                configurarTabla();
                rellenarTablaAsistencia();
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Asistencia no registrada--"
                        + "posiblemente se genera una advertencia pero no un error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ocurrió un error al registrar "
                    + "la asistencia","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    private void btnAsiste_P_click(ActionEvent event) {
        String aluCod=alumnoCod;
        String asiCod=VentanaPrincipalController.cursoSeleccionado;
        int numSesion=Integer.parseInt(txtN_Sesion.getText());
        int asisteP=1;
        int asisteT=0;
        String tipo="Asistencia_P";
        Asistencia asistencia=new Asistencia(aluCod, asiCod, numSesion, asisteT, asisteP);
        AsistenciaRepo asistenciaRepo=new AsistenciaRepo();
        try {
            if(asistenciaRepo.guardarAsistencia(asistencia, tipo)){
                vaciarCampos();
                JOptionPane.showMessageDialog(null,"Asistencia registrada");
                configurarTabla();
                rellenarTablaAsistencia();
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Asistencia no registrada--"
                        + "posiblemente se genera una advertencia pero no un error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ocurrió un error al registrar "
                    + "la asistencia","Error",JOptionPane.ERROR_MESSAGE);
        }        
    }

    @FXML
    private void btn_VerCursos_click(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide(); //cerra ventana
        Parent root = FXMLLoader.load(getClass().getResource("VerCursos.fxml")); //abrir ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ver cursos");
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

    public void configurarTabla() {
        colCod_Al.setCellValueFactory(new PropertyValueFactory<Asistencia,String>("codAlum"));
        colN_Sesion.setCellValueFactory(new PropertyValueFactory<Asistencia,Integer>("N_sesion"));
        colAsisten_T.setCellValueFactory(new PropertyValueFactory<Asistencia,Integer>("AsisteT"));
        colAsisten_P.setCellValueFactory(new PropertyValueFactory<Asistencia,Integer>("AsisteP"));

        dataAsistencia=FXCollections.observableArrayList();
        tablaAsistencias.setItems(dataAsistencia);
    }

    public void rellenarTablaAsistencia() {
        dataAsistencia.clear();
        AsistenciaRepo asistenciaRepo = new AsistenciaRepo();

        ObservableList<Asistencia> asistencias = asistenciaRepo.buscarAsistencias();

        dataAsistencia.setAll(asistencias);

    }

    @FXML
    private void contextMenu_click(ContextMenuEvent event) {

        ContextMenu menu = new ContextMenu();
        MenuItem itemSeleccionar = new MenuItem("Seleccionar alumno");

        itemSeleccionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Asistencia asistencia = tablaAsistencias.getSelectionModel().getSelectedItem();
                if (asistencia == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona un alumno");
                    return;
                }
                String Alcodigo = asistencia.getCodAlum();
                JOptionPane.showMessageDialog(null,"Codigo es "+Alcodigo);
                String query = "SELECT * FROM Alumno WHERE Alu_codigo='" + Alcodigo + "'";
                JdbcHelper jdbc = new JdbcHelper();
                ResultSet rs = jdbc.realizarConsulta(query);
                String nombre = "-", apellido = "-";

                try {
                    while (rs.next()) {
                        nombre = rs.getString("Alu_nombre");
                        apellido = rs.getString("Alu_Apellidos");
                        alumnoCod=rs.getString("Alu_codigo");
                    }
                    txtNombre_Al.setText(nombre);
                    txtApellido_Al.setText(apellido);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al cargar datos del alumno",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        menu.getItems().add(itemSeleccionar);
        tablaAsistencias.setContextMenu(menu);
    }

    //para poder usar info de un elemento seleccionado de la tabla:
    //listener para la tabla:
    private final ListChangeListener<Asistencia> selectorTablaAsistencia
            = new ListChangeListener<Asistencia>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Asistencia> c) {
            ponerAsistenciaSeleccionada();
        }
    };

    //para seeleccionar una celde la tabla:
    public Asistencia getTablaAsistenciaSeleccionada() {
        if (tablaAsistencias != null) {
            List<Asistencia> asistencia = tablaAsistencias.getSelectionModel().getSelectedItems();

            if (asistencia.size() == 1) {
                final Asistencia objetoSeleccinado = asistencia.get(0);
                return objetoSeleccinado;
            }
        }
        return null;
    }

    //metodo para poner en los textFields la tupla que seleccionemos:
    private void ponerAsistenciaSeleccionada() {
        JOptionPane.showMessageDialog(null,"Entra a ponerAsistenciaSeleccionada");
        final Asistencia asistencia = getTablaAsistenciaSeleccionada();
        posicionAsistenciaEnTabla = asistencias.indexOf(asistencia);

        if (asistencia != null) {
            String codigoAlu = asistencia.getCodAlum();
            JOptionPane.showMessageDialog(null,"codigoAlu es asistencia.getC");
            //consulta para obtener algunos datos del alumno:
            String query = "SELECT * FROM Alumno WHERE Alu_codigo='" + codigoAlu + "'";
            JdbcHelper jdbc = new JdbcHelper();
            ResultSet rs = jdbc.realizarConsulta(query);
            String nombre = "-", apellido = "-", codigo = "-";

            try {
                while (rs.next()) {
                    txtNombre_Al.setText(rs.getString("Alu_Nombre"));
                    txtApellido_Al.setText(rs.getString("Alu_Apellidos"));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al cargar datos del Alumno",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @FXML
    private void jTableMouseClicked_click(MouseEvent event) {
//        int seleccionX=tablaAsistencias.rowFactoryPropertyevent.getSceneX());
//        tablaAsistencias.get
    }
    
    private void vaciarCampos(){
        txtNombre_Al.setText("");
        txtApellido_Al.setText("");
        txtN_Sesion.setText("");
    }

}
