package GUI;

import Database.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Kasir.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class kasirModel {
    private final Connection conn;

    public kasirModel() {
        this.conn = DBHelper.getConnection();
    }
    
    public void addMakanan(Makanan makanan){
        String insert = "INSERT INTO makanan VALUES ('"
                + makanan.getNama_produk() + "', '" + makanan.getHarga()
                + "', '" + makanan.getJumlah() + "', '" + makanan.getDiskon() 
                + "', '" + makanan.getId() + "', '" + makanan.getDaya_tahan() + "');";

        try {
            if (conn.createStatement().executeUpdate(insert) > 0) {
                System.out.println("Berhasil memasukkan data !");
            } else {
                System.out.println("Gagal memasukkan data !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal memasukkan data !");
        }
    }
    
    public void addBarang(Barang barang){
        String insert = "INSERT INTO barang VALUES ('"
                + barang.getNama_produk() + "', '" + barang.getHarga()
                + "', '" + barang.getJumlah() + "', '" + barang.getDiskon() 
                + "', '" + barang.getBarcode() + "', '" + barang.getExpired() + "');";

        try {
            if (conn.createStatement().executeUpdate(insert) > 0) {
                System.out.println("Berhasil memasukkan data !");
            } else {
                System.out.println("Gagal memasukkan data !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal memasukkan data !");
        }
    }
    
    public void addKategori(Kategori kategori){
        String insert = "INSERT INTO kategori VALUES ('"
                + kategori.getNama_kategori() + "');";

        try {
            if (conn.createStatement().executeUpdate(insert) > 0) {
                System.out.println("Berhasil memasukkan data !");
            } else {
                System.out.println("Gagal memasukkan data !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal memasukkan data !");
        }
    } 
    
   public void addPenjualan(Penjualan penjualan){
        String insert = "INSERT INTO penjualan VALUES ('" + penjualan.getListProduk() 
                + "', " + penjualan.getJumlahProduk() 
                + ", " + penjualan.getStok() + ");";

        try {
            if(conn.createStatement().executeUpdate(insert)>0){
                System.out.println("Berhasil Memasukkan Data");
            }else{
                System.out.println("Gagal Memasukkan Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Memasukkan Data");
        }
    }
    
    public void deleteMakanan(Makanan makanan) {
        String delete = "DELETE FROM makanan WHERE makanan.id_makanan = "
                + makanan.getId();

        try {
            if (conn.createStatement().executeUpdate(delete) > 0) {
                System.out.println("Berhasil menghapuskan data");
            } else {
                System.out.println("Data tidak ada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal menghapuskan data");
        }

    }
    
    public void deleteBarang(Barang barang) {
        String delete = "DELETE FROM barang WHERE barang.barcode = '"
                + barang.getBarcode() + "';";

        try {
            if (conn.createStatement().executeUpdate(delete) > 0) {
                System.out.println("Berhasil menghapuskan data");
            } else {
                System.out.println("Data tidak ada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal menghapuskan data");
        }

    }
    
    public void deleteKategori(Kategori kategori) {
        String delete = "DELETE FROM kategori WHERE kategori.nama_kategori = '"
                + kategori.getNama_kategori() + "';";

        try {
            if (conn.createStatement().executeUpdate(delete) > 0) {
                System.out.println("Berhasil menghapuskan data");
            } else {
                System.out.println("Data tidak ada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal menghapuskan data");
        }

    }
    
    public void deletePenjualan(Penjualan penjualan) {
        String delete = "DELETE FROM penjualan WHERE penjualan.listProduk='" 
                + penjualan.getListProduk() + "';";
        try {
            if(conn.createStatement().executeUpdate(delete)>0){
                System.out.println("Berhasil Menghapus Data");
            }else{
                System.out.println("Gagal Menghapus Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Menghapus Data");
        }

    }
    
    public ObservableList<Makanan> getMakanan() {
        String querry = "SELECT * FROM makanan";
        ObservableList<Makanan> makanan = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery(querry);
            while (rs.next()) {
                Makanan temp = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getInt("id"), rs.getInt("daya_tahan"));
                makanan.add(temp);
            }
            System.out.println("\nBerhasil mengambil data");
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal mengambil data");
        }
        return makanan;
    }
    
    public ObservableList<Barang> getBarang() {
        String querry = "SELECT * FROM barang, kategori";
        ObservableList<Barang> barang = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery(querry);
            while (rs.next()) {
                Barang temp = new Barang(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getString("barcode"), rs.getString("expired"));
                barang.add(temp);
            }
            System.out.println("\nBerhasil mengambil data");
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal mengambil data");
        }
        return barang;
    }
    
    public ObservableList<Kategori> getKategori() {
        String querry = "SELECT * FROM kategori";
        ObservableList<Kategori> kategori = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery(querry);
            while (rs.next()) {
                Kategori temp = new Kategori(rs.getString("nama_kategori"));
                kategori.add(temp);
            }
            System.out.println("\nBerhasil mengambil data");
        } catch (SQLException ex) {
            Logger.getLogger(kasirModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal mengambil data");
        }
        return kategori;
    }
    
    public ObservableList<Penjualan> getPenjualan(String nama_produk){
        ObservableList<Penjualan> penjualan = FXCollections.observableArrayList();
        
        try{
            String sql = "SELECT nama_produk, jumlahProduk, stok "
                       + "FROM Penjualan WHERE nama_produk = " + nama_produk;
            
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()){
                penjualan.add(new Penjualan(rs.getString(1),rs.getInt(2), rs.getInt(3)));
            }
        }catch(Exception e){
            System.out.println("Error : " + e);
        }
        
        return penjualan;
    }
    
}