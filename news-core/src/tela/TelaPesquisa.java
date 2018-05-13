/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import news.core.News;
import news.core.NewsServer;
import news.core.Topic;

/**
 *
 * @author Chen
 */
public class TelaPesquisa extends javax.swing.JFrame {

    DefaultTableModel modelTable = new DefaultTableModel();
    NewsServer server;
    Date initialDate;
    Date finalDate;
    
    private TelaPesquisa() {
        initialDate = new Date();
        finalDate = new Date();
        initComponents();
    }
    
    public TelaPesquisa(NewsServer server) {
        initialDate = new Date();
        finalDate = new Date();
        this.server = server; 
        initComponents();
        // Inicializações para a aceitação de tela
        initTela();
        // Insere as notícias na janela 
        insereNoticias();
    }
        
    // Inicializações de tela para aceitação
    public final void initTela(){   
        Vector<String> opcoes = new Vector<>();
        try {
            // Popula a combo-box com as opções disponíveis
            List<Topic> topics = server.getTopics();
            for(Topic t:topics)
                opcoes.add(t.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Se tem opções disponíveis
        if (!opcoes.isEmpty()){
            jTopicos.setModel(new DefaultComboBoxModel(opcoes));
            jTopicos.setEnabled(true);
        } else{
            jTopicos.setEnabled(false);
        }
        //Colunas da tabela de notícias
        modelTable.addColumn("Tópico");
        modelTable.addColumn("Data");
        modelTable.addColumn("Escritor");
        modelTable.addColumn("Título");
        modelTable.addColumn("Notícia");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jAreaFiltros = new javax.swing.JPanel();
        jTopicos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jFiltrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDataInicial = new javax.swing.JFormattedTextField();
        jDataFinal = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pesquisa de notícias");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTable1.setModel(modelTable);
        jTable1.setEnabled(false);
        jScrollPane2.setViewportView(jTable1);

        jAreaFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jTopicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não há tópicos disponíveis" }));

        jLabel2.setText("a");

        jFiltrar.setText("Filtrar");
        jFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFiltrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Data:");

        jLabel3.setText("Tópico:");

        jDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jDataInicial.setText("01/01/2018");
        jDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDataInicialActionPerformed(evt);
            }
        });

        jDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jDataFinal.setText("31/12/2018");

        javax.swing.GroupLayout jAreaFiltrosLayout = new javax.swing.GroupLayout(jAreaFiltros);
        jAreaFiltros.setLayout(jAreaFiltrosLayout);
        jAreaFiltrosLayout.setHorizontalGroup(
            jAreaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAreaFiltrosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTopicos, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jFiltrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jAreaFiltrosLayout.setVerticalGroup(
            jAreaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAreaFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jAreaFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTopicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jFiltrar)
                    .addComponent(jDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jAreaFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAreaFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Insere as notícias na tabela para exibição
    private void insereNoticias() {
        // Pega a data inicial e final
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            initialDate = (java.util.Date)formatter.parse(jDataInicial.getText());
            finalDate = (java.util.Date)formatter.parse(jDataFinal.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TelaPesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int numRow = modelTable.getRowCount();
        //Remove as linhas da tabela de notícias
        for(int i=numRow - 1; i>=0; i--)
            modelTable.removeRow(i);
        try {
            // Busca os tópicos disponíveis
            List<Topic> topics = server.getTopics();
            for (Topic t: topics){
                if (t.getName().equals(jTopicos.getSelectedItem())){
                    try {
                        for (News n:server.retrieveNews(t.getName(), initialDate, finalDate)){
                            if (n != null){
                                String linha [] = {t.getName(), n.getPublicationDate().toString(), n.getPublisher(), n.getTitle(), n.getContent()};
                                modelTable.addRow(linha);
                            }
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(TelaPesquisa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(TelaPesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFiltrarActionPerformed
        // Insere as notícias na janela 
        insereNoticias();
    }//GEN-LAST:event_jFiltrarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDataInicialActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jAreaFiltros;
    private javax.swing.JFormattedTextField jDataFinal;
    private javax.swing.JFormattedTextField jDataInicial;
    private javax.swing.JButton jFiltrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jTopicos;
    // End of variables declaration//GEN-END:variables

}

