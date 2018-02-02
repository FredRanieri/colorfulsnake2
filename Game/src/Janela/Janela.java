package Janela;

import game.Game;
import java.awt.Graphics;

//Função abstrata para ter as informações base para todas as funções derivadas
public abstract class Janela {
    
    //Controle de Janela, variavel para controle de janela, set para selecionar nova janela e get para pegar janela já utilizada
    private static Janela janelaAtual = null;
    //Variavel utilizada para controlar se está iniciando um novo jogo ou apenas atualizando um jogo que ja esta acontecendo
    protected static boolean initGame = false;
    
    public static void setJanela(Janela atual){
        janelaAtual = atual;
    }
    
    public static Janela getJanela(){
        return janelaAtual;
    }
    
    //Metodos da função abstrata para ser usado em duas derivadas
    
    protected Game game;
    
    public Janela(Game game){
        this.game = game;
    }
    
    public abstract int update();
    public abstract void render(Graphics g);
}
