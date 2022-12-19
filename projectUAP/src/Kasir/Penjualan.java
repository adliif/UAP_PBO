package Kasir;

import java.util.ArrayList;

public class Penjualan implements ProductCounter {
    private ArrayList<Produk> listProduk;
    private int jumlahProduk;
    private int stok;

    public Penjualan(ArrayList<Produk> listProduk, int jumlahProduk, int stok) {
        this.listProduk = new ArrayList<>();
        this.jumlahProduk = jumlahProduk;
        this.stok = stok;
    }
    
    public Penjualan(String listProduk, int jumlahProduk, int stok) {
        this.listProduk = new ArrayList<>();
        this.jumlahProduk = jumlahProduk;
        this.stok = stok;
    }

    public ArrayList<Produk> getListProduk() {
        return listProduk;
    }

    public void setListProduk(ArrayList<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    public int getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(int jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    public int getStok() {
        return jumlahProduk - stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    public void getProduk() {
        System.out.println("Produk : " + listProduk);
    }
    
    @Override
    public double TAX() {
        return 0.1;
    }
    
    @Override
    public double hitungJumlahProduk() {
        return listProduk.size();
    }
    
    @Override
    public double hitungHargaProduk() {
        double total = .0;
        for(Produk produk : listProduk) {
            total += produk.hargaDiskon();
        }
        return 0;
    }
    
}