package classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import classes.negativas.AutocriticaExcessiva;
import classes.negativas.DuvidaParalisante;
import classes.negativas.MedoDeExposicao;
import classes.negativas.PensamentoNegativo;
import classes.negativas.Perigo;
import classes.positivas.ChaveDaAutoestima;
import classes.positivas.ConquistaAcademica;
import classes.positivas.ConquistaPessoal;
import classes.positivas.PequenaConquista;
import classes.positivas.SuperacaoDeDesafio;

public class Labirinto {
    private List<PequenaConquista> pequenasConquistasDisponiveis;
    private List<PensamentoNegativo> pensamentosNegativos;
    private Set<Integer> salasTrancadas;
    private List<List<String>> estrutura;
    private int tamanho;
    private List<Perigo> perigos; // NOVO


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

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int posAtual = i * tamanho + j;

                if (posAtual == posEstudante) {
                    System.out.printf("%s ", estudante.getNome());
                } else if (!salaLiberada(posAtual)) {
                    System.out.print("L ");
                } else if (encontrarPequenaConquistaNaPosicao(posAtual) != null) {
                    System.out.print("C ");
                } else if (encontrarPerigoNaPosicao(posAtual) != null) {
                    System.out.print("P ");
                } else {
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
        return estrutura.get(linha).get(coluna).equals("*");
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

        PequenaConquista conquista = new ConquistaAcademica("Elogio do Professor", 3);
        int posicaoAleatoria = random.nextInt(totalSalas);
        conquista.setLocalizacao(posicaoAleatoria);
        pequenasConquistasDisponiveis.add(conquista);

        PensamentoNegativo pn = new AutocriticaExcessiva(4);
        int posicaoPn = random.nextInt(totalSalas);
        pn.setLocalizacao(posicaoPn);
        pensamentosNegativos.add(pn);

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

}