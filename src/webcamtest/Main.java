/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamtest;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private final Dimension ds = new Dimension(640,480); //640,480
    private final Dimension cs = WebcamResolution.VGA.getSize();
    Webcam wCam = Webcam.getDefault();
    WebcamPanel wCamPanel = new WebcamPanel(wCam, WebcamResolution.VGA.getSize(), false);
    public Main() {
        initComponents();
       Dimension[] nonStandardResolutions = new Dimension[] { 
 		WebcamResolution.PAL.getSize(), 
 		WebcamResolution.HD720.getSize(), 
 		new Dimension(2000, 1000), 
 		new Dimension(1000, 500), 
 		}; 
       //wCam.setCustomViewSizes(nonStandardResolutions);
        wCam.setViewSize(cs);
        wCamPanel.setFillArea(true);
        CamView.setLayout(new FlowLayout());
        CamView.add(wCamPanel);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConnectButt = new javax.swing.JButton();
        CamView = new javax.swing.JPanel();
        CaptureButt = new javax.swing.JButton();
        DisconnectButt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        ConnectButt.setText("Connect ");
        ConnectButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectButtMouseClicked(evt);
            }
        });

        CamView.setBackground(new java.awt.Color(255, 255, 255));
        CamView.setOpaque(false);
        CamView.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout CamViewLayout = new javax.swing.GroupLayout(CamView);
        CamView.setLayout(CamViewLayout);
        CamViewLayout.setHorizontalGroup(
            CamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        CamViewLayout.setVerticalGroup(
            CamViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        CaptureButt.setText("Capture !!");
        CaptureButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaptureButtActionPerformed(evt);
            }
        });

        DisconnectButt.setText("Disconnect");
        DisconnectButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisconnectButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ConnectButt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DisconnectButt)
                        .addGap(156, 156, 156)
                        .addComponent(CaptureButt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CamView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CamView, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConnectButt)
                    .addComponent(DisconnectButt)
                    .addComponent(CaptureButt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectButtMouseClicked
       Webcam webcam = Webcam.getDefault(); 
 	if (webcam != null) { 
            JOptionPane.showMessageDialog(this, "Webcam: " + webcam.getName() + " is connected" , "Webcam device", 1);  
  
            Thread t = new Thread() {
           @Override 
           public void run(){
                wCamPanel.start();
           }
          };
         t.setDaemon(true);
         t.start();
         BufferedImage image = webcam.getImage();
 	} else { 
	    System.out.println("No webcam detected"); 
 	} 
       //wCam.open();
    }//GEN-LAST:event_ConnectButtMouseClicked

    private void CaptureButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptureButtActionPerformed
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
            
            File file = new File(String.format("img_%s.jpg", timeStamp));
            BufferedImage image = wCam.getImage();
            ImageIO.write(image, "JPG", file);
            JOptionPane.showMessageDialog(this, "Picture saved at \n " + file.getAbsolutePath(), "Image saved", 1);
        } catch (IOException | HeadlessException e) {
             JOptionPane.showMessageDialog(this, "Error: " + e.getMessage() , "Error occured", 1);       
             
        }
    }//GEN-LAST:event_CaptureButtActionPerformed

    private void DisconnectButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisconnectButtActionPerformed
        wCam.close();
    }//GEN-LAST:event_DisconnectButtActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CamView;
    private javax.swing.JButton CaptureButt;
    private javax.swing.JButton ConnectButt;
    private javax.swing.JButton DisconnectButt;
    // End of variables declaration//GEN-END:variables
}
