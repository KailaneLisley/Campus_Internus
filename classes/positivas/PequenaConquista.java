package classes.positivas;

//Tesouro
public class PequenaConquista {
    private String nome;
    private int valorEmPontosDeConfianca;
    private int localizacao;

    public PequenaConquista(String nome,int valorEmPontosDeConfianca) {
        this.nome = nome;
        this.valorEmPontosDeConfianca = valorEmPontosDeConfianca;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int getValorEmPontosDeConfianca() {
        return valorEmPontosDeConfianca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValorEmPontosDeConfianca(int valorEmPontosDeConfianca) {
        this.valorEmPontosDeConfianca = valorEmPontosDeConfianca;
    }

    public String efeito() {
        return "Pequena conquista alcançada!";
    }
}