package Display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    //Criar janela para o jogo
    private JFrame frame;
    
    //Local para fazer desenhos e outras informações do jogo
    private Canvas canvas;
    
    //Nome da Janela 
    private String nome;
    
    //Tamanhos da Janela
    private int w, h;
    
    public Display(String nome, int w, int h){
        this.nome = nome;
        this.w = w;
        this.h = h;
        
        initDisplay();
    }
    
    private void initDisplay(){
        //Construir frame com o nome do jogo
        frame = new JFrame(nome);
        
        //Set o tamanho da janela
        frame.setSize(w,h);
        //Para o jogo quando a janela for fechada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Não deixar que mude o tamanho da janela
        frame.setResizable(false);
        //Colocar janela no centro da tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        //Cria canvas e salva os fixa os tamanhos
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(w, h));
        canvas.setMaximumSize(new Dimension(w, h));
        canvas.setMinimumSize(new Dimension(w, h));
        canvas.setFocusable(false);
        
        //Adiciona o canvas no frame
        frame.add(canvas);
        //Altera o tamanho do frame para que o canvas fique fixo no tamanho escolhido
        frame.pack();
    }
    //Função get para utilizar o atributo privado em outras classes
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
}