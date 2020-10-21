package Controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Botoes implements KeyListener {
    //Vetor com todos os botões para caso precise pegar informaçoes
    private boolean[] botoes;
    
    //Variaveis para cada botão no menu e durante o jogo
    public boolean up, down, left, right, enter, upGame, downGame, leftGame, rightGame = true;
       
    public Botoes(){
        botoes = new boolean[255];
    }
    
    public void update(){
        //Atualizar caso um botão for apertado(MENU)
        up = botoes[KeyEvent.VK_UP];
        down = botoes[KeyEvent.VK_DOWN];
        left = botoes[KeyEvent.VK_LEFT];
        right = botoes[KeyEvent.VK_RIGHT];
        enter = botoes[KeyEvent.VK_ENTER];
    }
    
    public void controleReset(){
        //Fazer com que quando começe o jogo a snake sempre comece andando para direita
        upGame = false;
        downGame = false;
        leftGame = false;
        rightGame = true;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //Se um botão for clicado atualizar como verdadeiro
        botoes[ke.getKeyCode()] = true;
        
        //Fazer o controle das variaveis durante o jogo
        if(ke.getKeyCode() == KeyEvent.VK_UP && !downGame){
            upGame = true;
            downGame = false;
            leftGame = false;
            rightGame = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_DOWN && !upGame){
            upGame = false;
            downGame = true;
            leftGame = false;
            rightGame = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT && !rightGame){
            upGame = false;
            downGame = false;
            leftGame = true;
            rightGame = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT && !leftGame){
            upGame = false;
            downGame = false;
            leftGame = false;
            rightGame = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //Quando não tiver mais apertado o botão ele volta a ser falso
        botoes[ke.getKeyCode()] = false;
    }   
}
