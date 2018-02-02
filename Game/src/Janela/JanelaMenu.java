package Janela;

import Grafico.Recursos;
import game.Game;
import java.awt.Graphics;

public class JanelaMenu extends Janela{
    private int menuSelecao;
    
    public JanelaMenu(Game game) {
        super(game);
        //janelaInto = new JanelaInto(game);
    }
    
    @Override
    public int update() {
        //Coloca variavel initGame como true para a proxima vez que iniciar o jogo atualizar todas as informações antes de começar
        initGame = true;
        //Controlar velocidade do movimento no menu
        game.fps = 8;
        
        //Mudar seleção do menu
        if(game.getBotoes().up){
            if(menuSelecao != 0)
                menuSelecao--;
        }
        if(game.getBotoes().down){
            if(menuSelecao != 3)
                menuSelecao++;
        }
        
        //Selecionar alguma opção
        if(game.getBotoes().enter)
           /*
            0 - Game
            1 - Rank
            2 - Into
            3 - Sair
           */
            return menuSelecao;
        
        //4 - Menu
        return 4;
    }

    @Override
    public void render(Graphics g) {
        //Desenha fundo do menu
        g.drawImage(Recursos.base[1], 0, 0, 1100, 600, null);
        
        //Desenha as informações do menu, e mostra em qual esta selecionado
        g.drawImage(Recursos.menu[menuSelecao], 350, 130, null);
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