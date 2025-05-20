package classes.positivas;

public class SuperacaoDeDesafio  extends PequenaConquista {
    public SuperacaoDeDesafio(String nome, int valorEmPontosDeConfianca) {
        super(nome, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Você superou um desafio importante! Sua confiança aumentou. (" + getNome() + ")";
    }
}