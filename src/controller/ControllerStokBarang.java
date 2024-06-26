package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerStokBarang {

    public void updateStokBarang(String kodeBarang, int qty) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Koneksi.getConnection();
            String sql = "UPDATE barang SET stok = stok - ? WHERE kodeBarang = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, qty);
            pstmt.setString(2, kodeBarang);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    public int getStokBarang(String kodeBarang) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int stok = 0;

        try {
            conn = Koneksi.getConnection();
            String sql = "SELECT stok FROM barang WHERE kodeBarang = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kodeBarang);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                stok = rs.getInt("stok");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return stok;
    }

    private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
