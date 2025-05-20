package classes.negativas;

import classes.Estudante;

public class MedoDeExposicao  extends PensamentoNegativo {
    public MedoDeExposicao( int impactoConfianca) {
        super("Medo de Exposição",impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "O Medo de Exposição te deixou inseguro. Sua confiança diminuiu em " + getImpactoConfianca() + " pontos.";
    }
}