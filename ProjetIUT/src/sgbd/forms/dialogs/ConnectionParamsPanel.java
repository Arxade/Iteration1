/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.Document;
import sgbd.controllers.IntTextDocument;

/**
 *
 * @author Kazed
 */
public class ConnectionParamsPanel extends javax.swing.JPanel {
    private final HashMap<String, String> params;
    /**
     * Creates new form ConnectionParams
     * @param params
     */
    public ConnectionParamsPanel(HashMap<String, String> params) {
        initComponents();
        this.params = params;
        if(!params.isEmpty()) {
            txtHost.setText(params.get("Host"));
            txtPort.setText(params.get("Port"));
            txtDb.setText(params.get("Database"));
        }
    }
    
    public JTextField getInput(String param) {
        switch(param) {
            case "Host" : return txtHost;
            case "Port" : return txtPort;
            case "Database" : return txtDb;
            default : return null;
        }
    }
    
    public JButton getButton(String s) {
        switch(s.toLowerCase()) {
            case "confirm":
                return btnConfirm;
            case "cancel":
                return btnCancel;
            default:
                return null;
        }
    }
    
    public void setComponentsParams(Font f) {
        for(Component comp : lpnContainer.getComponents()) {
            boolean setFont = true;
            switch(comp.getClass().getSimpleName()) {
                case "JButton":
                    if(comp.getName().contains("Error")) {
                        comp.setVisible(false);
                    }
                    else comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    break;
                case "JTextField":
                    
                    break;
                default:
                    break;
            }
            if(setFont)comp.setFont(f);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lpnContainer = new javax.swing.JLayeredPane();
        btnConfirm = new javax.swing.JButton();
        lblHost = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblDb = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        txtDb = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(320, 218));

        btnConfirm.setText("Confirmer");
        btnConfirm.setName("btnConfirm"); // NOI18N
        btnConfirm.setPreferredSize(new java.awt.Dimension(96, 32));

        lblHost.setText("Hôte");

        lblPort.setText("Port");

        lblDb.setText("Base de données");

        txtHost.setPreferredSize(new java.awt.Dimension(96, 19));

        txtPort.setColumns(5);
        txtPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Document document = new IntTextDocument(0, 65535);
        txtPort.setDocument(document);

        txtDb.setPreferredSize(new java.awt.Dimension(96, 19));

        btnCancel.setText("Annuler");
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.setPreferredSize(new java.awt.Dimension(96, 32));

        lpnContainer.setLayer(btnConfirm, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblHost, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblPort, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblDb, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(txtHost, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(txtPort, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(txtDb, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnCancel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnContainerLayout = new javax.swing.GroupLayout(lpnContainer);
        lpnContainer.setLayout(lpnContainerLayout);
        lpnContainerLayout.setHorizontalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnContainerLayout.createSequentialGroup()
                        .addComponent(lblHost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lpnContainerLayout.createSequentialGroup()
                        .addComponent(lblPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnContainerLayout.createSequentialGroup()
                        .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(lpnContainerLayout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lpnContainerLayout.createSequentialGroup()
                                .addComponent(lblDb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );
        lpnContainerLayout.setVerticalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHost)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDb)
                    .addComponent(txtDb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lpnContainer)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lpnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblDb;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLayeredPane lpnContainer;
    private javax.swing.JTextField txtDb;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
