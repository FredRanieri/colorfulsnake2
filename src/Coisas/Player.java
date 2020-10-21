package Coisas;

import game.Game;
import java.awt.Color;
import java.awt.Graphics;

public class Player extends Coisas {
   
    private Game game;
    public Color cor;

    //Chamando construtor da classe principal
    public Player(Game game, int x, int y, Color cor) {
        super(x, y, SIZE, SIZE);
        this.game = game;
        this.cor = cor;
    }

    public void updateBotoes(){
        //Movimentar a parte da frente em 10 e 10 de acordo com os botões clicados
        if(game.getBotoes().upGame) y -= 10;
        if(game.getBotoes().downGame) y += 10;
        if(game.getBotoes().leftGame) x -= 10;
        if(game.getBotoes().rightGame) x += 10;
    }
    
    @Override
    public void update(int x, int y) {
        //Atualizar as posições de acordo com o movimento da parte da frente
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(cor);
        g.fillRect((int) x, (int) y, SIZE, SIZE);
    }
}
