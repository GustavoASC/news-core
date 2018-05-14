/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import news.core.NewsServer;
import news.core.Topic;
import news.core.User;

/**
 *
 * @author Chen
 */
public class TelaInscricao extends javax.swing.JFrame {

    NewsServer server;
    String username;
    
    public TelaInscricao() {
        initComponents();
    }
    
    public TelaInscricao(NewsServer server, String username) {
        this.server = server;
        this.username = username;
        initComponents();
        initTela();
    }
    
    // Inicializações de tela para aceitação
    public final void initTela(){
        //Desabilita os campos até que haja dados para exibição
        jComboInsc.setEnabled(false);
        jInscrever.setEnabled(false);
        try {
            // Busca o usuário
            User user = server.getUserByName(username);
            // Se não encontrou o usuário
            if (user == null)
                return;
            // Popula a combo-box com as opções disponíveis
            List<Topic> topics = this.server.getTopics();
            Vector<String> opcoes = new Vector<>();
            String topicosInscritos = "";
            for(Topic t:topics){
                if (!user.isSubscribed(t)){
                   opcoes.add(t.getName());
                }else{
                    if (!topicosInscritos.isEmpty())
                        topicosInscritos = topicosInscritos + ", ";
                    topicosInscritos = topicosInscritos + t.getName();
                }
            }
            if (topicosInscritos.isEmpty())
                topicosInscritos = "Nenhuma inscrição";
            // Se não tem opções disponíveis
            if (!opcoes.isEmpty()){
                jComboInsc.setModel(new DefaultComboBoxModel(opcoes));
                jComboInsc.setEnabled(true);
                jInscrever.setEnabled(true);
            }
            // Exibe as inscrições atuais
            jTopicosInscritos.setText(topicosInscritos);
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

        jInscricao = new javax.swing.JPanel();
        jInscrever = new javax.swing.JButton();
        jComboInsc = new javax.swing.JComboBox<>();
        jInsc = new javax.swing.JLabel();
        jInscricao1 = new javax.swing.JPanel();
        jTopicosInscritos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inscrição");

        jInscricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nova inscrição"));

        jInscrever.setText("Inscrever-se");
        jInscrever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInscreverActionPerformed(evt);
            }
        });

        jComboInsc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não há tópicos disponíveis para inscrição" }));

        javax.swing.GroupLayout jInscricaoLayout = new javax.swing.GroupLayout(jInscricao);
        jInscricao.setLayout(jInscricaoLayout);
        jInscricaoLayout.setHorizontalGroup(
            jInscricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInscricaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboInsc, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInscrever, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jInscricaoLayout.setVerticalGroup(
            jInscricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInscricaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInscricaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboInsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jInscrever))
                .addContainerGap())
        );

        jInscricao1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tópicos inscritos"));

        jTopicosInscritos.setText("Inscrito: ");

        javax.swing.GroupLayout jInscricao1Layout = new javax.swing.GroupLayout(jInscricao1);
        jInscricao1.setLayout(jInscricao1Layout);
        jInscricao1Layout.setHorizontalGroup(
            jInscricao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInscricao1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTopicosInscritos, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jInscricao1Layout.setVerticalGroup(
            jInscricao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInscricao1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTopicosInscritos)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(501, 501, 501)
                                .addComponent(jInsc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jInscricao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jInscricao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jInsc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jInscricao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jInscreverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInscreverActionPerformed
        try {
            Topic topic = server.getTopicByName((String) jComboInsc.getSelectedItem());
            if (topic!=null){
                try {
                    server.subscribe(username, topic);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());                    
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPublic.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jInscreverActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInscricao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInscricao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboInsc;
    private javax.swing.JLabel jInsc;
    private javax.swing.JButton jInscrever;
    private javax.swing.JPanel jInscricao;
    private javax.swing.JPanel jInscricao1;
    private javax.swing.JLabel jTopicosInscritos;
    // End of variables declaration//GEN-END:variables
}
