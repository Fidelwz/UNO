/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.unogame;

import com.mycompany.unogame.Game.InvalidPlayerTurnException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author mclovin
 */
public class GameStage extends javax.swing.JFrame {

    private AddPlayerNames aggJugadores = new AddPlayerNames();
    ArrayList<String> temp = new ArrayList<>();
    String[] pids;
    Game game;
    ArrayList<JButton> cardButtons = new ArrayList<JButton>();
    ArrayList<String> cardIds;
    PopUp window;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameStage.class.getName());

    /**
     * Creates new form GameStage
     */
    public GameStage() {
    }

    public GameStage(ArrayList<String> playerIds) {
        initComponents();

        this.temp = playerIds;
        this.pids = temp.toArray(new String[0]);
        this.game = new Game(pids);
        populateArrayList();
        game.start(game);
        setPidName();

        // Aquí usas el código corregido:
        String topCardPath = "src/main/resources/imagenes/Cartas/" + game.getTopCardImage();
        ImageIcon originalIcon = new ImageIcon(topCardPath);

        int width = topCardButton.getWidth();
        int height = topCardButton.getHeight();
        if (width == 0 || height == 0) {
            width = topCardButton.getPreferredSize().width;
            height = topCardButton.getPreferredSize().height;
        }

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
         topCardButton.setBorderPainted(false);
            topCardButton.setContentAreaFilled(false);
            topCardButton.setFocusPainted(false);
            topCardButton.setOpaque(false);
        topCardButton.setIcon(scaledIcon);

        setButtonIcons();


// Mostrar imagen de la carta para robar (deckCard)
String deckCardPath = "src/main/resources/imagenes/UnoBack.png";
ImageIcon deckIcon = new ImageIcon(deckCardPath);

// Dimensiones fijas deseadas
int deckWidth = 185;
int deckHeight = 268;

// Escalar imagen
Image scaledDeckImage = deckIcon.getImage().getScaledInstance(deckWidth, deckHeight, Image.SCALE_SMOOTH);
ImageIcon scaledDeckIcon = new ImageIcon(scaledDeckImage);

// Asignar imagen al botón deckCard
deckCard.setIcon(scaledDeckIcon);

// Ajustar botón visualmente
deckCard.setPreferredSize(new java.awt.Dimension(deckWidth, deckHeight));
deckCard.setMinimumSize(new java.awt.Dimension(deckWidth, deckHeight));
deckCard.setMaximumSize(new java.awt.Dimension(deckWidth, deckHeight));
deckCard.setBorderPainted(false);
deckCard.setContentAreaFilled(false);
deckCard.setFocusPainted(false);
deckCard.setOpaque(false);
        

    }

    public void setButtonIcons() {
        String listString = game.getPlayerHand(game.getCurrentPlayer())
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String[] cardNames = listString.split(",");
        cardIds = new ArrayList<>(Arrays.asList(cardNames));

        for (int i = 0; i < cardButtons.size(); i++) {
            JButton button = cardButtons.get(i);
            if (i < cardIds.size()) {
                String imagePath = "src/main/resources/imagenes/Cartas/" + cardIds.get(i) + ".png";
                ImageIcon originalIcon = new ImageIcon(imagePath);

                int width = button.getPreferredSize().width;
                int height = button.getPreferredSize().height;
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                button.setIcon(scaledIcon);
                button.setVisible(true); // ✅ mostramos solo si hay carta
            } else {
                button.setIcon(null);
                button.setVisible(false); // ✅ ocultamos si no hay carta
            }
        }
    }

    public void populateArrayList() {
        cardButtons.addAll(Arrays.asList(
                jButton1, jButton2, jButton3, jButton4, jButton5,
                jButton6, jButton7, jButton8, jButton9, jButton10,
                jButton11, jButton12
        ));

        Dimension fixedSize = new Dimension(130, 199);
        for (JButton button : cardButtons) {
            button.setPreferredSize(fixedSize);
            button.setMinimumSize(fixedSize);
            button.setMaximumSize(fixedSize);

            // 🔹 Quitamos el borde y el fondo
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);

            // 🔹 Ocultamos el botón hasta que se le asigne una carta
            button.setVisible(false);
        }
    }

    public void setPidName() {
        String currentPlayer = game.getCurrentPlayer();
        pidNameLabel.setText("Cartas de :   " + currentPlayer);
    }

    public void setPidName(String currentPlayer) {
        pidNameLabel.setText("Cartas de: " + currentPlayer);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        pidNameLabel = new javax.swing.JLabel();
        topCardButton = new javax.swing.JButton();
        deckCard = new javax.swing.JButton();
        drawCardButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(130, 199));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        pidNameLabel.setFont(new java.awt.Font("Cantarell", 0, 48)); // NOI18N
        pidNameLabel.setForeground(new java.awt.Color(255, 255, 255));

        topCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topCardButtonActionPerformed(evt);
            }
        });

        deckCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckCardActionPerformed(evt);
            }
        });

        drawCardButton.setBackground(new java.awt.Color(0, 0, 0));
        drawCardButton.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        drawCardButton.setForeground(new java.awt.Color(255, 255, 255));
        drawCardButton.setText("Elegir carta");
        drawCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawCardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(deckCard, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(topCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 500, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pidNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(drawCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(296, 296, 296))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(topCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(deckCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pidNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(drawCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2299, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawCardButtonActionPerformed
        JLabel message = new JLabel("El jugador "+game.getCurrentPlayer() + "  selecciono una carta");
        message.setFont(new Font("Arial", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        try {
            game.submitDraw(game.getCurrentPlayer());

        } catch (InvalidPlayerTurnException ex) {
            Logger.getLogger(GameStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setPidName(game.getCurrentPlayer());
        this.setButtonIcons();

    }//GEN-LAST:event_drawCardButtonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (cardIds.get(6) != null) {
            int index = 6;
            String cardId = cardIds.get(6);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (cardIds.get(0) != null) {
            int index = 0;
            String cardId = cardIds.get(0);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (cardIds.get(1) != null) {
            int index = 1;
            String cardId = cardIds.get(1);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (cardIds.get(2) != null) {
            int index = 2;
            String cardId = cardIds.get(2);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (cardIds.get(3) != null) {
            int index = 3;
            String cardId = cardIds.get(3);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (cardIds.get(4) != null) {
            int index = 4;
            String cardId = cardIds.get(4);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (cardIds.get(5) != null) {
            int index = 5;
            String cardId = cardIds.get(5);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (cardIds.get(7) != null) {
            int index = 7;
            String cardId = cardIds.get(7);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (cardIds.get(8) != null) {
            int index = 8;
            String cardId = cardIds.get(8);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (cardIds.get(9) != null) {
            int index = 9;
            String cardId = cardIds.get(9);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (cardIds.get(10) != null) {
            int index = 10;
            String cardId = cardIds.get(10);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if (cardIds.get(11) != null) {
            int index = 11;
            String cardId = cardIds.get(11);
            window = new PopUp(cardId, game, index, cardButtons, this, topCardButton);
            window.setVisible(true);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void topCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topCardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topCardButtonActionPerformed

    private void deckCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckCardActionPerformed
    }//GEN-LAST:event_deckCardActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GameStage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deckCard;
    private javax.swing.JButton drawCardButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pidNameLabel;
    private javax.swing.JButton topCardButton;
    // End of variables declaration//GEN-END:variables
}
