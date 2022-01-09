package com.example.ihavetrylma;

import javafx.scene.paint.Color;

public class Colour {

    public static Color setTileColor(int owner) {
        return switch (owner) {
            case 0 -> Color.web("#FCC6F6");
            case 1 -> Color.web("#C55FFC");
            case 2 -> Color.web("#68BBE3");
            case 3 -> Color.web("#94C973");
            case 4 -> Color.web("#E7625F");
            case 5 -> Color.web("#FDB750");
            default -> Color.web("#F3F3F3");
        };
    }

}
