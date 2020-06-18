/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import controlador.controlador;
import java.util.ArrayList;
import modelo.votacion;

/**
 *
 * @author Eddie
 */
public class grafico extends javax.swing.JFrame {
    controlador crt = new controlador();

    /**
     * Creates new form grafico
     */
    public grafico() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new grafico().setVisible(true);
            }
        });
    }
            public void GraficoSexCom(String comuna){

            ArrayList<votacion> lista = crt.Grafico_Sex_Com("Presidencial 2020",comuna);
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Genero", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            
        }
            public void GraficoSexReg(String region){

            ArrayList<votacion> lista = crt.Grafico_Sex_Reg("Presidencial 2020",region);
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Genero", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            
        }
            public void GraficoSexGen(){

            ArrayList<votacion> lista = crt.Grafico_Sex_Gen("Presidencial 2020");
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Genero", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            
        }
            public void GraficoEdadCom(String comuna){

            ArrayList<votacion> lista = crt.Grafico_edad_Com("Presidencial 2020",comuna);
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Edad", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            }
            
            public void GraficoEdadReg(String comuna){

            ArrayList<votacion> lista = crt.Grafico_edad_Reg("Presidencial 2020",comuna);
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Edad", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            }
            public void GraficoEdadGen(){

            ArrayList<votacion> lista = crt.Grafico_edad_Gen("Presidencial 2020");
            DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
            
            for(votacion tmp : lista){
            dtsc.setValue(tmp.getId_opcion(), tmp.getNombre_votacion(), tmp.getOpcion_voto());}
            
            JFreeChart ch = ChartFactory.createBarChart("Grafico de votos", "Edad", "Cantidad", dtsc,PlotOrientation.VERTICAL, true, true, false);
            ChartPanel cp = new ChartPanel(ch);
            add(cp);
            cp.setBounds(50,40,500,400);
            }
                

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
