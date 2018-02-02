package Grafico;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagem {
    //Path do caminho já como padrão até os recursos, configurado atravez do Game >> Propriedades >> Bibliotecas >> Add JAR/Pasta
    //Metodo para carregar imagem
    public static BufferedImage carregarImagem(String nomeArquivo){
        //Tentar carregar imagem, caso não conseguir fechar o programa
        try {
            return ImageIO.read(Imagem.class.getResource(nomeArquivo));
        } catch (IOException e) {
            System.out.println("Falhou!");
            System.exit(1);
        }
        return null;
    }
}
