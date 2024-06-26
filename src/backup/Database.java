package backup;

import isenguas.*;
import model.Barang;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;

//    private final String DB_TYPE = "mysql";
//    private final String DB_HOST = "localhost";
//    private final String DB_PORT = "3306";
//    private final String DB_NAME = "wirus";
//    private final String DB_USER = "root";
//    private final String DB_PASS = "";
    
    // SQLite database file path
    private final String DB_URL = "jdbc:sqlite:src/wirus.db";

    private Database() {
        // Private constructor to prevent instantiation.
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

//    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:" + DB_TYPE + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASS);
//    }
    
    private Connection getConnection() throws SQLException {
    // Check if the database file exists
    File dbFile = new File("src/wirus.db");
    System.out.println("Database path: " + dbFile.getAbsolutePath());
    if (!dbFile.exists()) {
        throw new SQLException("Database file not found: " + dbFile.getAbsolutePath());
    }
    return DriverManager.getConnection(DB_URL);
}


    private void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // handle or log the exception properly
        }
    }
    
    public Barang getBarangByKodeBarang(String kodeBarang) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Barang barang = null;

        try {
            conn = getConnection();
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
            ex.printStackTrace();
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
            conn = getConnection();
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
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return barangList;
    }

    public void insertBarang(Barang barang) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO barang (kodeBarang, namaBarang, stok, harga) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barang.getKodeBarang());
            pstmt.setString(2, barang.getNamaBarang());
            pstmt.setInt(3, barang.getStok());
            pstmt.setInt(4, barang.getHarga());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }


    public void updateBarang(Barang barang) {
        String sql = "UPDATE barang SET namaBarang = ?, stok = ?, harga = ? WHERE kodeBarang = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, barang.getNamaBarang());
            pstmt.setInt(2, barang.getStok());
            pstmt.setInt(3, barang.getHarga());
            pstmt.setString(4, barang.getKodeBarang());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    public void deleteBarang(String kodeBarang) {
        String sql = "DELETE FROM barang WHERE kodeBarang = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kodeBarang);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }
    
    public void insertData(String noFaktur, String kodeBarang, String namaBarang, int harga, int qty) {
        int totalBayar = harga * qty;
        String query = "INSERT INTO barangterjual (noFaktur, kodeBarang, namaBarang, harga, qty, totalBayar) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            pstmt.setString(2, kodeBarang);
            pstmt.setString(3, namaBarang);
            pstmt.setInt(4, harga);
            pstmt.setInt(5, qty);
            pstmt.setInt(6, totalBayar);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    // Method to get data from barangterjual table by noFaktur
    public List<Object[]> getBarangByNoFak(String noFaktur) {
        List<Object[]> barangList = new ArrayList<>();
        String query = "SELECT noFaktur, kodeBarang, namaBarang, harga, qty, totalBayar FROM barangterjual WHERE noFaktur = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getString("kodeBarang");
                row[1] = rs.getString("namaBarang");
                row[2] = rs.getInt("harga");
                row[3] = rs.getInt("qty");
                row[4] = rs.getInt("totalBayar");
                barangList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return barangList;
    }
    
    public String noFak() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String nomorFaktur = "";

        try {
            conn = getConnection();
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
                // Simpan nomor faktur baru ke tabel penjualan
            } else {
                nomorFaktur = "F0001";
                // Jika tidak ada data di tabel barangterjual, inisialisasi dengan F0001
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return nomorFaktur;
    }
    
    public String noKodeBarang() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String kodeBarang = "";

        try {
            conn = getConnection();
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
            ex.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, rs);
        }

        return kodeBarang;
    }

    public int getStokBarang(String kodeBarang) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int stok = 0;

        try {
            conn = getConnection();
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

    // Method to update stock of a specific item by its code and quantity
    public void updateStokBarang(String kodeBarang, int qty) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
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
    
    public void insertBarangTerjual(String noFaktur, String kodeBarang, String namaBarang, int harga, int qty, int totalBayar) {
        String query = "INSERT INTO barangterjual (noFaktur, kodeBarang, namaBarang, harga, qty, bayar) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            pstmt.setString(2, kodeBarang);
            pstmt.setString(3, namaBarang);
            pstmt.setInt(4, harga);
            pstmt.setInt(5, qty);
            pstmt.setInt(6, totalBayar);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

    public void simpanPelanggan(String noFaktur, int subTotal, int diskon, int jumlahDiskon, int grandTotal, int bayar, int kembalian) {
        String query = "INSERT INTO pelanggan (noFaktur, subTotal, diskon, jumlahdiskon, grandTotal, bayar, kembalian) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, noFaktur);
            pstmt.setInt(2, subTotal);
            pstmt.setInt(3, diskon);
            pstmt.setInt(4, jumlahDiskon);
            pstmt.setInt(5, grandTotal);
            pstmt.setInt(6, bayar);
            pstmt.setInt(7, kembalian);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pstmt, null);
        }
    }

}
