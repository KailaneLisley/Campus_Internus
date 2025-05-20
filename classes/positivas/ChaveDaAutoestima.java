package classes.positivas;

public class ChaveDaAutoestima  extends PequenaConquista {
    private int posicaoDesbloqueio; // Pode representar o ID de uma porta ou área

    public ChaveDaAutoestima(String nome, int valorEmPontosDeConfianca, int posicaoDesbloqueio) {
        super(nome, valorEmPontosDeConfianca); // valorEmPontosDeConfianca pode ser 0 se for só chave
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
