package GUI;

import Kasir.Penjualan;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class formBarangController {

    @FXML
    private MenuItem btnBeliBarang;

    @FXML
    private MenuItem btnHapusPenjualanBarang;

    @FXML
    private Button kembali;

    @FXML
    private TableColumn<?, ?> kolomBarcodeBarang;

    @FXML
    private TableColumn<?, ?> kolomDiskonBarang;

    @FXML
    private TableColumn<?, ?> kolomExpiredBarang;

    @FXML
    private TableColumn<?, ?> kolomHargaBarang;

    @FXML
    private TableColumn<?, ?> kolomJumlahBarang;

    @FXML
    private TableColumn<?, ?> kolomJumlahPenjualanBarang;

    @FXML
    private TableColumn<?, ?> kolomNamaBarang;

    @FXML
    private TableColumn<?, ?> kolomNamaPenjualanBarang;

    @FXML
    private TableColumn<?, ?> kolomStokPenjualanBarang;

    @FXML
    private TableView<?> tabelPenjualanBarang;

    @FXML
    private TableView<?> tabelProdukBarang;

    @FXML
    private TextField txtBarcodePenjualanBarang;

    @FXML
    private TextField txtJumlahPenjualanBarang;

    @FXML
    private TextField txtStokPenjualanBarang;

    @FXML
    void backData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("formKasir.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) kembali.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void handleAddPenjualanBarang(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        int jumlahProduk = Integer.parseInt(txtJumlahPenjualanBarang.getText());
        int stok = Integer.parseInt(txtStokPenjualanBarang.getText());
                                           
        Penjualan pjl2 = new Penjualan(txtBarcodePenjualanBarang.getText(), jumlahProduk, stok);
        barangBaru.addPenjualan(pjl2);
    }

    @FXML
    void handleDeletePenjualanBarang(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        int jumlahProduk = Integer.parseInt(txtJumlahPenjualanBarang.getText());
        int stok = Integer.parseInt(txtStokPenjualanBarang.getText());
                                           
        Penjualan pjl2 = new Penjualan(txtBarcodePenjualanBarang.getText(), jumlahProduk, stok);
        barangBaru.deletePenjualan(pjl2);
    }

}