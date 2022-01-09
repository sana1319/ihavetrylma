package Server;

import java.util.ArrayList;

public class Game {

    protected int goalPlayers = 0;

    protected int current;

    private static final int side = 5;
    private static final int height = 17;
    private static final int width = 25;

    GameBoard gameBoard;

    ArrayList<Player> players = new ArrayList<>();

    protected void setGoalPlayers(int goalPlayers) {
        this.goalPlayers = goalPlayers;
    }

    protected int getGoalPlayers() {
        return goalPlayers;
    }

    protected int currentPlayers() {
        return players.size();
    }

    private Player getPlayer(int id) {
        return players.get(id);
    }

    protected void assignColors() {
        int size = currentPlayers();
        switch (goalPlayers) {
            case 2 -> {
                for (int i = 0; i < size; i++) {
                    getPlayer(i).setColor(i * 3);
                }
            }
            case 3 -> {
                for (int i = 0; i < size; i++) {
                    getPlayer(i).setColor(i + i + 1);
                }
            }
            case 4 -> {
                for (int i = 0; i < size / 2; i++) {
                    getPlayer(i).setColor(i + 1);
                    getPlayer(size - i - 1).setColor(5 - i);
                }
            }
            case 6 -> {
                for (int i = 0; i < size; i++) {
                    getPlayer(i).setColor(i);
                }
            }
        }
    }

    protected void randomPlayer() {
        this.current = (int) Math.floor(Math.random() * (goalPlayers));
        getPlayer(current).setTurn(true);
    }

    protected void nextPlayer() {
        getPlayer(current).setTurn(false);
        getPlayer(current).sendMessage("END_OF_TURN");
        getPlayer(current).sendMessage("TEXT");

        int active = 0;
        for (Player p : players) {
            if (!p.getWon()) {
                active++;
            }
        }

        current = (current + 1) % active;
        getPlayer(current).setTurn(true);
        getPlayer(current).sendMessage("YOUR_TURN");
        getPlayer(current).sendMessage("TEXT");
    }

    protected void makeBoard() {
        GameTile[][] tile = new GameTile[width][height];
        gameBoard = new GameBoard(width, height, tile, goalPlayers, side);
        gameBoard.createTiles();
        gameBoard.makePieces();
    }

    protected boolean moveValidation(String command) {
        String[] move = command.split(" ");
        int x1 = Integer.parseInt(move[1]);
        int y1 = Integer.parseInt(move[2]);
        int x2 = Integer.parseInt(move[3]);
        int y2 = Integer.parseInt(move[4]);

        return gameBoard.makeMove(x1, y1, x2, y2);
    }

    protected void sendToAll(String command) {
        for (Player p : players) {
            p.sendMessage(command);
        }
    }
}