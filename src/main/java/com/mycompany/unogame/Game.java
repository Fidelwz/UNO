package com.mycompany.unogame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private int currentPlayer;
    private String[] playerIds;

    private UnoDeck deck;
    private ArrayList<ArrayList<UnoCard>> playerHand;
    private ArrayList<UnoCard> stockpile;

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    private boolean gameDirection; // false = derecha, true = izquierda

    public Game() {}

    public Game(String[] pids) {
        deck = new UnoDeck();
        deck.shuffle();
        stockpile = new ArrayList<>();

        playerIds = pids;
        currentPlayer = 0;
        gameDirection = false;

        playerHand = new ArrayList<>();
        for (String pid : pids) {
            ArrayList<UnoCard> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }

    public void start(Game juego) {
        UnoCard card;
        do {
            card = deck.drawCard();
        } while (card.getValue() == UnoCard.Value.Wild ||
                 card.getValue() == UnoCard.Value.Wild_Four ||
                 card.getValue() == UnoCard.Value.DrawTwo);

        validColor = card.getColor();
        validValue = card.getValue();
        stockpile.add(card);

        if (card.getValue() == UnoCard.Value.Skip) {
            mostrarMensaje(playerIds[currentPlayer] + " fue saltado.");
            avanzarJugador();
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            mostrarMensaje("¡Se invirtió la dirección del juego!");
            gameDirection = !gameDirection;
            currentPlayer = playerIds.length - 1;
        }
    }

    public UnoCard getTopCard() {
        return new UnoCard(validColor, validValue);
    }

    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColor + "_" + validValue + ".png");
    }

    public boolean isGameOver() {
        for (String player : playerIds) {
            if (hasEmptyHand(player)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer() {
        return playerIds[currentPlayer];
    }

    public String[] getPlayers() {
        return playerIds;
    }

    public ArrayList<UnoCard> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    public UnoCard getPlayerCard(String pid, int choice) {
        return getPlayerHand(pid).get(choice);
    }

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public boolean validCardPlay(UnoCard card) {
        return card.getColor() == validColor ||
               card.getValue() == validValue ||
               card.getColor() == UnoCard.Color.Wild;
    }

    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (!playerIds[currentPlayer].equals(pid)) {
            throw new InvalidPlayerTurnException("No es el turno de " + pid, pid);
        }
    }

    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockpile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
        avanzarJugador();
    }

    public void setCardColor(UnoCard.Color color) {
        validColor = color;
    }

   public void submitPlayerCard(String pid, UnoCard card, UnoCard.Color declaredColor)
        throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException {

    checkPlayerTurn(pid);
    ArrayList<UnoCard> pHand = getPlayerHand(pid);

    // 👉 SI LA CARTA ES UN COMODÍN, ACTUALIZAMOS EL COLOR PRIMERO
    if (card.getColor() == UnoCard.Color.Wild) {
        validColor = declaredColor;
        validValue = card.getValue();
    }

    // ✅ VALIDACIÓN GENERAL
    if (!(card.getColor() == validColor ||
          card.getValue() == validValue ||
          card.getColor() == UnoCard.Color.Wild)) {

        mostrarMensaje("¡Jugada inválida! Se esperaba " + validColor + " o " + validValue + ", pero se jugó " + card.getColor() + " " + card.getValue());
        throw new InvalidColorSubmissionException("Carta inválida", card.getColor(), validColor);
    }

    // 👉 Remover carta jugada
    pHand.remove(card);

    // ✅ Actualizar color y valor jugado
    if (card.getColor() == UnoCard.Color.Wild) {
        validColor = declaredColor;
    } else {
        validColor = card.getColor();
    }
    validValue = card.getValue();
    stockpile.add(card);

    // ✅ Verificar victoria
    if (hasEmptyHand(playerIds[currentPlayer])) {
        mostrarMensaje(playerIds[currentPlayer] + " ganó la partida 🎉");
        System.exit(0);
    }

    // 💥 Efectos especiales
    if (card.getValue() == UnoCard.Value.DrawTwo) {
        String nextPlayer = getNextPlayerId();
        getPlayerHand(nextPlayer).add(deck.drawCard());
        getPlayerHand(nextPlayer).add(deck.drawCard());
        mostrarMensaje(nextPlayer + " roba 2 cartas.");
    }

    if (card.getValue() == UnoCard.Value.Wild_Four) {
        String nextPlayer = getNextPlayerId();
        for (int i = 0; i < 4; i++) {
            getPlayerHand(nextPlayer).add(deck.drawCard());
        }
        mostrarMensaje(nextPlayer + " roba 4 cartas.");
    }

    if (card.getValue() == UnoCard.Value.Skip) {
        mostrarMensaje(getNextPlayerId() + " fue saltado.");
        avanzarJugador();
    }

    if (card.getValue() == UnoCard.Value.Reverse) {
        gameDirection = !gameDirection;
        mostrarMensaje("¡Se invirtió la dirección del juego!");
    }

    avanzarJugador();
}

    private void avanzarJugador() {
        if (!gameDirection) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        } else {
            currentPlayer = Math.floorMod(currentPlayer - 1, playerIds.length);
        }
    }

    private String getNextPlayerId() {
        int index;
        if (!gameDirection) {
            index = (currentPlayer + 1) % playerIds.length;
        } else {
            index = Math.floorMod(currentPlayer - 1, playerIds.length);
        }
        return playerIds[index];
    }

    private void mostrarMensaje(String mensaje) {
        JLabel label = new JLabel(mensaje);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        JOptionPane.showMessageDialog(null, label);
    }

    // Excepciones personalizadas
    class InvalidPlayerTurnException extends Exception {
        String playerId;

        public InvalidPlayerTurnException(String message, String pid) {
            super(message);
            playerId = pid;
        }

        public String getPid() {
            return playerId;
        }
    }

    class InvalidColorSubmissionException extends Exception {
        private UnoCard.Color expected;
        private UnoCard.Color actual;

        public InvalidColorSubmissionException(String message, UnoCard.Color actual, UnoCard.Color expected) {
            super(message);
            this.actual = actual;
            this.expected = expected;
        }
    }

    class InvalidValueSubmissionException extends Exception {
        private UnoCard.Value expected;
        private UnoCard.Value actual;

        public InvalidValueSubmissionException(String message, UnoCard.Value actual, UnoCard.Value expected) {
            super(message);
            this.actual = actual;
            this.expected = expected;
        }
    }
}