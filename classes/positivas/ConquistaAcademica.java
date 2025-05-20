package classes.positivas;

public class ConquistaAcademica extends PequenaConquista {
    public ConquistaAcademica(String nome, int valorEmPontosDeConfianca) {
        super(nome, valorEmPontosDeConfianca);
    }

    @Override
    public String efeito() {
        return "Um elogio recebido ou um problema resolvido aumentou sua confiança! (" + getNome() + ")";
    }
}
