package View;

import Controller.SerialRXTX;
import java.awt.Image;
//import images.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class InterfaceGrafica extends javax.swing.JFrame {
    
    
    public InterfaceGrafica() throws InterruptedException {
        initComponents();
        //Thread para atualizacao do peso
        Thread iniciaAtualizacao = new Thread(new InterfaceGrafica.atualiza());
        iniciaAtualizacao.start();
    }
    class atualiza implements Runnable {

        @Override
        public void run() {
            
            //Instancia a clase SerialRXTX
            SerialRXTX serial = new SerialRXTX();
            while (true) {
                
                try {

                    //Chama o metodo para comunicacao
                    serial.iniciaSerial();

                    //Retorna a varivel peso lido pelo metodo leituraSerial
                    String inputPeso = serial.leituraSerial();

                    //Criacao do arquivo txt
                    FileWriter arq = new FileWriter("C:\\PESO\\PESO.txt");
                    
                    //Instancia classe PrintWriter para escerver no arquivo PESO.txt
                    PrintWriter gravarArq = new PrintWriter(arq);
                    
                    //Escreve no arquivo PESO.txt
                    gravarArq.print("PESO = " + inputPeso);

                    //Atrubui o valor do peso para o LABEL
                    txt_peso.setText(inputPeso);
                    txt_info_peso.setText("Peso recebido !!");

                    //Fecha o Arquivo
                    arq.close();

                    //Time de 1 segundo para atualização do peso tanto no arquivo quanto no LABEL
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();

                    //Fecha a porta 4800
                    serial.close();
                }
                serial.close();

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_peso = new javax.swing.JLabel();
        txt_info_peso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CERTRIM PESO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_peso.setFont(new java.awt.Font("Arial", 1, 120)); // NOI18N
        txt_peso.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_info_peso.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_info_peso, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_info_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(578, 288));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws InterruptedException {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new InterfaceGrafica().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(InterfaceGrafica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel txt_info_peso;
    public static javax.swing.JLabel txt_peso;
    // End of variables declaration//GEN-END:variables

}
