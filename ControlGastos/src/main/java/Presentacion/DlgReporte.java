package Presentacion;


import SistemaControlGastos.Negocio.gastosDTO;
import SistemaReporte.Negocio.Pintar;
import SistemaReporte.Negocio.ConsultaReporte;
import SistemaReporte.Negocio.IConsultaReporte;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import java.util.Map;
import java.awt.BorderLayout;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JPanel;
import org.jfree.chart.plot.PlotOrientation;


public class DlgReporte extends javax.swing.JFrame {
IConsultaReporte consulta = new ConsultaReporte();
 long idUsuario;
    /**
     * Creates new form FrmOpcionesCliente
     */
    public DlgReporte() {
        initComponents();
      Pintar imagen = new Pintar();
      this.setLocationRelativeTo(this);//
     imagen.PintarImagen(Alimento, "src\\\\main\\\\java\\\\Comida.jpg");
     imagen.PintarImagen(Transporte, "src\\\\main\\\\java\\\\transporte.jpg");
     imagen.PintarImagen(Entretenimiento, "src\\\\main\\\\java\\\\entretenimiento.jpg");
     imagen.PintarImagen(TotalFoto, "src\\\\main\\\\java\\\\Total.jpeg");

    TextoTotalEntretenimiento.setText("0.0");
    TextoTotalAlimentacion.setText("0.0");
    TextoTotalTransporte.setText("0.0");
        this.setVisible(true);
        //centrarVentana(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblOpciones = new javax.swing.JLabel();
        dateChooserFin = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        panelOpciones = new javax.swing.JPanel();
        lblTituloSeccion = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnReporteSemanal = new javax.swing.JButton();
        btnReporteMensual = new javax.swing.JButton();
        btnLimpiarHistograma = new javax.swing.JButton();
        Entretenimiento = new javax.swing.JLabel();
        Alimento = new javax.swing.JLabel();
        Transporte = new javax.swing.JLabel();
        TextoTotalEntretenimiento = new javax.swing.JTextField();
        TextoTotalAlimentacion = new javax.swing.JTextField();
        TextoTotalTransporte = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblOpciones1 = new javax.swing.JLabel();
        dateChooserFin1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        PanelHistograma1 = new javax.swing.JPanel();
        TotalFoto = new javax.swing.JLabel();
        TextoTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOpciones.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N
        lblOpciones.setForeground(new java.awt.Color(204, 204, 204));
        lblOpciones.setText("Periodo");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 2, 18)); // NOI18N
        jLabel8.setText("Hasta:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelOpciones.setBackground(new java.awt.Color(204, 204, 204));
        panelOpciones.setMaximumSize(new java.awt.Dimension(772, 440));
        panelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloSeccion.setBackground(new java.awt.Color(51, 51, 51));
        lblTituloSeccion.setFont(new java.awt.Font("Segoe UI Black", 2, 48)); // NOI18N
        lblTituloSeccion.setForeground(new java.awt.Color(71, 100, 104));
        lblTituloSeccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloSeccion.setText("Reporte");
        panelOpciones.add(lblTituloSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, -10, 350, 70));

        btnCancelar.setBackground(new java.awt.Color(0, 204, 153));
        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setFocusable(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelOpciones.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        btnReporteSemanal.setBackground(new java.awt.Color(0, 204, 153));
        btnReporteSemanal.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnReporteSemanal.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteSemanal.setText("Semanal");
        btnReporteSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteSemanalActionPerformed(evt);
            }
        });
        panelOpciones.add(btnReporteSemanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 120, 40));

        btnReporteMensual.setBackground(new java.awt.Color(0, 204, 153));
        btnReporteMensual.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnReporteMensual.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteMensual.setText("Mensual");
        btnReporteMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMensualActionPerformed(evt);
            }
        });
        panelOpciones.add(btnReporteMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 120, 40));

        btnLimpiarHistograma.setBackground(new java.awt.Color(0, 204, 153));
        btnLimpiarHistograma.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        btnLimpiarHistograma.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarHistograma.setText("Limpiar");
        btnLimpiarHistograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarHistogramaActionPerformed(evt);
            }
        });
        panelOpciones.add(btnLimpiarHistograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 120, 40));

        Entretenimiento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOpciones.add(Entretenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 120, 110));

        Alimento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOpciones.add(Alimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 120, 110));

        Transporte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOpciones.add(Transporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 120, 110));

        TextoTotalEntretenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoTotalEntretenimientoActionPerformed(evt);
            }
        });
        panelOpciones.add(TextoTotalEntretenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 120, 30));

        TextoTotalAlimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoTotalAlimentacionActionPerformed(evt);
            }
        });
        panelOpciones.add(TextoTotalAlimentacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, 30));

        TextoTotalTransporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoTotalTransporteActionPerformed(evt);
            }
        });
        panelOpciones.add(TextoTotalTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 120, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel1.setText("Entretenimiento");
        panelOpciones.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel7.setText("Alimentación");
        panelOpciones.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel4.setText("Transporte");
        panelOpciones.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        lblOpciones1.setBackground(new java.awt.Color(102, 102, 102));
        lblOpciones1.setFont(new java.awt.Font("Segoe UI Black", 2, 24)); // NOI18N
        lblOpciones1.setForeground(new java.awt.Color(102, 102, 102));
        lblOpciones1.setText("Fecha");
        panelOpciones.add(lblOpciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));
        panelOpciones.add(dateChooserFin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 2, 18)); // NOI18N
        jLabel9.setText("Inicio");
        panelOpciones.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, -1, -1));

        PanelHistograma1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelHistograma1Layout = new javax.swing.GroupLayout(PanelHistograma1);
        PanelHistograma1.setLayout(PanelHistograma1Layout);
        PanelHistograma1Layout.setHorizontalGroup(
            PanelHistograma1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelHistograma1Layout.setVerticalGroup(
            PanelHistograma1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelOpciones.add(PanelHistograma1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 520, 170));

        TotalFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOpciones.add(TotalFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 120, 110));

        TextoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextoTotalActionPerformed(evt);
            }
        });
        panelOpciones.add(TextoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 120, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel10.setText("Total");
        panelOpciones.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         //control.desplegarMenu();
        DlgConsultas dlg=new DlgConsultas(idUsuario);
        //dlg.idUsuarioRecibido=idUsuario;
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnReporteSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSemanalActionPerformed
   JOptionPane.showMessageDialog(this, "El reporte se mostrara apartir de la fecha seleccionada hasta 6 dias despues", "Aviso", JOptionPane.INFORMATION_MESSAGE);
// 1. Obtener la lista de gastos para el periodo semanal
    Date fechaSeleccionada = dateChooserFin1.getDate();
    // Aquí se llama al nuevo método que solo recibe una fecha
List<gastosDTO> gastos = consulta.listaPorPeriodoSemanal(fechaSeleccionada,idUsuario);

    // 2. Inicializar las variables para acumular los totales por categoría
    double totalEntretenimiento = 0;
    double totalAlimentacion = 0;
    double totalTransporte = 0;
    double SumaTotal = 0;
    // 3. Calcular los totales recorriendo la lista de gastos
    for (gastosDTO gasto : gastos) {
        switch (gasto.getCategoria()) {
            case "Ocio":
                totalEntretenimiento += gasto.getGasto();
                break;
            case "Alimentacion":
                totalAlimentacion += gasto.getGasto();
                break;
            case "Transporte":
                totalTransporte += gasto.getGasto();
                break;
        }
    }
SumaTotal = totalEntretenimiento + totalAlimentacion + totalTransporte;
    // 4. Actualizar los campos de texto con los totales calculados
    TextoTotalEntretenimiento.setText(String.valueOf(totalEntretenimiento));
    TextoTotalAlimentacion.setText(String.valueOf(totalAlimentacion));
    TextoTotalTransporte.setText(String.valueOf(totalTransporte));
    TextoTotal.setText(String.valueOf(SumaTotal));

   // 1. Crear el histograma a partir de la lista de gastos
Map<String, Double> histograma = crearHistograma(gastos);

// 2. Crear el gráfico de barras a partir del histograma
JPanel graficoPanel = crearGraficoDeBarras(histograma);

// 3. Agregar el panel del gráfico a un JPanel específico (por ejemplo, PanelGraficoDeBarras)
PanelHistograma1.removeAll(); // Eliminar cualquier componente previo en el panel
PanelHistograma1.setLayout(new BorderLayout()); // Establecer el layout
PanelHistograma1.add(graficoPanel, BorderLayout.CENTER); // Añadir el ChartPanel al JPanel
PanelHistograma1.revalidate(); // Revalidar el panel para asegurarse de que se actualice visualmente
PanelHistograma1.repaint(); // Repintar el panel si es necesario
    }//GEN-LAST:event_btnReporteSemanalActionPerformed

    private void btnReporteMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMensualActionPerformed
   JOptionPane.showMessageDialog(this, "El reporte se mostrara apartir de la fecha seleccionada hasta 30 dias despues", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        Date fechaSeleccionada = dateChooserFin1.getDate();
    // Aquí se llama al nuevo método que solo recibe una fecha
List<gastosDTO> gastos = consulta.listaPorPeriodoMensual(fechaSeleccionada,idUsuario);


    // 2. Inicializar las variables para acumular los totales por categoría
    double totalEntretenimiento = 0;
    double totalAlimentacion = 0;
    double totalTransporte = 0;
    double SumaTotal = 0;

    // 3. Calcular los totales recorriendo la lista de gastos
    for (gastosDTO gasto : gastos) {
        switch (gasto.getCategoria()) {
            case "Ocio":
                totalEntretenimiento += gasto.getGasto();
                break;
            case "Alimentacion":
                totalAlimentacion += gasto.getGasto();
                break;
            case "Transporte":
                totalTransporte += gasto.getGasto();
                break;
        }
    }
SumaTotal = totalEntretenimiento + totalAlimentacion + totalTransporte;

    // 4. Actualizar los campos de texto con los totales calculados
    TextoTotalEntretenimiento.setText(String.valueOf(totalEntretenimiento));
    TextoTotalAlimentacion.setText(String.valueOf(totalAlimentacion));
    TextoTotalTransporte.setText(String.valueOf(totalTransporte));
    TextoTotal.setText(String.valueOf(SumaTotal));
     // 1. Crear el histograma a partir de la lista de gastos
Map<String, Double> histograma = crearHistograma(gastos);

// 2. Crear el gráfico de barras a partir del histograma
JPanel graficoPanel = crearGraficoDeBarras(histograma);

// 3. Agregar el panel del gráfico a un JPanel específico (por ejemplo, PanelGraficoDeBarras)
PanelHistograma1.removeAll(); // Eliminar cualquier componente previo en el panel
PanelHistograma1.setLayout(new BorderLayout()); // Establecer el layout
PanelHistograma1.add(graficoPanel, BorderLayout.CENTER); // Añadir el ChartPanel al JPanel
PanelHistograma1.revalidate(); // Revalidar el panel para asegurarse de que se actualice visualmente
PanelHistograma1.repaint(); // Repintar el panel si es necesario
                           
    }//GEN-LAST:event_btnReporteMensualActionPerformed
   /**
     * Centra la ventana en la pantalla.
     * @param frame La ventana que se desea centrar.
     */
    public static void centrarVentana(JFrame frame) {
        // Obtiene la dimensión de la pantalla
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension pantalla = toolkit.getScreenSize();
        
        // Obtiene la dimensión de la ventana
        Dimension ventana = frame.getSize();
        
        // Calcula la posición x e y para centrar la ventana
        int x = (pantalla.width - ventana.width) / 2;
        int y = (pantalla.height - ventana.height) / 2;
        
        // Establece la posición de la ventana
        frame.setLocation(x, y);
    }
    private void btnLimpiarHistogramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarHistogramaActionPerformed
 TextoTotalEntretenimiento.setText("0.0");
    TextoTotalAlimentacion.setText("0.0");
    TextoTotalTransporte.setText("0.0");
    TextoTotal.setText("0.0");
    PanelHistograma1.removeAll(); // Elimina todos los componentes del panel
    PanelHistograma1.revalidate(); // Revalida el panel para asegurar que se actualice visualmente
    PanelHistograma1.repaint(); // Repinta el panel por si es necesario
    }//GEN-LAST:event_btnLimpiarHistogramaActionPerformed

    private void TextoTotalEntretenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoTotalEntretenimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoTotalEntretenimientoActionPerformed

    private void TextoTotalTransporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoTotalTransporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoTotalTransporteActionPerformed

    private void TextoTotalAlimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoTotalAlimentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoTotalAlimentacionActionPerformed

    private void TextoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoTotalActionPerformed
 //Método para crear un histograma a partir de la lista de gastos
