import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static AFD lerAFD(String nomeArquivo, String questao) {
        try {
            FileReader arquivo = new FileReader(nomeArquivo);
            BufferedReader lerArquivo = new BufferedReader(arquivo);

            //lê os estados
            ArrayList<String> estados = new ArrayList<String>(Arrays.asList(lerArquivo.readLine().split(" ")));

            //lê o alfabeto
            ArrayList<String> alfabeto = new ArrayList<String>(Arrays.asList(lerArquivo.readLine().split(" ")));

            //mapa de transições. chave: estado -> valor : (chave : letra -> valor : novoEstado)
            HashMap<String, HashMap<String, String>> transicoes = new HashMap<String, HashMap<String, String>>();

            for (String estado : estados) {
                //lê a linha de transição do estado atual
                String linha[] = lerArquivo.readLine().split(" ");

                //cria o mapa Letra -> Novo Estado
                HashMap<String, String> transicoesEstado = new HashMap<String, String>();

                //atribui o novo estado para cada letra do alfabeto
                for (int i = 0; i < alfabeto.size(); i++)
                    transicoesEstado.put(alfabeto.get(i),linha[i]);

                //atribui o mapeamento para o estado "atual"
                transicoes.put(estado,transicoesEstado);
            }

            //lê o estado inicial
            String estadoInicial = lerArquivo.readLine();

            //lê os estados finais
            ArrayList<String> estadosFinais = new ArrayList<String>(Arrays.asList(lerArquivo.readLine().split(" ")));

            //lê o número de palavras a ser testada
            int numeroPalavrasTeste = Integer.parseInt(lerArquivo.readLine());

            //lê os estados finais
            ArrayList<String> palavrasTeste = new ArrayList<String>();

            //lê as palavras testes
            for (int i = 0; i < numeroPalavrasTeste; i++) {
                palavrasTeste.add(lerArquivo.readLine());
            }

            //retorna o AFD lido
            return new AFD(questao, estados, alfabeto, transicoes, estadoInicial, estadosFinais, palavrasTeste);
        } catch (IOException e) {
            System.err.print("Erro ao ler o arquivo. "+ e.getMessage()); //caso dê erro em ler o arquivo, printa uma mensagem
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<AFD> AFDs = new ArrayList<AFD>();

        //chama a função lerAFD, que lê o arquivo txt e retorna um objeto AFD, e adiciona o AFD retornado a lista de AFDs
        AFDs.add(lerAFD("src/AFD1C.txt","1C"));
        AFDs.add(lerAFD("src/AFD2A.txt","2A"));
        AFDs.add(lerAFD("src/AFD2B.txt","2B"));
        AFDs.add(lerAFD("src/AFD3B.txt","3B"));
        AFDs.add(lerAFD("src/AFD3D.txt","3D"));

        //faz os testes dos AFDs
        for (int i = 0; i < 5; i++) {
            System.out.println("\n-----------------------------------------------------------------------------------");
            System.out.println("\nTESTE DO AFD DA QUESTAO "+AFDs.get(i).getQuestao());
            AFDs.get(i).testarPalavras();
        }
    }
}
