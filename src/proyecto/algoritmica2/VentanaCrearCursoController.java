/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.algoritmica2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VentanaCrearCursoController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUniversidad;
    @FXML
    private TextField txtFacultad;
    @FXML
    private TextField txtEAP;
    @FXML
    private ComboBox<?> selectPlan;
    @FXML
    private ComboBox<?> selectCredito;
    @FXML
    private TextField txtPesoPP;
    @FXML
    private TextField txtPesoEP;
    @FXML
    private TextField txtPesoEF;
    @FXML
    private TextField txtCocienteNotaFinal;
    @FXML
    private Pane panelSeleccionarNota_Susti;
    @FXML
    private ComboBox<?> panel_NotaSustituir;
    @FXML
    private TextField txtT_inic;
    @FXML
    private TextField txtT_fin;
    @FXML
    private TextField txtP_inic;
    @FXML
    private TextField txtP_fin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
