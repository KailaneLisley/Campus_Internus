import java.util.ArrayList;
import java.util.List;

class Tesouro {
    private String nome;
    private int localizacao;
    private int valorPontos;

    public Tesouro(String nome, int localizacao, int valorPontos) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.valorPontos = valorPontos;
    }

    public String getNome() {
        return nome;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public int getValorPontos() {
        return valorPontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public void setValorPontos(int valorPontos) {
        this.valorPontos = valorPontos;
    }

    // Método que poderá ser sobrescrito pelas subclasses
    public String efeito() {
        return ("Fragmento de memória recuperado!");
    }
}

class MemoriaInfancia extends Tesouro {
    public MemoriaInfancia(String nome, int localizacao, int valorPontos) {
        super(nome, localizacao, valorPontos);
    }

    @Override
    public String efeito() {
        return ("Uma lembrança feliz da infância fortaleceu sua determinação! +10% de resistência emocional.");
    }
}

class MemoriaMae extends Tesouro {
    public MemoriaMae(String nome, int localizacao, int valorPontos) {
        super(nome, localizacao, valorPontos);
    }

    @Override
    public String efeito() {
        return ("O abraço da mãe reaquece o coração. Você se cura totalmente!");
    }
}

class MemoriaDescobertaCientifica extends Tesouro {
    public MemoriaDescobertaCientifica(String nome, int localizacao, int valorPontos) {
        super(nome, localizacao, valorPontos);
    }

    @Override
    public String efeito() {
        return ("Você se lembra da sua grande conquista científica! Ganha visão extra por 3 movimentos.");
    }
}

class Aventureiro {
    private String nome;
    private int localizacao;
    private List<Tesouro> tesourosEncontrados;

    public Aventureiro(String nome, int localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.tesourosEncontrados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public List<Tesouro> getTesourosEncontrados() {
        return tesourosEncontrados;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public void adicionarTesouro(Tesouro tesouro) {
        tesourosEncontrados.add(tesouro);
    }
}

class Perigo {
    private int localizacao_perigo;
    private double dano;

    public Perigo(int localizacao_perigo, double dano){
        this.localizacao_perigo = localizacao_perigo;
        this.dano = dano;
    }

    public int getLocalizacao_perigo(){
        return localizacao_perigo;
    }

    public double getDano(){
        return dano;
    }

    public void setLocalizacao_perigo(int localizacao_perigo){
        this.localizacao_perigo = localizacao_perigo;
    }

    public void setDano(double dano){
        this.dano = dano;
    }
}

class Labirinto{
    
}

public class LabirintodaMemoria {

}
