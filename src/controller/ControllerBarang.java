package controller;

import model.Barang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControllerBarang {

    private static final Logger logger = Logger.getLogger(ControllerBarang.class.getName());

    public String noKodeBarang() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String kodeBarang = "";

        try {
            conn = Koneksi.getConnection();
            String sql = "SELECT kodeBarang FROM barang ORDER BY kodeBarang DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String kode = rs.getString("kodeBarang").substring(1);
                int angka = Integer.parseInt(kode) + 1;
                String angkaStr = String.format("%03d", angka); // Format angka ke tiga digit
                kodeBarang = "A" + angkaStr;
            } else {
                kodeBarang = "A001"; // Jika tidak ada data, inisialisasi dengan A001
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error generating kodeBarang", ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return kodeBarang;
    }

    public Barang getBarangByKodeBarang(String kodeBarang) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Barang barang = null;

        try {
            conn = Koneksi.getConnection();
            String sql = "SELECT * FROM barang WHERE kodeBarang = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kodeBarang);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                barang = new Barang();
                barang.setKodeBarang(rs.getString("kodeBarang"));
                barang.setNamaBarang(rs.getString("namaBarang"));
                barang.setStok(rs.getInt("stok"));
                barang.setHarga(rs.getInt("harga"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error getting barang by kodeBarang", ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return barang;
    }

    public List<Barang> getAllBarang() {
        List<Barang> barangList = new ArrayList<>();
        String sql = "SELECT * FROM barang";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Barang barang = new Barang();
                barang.setKodeBarang(rs.getString("kodeBarang"));
                barang.setNamaBarang(rs.getString("namaBarang"));
                barang.setStok(rs.getInt("stok"));
                barang.setHarga(rs.getInt("harga"));
                barangList.add(barang);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error getting all barang", ex);
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return barangList;
    }

    public void insertBarang(Barang barang) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Koneksi.getConnection();
            String sql = "INSERT INTO barang (kodeBarang, namaBarang, stok, harga) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barang.getKodeBarang());
            pstmt.setString(2, barang.getNamaBarang());
            pstmt.setInt(3, barang.getStok());
            pstmt.setInt(4, barang.getHarga());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error inserting barang", ex);
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    public void updateBarang(Barang barang) {
        String sql = "UPDATE barang SET namaBarang = ?, stok = ?, harga = ? WHERE kodeBarang = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Koneksi.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barang.getNamaBarang());
            pstmt.setInt(2, barang.getStok());
            pstmt.setInt(3, barang.getHarga());
            pstmt.setString(4, barang.getKodeBarang());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating barang", ex);
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    public void deleteBarang(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kodeBarang = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Koneksi.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kodeBarang);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error deleting barang", ex);
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error closing connection", ex);
        }
    }
}
