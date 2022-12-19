package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Kasir.*;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class formKasirController implements Initializable {

    @FXML
    private TextField barcode_barang;

    @FXML
    private MenuItem btnBeliMakanan;

    @FXML
    private MenuItem btnHapusBarang;

    @FXML
    private MenuItem btnHapusBarangKategori;

    @FXML
    private MenuItem btnHapusMakanan;

    @FXML
    private MenuItem btnHapusPenjualanMakanan;

    @FXML
    private MenuItem btnTambahBarang;

    @FXML
    private MenuItem btnTambahBarangKategori;

    @FXML
    private MenuItem btnTambahMakanan;

    @FXML
    private Button data_penjualan;

    @FXML
    private TextField dayaTahan_Makanan;

    @FXML
    private TextField diskon_barang;

    @FXML
    private TextField diskon_makanan;

    @FXML
    private TextField expired_barang;

    @FXML
    private TextField harga_barang;

    @FXML
    private TextField harga_makanan;

    @FXML
    private TextField id_makanan;

    @FXML
    private TextField jumlah_barang;

    @FXML
    private TextField jumlah_makanan;

    @FXML
    private TextField kategori;

    @FXML
    private TextField kategori_barang;

    @FXML
    private TableColumn<?, ?> kolomBarcodeBarang;

    @FXML
    private TableColumn<Makanan, Integer> kolomDayaTahanMakanan;

    @FXML
    private TableColumn<?, ?> kolomDiskonBarang;

    @FXML
    private TableColumn<Makanan, Double> kolomDiskonMakanan;

    @FXML
    private TableColumn<?, ?> kolomExpiredBarang;

    @FXML
    private TableColumn<?, ?> kolomHargaBarang;

    @FXML
    private TableColumn<Makanan, Double> kolomHargaMakanan;

    @FXML
    private TableColumn<Makanan, Integer> kolomIDMakanan;

    @FXML
    private TableColumn<?, ?> kolomJumlahBarang;

    @FXML
    private TableColumn<Makanan, Integer> kolomJumlahMakanan;

    @FXML
    private TableColumn<?, ?> kolomJumlahPenjualanMakanan;

    @FXML
    private TableColumn<?, ?> kolomKategori;

    @FXML
    private TableColumn<?, ?> kolomNamaBarang;

    @FXML
    private TableColumn<Makanan, String> kolomNamaMakanan;

    @FXML
    private TableColumn<?, ?> kolomNamaPenjualanMakanan;

    @FXML
    private TableColumn<?, ?> kolomStokPenjualanMakanan;

    @FXML
    private TextField nama_barang;

    @FXML
    private TextField nama_makanan;

    @FXML
    private MenuButton pilihanBarang;

    @FXML
    private MenuButton pilihanKategori;

    @FXML
    private TableView<?> tabelPenjualanMakanan;

    @FXML
    private TableView<?> tabelProdukBarang;

    @FXML
    private TableView<?> tabelProdukBarangKategori;

    @FXML
    private TableView<Makanan> tabelProdukMakanan;

    @FXML
    private TextField txtIDPenjualanMakanan;

    @FXML
    private TextField txtJumlahPenjualanMakanan;
    
    @FXML
    private TextField txtStokPenjualanMakanan;

    kasirModel datamodel;
    
    public void viewPenjualan(String nama_produk){
        try{
            ObservableList<Penjualan> data = datamodel.getPenjualan(nama_produk);
            kolomNamaPenjualanMakanan.setCellValueFactory(new PropertyValueFactory<>("listProduk"));
            kolomJumlahPenjualanMakanan.setCellValueFactory(new PropertyValueFactory<>("jumlahProduk"));
            kolomStokPenjualanMakanan.setCellValueFactory(new PropertyValueFactory<>("stok"));
            tabelPenjualanMakanan.setItems(null);
            System.out.println(data);
        }catch(Exception e){
            System.out.println("Error : " + e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tabelProdukMakanan.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if(tabelProdukMakanan.getSelectionModel().getSelectedItem() != null){
                Makanan akun = (Makanan) tabelProdukMakanan.getSelectionModel().getSelectedItem();
                viewPenjualan(akun.getNama_produk());
                
                btnBeliMakanan.setDisable(false);
                try{
                    txtIDPenjualanMakanan.setText("" + akun.getId());
                }catch(Exception e){
                    System.out.println("Error : " + e);
                }
            }
        });
        
        tabelProdukBarang.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if(tabelProdukBarang.getSelectionModel().getSelectedItem() != null){
                Barang akun = (Barang) tabelProdukBarang.getSelectionModel().getSelectedItem();
                viewPenjualan(akun.getBarcode());
                
                pilihanKategori.setDisable(false);
            }
        });
    }    
      
    
    @FXML
    void handleAddPenjualanMakanan(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        int jumlahProduk = Integer.parseInt(txtJumlahPenjualanMakanan.getText());
        int stok = Integer.parseInt(txtStokPenjualanMakanan.getText());
                                           
        Penjualan pjl1 = new Penjualan(txtIDPenjualanMakanan.getText(), jumlahProduk, stok);
        barangBaru.addPenjualan(pjl1);
    }

    @FXML
    void handleAddProdukBarang(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        double harga = Double.parseDouble(harga_barang.getText());
        int jumlah = Integer.parseInt(jumlah_barang.getText());
        double diskon = Double.parseDouble(diskon_barang.getText());
                                           
        Barang pdk1 = new Barang(nama_barang.getText(), harga, jumlah, diskon, barcode_barang.getText(), expired_barang.getText());
        barangBaru.addBarang(pdk1);
    }

    @FXML
    void handleAddProdukBarangKategori(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
                                           
        Kategori ktg1 = new Kategori(kategori.getText());
        barangBaru.addKategori(ktg1);
    }

    @FXML
    void handleAddProdukMakanan(ActionEvent event) {
        kasirModel makananBaru = new kasirModel();
        double harga = Double.parseDouble(harga_makanan.getText());
        int jumlah = Integer.parseInt(jumlah_makanan.getText());
        double diskon = Double.parseDouble(diskon_makanan.getText());
        int id = Integer.parseInt(id_makanan.getText());
        int daya_tahan = Integer.parseInt(dayaTahan_Makanan.getText());
                                           
        Makanan mkn1 = new Makanan(nama_makanan.getText(), harga, jumlah, diskon, id, daya_tahan);
        makananBaru.addMakanan(mkn1);
    }

    @FXML
    void handleDeletePenjualanMakanan(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        int jumlahProduk = Integer.parseInt(txtJumlahPenjualanMakanan.getText());
        int stok = Integer.parseInt(txtStokPenjualanMakanan.getText());
                                           
        Penjualan pjl1 = new Penjualan(txtIDPenjualanMakanan.getText(), jumlahProduk, stok);
        barangBaru.deletePenjualan(pjl1);
    }

    @FXML
    void handleDeleteProdukBarang(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
        double harga = Double.parseDouble(harga_barang.getText());
        int jumlah = Integer.parseInt(jumlah_barang.getText());
        double diskon = Double.parseDouble(diskon_barang.getText());
                                           
        Barang pdk1 = new Barang(nama_barang.getText(), harga, jumlah, diskon, barcode_barang.getText(), expired_barang.getText());
        barangBaru.deleteBarang(pdk1);
    }

    @FXML
    void handleDeleteProdukBarangKategori(ActionEvent event) {
        kasirModel barangBaru = new kasirModel();
                                           
        Kategori ktg1 = new Kategori(kategori.getText());
        barangBaru.deleteKategori(ktg1);
    }

    @FXML
    void handleDeleteProdukMakanan(ActionEvent event) {
        
        kasirModel makananBaru = new kasirModel();
        double harga = Double.parseDouble(harga_makanan.getText());
        int jumlah = Integer.parseInt(jumlah_makanan.getText());
        double diskon = Double.parseDouble(diskon_makanan.getText());
        int id = Integer.parseInt(id_makanan.getText());
        int daya_tahan = Integer.parseInt(dayaTahan_Makanan.getText());
                                           
        Makanan mkn1 = new Makanan(nama_makanan.getText(), harga, jumlah, diskon, id, daya_tahan);
        makananBaru.deleteMakanan(mkn1);
    }

    @FXML
    void nextData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("formBarang.fxml"));
        Parent root = loader.load();
        
        Stage stage = (Stage) data_penjualan.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}