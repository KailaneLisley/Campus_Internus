import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

//Tesouro
class PequenaConquista {
    private String nome;
    private int localizacao;
    private int valorEmPontosDeConfianca; 

    public PequenaConquista(String nome, int localizacao, int valorEmPontosDeConfianca) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.valorEmPontosDeConfianca = valorEmPontosDeConfianca;
    }

    public String getNome() {
        return nome;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public int getValorEmPontosDeConfianca() {
        return valorEmPontosDeConfianca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public void setValorEmPontosDeConfianca(int valorEmPontosDeConfianca) {
        this.valorEmPontosDeConfianca = valorEmPontosDeConfianca;
    }

    public String efeito() {
        return "Pequena conquista alcançada!"; 
    }
}

class ConquistaAcademica extends PequenaConquista {
    public ConquistaAcademica(String nome, int localizacao, int valorEmPontosDeConfianca) {
        super(nome, localizacao, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Um elogio recebido ou um problema resolvido aumentou sua confiança! (" + getNome() + ")";
    }
}

class ConquistaPessoal extends PequenaConquista {
    public ConquistaPessoal(String nome, int localizacao, int valorEmPontosDeConfianca) {
        super(nome, localizacao, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Um momento de autocuidado ou uma pequena vitória pessoal renovou suas energias! (" + getNome() + ")";
    }
}

class SuperacaoDeDesafio extends PequenaConquista {
    public SuperacaoDeDesafio(String nome, int localizacao, int valorEmPontosDeConfianca) {
        super(nome, localizacao, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Você superou um desafio importante! Sua confiança aumentou. (" + getNome() + ")";
    }
}

class ChaveDaAutoestima extends PequenaConquista {
    private int posicaoDesbloqueio; // Pode representar o ID de uma porta ou área

    public ChaveDaAutoestima(String nome, int localizacao, int valorEmPontosDeConfianca, int posicaoDesbloqueio) {
        super(nome, localizacao, valorEmPontosDeConfianca); // valorEmPontosDeConfianca pode ser 0 se for só chave
        this.posicaoDesbloqueio = posicaoDesbloqueio;
    }

    public int getPosicaoDesbloqueio() {
        return posicaoDesbloqueio;
    }

    @Override
    public String efeito() {
        return "Você encontrou a Chave da Autoestima: " + getNome() + "! Um novo caminho pode ter se aberto.";
    }
}

class Estudante {
    private String nome;
    private int localizacao;
    private List<PequenaConquista> pequenasConquistasColetadas; 
    private int pontosDeConfianca; 

    public Estudante(String nome, int localizacao, int pontosDeConfiancaInicial) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.pequenasConquistasColetadas = new ArrayList<>();
        this.pontosDeConfianca = pontosDeConfiancaInicial;
    }

    public String getNome() {
        return nome;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public List<PequenaConquista> getPequenasConquistasColetadas() {
        return pequenasConquistasColetadas;
    }

    public int getPontosDeConfianca() {
        return pontosDeConfianca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public void adicionarPequenaConquista(PequenaConquista conquista) {
        pequenasConquistasColetadas.add(conquista);
        this.pontosDeConfianca += conquista.getValorEmPontosDeConfianca();
        System.out.println(conquista.efeito());
    }

    public void perderConfianca(int valor) {
        this.pontosDeConfianca -= valor;
        if (this.pontosDeConfianca < 0) {
            this.pontosDeConfianca = 0;
        }
        System.out.println("Sua confiança diminuiu...");
    }

    public void recuperarConfianca(int valor) {
        this.pontosDeConfianca += valor;
        System.out.println("Sua confiança aumentou!");
    }
}

//Perigo
class PensamentoNegativo {
    private String nome;
    private int localizacao;
    private int impactoConfianca; //Dano do perigo

    public PensamentoNegativo(String nome, int localizacao, int impactoConfianca) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.impactoConfianca = impactoConfianca;
    }

    public String getNome(){
        return nome;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public int getImpactoConfianca() {
        return impactoConfianca;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public void setImpactoConfianca(int impactoConfianca) {
        this.impactoConfianca = impactoConfianca;
    }

    public String efeito(Estudante estudante) { 
        estudante.perderConfianca(this.impactoConfianca);
        return "Você foi confrontado por um Pensamento Negativo: " + nome + "!";
    }
}

class AutocriticaExcessiva extends PensamentoNegativo {
    public AutocriticaExcessiva(int localizacao, int impactoConfianca) {
        super("Autocrítica Excessiva", localizacao, impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "A Autocrítica Excessiva abalou sua confiança! Você perdeu " + getImpactoConfianca() + " pontos de confiança.";
    }
}

class MedoDeExposicao extends PensamentoNegativo {
    public MedoDeExposicao(int localizacao, int impactoConfianca) {
        super("Medo de Exposição", localizacao, impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "O Medo de Exposição te deixou inseguro. Sua confiança diminuiu em " + getImpactoConfianca() + " pontos.";
    }
}

class DuvidaParalisante extends PensamentoNegativo {
    public DuvidaParalisante(int localizacao, int impactoConfianca) {
        super("Dúvida Paralisante", localizacao, impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "A Dúvida Paralisante te fez hesitar. Você perdeu " + getImpactoConfianca() + " pontos de confiança e talvez um turno para refletir."; // Efeito de perder turno precisa ser implementado na lógica do jogo
    }
}

class Labirinto {
    private List<PequenaConquista> pequenasConquistasDisponiveis; 
    private List<PensamentoNegativo> pensamentosNegativos; 
    private Set<Integer> salasTrancadas; 
    private List<List<String>> estrutura; 
    private int tamanho;

    public Labirinto(int tamanho) {
        this.tamanho = tamanho;
        this.pequenasConquistasDisponiveis = new ArrayList<>();
        this.pensamentosNegativos = new ArrayList<>();
        this.salasTrancadas = new HashSet<>();
        this.estrutura = new ArrayList<>();
        inicializarLabirinto();
    }

    private void inicializarLabirinto() {
       
        pequenasConquistasDisponiveis.add(new ConquistaAcademica("Elogio do Professor", 3, 20));
        pequenasConquistasDisponiveis.add(new ConquistaPessoal("Pausa para Relaxar", 7, 15));
        pequenasConquistasDisponiveis.add(new SuperacaoDeDesafio("Entregar trabalho difícil", 10, 30));
        pequenasConquistasDisponiveis.add(new ChaveDaAutoestima("Aceitação das Imperfeições", 5, 0, 12)); // Chave para sala 12

       
        pensamentosNegativos.add(new AutocriticaExcessiva(4, 10));
        pensamentosNegativos.add(new MedoDeExposicao(9, 5));
        pensamentosNegativos.add(new DuvidaParalisante(6, 15));

        salasTrancadas.add(12); // Sala 12 está trancada
    }

    public PequenaConquista encontrarPequenaConquistaNaPosicao(int posicao) {
        for (PequenaConquista pq : pequenasConquistasDisponiveis)
            if (pq.getLocalizacao() == posicao)
                return pq;
        return null;
    }

    public PensamentoNegativo encontrarPensamentoNegativoNaPosicao(int posicao) {
        for (PensamentoNegativo pn : pensamentosNegativos)
            if (pn.getLocalizacao() == posicao)
                return pn;
        return null;
    }

    public void removerPequenaConquista(PequenaConquista pq) {
        pequenasConquistasDisponiveis.remove(pq);
    }

    public void adicionarPequenaConquista(PequenaConquista pq) {
        pequenasConquistasDisponiveis.add(pq);
    }

    public boolean salaLiberada(int posicao) {
        return !salasTrancadas.contains(posicao);
    }

    public void desbloquearSala(int posicao) {
        salasTrancadas.remove(posicao);
        System.out.println("A sala " + posicao + " foi desbloqueada!");
    }

    public void adicionarPensamentoNegativo(PensamentoNegativo pn) {
        pensamentosNegativos.add(pn);
    }

    public void removerPensamentoNegativo(PensamentoNegativo pn) {
        pensamentosNegativos.remove(pn);
    }

    // Ainda precisa de adaptação
    public void gerarLabirinto() {
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            List<String> caminho = new ArrayList<>();
            for (int j = 0; j < tamanho; j++) {
                // Simplificando: '*' caminho livre, 'X' parede, 'P' jogador
                // A lógica de geração precisa ser mais robusta
                if (random.nextInt(100) < 70) {
                    caminho.add("*"); // Caminho
                } else {
                    caminho.add("X"); // Parede
                }
            }
            estrutura.add(caminho);
        }
    }
    //Adicionar função de visualizar labirinto de forma mais clara
}

//Classe principal 
public class LabirintoDaAutoestima {

    public static void digitando(String texto, int tempo_atraso) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(tempo_atraso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        digitando("Bem-vindo(a) ao Labirinto da Autoestima!", 30);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Começar Jornada");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");
            if (sc.hasNextInt()){
                op = sc.nextInt();
            } else {
                sc.next(); // Limpar input inválido
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }
            sc.nextLine(); // Consumir nova linha

            switch (op) {
                case 1:
                    digitando("Iniciando sua jornada de autoconhecimento...", 20);

                    System.out.print("Digite o nome do(a) estudante: ");
                    String nomeEstudante = sc.nextLine();
                    Estudante estudante = new Estudante(nomeEstudante, 0, 50); // Posição inicial 0, Confiança inicial 50

                    digitando("Olá, " + estudante.getNome() + ". Você se encontra em um labirinto que reflete seus desafios internos.", 20);
                    digitando("Sua confiança atual é: " + estudante.getPontosDeConfianca(), 20);
                    digitando("Colete 'Pequenas Conquistas' para aumentar sua confiança e enfrente 'Pensamentos Negativos'.", 20);
                    
                    // Lógica básica do jogo (placeholder)
                    // Esta parte precisa ser expandida com a movimentação no labirinto, interações, etc.
                    Labirinto labirinto = new Labirinto(5); // Labirinto 5x5 (exemplo)
                    labirinto.gerarLabirinto(); // Gerar estrutura básica

                    digitando("O labirinto foi gerado. A exploração começa agora!", 20);
                    // Loop de jogo: mostrar labirinto, pedir movimento, verificar eventos...
                    // Interação (Em construção)
                    PequenaConquista conquista = labirinto.encontrarPequenaConquistaNaPosicao(3);
                    if(conquista != null){
                        estudante.adicionarPequenaConquista(conquista);
                        labirinto.removerPequenaConquista(conquista);
                        digitando("Confiança atual: " + estudante.getPontosDeConfianca(), 20);
                    }

                    PensamentoNegativo pensamento = labirinto.encontrarPensamentoNegativoNaPosicao(4);
                    if(pensamento != null){
                        digitando(pensamento.efeito(estudante), 20);
                        digitando("Confiança atual: " + estudante.getPontosDeConfianca(), 20);
                    }
                    // Simplificando a escolha de característica para uma mensagem inicial
                    digitando("\nLembre-se, " + estudante.getNome() + ", cada passo aqui é um passo em direção a reconhecer seu valor.", 50);
                    digitando("Foco, resiliência e autocompaixão serão seus guias.", 50);
                    digitando("\nRetornando ao menu principal após esta breve introdução... (Implementação do jogo em progresso)", 20);
                    break;

                case 2:
                    digitando("Saindo do jogo. Lembre-se da sua força! Até a próxima!", 50);
                    sc.close();
                    return;

                default:
                    digitando("Opção inválida. Tente novamente.", 50);
            }
        }
    }
}

