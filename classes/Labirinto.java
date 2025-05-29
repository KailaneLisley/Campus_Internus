package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import classes.negativas.*;
import classes.positivas.*;


public class Labirinto {
    private List<PequenaConquista> pequenasConquistasDisponiveis;
    private List<PensamentoNegativo> pensamentosNegativos;
    private Set<Integer> salasTrancadas;
    private List<List<String>> estrutura;
    private int tamanho;
    private List<Perigo> perigos; // NOVO
    private Integer salaSaida = null;


    public List<PequenaConquista> getPequenasConquistasDisponiveis() {
       return pequenasConquistasDisponiveis;
    }


    public Labirinto(int tamanho) {
        this.tamanho = tamanho;
        this.pequenasConquistasDisponiveis = new ArrayList<>();
        this.pensamentosNegativos = new ArrayList<>();
        this.salasTrancadas = new HashSet<>();
        this.estrutura = new ArrayList<>();
        this.perigos = new ArrayList<>();

        inicializarLabirinto();
        gerarLabirinto();
    }

    
    private void inicializarLabirinto() {

        pequenasConquistasDisponiveis.add(new ConquistaAcademica("Elogio do Professor", 3));
        pequenasConquistasDisponiveis.add(new ConquistaPessoal("Pausa para Relaxar", 7));
        pequenasConquistasDisponiveis.add(new SuperacaoDeDesafio("Entregar trabalho difícil", 10));
        pequenasConquistasDisponiveis.add(new ChaveDaAutoestima("Aceitação das Imperfeições", 5,  12)); // Chave para sala 12


        pensamentosNegativos.add(new AutocriticaExcessiva(4));
        pensamentosNegativos.add(new MedoDeExposicao( 5));
        pensamentosNegativos.add(new DuvidaParalisante(6));

        salasTrancadas.add(12); // Sala 12 está trancada
    }
    

   public void visualizarLabirinto(Estudante estudante) {
    int posEstudante = estudante.getLocalizacao();
    String inicial = "\u001B[31m" + estudante.getNome().substring(0, 1).toUpperCase() + "\u001B[0m";

    for (int i = 0; i < tamanho; i++) {
        for (int j = 0; j < tamanho; j++) {
            int posAtual = i * tamanho + j;

            if (posAtual == posEstudante) {
                System.out.print(inicial + " ");
            } else if (!salaLiberada(posAtual)) {
                System.out.print("L ");
            } else if (encontrarPequenaConquistaNaPosicao(posAtual) != null) {
                System.out.print("\u001B[32mC\u001B[0m "); // Verde
            } else if (encontrarPerigoNaPosicao(posAtual) != null) {
                System.out.print("\u001B[35mP\u001B[0m "); // Roxo
            } 
             else if (salaSaida != null && posAtual == salaSaida) {
                    System.out.print("\u001B[36mS\u001B[0m "); // Cyan para Saída
                }
            else {
                System.out.print("= ");
            }
        }
        System.out.println();
    }
    
}

