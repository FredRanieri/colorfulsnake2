package Coisas;

import java.awt.Graphics;

//Classes abstrata para ter informações em comum de coisas do jogo, comida e snake
public abstract class  Coisas {
    
    
    //Definir valores padrão para o player
    protected static final int SIZE = 10;
    
    //Posição da coisa na tela
    protected int x, y;
    
    protected int w, h;
    
    public Coisas(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public abstract void update(int x, int y);
    public abstract void render(Graphics g);
    
    //Getting e Setting para utilizar variaveis privadas
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
}
