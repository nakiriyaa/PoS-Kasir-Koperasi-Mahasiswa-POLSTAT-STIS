/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ControllerBarang;
import isenguas.*;
import model.Barang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adit
 */
public class InputBarang extends javax.swing.JFrame {


    private ControllerBarang controller;
    private String selectedKodeBarang;
    /**
     * Creates new form InputBarang
     */
    public InputBarang() {
        initComponents();
        controller = new ControllerBarang();
        loadTable();
        namaBarangTextField.grabFocus();
        disableEditAndDelete();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        namaBarangTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        stokTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        hargaTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        barangTable = new javax.swing.JTable();
        simpanButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("NAMA BARANG");

        namaBarangTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaBarangTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("STOK");

        stokTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("HARGA");

        hargaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTextFieldActionPerformed(evt);
            }
        });

        barangTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Stok", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        barangTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barangTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(barangTable);

        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        hapusButton.setText("HAPUS");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        backButton.setText("Kembali");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(namaBarangTextField)
                        .addComponent(stokTextField)
                        .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(simpanButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaBarangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stokTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(hapusButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disableEditAndDelete() {
        editButton.setEnabled(false);
        hapusButton.setEnabled(false);
    }
    
    private void enableEditAndDelete() {
        editButton.setEnabled(true);
        hapusButton.setEnabled(true);
    }
    
    private void namaBarangTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaBarangTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaBarangTextFieldActionPerformed

    private void stokTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokTextFieldActionPerformed

    private void hargaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTextFieldActionPerformed
        // TODO add your handling code here:
        if (barangTable.getSelectedRow() == -1) {
            simpanButtonActionPerformed(evt);
        } else {
            editButtonActionPerformed(evt);
        }
    }//GEN-LAST:event_hargaTextFieldActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
        String kodeBarang = controller.noKodeBarang(); // Mengambil kode barang baru dari controller

        String namaBarang = namaBarangTextField.getText().trim();
        String stokText = stokTextField.getText().trim();
        String hargaText = hargaTextField.getText().trim();

        int stok = 0;
        int harga = 0;

        try {
            stok = Integer.parseInt(stokText);
            harga = Integer.parseInt(hargaText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stok dan harga harus berupa angka", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Barang barang = new Barang();
        barang.setKodeBarang(kodeBarang);
        barang.setNamaBarang(namaBarang);
        barang.setStok(stok);
        barang.setHarga(harga);

        controller.insertBarang(barang);

        JOptionPane.showMessageDialog(this, "Data barang berhasil disimpan", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        loadTable();

        namaBarangTextField.setText("");
        stokTextField.setText("");
        hargaTextField.setText("");
        namaBarangTextField.grabFocus();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        String namaBarang = namaBarangTextField.getText();
        int stok = Integer.parseInt(stokTextField.getText());
        int harga = Integer.parseInt(hargaTextField.getText());

        Barang barang = new Barang();
        barang.setKodeBarang(selectedKodeBarang);
        barang.setNamaBarang(namaBarang);
        barang.setStok(stok);
        barang.setHarga(harga);

        controller.updateBarang(barang);
        JOptionPane.showMessageDialog(this, "Sukses Diedit");
        loadTable();
        clearSelection();
        namaBarangTextField.grabFocus();
        disableEditAndDelete();
        simpanButton.setEnabled(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin untuk menghapus barang ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteBarang(selectedKodeBarang);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clearSelection();
            namaBarangTextField.grabFocus();
            disableEditAndDelete();
            simpanButton.setEnabled(true);
        }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void barangTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = barangTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ada", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        simpanButton.setEnabled(false);

        selectedKodeBarang = (String) barangTable.getValueAt(selectedRow, 0);
        Barang barang = controller.getBarangByKodeBarang(selectedKodeBarang);

        namaBarangTextField.setText(barang.getNamaBarang());
        stokTextField.setText(String.valueOf(barang.getStok()));
        hargaTextField.setText(String.valueOf(barang.getHarga()));

        enableEditAndDelete();
    }//GEN-LAST:event_barangTableMouseClicked

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        Utama utama = new Utama();
        utama.setVisible(true);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void clearSelection() {
        namaBarangTextField.setText("");
        stokTextField.setText("");
        hargaTextField.setText("");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTable barangTable;
    private javax.swing.JButton editButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField hargaTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namaBarangTextField;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTextField stokTextField;
    // End of variables declaration//GEN-END:variables

    private void loadTable() {
        List<Barang> barangList = controller.getAllBarang();
        DefaultTableModel model = (DefaultTableModel) barangTable.getModel();
        model.setRowCount(0); // Clear table

        for (Barang barang : barangList) {
            model.addRow(new Object[]{barang.getKodeBarang(), barang.getNamaBarang(), barang.getStok(), barang.getHarga()});
        }
    }

    
}
