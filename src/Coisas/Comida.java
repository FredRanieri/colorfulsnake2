package Coisas;

import game.Game;
import java.awt.Color;
import java.awt.Graphics;

public class Comida extends Coisas {
    
    private Game game;
    public Color cor;
    
    public Comida(Game game, int x, int y, Color cor){
        super(x, y, SIZE, SIZE);
        this.game = game;
        this.cor = cor;
    }

    //A comida está sendo atulizada na propria janela do jogo
    @Override
    public void update(int x, int y) {}

    @Override
    public void render(Graphics g) {
        //Desenhar a comida que é um retangulo de uma determinada cor
        g.setColor(cor);
        g.fillRect(x * SIZE + SIZE, y * SIZE + SIZE, SIZE, SIZE);
    } 
}
