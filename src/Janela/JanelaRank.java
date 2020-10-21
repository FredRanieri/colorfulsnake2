package Janela;

import Grafico.Recursos;
import game.Game;
import java.awt.Graphics;

public class JanelaRank extends Janela {

    public JanelaRank(Game game) {
        super(game);
    }

    @Override
    public int update() {
        if(game.getBotoes().enter){
            //4 - Menu
            return 4;
        }
        //1 - Rank
        return 1;
    }

    @Override
    public void render(Graphics g) {
        //Desenha fundo do ranking
        g.drawImage(Recursos.base[5], 0, 0, 1100, 600, null);
        
        //Converte para centenas, dezenas e unidades a variavel do recorde
        int u = 0, d = 0, c = 0 , posX = 640, posY = 160, sizeX = 25 , sizeY = 35;
        
        if(game.recorde < 100){
            u = game.recorde % 10;
            d = game.recorde/10;
        } else {
            u = game.recorde % 10;
            d = game.recorde % 100 / 10;
            c = game.recorde / 100;
        }
        
        //Desenha na tela o recorde
        g.drawImage(Recursos.num[c], posX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[d], posX + sizeX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[u], posX + sizeX*2, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[10], posX + sizeX*3 + 5, posY, 125, sizeY, null);
    }
}
/*
    0 - Game
    1 - Rank
    2 - Into
    3 - Sair
    4 - Menu
    5 - Perdeu
    6 - Ganhou
*/