private Map<String, Double> crearHistograma(List<gastosDTO> gastos) {
    Map<String, Double> histograma = new HashMap<>();
    for (gastosDTO gasto : gastos) {
        histograma.put(gasto.getCategoria(),
                histograma.getOrDefault(gasto.getCategoria(), 0.0) + gasto.getGasto());
    }
    return histograma;
}
private ChartPanel crearGraficoDeBarras(Map<String, Double> histograma) {
    // Crear el dataset para el gráfico
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Map.Entry<String, Double> entry : histograma.entrySet()) {
        dataset.addValue(entry.getValue(), "Gastos", entry.getKey());
    }

     // Crear el gráfico de barras (horizontal)
    JFreeChart chart = ChartFactory.createBarChart(
            "Distribución de Gastos", // Título del gráfico
            "Monto",                  // Etiqueta del eje X (ahora representa los valores)
            "Categoría",              // Etiqueta del eje Y (ahora representa las categorías)
            dataset,
            PlotOrientation.HORIZONTAL, // Especificar que el gráfico es horizontal
            true,                      // Mostrar leyenda
            true,                      // Usar tooltips
            false                      // Generar URLs
    );

    // Crear y devolver un ChartPanel con el gráfico
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(30, 260));
    return chartPanel;
}

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DlgConsultas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Alimento;
    private javax.swing.JLabel Entretenimiento;
    private javax.swing.JPanel PanelHistograma1;
    private javax.swing.JTextField TextoTotal;
    private javax.swing.JTextField TextoTotalAlimentacion;
    private javax.swing.JTextField TextoTotalEntretenimiento;
    private javax.swing.JTextField TextoTotalTransporte;
    private javax.swing.JLabel TotalFoto;
    private javax.swing.JLabel Transporte;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpiarHistograma;
    private javax.swing.JButton btnReporteMensual;
    private javax.swing.JButton btnReporteSemanal;
    private com.toedter.calendar.JDateChooser dateChooserFin;
    private com.toedter.calendar.JDateChooser dateChooserFin1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblOpciones1;
    private javax.swing.JLabel lblTituloSeccion;
    private javax.swing.JPanel panelOpciones;
    // End of variables declaration//GEN-END:variables

}
