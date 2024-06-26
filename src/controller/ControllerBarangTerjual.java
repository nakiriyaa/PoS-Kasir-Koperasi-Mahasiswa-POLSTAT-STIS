package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerBarangTerjual {

    public void insertBarangTerjual(String noFaktur, String kodeBarang, String namaBarang, int harga, int qty, int totalBayar) {
        String query = "INSERT INTO barangterjual (noFaktur, kodeBarang, namaBarang, harga, qty, bayar) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            pstmt.setString(2, kodeBarang);
            pstmt.setString(3, namaBarang);
            pstmt.setInt(4, harga);
            pstmt.setInt(5, qty);
            pstmt.setInt(6, totalBayar);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
