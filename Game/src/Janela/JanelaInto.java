package Janela;

import Grafico.Recursos;
import game.Game;
import java.awt.Graphics;

public class JanelaInto extends Janela{

    
    public JanelaInto(Game game) {
        super(game);
    }

    @Override
    public int update() {
        //Quando for clicado no enter atualizar para nova pagina
        if(game.getBotoes().enter){
            //4 - Menu
            return 4;
        }
        //2 - Into
        return 2;
    }

    @Override
    public void render(Graphics g) {
        //Desenha fundo da into
        g.drawImage(Recursos.base[0], 0, 0, 1100, 600, null);
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