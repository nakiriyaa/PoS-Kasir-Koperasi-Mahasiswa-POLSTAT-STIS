package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPelanggan {

    public void simpanPelanggan(String noFaktur, int subTotal, int diskon, int jumlahDiskon, int grandTotal, int bayar, int kembalian) {
        String query = "INSERT INTO pelanggan (noFaktur, subTotal, diskon, jumlahDiskon, grandTotal, bayar, kembalian) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = Koneksi.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            pstmt.setInt(2, subTotal);
            pstmt.setInt(3, diskon);
            pstmt.setInt(4, jumlahDiskon);
            pstmt.setInt(5, grandTotal);
            pstmt.setInt(6, bayar);
            pstmt.setInt(7, kembalian);
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
