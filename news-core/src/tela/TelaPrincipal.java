/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import news.core.News;
import news.core.NewsServer;
import news.core.Topic;
import news.core.User;

/**
 *
 * @author Chen
 */
public class TelaPrincipal extends javax.swing.JFrame {

    DefaultTableModel modelTable = new DefaultTableModel();
    NewsServer server;
    String username;
    
    private TelaPrincipal() {
        initComponents();
    }
    
    public TelaPrincipal(NewsServer server, String username) throws RemoteException, NotBoundException {
        this.server = server; 
        this.username = username;
        initComponents();
        initTela();
    }
        
    // Inicializações de tela para aceitação
    public final void initTela(){   
        User logUser = null;
        try {
            // Busca o usuário
            logUser = server.getUserByName(username);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Se não tem usuário logado
        if (logUser == null){
            //Não exibe o campo de nome de usuário
            jUserName.setVisible(false);
            jUserLabel.setVisible(false);
            // Desabilita botões que nao poderão ser acessados por usuários anônimos
            jPublicacao.setEnabled(false);
            jTopico.setEnabled(false);
            jInscricao.setEnabled(false);
        }else{
            // Adiciona o nome do usuário
            jUserName.setText(username);
            //Desabilita os botões que são exclusivos para escritores
            if(!logUser.isPublisher()){
                jPublicacao.setEnabled(false);
                jTopico.setEnabled(false);
            }
        }
        //Colunas da tabela de notícias
        modelTable.addColumn("Tópico");
        modelTable.addColumn("Data");
        modelTable.addColumn("Escritor");
        modelTable.addColumn("Notícia");                
        // Insere as notícias na janela principal
        insereNoticias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPesquisa = new javax.swing.JButton();
        jInscricao = new javax.swing.JButton();
        jPublicacao = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jUserLabel = new javax.swing.JLabel();
        jUserName = new javax.swing.JTextField();
        jTopico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela inicial");
        setPreferredSize(new java.awt.Dimension(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPesquisa.setText("Pesquisar");
        jPesquisa.setMaximumSize(new java.awt.Dimension(83, 23));
        jPesquisa.setMinimumSize(new java.awt.Dimension(83, 23));
        jPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPesquisaActionPerformed(evt);
            }
        });

        jInscricao.setText("Inscrição");
        jInscricao.setMaximumSize(new java.awt.Dimension(83, 23));
        jInscricao.setMinimumSize(new java.awt.Dimension(83, 23));
        jInscricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInscricaoActionPerformed(evt);
            }
        });

        jPublicacao.setText("Publicar");
        jPublicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPublicacaoActionPerformed(evt);
            }
        });

        jTable1.setModel(modelTable);
        jTable1.setEnabled(false);
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Últimas notícias");

        jUserLabel.setText("Usuário");

        jUserName.setEditable(false);

        jTopico.setText("Tópicos");
        jTopico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTopicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTopico, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jUserLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPublicacao)
                    .addComponent(jUserLabel)
                    .addComponent(jUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTopico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jInscricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInscricaoActionPerformed
        // Cria janela para inscrição de tópicos
        TelaInscricao tela = new TelaInscricao(server, username);
        tela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tela.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
            // Insere as notícias na janela principal
                insereNoticias();
            }
        });
        tela.setVisible(true);    }//GEN-LAST:event_jInscricaoActionPerformed

    private void jPublicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPublicacaoActionPerformed
        // Cria janela para aceitar nova publicação
        TelaPublic tela = new TelaPublic(server, username);
        tela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tela.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
            // Insere as notícias na janela principal
                insereNoticias();
            }
        });
        tela.setVisible(true);
    }//GEN-LAST:event_jPublicacaoActionPerformed

    // Insere as notícias na tabela para exibição
    private void insereNoticias() {
        //Remove as linhas da tabela de notícias
        for(int i=1; i<modelTable.getRowCount(); i++){
            modelTable.removeRow(i);
        }
        try {
            // Busca os tópicos disponíveis
            List<Topic> topics = server.getTopics();
            topics.forEach((t) -> {
                try {
                    if (topicoValido(t)){
                        try {
                            News n = server.retrieveLastNews(t);
                            if (n != null){
                                String linha [] = {t.getName(), n.getPublicationDate().toString(), n.getPublisher(), n.getContent()};
                                modelTable.addRow(linha);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Valida o tópico para inserção na tela principal
    private boolean topicoValido(Topic t) throws RemoteException {
        // Retorna o usuário logado no servidor
        User logUser = server.getUserByName(username);
        // Se entrar com usuário anônimo, todos os tópicos são válidos
        if (logUser == null)
            return true;
        // Retorna se o usuário está inscrito neste tópico
        return logUser.isSubscribed(t);
    }

    private void jPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPesquisaActionPerformed

    private void jTopicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTopicoActionPerformed
        // Cria janela cadastrar tópicos
        TelaTopic tela = new TelaTopic(server);
        tela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        tela.setVisible(true);
    }//GEN-LAST:event_jTopicoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

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
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jInscricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jPesquisa;
    private javax.swing.JButton jPublicacao;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jTopico;
    private javax.swing.JLabel jUserLabel;
    private javax.swing.JTextField jUserName;
    // End of variables declaration//GEN-END:variables

}