    public boolean isPosicaoValida(int posicao) {
        return posicao >= 0 && posicao < tamanho * tamanho && salaLiberada(posicao);
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
        int linha = posicao / tamanho;
        int coluna = posicao % tamanho;

        return estrutura.get(linha).get(coluna).equals("*") && !salasTrancadas.contains(posicao);
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
    public int getTamanho(){
        return tamanho;
    }
    public Perigo encontrarPerigoNaPosicao(int posicao) {
        for (Perigo p : perigos) {
            if (p.getLocalizacao() == posicao) {
                return p;
            }
        }
        return null;
    }

    public void adicionarPerigo(Perigo perigo) {
        perigos.add(perigo);
    }

    public void removerPerigo(Perigo perigo) {
        perigos.remove(perigo);
    }
   
    public void gerarLabirinto() {
    Random random = new Random();
    int totalSalas = tamanho * tamanho;

    // 1. Gera a estrutura e define salas trancadas
    estrutura.clear();
    salasTrancadas.clear();
    
    for (int i = 0; i < tamanho; i++) {
        List<String> caminho = new ArrayList<>();
        for (int j = 0; j < tamanho; j++) {
            int posicao = i * tamanho + j;

            if (random.nextInt(100) < 70) {
                caminho.add("*");  // sala livre
            } else {
                caminho.add("X");  // sala trancada
                salasTrancadas.add(posicao);
            }
        }
        estrutura.add(caminho);
    }

    // 2. Gerar conquistas e pensamentos negativos em posições distintas e livres,
    //    garantindo acessibilidade

    boolean sucesso = false;

    while (!sucesso) {
        pequenasConquistasDisponiveis.clear();
        pensamentosNegativos.clear();

        Set<Integer> posicoesUsadas = new HashSet<>();

        // Lista de conquistas possíveis
        List<PequenaConquista> conquistasPossiveis = new ArrayList<>();
        conquistasPossiveis.add(new ConquistaAcademica("Elogio do Professor", 3));
        conquistasPossiveis.add(new ConquistaPessoal("Pausa para Relaxar", 7));
        conquistasPossiveis.add(new SuperacaoDeDesafio("Entregar trabalho difícil", 10));
        conquistasPossiveis.add(new ChaveDaAutoestima("Aceitação das Imperfeições", 5, 12));

        // Sorteia posições para conquistas
        for (PequenaConquista conquista : conquistasPossiveis) {
            int posicao;
            do {
                posicao = random.nextInt(totalSalas);
            } while (salasTrancadas.contains(posicao) || posicoesUsadas.contains(posicao));

            conquista.setLocalizacao(posicao);
            pequenasConquistasDisponiveis.add(conquista);
            posicoesUsadas.add(posicao);
        }

        // Sorteia pensamentos negativos — para variar, podemos definir quantos queremos
        List<PensamentoNegativo> pensamentosPossiveis = new ArrayList<>();
        pensamentosPossiveis.add(new AutocriticaExcessiva(4));
        pensamentosPossiveis.add(new MedoDeExposicao(5));
        pensamentosPossiveis.add(new DuvidaParalisante(6));

        for (PensamentoNegativo pn : pensamentosPossiveis) {
            int posicaoPn;
            do {
                posicaoPn = random.nextInt(totalSalas);
            } while (salasTrancadas.contains(posicaoPn) || posicoesUsadas.contains(posicaoPn));

            pn.setLocalizacao(posicaoPn);
            pensamentosNegativos.add(pn);
            posicoesUsadas.add(posicaoPn);
        }

        // 3. Verifica se todas as conquistas são acessíveis a partir da posição inicial (0)
        sucesso = true;
        for (PequenaConquista conquista : pequenasConquistasDisponiveis) {
            if (!ehAcessivel(0, conquista.getLocalizacao())) {
                sucesso = false;
                break;
            }
        }

        // Se não for sucesso, o loop reinicia e gera um novo labirinto
    }
}

    public boolean todasConquistasColetadas() {
        return pequenasConquistasDisponiveis.isEmpty();
    }
    private boolean ehAcessivel(int inicio, int destino) {
        if (!salaLiberada(inicio) || !salaLiberada(destino)) return false;

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            if (atual == destino) return true;

            int linha = atual / tamanho;
            int coluna = atual % tamanho;

            // vizinhos válidos: cima, baixo, esquerda, direita
            int[][] direcoes = {
                {linha - 1, coluna}, // cima
                {linha + 1, coluna}, // baixo
                {linha, coluna - 1}, // esquerda
                {linha, coluna + 1}  // direita
            };

            for (int[] dir : direcoes) {
                int novaLinha = dir[0];
                int novaColuna = dir[1];

                if (novaLinha >= 0 && novaLinha < tamanho &&
                    novaColuna >= 0 && novaColuna < tamanho) {

                    int vizinho = novaLinha * tamanho + novaColuna;

                    if (isPosicaoValida(vizinho) && !visitados.contains(vizinho)) {
                        visitados.add(vizinho);
                        fila.add(vizinho);
                    }
                }
            }
        }

        return false;
    }

     public void gerarSalaSaida() {
        Random random = new Random();
        int totalSalas = tamanho * tamanho;
        int tentativa;

        do {
            tentativa = random.nextInt(totalSalas);
        } while (!isPosicaoValida(tentativa) || tentativa == 0); // não pode ser trancada nem a inicial

        salaSaida = tentativa;
        System.out.println("⚠ A sala de saída foi revelada! Vá até a sala " + salaSaida + " para vencer!");
    }
  
    public Integer getSalaSaida(){
        return this.salaSaida;
    }
}

