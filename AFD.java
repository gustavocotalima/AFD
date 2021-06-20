import java.util.ArrayList;
import java.util.HashMap;

public class AFD {
    private String questao;  //nome da questão que o AFD está
    private ArrayList<String> estados; //lista com os estados do AFD
    private ArrayList<String> alfabeto; //lista com o alfabeto do AFD
    private HashMap<String, HashMap<String, String>> transicoes; //mapa de transições. chave: estado -> valor : (chave : letra -> valor : novoEstado)
    private String estadoInicial; //estado inicial do AFD
    private ArrayList<String> estadosFinais; //lista com os estados finais
    private ArrayList<String> palavrasTeste; //lista de palavras a serem testadas

    public String getQuestao() {
        return questao;
    }

    public AFD(String questao, ArrayList<String> estados, ArrayList<String> alfabeto, HashMap<String, HashMap<String, String>> transicoes, String estadoInicial, ArrayList<String> estadosFinais, ArrayList<String> palavrasTeste) {
        this.questao = questao;
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.transicoes = transicoes;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.palavrasTeste = palavrasTeste;
    }

    //Função para testar se as palavras são válidas no AFD
    public void testarPalavras (){
        //percorre as palavras
        for (String palavra: palavrasTeste) {
            System.out.println("\nTeste da palavra: "+palavra);

            //Define o estado atual como o estado incial
            String estadoAtual = this.estadoInicial;

            System.out.print("Estados percorridos: "+estadoAtual);

            //percorre cada letra da palavra atual
            for (char letra : palavra.toCharArray()) {
                //Mapa com as transições possiveis do estado atual
                HashMap<String, String> transicoesEstado = this.transicoes.get(estadoAtual);

                //atribui o próximo estado como estadoAtual de acordo com a letra
                estadoAtual = transicoesEstado.get(Character.toString(letra));

                System.out.print(" -> "+estadoAtual);
            }

            //checa se o ultimo estado é um estado final
            if(estadosFinais.contains(estadoAtual))
                System.out.println("\nA palavra foi aceita");
            else
                System.out.println("\nA palavra não foi aceita");
        }
    }
}
