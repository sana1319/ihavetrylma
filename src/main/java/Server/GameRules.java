package Server;

public class GameRules {

    private final int oldColumn;
    private final int oldRow;
    private final int newColumn;
    private final int newRow;
    private final int oldOwner;
    private final int newOwner;
    private final GameTile[][] arrayOfTiles;

    public GameRules(int oldColumn, int oldRow, int newColumn, int newRow, int oldOwner, int newOwner, GameTile[][] arrayOfTiles) {
        this.oldColumn = oldColumn;
        this.oldRow = oldRow;
        this.newColumn = newColumn;
        this.newRow = newRow;
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
        this.arrayOfTiles = arrayOfTiles;
    }

    public boolean isValid() {
        if (yourPiece()) {               // is it your piece? if yes - it's not a valid move
            return false;
        } else if (!emptyTile()) {      // is this tile empty? if not - it's not a valid move
            return false;
        } else if (jump()) { // is this move to one of the neighbouring tiles? if not - it's not a valid move
            return true;
        } else return isGood();
    }

    private boolean yourPiece() {
        return oldOwner == newOwner;
    }

    private boolean emptyTile() {
        return newOwner == -1;
    }

    private boolean jump() {
        int column = newColumn - oldColumn;
        int row = newRow - oldRow;

        if ((Math.abs(column) == 4 && Math.abs(row) == 0) || (Math.abs(column) == 2 && Math.abs(row) == 2)) {
            if (column == -4 && row == 0 && arrayOfTiles[oldColumn - 2][oldRow].hasOwner()) {
                return true;
            } else if (column == 4 && row == 0 && arrayOfTiles[oldColumn + 2][oldRow].hasOwner()) {
                return true;
            } else if (column == -2 && row == -2 && arrayOfTiles[oldColumn - 1][oldRow - 1].hasOwner()) {
                return true;
            } else if (column == 2 && row == -2 && arrayOfTiles[oldColumn + 1][oldRow - 1].hasOwner()) {
                return true;
            } else if (column == -2 && row == 2 && arrayOfTiles[oldColumn - 1][oldRow + 1].hasOwner()) {
                return true;
            } else return column == 2 && row == 2 && arrayOfTiles[oldColumn + 1][oldRow + 1].hasOwner();
        }

        return false;
    }

    private boolean isGood() {
        int column = newColumn - oldColumn;
        int row = newRow - oldRow;
        if (Math.abs(column) == 2 && row == 0) {
            return true;
        } else return Math.abs(row) == 1 && Math.abs(column) == 1;
    }

}