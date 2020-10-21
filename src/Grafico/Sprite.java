package Grafico;

import java.awt.image.BufferedImage;

public class Sprite {
    
    //Variavel para carregar imagem inteira
    private BufferedImage sprite;
    
    public Sprite(BufferedImage sprite){
        this.sprite = sprite;
    }
    
    public BufferedImage cortar(int x, int y, int w, int h){
        //Recortar apenas uma parte da imagem
        return sprite.getSubimage(x, y, w, h);
    }   
}
