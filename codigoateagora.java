import java.util.ArrayList; 

import java.util.List; 

import java.util.Set; 

import java.util.HashSet; 

import java.util.Random; 

 

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

 

    public String efeito() { 

        return "Fragmento de memória recuperado!"; 

    } 

} 

 

class MemoriaInfancia extends Tesouro { 

    public MemoriaInfancia(String nome, int localizacao, int valorPontos) { 

        super(nome, localizacao, valorPontos); 

    } 

 

    @Override 

    public String efeito() { 

        return "Uma lembrança feliz da infância fortaleceu sua determinação! +10% de resistência emocional."; 

    } 

} 

 

class MemoriaMae extends Tesouro { 

    public MemoriaMae(String nome, int localizacao, int valorPontos) { 

        super(nome, localizacao, valorPontos); 

    } 

 

    @Override 

    public String efeito() { 

        return "O abraço da mãe reaquece o coração. Você se cura totalmente!"; 

    } 

} 

 

class MemoriaDescobertaCientifica extends Tesouro { 

    public MemoriaDescobertaCientifica(String nome, int localizacao, int valorPontos) { 

        super(nome, localizacao, valorPontos); 

    } 

 

    @Override 

    public String efeito() { 

        return "Você se lembra da sua grande conquista científica! Ganha visão extra por 3 movimentos."; 

    } 

} 

 

class MemoriaChave extends Tesouro { 

    private int posicaoDesbloqueio; 

 

    public MemoriaChave(String nome, int localizacao, int valorPontos, int posicaoDesbloqueio) { 

        super(nome, localizacao, valorPontos); 

        this.posicaoDesbloqueio = posicaoDesbloqueio; 

    } 

 

    public int getPosicaoDesbloqueio() { 

        return posicaoDesbloqueio; 

    } 

 

    @Override 

    public String efeito() { 

        return "Você encontrou a Chave da Mente! Sala secreta na posição "  

               + posicaoDesbloqueio + " desbloqueada!"; 

    } 

} 

 

class Cientista { 

    private String nome; 

    private int localizacao; 

    private List<Tesouro> tesourosEncontrados; 

 

    public Cientista(String nome, int localizacao) { 

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

    private int localizacao; 

    private double dano; 

 

    public Perigo(int localizacao, double dano) { 

        this.localizacao = localizacao; 

        this.dano = dano; 

    } 

 

    public int getLocalizacao() { 

        return localizacao; 

    } 

 

    public double getDano() { 

        return dano; 

    } 

 

    public void setLocalizacao(int localizacao) { 

        this.localizacao = localizacao; 

    } 

 

    public void setDano(double dano) { 

        this.dano = dano; 

    } 

 

    public String efeito() { 

        return "Você foi atingido por um perigo!"; 

    } 

} 

 

class Pesadelo extends Perigo { 

    public Pesadelo(int localizacao, double dano) { 

        super(localizacao, dano); 

    } 

 

    @Override 

    public String efeito() { 

        return "Você foi paralisado por um terrível pesadelo! Perde um turno."; 

    } 

} 

 

class Ansiedade extends Perigo { 

    public Ansiedade(int localizacao, double dano) { 

        super(localizacao, dano); 

    } 

 

    @Override 

    public String efeito() { 

        return "Uma crise de ansiedade te confunde. Você anda aleatoriamente!"; 

    } 

} 

 

class Duvida extends Perigo { 

    public Duvida(int localizacao, double dano) { 

        super(localizacao, dano); 

    } 

 

    @Override 

    public String efeito() { 

        return "A dúvida te faz hesitar. Seu próximo movimento é bloqueado."; 

    } 

} 

 

class Labirinto { 

    private List<Tesouro> tesourosDisponiveis; 

    private List<Perigo> perigos; 

    private Set<Integer> salasTrancadas; 

    private List<List<String>> estrutura; 

    private int tamanho; 

 

    public Labirinto(int tamanho) { 

        this.tamanho = tamanho; 

        this.tesourosDisponiveis = new ArrayList<>(); 

        this.perigos = new ArrayList<>(); 

        this.salasTrancadas = new HashSet<>(); 

        this.estrutura = new ArrayList<>(); 

        inicializarLabirinto(); 

    } 

 

    private void inicializarLabirinto() { 

        tesourosDisponiveis.add(new MemoriaInfancia("Brinquedo Favorito", 3, 50)); 

        tesourosDisponiveis.add(new MemoriaMae("Abraço da Mãe", 7, 80)); 

        tesourosDisponiveis.add(new MemoriaChave("Chave Secreta", 5, 0, 12)); 

 

        perigos.add(new Pesadelo(4, 20)); 

        perigos.add(new Ansiedade(9, 15)); 

 

        salasTrancadas.add(12); 

    } 

 

    public Tesouro encontrarTesouroNaPosicao(int posicao) { 

        for (Tesouro t : tesourosDisponiveis) 

            if (t.getLocalizacao() == posicao) 

                return t; 

        return null; 

    } 

 

    public Perigo encontrarPerigoNaPosicao(int posicao) { 

        for (Perigo p : perigos) 

            if (p.getLocalizacao() == posicao) 

                return p; 

        return null; 

    } 

 

    public void removerTesouro(Tesouro t) { 

        tesourosDisponiveis.remove(t); 

    } 

    Public void adicionar tesouro(tesouro t){ 

       tesoudosDisponiveis.add(t); 

} 

 

    public boolean salaLiberada(int posicao) { 

        return !salasTrancadas.contains(posicao); 

    } 

 

    public void desbloquearSala(int posicao) { 

        salasTrancadas.remove(posicao); 

    } 

Public void adicionarPerigo(perigo p) { 

perigos.add(p); 

} 

Public void removerPerigo(perigo p) { 

Perigos.remove(p); 

} 

 

    public void gerarLabirinto() { 

        Random random = new Random(); 

        for (int i = 0; i < tamanho; i++) { 

            List<String> caminho = new ArrayList<>(); 

            for (int j = 0; j < tamanho; j++) { 

                if (random.nextInt(100) < 70) { 

                    caminho.add("*"); 

                } else { 

                    caminho.add("!"); 

                } 

            } 

            estrutura.add(caminho); 

        } 

    } 

} 
