package Janela;

import Coisas.Comida;
import Coisas.Player;
import Grafico.Recursos;
import game.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class JanelaGame extends Janela{
    
    //Variave para controlar tamanho inicial da snake
    private int tamanho = 15;
    
    //Vetores que são a snake e as comidas que estão pelo mapa
    private ArrayList<Player> snake;
    private ArrayList<Comida> comida;
    
    //Vetor de cores
    private Color cor[] = new Color[tamanho];
    
    //Variavel para gerar um valor aleatorio para a posição da comida e para a cor da snake
    private Random random = new Random();
    
    //Matriz de boolean para marca as posições onde tem alguma comida
    private boolean cheio[][] = new boolean[108][58];
    
    //Flag para que não gere mais de uma vez a comida em um determinado segundo onde é pra gerar as comidas
    private boolean gerado;
    
    public JanelaGame(Game game){
        super(game);
        snake = new ArrayList<>();
        comida = new ArrayList<>();
    }
    
    public void janelaGameInit(){
        //Limpa o vetor da snake e de comida para poder gerar um novo
        snake.clear();
        comida.clear();
        
        //Começa a contar os segundos apartir do inicio do jogo
        game.segundos = 1; //Inicia no segundo 1 para que no segundo 0 não gere mais comidas do que previsto 
        
        //Inicia o vetor com as posiveis cores do jogo
        cor[0] = new Color(255, 215, 0); //Amarelo
        cor[1] = new Color(0, 0, 200); //Azul
        cor[2] = new Color(255, 140, 0); //Laranja
        cor[3] = new Color(0, 200, 0); //Verde
        cor[4] = new Color(178, 34, 34); //Vermelho
        
        //Inicia a snake com cores aleatorias e o tamanho previamente definido
        for(int i = 0; i < tamanho; i++){
            snake.add(new Player(game, 300 - i*10, 100, cor[random.nextInt(5)]));
        }
        
        //Gera as comidas pelo mapa
        gerarComida(4);
    }
    
    public void gerarComida(int num){
        //Variaveis para controlar as posições onde vão nascer as comidas
        int x, y;
        
        //For para gerar n comidas de cada uma
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < num; j++){
                //Sorteia um numero aleatorio x e y
                x = random.nextInt(108);
                y = random.nextInt(58);
                //Se esse numero gerado já tiver uma comida entao sorteia outros numeros ate achar um onde ainda não tem
                while(cheio[x][y] == true){
                    x = random.nextInt(108);
                    y = random.nextInt(58);
                }
                //Apos encontrar um local vazio adiciona a comida e marca o local como cheio
                comida.add(new Comida(game, x, y, cor[i]));
                cheio[x][y] = true;
            }
        }
    }
    
    @Override
    public int update() {
        //Se o jogo estiver começando então inicia todas as informações, caso não, apenas atualiza as informações
        if(initGame == true){
            janelaGameInit();
            initGame = false;
        }
        
        //Controla a velocidade da snake conforme seu tamanho, quanto menor o snake mais rapido ele fica
        if(snake.size() < 40)
            game.fps =  40 - snake.size();
        else
            game.fps = 10;
        
        //Se a snake estiver vazia então fim de jogo e abre a janela de vitoria
        if(snake.size() == 0){
            //Tempo que o player terminou o jogo é salvo na variavel finalSegundos que vai ser comparada com o recorde
            game.finalSegundos = game.segundos;
            //6 - Ganhou
            return 6;
        }
        
        //A posição anterior pega a posição da parte que esta na frente e assim gera o movimento de todo o corpo
        for(int i = snake.size()-1; i > 0; i--){
            snake.get(i).update(snake.get(i-1).getX(), snake.get(i-1).getY());
        }
        
        //Depois de todo o corpo ser atualizado a cabeça anda para uma nova posição
        snake.get(0).updateBotoes();
        
        //Variaveis para testar onde está a frente da snake comparado com as outras informações do jogo
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        
        //Se a frente estiver batido em alguma parede ou passou muito tempo, então fim de jogo, compara com os limites da tela 
        if(x < 5 || x > 1085 || y < 5 || y > 585 || game.segundos == 999){
            //5 - Perdeu
            return 5;
        }
        
        //Teste se a frente da snake colidiu com alguma comida
        if(cheio[x/10 - 1][y/10 - 1] == true){
            //Se colidiu então a snake come a comida e aquela posição fica vazia
            cheio[x/10 - 1][y/10 - 1] = false;
            //Percorre o vetor de comidas procurando qual estava naquela posição
            for(int i = 0; i < comida.size(); i++){
                if(comida.get(i).getX() == x/10 - 1 && comida.get(i).getY() == y/10 - 1){
                    //Quando achar testa se a cor da frente da snake é a mesma da comida
                    if(snake.get(0).cor == comida.get(i).cor){
                        //Se for igual então a frente da snake e a comida somem fazendo com que a snake diminua
                        snake.remove(0);
                        comida.remove(i);
                    } else {
                        //Se for diferente então a snake come a comida, fazendo com que a snake cresça e a comida suma
                        snake.add(new Player(game, snake.get(snake.size()-1).getX(), snake.get(snake.size()-1).getY(), comida.get(i).cor));
                        comida.remove(i);
                    }
                }
            }
        }
        
        //A cada vez que passar 10 segundos de jogo gera mais comidas, caso já tiver gerado naquele segundo não deve ser gerado de novo
        if(game.segundos%10 == 0 && !gerado){
            gerarComida(2);
            gerado = true;
        //Apos passar o segundo libera para que possa ser gerada no proximo divisor de  10
        } else if(game.segundos%11 == 0 && game.segundos% 10 != 0) {
            gerado = false;
        }
        //0 - Jogo, se mantem no jogo até o jogo acabar
        return 0;
    }

    @Override
    public void render(Graphics g) {
        //Variaveis para mostrar os segundos
        int u = 0, d = 0, c = 0;
        
        //Controle para separar centenas, dezenas e unidades
        if(game.segundos < 100){
            u = game.segundos % 10;
            d = game.segundos/10;
        } else {
            u = game.segundos % 10;
            d = game.segundos % 100 / 10;
            c = game.segundos / 100;
        }
        
        //Posição onde começa a desenhar os segundos
        int inicio = 1005;
        
        //Desenha a base do jogo na tela
        g.drawImage(Recursos.base[2], 0, 0, 1100, 600, null);
        
        //Desenha os segundos que passaram
        g.drawImage(Recursos.num[c], inicio, 10, 10, 14, null);
        g.drawImage(Recursos.num[d], inicio + 10, 10, 10, 14, null);
        g.drawImage(Recursos.num[u], inicio + 20, 10, 10, 14, null);
        g.drawImage(Recursos.num[10], inicio + 30, 10, 54, 15, null);
        
        //Desenha a snake
        for(int i = 0; i < snake.size(); i++){
            snake.get(i).render(g);
        }
        
        //Desenha as comidas
        for(int i = 0; i < comida.size(); i++){
            comida.get(i).render(g);
        }
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