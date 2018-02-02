package Janela;

import Grafico.Recursos;
import game.Game;
import java.awt.Graphics;

public class JanelaPerdeu extends Janela{

    
    public JanelaPerdeu(Game game) {
        super(game);
    }

    @Override
    public int update() {
        game.fps = 8;
         if(game.getBotoes().enter){
             //4 - Menu
             return 4;
         }
         //5 - Perdeu
         return 5;
    }

    @Override
    public void render(Graphics g) {
        //Desenha fundo do perdeu
        g.drawImage(Recursos.base[3], 0, 0, 1100, 600, null);
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