/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import Repositorio.NotasRepo;
import db.JdbcHelper;
import entidades.Notas;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VerRegistrarNotasController implements Initializable {
    
    public ObservableList<Notas> dataNota=FXCollections.observableArrayList();
    @FXML
    private Label lbNombre_curso;
    @FXML
    private TableView<Notas> tablaNotas;
    @FXML
    private TableColumn<Notas, String> colCodigo;
    @FXML
    private TableColumn<Notas,Double> colCtrlLect;
    @FXML
    private TableColumn<Notas,Double> colProyect;
    @FXML
    private TableColumn<Notas,Double> colLabo;
    @FXML
    private TableColumn<Notas,Double> colParcial;
    @FXML
    private TableColumn<Notas,Double> colFinal;
    @FXML
    private TableColumn<Notas,Double> colPromedio;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCtrlLectu;
    @FXML
    private TextField txtLabo;
    @FXML
    private TextField txtProyect;
    @FXML
    private TextField txtParcial;
    @FXML
    private TextField txtFinal;
    @FXML
    private Button btnRegistrarNota;


    //variiables utiles:
    double N_ctrlLec=0,N_Proy=0,N_Labo=0,N_Par=0,N_Fin=0;
    //pesos de notas:
    double P_ctrlLec=0,P_Proy=0,P_Labo=0,P_Par=0,P_Fin=0;
    String nombre = "-", apellido = "-";
    String Alcodigo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblPesoContrLect;
    @FXML
    private Label lblPEsoProye;
    @FXML
    private Label lblPesoLabo;
    @FXML
    private Label lblPesoParc;
    @FXML
    private Label lblPesoFin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnRegistrarNota.setDisable(true);
        //obtener nombre del curso mediante su codigo:
        String query="SELECT * FROM Asignatura WHERE Asi_codigo='"+VentanaPrincipalController.cursoSeleccionado+"'";
        JdbcHelper jdbc=new JdbcHelper();
        ResultSet rs=jdbc.realizarConsulta(query); 
        String nombreCurso="---";
        try {
            while(rs.next()){
                nombreCurso=rs.getString("Asi_Nombre");
            }
            System.out.println("RegistrarNotas--- consulta: "+query);
            this.lbNombre_curso.setText("Curso: "+nombreCurso);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un problema al cargar el nombre del curso",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
        
        //consulta para mostrar los pesos de las notas:
        String query2="SELECT * FROM Nota WHERE Asi_codigo='"+VentanaPrincipalController.cursoSeleccionado+"'";
        JdbcHelper jdbc2=new JdbcHelper();
        ResultSet rs2=jdbc.realizarConsulta(query2);
        try {
            while(rs2.next()){
                lblPEsoProye.setText(String.valueOf(rs2.getDouble("Peso_Proyecto")));// .getText();
                P_Proy=rs2.getDouble("Peso_Proyecto");
                lblPesoLabo.setText(String.valueOf(rs2.getDouble("Peso_Laboratorio")));
                P_Labo=rs2.getDouble("Peso_Laboratorio");
                lblPesoContrLect.setText(String.valueOf(rs2.getDouble("Peso_ControlLectura")));
                P_ctrlLec=rs2.getDouble("Peso_ControlLectura");
                lblPesoParc.setText(String.valueOf(rs2.getDouble("Peso_Parcial")));
                P_Par=rs2.getDouble("Peso_Parcial");
                lblPesoFin.setText(String.valueOf(rs2.getDouble("Peso_Final")));
                P_Fin=rs2.getDouble("Peso_Final");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al cargar los pesos de las notas");
        }
        configurarTabla();
        rellenarTabla();
       
    }    
    
            
    public void configurarTabla() {
        colCtrlLect.setCellValueFactory(new PropertyValueFactory<Notas,Double>("ControlLectura"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<Notas,String>("codAlumno"));
        colLabo.setCellValueFactory(new PropertyValueFactory<Notas,Double>("Laboratorio"));
        colProyect.setCellValueFactory(new PropertyValueFactory<Notas,Double>("Proyecto"));
        colParcial.setCellValueFactory(new PropertyValueFactory<Notas,Double>("Parcial"));
        colFinal.setCellValueFactory(new PropertyValueFactory<Notas,Double>("Final"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<Notas,Double>("promedio"));
        tablaNotas.setItems(dataNota);
    }
    
    
        
    public void rellenarTabla(){
        dataNota.clear();
        NotasRepo notasRepo=new NotasRepo();
        ObservableList<Notas> notas=notasRepo.BuscarNotas();
        dataNota.setAll(notas);
        
    }

    @FXML
    private void contextMenu_click(ContextMenuEvent event) {
        
        ContextMenu menu = new ContextMenu();
        MenuItem itemSeleccionarAl = new MenuItem("Seleccionar alumno");

        itemSeleccionarAl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Notas notas = tablaNotas.getSelectionModel().getSelectedItem();
                if (notas == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona un alumno");
                    return;
                }
                //obtener informacion del alumno mediante su codigo:
                Alcodigo = notas.getCodAlumno();
                String query = "SELECT * FROM Alumno WHERE Alu_codigo='" + Alcodigo + "'";
                JdbcHelper jdbc = new JdbcHelper();
                ResultSet rs = jdbc.realizarConsulta(query);
                
                try {
                    while (rs.next()) {
                        nombre = rs.getString("Alu_nombre");
                        apellido = rs.getString("Alu_Apellidos");                        
                    }
                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al cargar datos del alumno",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                //obtener las notas
                String query2="SELECT * FROM Nota WHERE Alu_codigo='"+Alcodigo+"'";
                JdbcHelper jdbc2 = new JdbcHelper();
                ResultSet rs2 = jdbc2.realizarConsulta(query2);
                
                try {
                    while (rs.next()) {
                        N_ctrlLec = rs2.getDouble("Nota_ControlLectura");
                        P_ctrlLec=rs2.getDouble("Peso_ControlLectura");
                        N_Proy = rs2.getDouble("Nota_Proyecto");
                        P_Proy=rs2.getDouble("Peso_Proyecto");
                        N_Labo = rs2.getDouble("Nota_Laboratorio");
                        P_Labo=rs2.getDouble("Peso_Laboratorio");
                        N_Par = rs2.getDouble("Nota_Parcial");
                        P_Par=rs2.getDouble("Peso_Parcial");
                        N_Fin = rs2.getDouble("Nota_Final");
                        P_Fin=rs2.getDouble("Peso_Final");

                        //agregar notas a los campos de texto:
                        txtCtrlLectu.setText(String.valueOf(N_ctrlLec));
                        txtLabo.setText(String.valueOf(N_Labo));
                        txtProyect.setText(String.valueOf(N_Proy));
                        txtParcial.setText(String.valueOf(N_Par));
                        txtFinal.setText(String.valueOf(N_Fin));
                    }
                    btnRegistrarNota.setDisable(false); //activar  el boton de registrar Nota
                } catch (Exception e) {
                    if(N_ctrlLec==0 || N_Proy==0 || N_Labo==0 || N_Par==0 || N_Fin==0){
                        //no todas las notas se han registrado aun
                        JOptionPane.showMessageDialog(null,"El registro de notas de "
                                + "este alumno no se ha completado","Advertencia",JOptionPane.WARNING_MESSAGE);
                        btnRegistrarNota.setDisable(false);
                    }
                    else{
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un problema al cargar las notas"
                            + " del alumno",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });

        menu.getItems().add(itemSeleccionarAl);
        tablaNotas.setContextMenu(menu);
    }

    @FXML
    private void btnRegistrarNota_click(ActionEvent event) {
        N_ctrlLec=Double.parseDouble(txtCtrlLectu.getText());
        N_Proy=Double.parseDouble(txtProyect.getText());
        N_Labo=Double.parseDouble(txtLabo.getText());
        N_Par=Double.parseDouble(txtParcial.getText());
        N_Fin=Double.parseDouble(txtFinal.getText());
        
        Notas notas=new Notas(N_ctrlLec, P_ctrlLec, N_Proy, P_Proy, N_Labo, P_Labo,
                N_Par, P_Par, N_Fin, P_Fin, Alcodigo, VentanaPrincipalController.cursoSeleccionado);

        NotasRepo notasRepo=new NotasRepo();
        
        if(notasRepo.guardarNotas(notas)){
            vaciarCampos();
            JOptionPane.showMessageDialog(null,"Nota(s) registrada(s)","Exito",
                    JOptionPane.OK_OPTION);
            rellenarTabla();
        }
        else{
            JOptionPane.showMessageDialog(null,"No se pudo guardar la nota","Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void vaciarCampos(){
        txtApellido.setText("");
        txtCtrlLectu.setText("");
        txtFinal.setText("");
        txtLabo.setText("");
        txtNombre.setText("");
        txtParcial.setText("");
        txtProyect.setText("");
    }

    @FXML
    private void btnRegresar_click(ActionEvent event) throws Exception {
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
    
}
