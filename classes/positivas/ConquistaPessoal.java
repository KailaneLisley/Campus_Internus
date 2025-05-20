package classes.positivas;

public class ConquistaPessoal extends PequenaConquista {
    public ConquistaPessoal(String nome, int valorEmPontosDeConfianca) {
        super(nome, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Um momento de autocuidado ou uma pequena vitória pessoal renovou suas energias! (" + getNome() + ")";
    }
}
