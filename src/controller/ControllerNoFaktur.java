package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerNoFaktur {

    public String noFak() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String nomorFaktur = "";

        try {
            conn = Koneksi.getConnection();
            String sql = "SELECT noFaktur FROM barangterjual ORDER BY noFaktur DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nofak = rs.getString("noFaktur").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String nol = "";

                if (AN.length() == 1) {
                    nol = "000";
                } else if (AN.length() == 2) {
                    nol = "00";
                } else if (AN.length() == 3) {
                    nol = "0";
                } else if (AN.length() == 4) {
                    nol = "";
                }
                nomorFaktur = "F" + nol + AN;
            } else {
                nomorFaktur = "F0001";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return nomorFaktur;
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
