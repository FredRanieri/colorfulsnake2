package game;

import Controle.Botoes;
import Display.Display;
import Grafico.Recursos;
import Janela.Janela;
import Janela.JanelaGame;
import Janela.JanelaGanhou;
import Janela.JanelaInto;
import Janela.JanelaMenu;
import Janela.JanelaPerdeu;
import Janela.JanelaRank;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Game {
    //Janela onde vai rodar o jogo, tamanho e nome
    private Display display;
    public int w = 1100, h = 600;
    public String nome = "Snake 2.0";
    
    //Variavel para contrar quando o jogo está rodando e quando vai parar
    private boolean executando = false;
    public int fps = 10;
    
    //Variaveis de controle para mostrar as informações na tela
    private BufferStrategy bs;
    private Graphics g;

    //Variaveis para controle de janela
    private Janela janelaGame;
    private Janela janelaMenu;
    private Janela janelaInto;
    private Janela janelaPerdeu;
    private Janela janelaGanhou;
    private Janela janelaRank;
    
    
    //Seleciona janela desejada
    private int seleciona;
    
    //Variaveis de controle de movimento
    private Botoes controle = new Botoes();

    //Variavel para marcar o tempo
    public int segundos;
    public int finalSegundos;
    public int recorde;
    
    //Variavel para o caminho do arquivo
    public Path caminho = Paths.get(System.getProperty("user.dir") + "\\recursos\\recorde.txt");
    
    private void init(){   
        //Inicia janela do jogo
        display = new Display(nome, w, h);
        //Adicionar o controle pelos botoes no jogo
        display.getFrame().addKeyListener(controle);
        //Recorta as imagens e ja salva cada uma em uma variaveis
        Recursos.init();
        
        //Inicia variavel de controle de janela
        janelaGame = new JanelaGame(this);
        janelaMenu = new JanelaMenu(this);
        janelaInto = new JanelaInto(this);
        janelaPerdeu = new JanelaPerdeu(this);
        janelaGanhou = new JanelaGanhou(this);
        janelaRank = new JanelaRank(this);
        //2 - into, Inicia janela na janela de introdução
        seleciona = 2;
        //Inicia jogo!!
        executando = true;
        
        //Abre arquivo txt onde está salvo o recorde anterior e salva na variavel de recorde
        try{
            byte[] auxRecorde = Files.readAllBytes(caminho);
            String r = new String(auxRecorde);
            recorde = Integer.parseInt(r);
        }catch (Exception e){
            System.out.println("Falha ao carregar txt!!!");
        }
    }
    
    private void update(){
        //Atualizar os botões que foram pressionados
        controle.update();
        
        //Se o jogo for começar, reseta os botões para a snake sempre começar andando para a direita
        if(seleciona != 0)
            controle.controleReset();
        
        //Se tiver alguma janela selecionada atualizar janela
        if(Janela.getJanela() != null){
            //Recebe da classe janela qual deve ser a proxima janela mostrada na tela
            seleciona = Janela.getJanela().update();
        }
        
        //Testar para ver qual janela está selecionada
        if(seleciona == 0) Janela.setJanela(janelaGame);
        if(seleciona == 1) Janela.setJanela(janelaRank);
        if(seleciona == 2) Janela.setJanela(janelaInto);
        if(seleciona == 3) executando = false;
        if(seleciona == 4) Janela.setJanela(janelaMenu);
        if(seleciona == 5) Janela.setJanela(janelaPerdeu);
        if(seleciona == 6) Janela.setJanela(janelaGanhou);
    }
    
    //Desenha na tela as informações do jogo
    private void render(){
        //Pegar o canvas do display e entao salvar no Buffer para então mostrar na tela
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            //Se a canvas(Janela para desenhar as informações) ainda não foi criado, cria o canvas
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        //Pegar as ingormações que estava no buffer e salvar na variavel graphics para mostrar na tela
        g = bs.getDrawGraphics();
        
        //Desenha no canvas as informações desejadas e deixa no buffer
        //Se tiver algo pra desenhar, desenha
        if(Janela.getJanela() != null){
            Janela.getJanela().render(g);
        }
        
        //Manda para a tela todas as informações que foram desenhadas no buffer
        bs.show();
        g.dispose();
    }
    
    //Roda o jogo
    public void run(){
        init();
        
        //Codigo de controlo de fps, esse codigo não foi criado pela gente, a gente pegou na internet e alterou as informações
        double timePerTick;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(executando){
            //Calculo de controle de fps
            timePerTick = 1000000000/fps;
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                update();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
                segundos++;
            }
        }
    }
    
    public Botoes getBotoes(){
        return controle;
    }
}
