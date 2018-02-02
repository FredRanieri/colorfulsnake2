package Janela;

import Grafico.Recursos;
import game.Game;
import java.awt.Graphics;
import java.nio.file.Files;

public class JanelaGanhou extends Janela{
       
    public JanelaGanhou(Game game) {
        super(game);
    }

    @Override
    public int update() {
        //Diminui o fps para não atualizar os botões muito rapido no menu
        game.fps = 8;
        
        //Quando for clicado no enter atualizar para nova pagina
        if(game.getBotoes().enter){
             //4 - Menu
             return 4;
         }
         //6 - Ganhou
         return 6;
    }

    @Override
    public void render(Graphics g) {
        //Se o tempo final tiver sido menor do que o recorde, salva no arquivo txt o novo recorde
        if(game.finalSegundos < game.recorde){
            //Recorde recebe novo tempo
            game.recorde = game.finalSegundos;
            
            //Salva informações em uma string para salvar no txt
            String novoRecorede = "" + game.recorde; 
            byte[] r = novoRecorede.getBytes();
            
            try{
                //Salva no arquivo txt
                Files.write(game.caminho, r);
            } catch (Exception e) {
                System.out.println("O arquivo não pode ser salvo");
            }
        }
        
        //Desenha o fundo
        g.drawImage(Recursos.base[4], 0, 0, 1100, 600, null);
        
        //Faz a conversão dos segundos finais e do recorde para centenas, dezenas e unidades
        int u = 0, d = 0, c = 0 , posX = 125, posY = 300, sizeX = 25 , sizeY = 35;
        if(game.finalSegundos < 100){
            u = game.finalSegundos % 10;
            d = game.finalSegundos/10;
        } else {
            u = game.finalSegundos % 10;
            d = game.finalSegundos % 100 / 10;
            c = game.finalSegundos / 100;
        }
        
        //Desenha na tela as informações dos segundos finais
        g.drawImage(Recursos.num[c], posX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[d], posX + sizeX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[u], posX + sizeX*2, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[10], posX + sizeX*3 + 5, posY, 125, sizeY, null);
        
        if(game.recorde < 100){
            u = game.recorde % 10;
            d = game.recorde/10;
        } else {
            u = game.recorde % 10;
            d = game.recorde % 100 / 10;
            c = game.recorde / 100;
        }
        
        //Atualiza posição de escrita para o recorde
        posX = 785;
        
        //Desenha na tela as informações do recorde
        g.drawImage(Recursos.num[c], posX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[d], posX + sizeX, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[u], posX + sizeX*2, posY, sizeX, sizeY, null);
        g.drawImage(Recursos.num[10], posX + sizeX*3 + 5, posY, 125, sizeY, null);
        
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