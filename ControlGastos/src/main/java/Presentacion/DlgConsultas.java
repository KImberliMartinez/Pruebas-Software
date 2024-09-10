package Presentacion;

//import Control.ControlPresentacion;
import Negocio.dtos.IconsultaGastos;
import Negocio.dtos.consultaGastos;
import Negocio.dtos.gastosDTO;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DlgConsultas extends javax.swing.JFrame {

    
    List<gastosDTO> gastos;
    IconsultaGastos p;
    private DefaultTableModel modeloTabla;

    /**
     * Creates new form FrmOpcionesCliente
     */
    public DlgConsultas() {
        p=new consultaGastos();
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOpciones = new javax.swing.JPanel();
        lblTituloSeccion = new javax.swing.JLabel();
        cbxBusqueda = new javax.swing.JComboBox<>();
        txtCoincidencia = new javax.swing.JTextField();
        lblOpciones = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblOpciones1 = new javax.swing.JLabel();
        lblOpciones2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblOpciones3 = new javax.swing.JLabel();
        dateChooserInicio = new com.toedter.calendar.JDateChooser();
        dateChooserFin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelOpciones.setBackground(new java.awt.Color(204, 255, 204));
        panelOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloSeccion.setFont(new java.awt.Font("Amazon Ember", 1, 48)); // NOI18N
        lblTituloSeccion.setForeground(new java.awt.Color(64, 53, 44));
        lblTituloSeccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloSeccion.setText("Consultas");
        panelOpciones.add(lblTituloSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 750, -1));

        cbxBusqueda.setBackground(new java.awt.Color(132, 203, 132));
        cbxBusqueda.setFont(new java.awt.Font("Amazon Ember Light", 0, 18)); // NOI18N
        cbxBusqueda.setForeground(new java.awt.Color(51, 51, 51));
        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "categoria", "monto" }));
        cbxBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 53, 44), 2));
        cbxBusqueda.setFocusable(false);
        cbxBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBusquedaActionPerformed(evt);
            }
        });
        panelOpciones.add(cbxBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 230, 40));

        txtCoincidencia.setBackground(new java.awt.Color(132, 203, 132));
        txtCoincidencia.setFont(new java.awt.Font("Amazon Ember Light", 0, 20)); // NOI18N
        txtCoincidencia.setForeground(new java.awt.Color(51, 51, 51));
        txtCoincidencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(64, 53, 44), 2));
        txtCoincidencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCoincidenciaKeyPressed(evt);
            }
        });
        panelOpciones.add(txtCoincidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 260, 40));

        lblOpciones.setFont(new java.awt.Font("Amazon Ember", 0, 24)); // NOI18N
        lblOpciones.setForeground(new java.awt.Color(64, 53, 44));
        lblOpciones.setText("Periodo:");
        panelOpciones.add(lblOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(0, 153, 0));
        btnCancelar.setFont(new java.awt.Font("Amazon Ember", 0, 24)); // NOI18N
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
        panelOpciones.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 160, 50));

        btnSeleccionar.setBackground(new java.awt.Color(0, 153, 0));
        btnSeleccionar.setFont(new java.awt.Font("Amazon Ember", 0, 24)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Filtrar fecha");
        btnSeleccionar.setBorderPainted(false);
        btnSeleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeleccionar.setFocusable(false);
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        panelOpciones.add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 160, 50));

        lblOpciones1.setFont(new java.awt.Font("Amazon Ember", 0, 36)); // NOI18N
        lblOpciones1.setForeground(new java.awt.Color(64, 53, 44));
        lblOpciones1.setText("Opciones");
        panelOpciones.add(lblOpciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        lblOpciones2.setFont(new java.awt.Font("Amazon Ember", 0, 24)); // NOI18N
        lblOpciones2.setForeground(new java.awt.Color(64, 53, 44));
        lblOpciones2.setText("Busqueda por:");
        panelOpciones.add(lblOpciones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jTabla.setBackground(new java.awt.Color(132, 203, 132));
        jTabla.setFont(new java.awt.Font("Amazon Ember", 0, 12)); // NOI18N
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "Descripcion", "Monto", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTabla);

        panelOpciones.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 670, 100));

        btnAgregar.setText("AgregarNuevo");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        panelOpciones.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panelOpciones.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelOpciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, -1, -1));

        lblOpciones3.setFont(new java.awt.Font("Amazon Ember", 0, 24)); // NOI18N
        lblOpciones3.setForeground(new java.awt.Color(64, 53, 44));
        lblOpciones3.setText("Coincidencia:");
        panelOpciones.add(lblOpciones3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));
        panelOpciones.add(dateChooserInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));
        panelOpciones.add(dateChooserFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Hasta:");
        panelOpciones.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Desde:");
        panelOpciones.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBusquedaActionPerformed
       
    }//GEN-LAST:event_cbxBusquedaActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
                     limpiarTabla();

        Date fechaInicio = dateChooserInicio.getDate();
    Date fechaFin = dateChooserFin.getDate();
        if (fechaInicio != null && fechaFin != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (gastosDTO pn : gastos) {
                    // Formatear fecha de nacimiento
                    String fechaFormateada = sdf.format(pn.getFecha());
                    
                    // Convertir fecha de nacimiento a objeto Date para la comparación
                    try {
                        Date fechaNacimiento = sdf.parse(fechaFormateada);
                        if (fechaNacimiento.compareTo(fechaInicio) >= 0 && fechaNacimiento.compareTo(fechaFin) <= 0) {
                            insertarFila(pn);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el rango de fechas", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
//        int filaSeleccionada = jTabla.getSelectedRow();
//
//        if ( filaSeleccionada != -1) { // Verificar si se ha seleccionado alguna fila
//            Object[] datosFila = new Object[jTabla.getColumnCount()];
//
//            for (int i = 0; i < jTabla.getColumnCount(); i++) {
//                datosFila[i] = jTabla.getValueAt(filaSeleccionada, i);
//            }
//            String rfc=datosFila[1].toString();
//             control.desplegarDlgHistorial(rfc);
//            this.dispose();
            
        //}
       
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         //control.desplegarMenu();
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCoincidenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoincidenciaKeyPressed
        buscarCoincidencias(txtCoincidencia.getText() + evt.getKeyChar());
    }//GEN-LAST:event_txtCoincidenciaKeyPressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        DlgAgregar dl=new DlgAgregar();
        dl.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        DlgModificar dl=new DlgModificar();
         int filaSeleccionada = jTabla.getSelectedRow();

        if ( filaSeleccionada != -1) { // Verificar si se ha seleccionado alguna fila
            Object[] datosFila = new Object[jTabla.getColumnCount()];

            for (int i = 0; i < jTabla.getColumnCount(); i++) {
                datosFila[i] = jTabla.getValueAt(filaSeleccionada, i);
            }
        
            String id=datosFila[0].toString();
            String categoria=datosFila[1].toString();
            String descripcion=datosFila[2].toString();
            String Monto=datosFila[3].toString();
            String Fecha=datosFila[4].toString();
             
             dl.idCambio=id;
             dl.catCambio=categoria;
             dl.desCambio=descripcion;
             dl.montCambio=Monto;
             
             
             
            dl.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
         int filaSeleccionada = jTabla.getSelectedRow();

        if ( filaSeleccionada != -1) { // Verificar si se ha seleccionado alguna fila
            Object[] datosFila = new Object[jTabla.getColumnCount()];

            for (int i = 0; i < jTabla.getColumnCount(); i++) {
                datosFila[i] = jTabla.getValueAt(filaSeleccionada, i);
            }
        
             String id=datosFila[1].toString();
            long num=Long.parseLong(id);
        
        p.Eliminar(num);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void buscarCoincidencias(String texto) {
        limpiarTabla();
        int sele = cbxBusqueda.getSelectedIndex();
        gastos=p.obtenerLista();
         // Obtener las fechas del periodo desde los campos de entrada
    Date fechaInicio = dateChooserInicio.getDate();
    Date fechaFin = dateChooserFin.getDate();
    
        switch (sele) {
            case 0:
                for (gastosDTO pn : gastos) {
                    if (pn.getCategoria().contains(texto)) {
                        insertarFila(pn);
                                

                    }
                }
                break;
            case 1:
                // Buscar por fecha en un rango
            if (fechaInicio != null && fechaFin != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                for (gastosDTO pn : gastos) {
                    // Formatear fecha de nacimiento
                    String fechaFormateada = sdf.format(pn.getFecha());
                    
                    // Convertir fecha de nacimiento a objeto Date para la comparación
                    try {
                        Date fechaNacimiento = sdf.parse(fechaFormateada);
                        if (fechaNacimiento.compareTo(fechaInicio) >= 0 && fechaNacimiento.compareTo(fechaFin) <= 0) {
                            insertarFila(pn);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el rango de fechas", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            break;                
                case 2:
                    for (gastosDTO pn : gastos) {
                    if (pn.getDescripcion().contains(texto)) {
                        insertarFila(pn);
                    }
                }
                break;
                    
            default:
                JOptionPane.showMessageDialog(this, "Busqueda no valida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        

        }

    

    private void limpiarTabla() {
        modeloTabla = (DefaultTableModel) jTabla.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }
    }

    private void insertarFila(gastosDTO p) {
        Object[] fila = {
            p.getId(),
            p.getCategoria(),
            p.getDescripcion(),
            p.getGasto(),
            p.getFecha(),
           
            };
        modeloTabla.addRow(fila);
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
            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgConsultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DlgConsultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private com.toedter.calendar.JDateChooser dateChooserFin;
    private com.toedter.calendar.JDateChooser dateChooserInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabla;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblOpciones1;
    private javax.swing.JLabel lblOpciones2;
    private javax.swing.JLabel lblOpciones3;
    private javax.swing.JLabel lblTituloSeccion;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTextField txtCoincidencia;
    // End of variables declaration//GEN-END:variables

}
