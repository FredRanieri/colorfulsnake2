package Grafico;

import java.awt.image.BufferedImage;

public class Recursos {
    
    //Vetores para separar as imagens de acordo com suas funcionalidades
    public static BufferedImage[] base = new BufferedImage[6];
    public static BufferedImage[] menu = new BufferedImage[4];
    public static BufferedImage[] num = new BufferedImage[11];
    
    public static void init(){
        //Sprite para cortar as imagem
        Sprite spriteBase = new Sprite(Imagem.carregarImagem("/imagem/base.png"));
        Sprite spriteMenu = new Sprite(Imagem.carregarImagem("/imagem/menu.png"));
        Sprite spriteNum = new Sprite(Imagem.carregarImagem("/imagem/numeros.png"));
        
        //Corta a imagem da base
        base[0] = spriteBase.cortar(0, 0, 1100, 600);
        base[1] = spriteBase.cortar(1100, 0, 1100, 600);
        base[2] = spriteBase.cortar(2200, 0, 1100, 600);
        base[3] = spriteBase.cortar(3300, 0, 1100, 600);
        base[4] = spriteBase.cortar(4400, 0, 1100, 600);
        base[5] = spriteBase.cortar(5500, 0, 1100, 600);
        
        //Cortar imagem do menu
        menu[0] = spriteMenu.cortar(0, 0, 400, 267);
        menu[1] = spriteMenu.cortar(400, 0, 400, 267);
        menu[2] = spriteMenu.cortar(800, 0, 400, 267);
        menu[3] = spriteMenu.cortar(1200, 0, 400, 267);
    
        //Cortar a imagem dos numeros
    
        num[0] = spriteNum.cortar(0, 0, 25, 35);
        num[1] = spriteNum.cortar(25, 0, 25, 35);
        num[2] = spriteNum.cortar(50, 0, 25, 35);
        num[3] = spriteNum.cortar(75, 0, 25, 35);
        num[4] = spriteNum.cortar(100, 0, 25, 35);
        num[5] = spriteNum.cortar(125, 0, 25, 35);
        num[6] = spriteNum.cortar(150, 0, 25, 35);
        num[7] = spriteNum.cortar(175, 0, 25, 35);
        num[8] = spriteNum.cortar(200, 0, 25, 35);
        num[9] = spriteNum.cortar(225, 0, 25, 35);
        num[10] = spriteNum.cortar(250, 0, 125, 35);
    }    
}
