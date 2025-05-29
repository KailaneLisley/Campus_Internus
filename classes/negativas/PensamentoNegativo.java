package classes.negativas;

import classes.Estudante;

public class PensamentoNegativo {
    private String nome;
    private int impactoConfianca; //Dano do perigo
    private int localizacao;

    public PensamentoNegativo(String nome,  int impactoConfianca) {
        this.nome = nome;
        this.impactoConfianca = impactoConfianca;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }
    public String getNome(){
        return nome;
    }

    public int getImpactoConfianca() {
        return impactoConfianca;
    }

    public void setImpactoConfianca(int impactoConfianca) {
        this.impactoConfianca = impactoConfianca;
    }

    public String efeito(Estudante estudante) {
        estudante.diminuiConfianca(this.impactoConfianca);
        return "Você foi confrontado por um Pensamento Negativo: " + nome + "!";
    }

    public void aumentarImpactoConfianca() { // NOVO
        this.impactoConfianca += 10; // Aumenta o dano para estudantes com ansiedade
    }
}