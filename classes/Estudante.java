package classes;

import java.util.ArrayList;
import java.util.List;

import classes.positivas.PequenaConquista;

public class Estudante {
    private String nome;
    private int localizacao;
    private List<PequenaConquista> pequenasConquistasColetadas;
    private int pontosDeConfianca;
    private String caracteristica; //NOVOOO

    public Estudante(String nome, int localizacao, int pontosDeConfiancaInicial) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.pequenasConquistasColetadas = new ArrayList<>();
        this.pontosDeConfianca = pontosDeConfiancaInicial;
        this.caracteristica = "neutra";
    }

    public String getNome() {
        return nome;
    }

    public List<PequenaConquista> getPequenasConquistasColetadas() {
        return pequenasConquistasColetadas;
    }

    public int getLocalizacao(){
        return localizacao;
    }

    public int getPontosDeConfianca() {
        return pontosDeConfianca;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getCaracteristica() {
        return caracteristica;
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
        System.out.println("Sua confiança diminuiu para " + pontosDeConfianca);
    }

    public void recuperarConfianca(int valor) {
        this.pontosDeConfianca += valor;
        System.out.println("Sua confiança aumentou para " + pontosDeConfianca);
    }
    public void diminuiConfianca(int valor) {
        this.pontosDeConfianca -= valor;
        if (this.pontosDeConfianca < 0) {
            this.pontosDeConfianca = 0;
        }
        System.out.println("Você perdeu " + valor + " pontos de confiança.");
    }
    public void adicionarConfianca(int pontos) {
        this.pontosDeConfianca += pontos;
    }


}