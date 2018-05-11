/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import news.core.News;
import news.core.Topic;
import news.core.User;
import news.core.NewsServer;

/**
 *
 * @author Chen
 */
public class TelaPublic extends javax.swing.JFrame {

    NewsServer server;
    User logUser;
    /**
     * Creates new form TelaPublic
     */
    public TelaPublic() {
        initComponents();
    }

    TelaPublic(NewsServer serv, User user) {
        initComponents();
        this.server = serv; 
        this.logUser = user;
        //Desabilita os campos até que haja dados para exibição
        jTopic.setEnabled(false);
        jTitle.setEnabled(false);
        jMensagem.setEnabled(false);
        jPublicar.setEnabled(false);
        // Popula a combo-box com as opções disponíveis
        try {
            List<Topic> topics = server.getTopics();
            Vector<String> entries = new Vector<>();
            topics.forEach((t) -> {entries.add(t.getName());});
            if (!entries.isEmpty()){
                jTopic.setModel(new DefaultComboBoxModel(entries));
                jTopic.setEnabled(true);
                jTitle.setEnabled(true);
                jMensagem.setEnabled(true);
                jPublicar.setEnabled(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TelaInscricao.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMensagem = new javax.swing.JTextArea();
        jPublicar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTopic = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTitle = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Publicacao");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nova publicação"));

        jMensagem.setColumns(20);
        jMensagem.setRows(5);
        jMensagem.setText("A copa do mundo 2018 será na Russia.");
        jMensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jMensagemKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jMensagem);

        jPublicar.setText("Publicar");
        jPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPublicarActionPerformed(evt);
            }
        });

        jLabel1.setText("Tópico:");

        jTopic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTopicActionPerformed(evt);
            }
        });

        jLabel2.setText("Título:");

        jTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPublicar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTitle)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPublicar)
                    .addComponent(jLabel1)
                    .addComponent(jTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTopicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTopicActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void jPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPublicarActionPerformed
        // TODO add your handling code here:
        Topic topic = null;
	News news = new News(jTitle.getText(), logUser);
        try {
            List<Topic> topicList = server.getTopics();
            for(int i=0; i < topicList.size(); i++){
                if(topicList.get(i).getName().equals(jTopic.getSelectedItem())){
                    topic = topicList.get(i);
                    break;
                }
            }
            server.addNews(news, topic);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPublic.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_jPublicarActionPerformed

    private void jTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTitleActionPerformed

    private void jMensagemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMensagemKeyTyped
        // TODO add your handling code here:
        // limita 180 caracteres
        if (jMensagem.getText().length() >= 180 ){
                evt.consume();
        }
    }//GEN-LAST:event_jMensagemKeyTyped

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
            java.util.logging.Logger.getLogger(TelaPublic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPublic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPublic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPublic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPublic().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextArea jMensagem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jPublicar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTitle;
    private javax.swing.JComboBox<String> jTopic;
    // End of variables declaration//GEN-END:variables
}
