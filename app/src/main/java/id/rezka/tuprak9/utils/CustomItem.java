package id.rezka.tuprak9.utils;

import javafx.scene.image.Image;

public class CustomItem {
    private String text;
    private Image image;

    public CustomItem(String text, Image image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return text; 
    }
}


