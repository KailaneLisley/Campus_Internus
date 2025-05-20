package classes.negativas;


public class Perigo {
    private String descricao;
    private int impacto;
    private int localizacao;

    public Perigo(String descricao, int impacto) {
        this.descricao = descricao;
        this.impacto = impacto;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImpacto() {
        return impacto;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImpacto(int impacto) {
        this.impacto = impacto;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }
}
