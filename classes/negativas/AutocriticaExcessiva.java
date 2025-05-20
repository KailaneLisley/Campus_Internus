package classes.negativas;

import classes.Estudante;

public class AutocriticaExcessiva  extends PensamentoNegativo {
    public AutocriticaExcessiva( int impactoConfianca) {
        super("Autocrítica Excessiva", impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "A Autocrítica Excessiva abalou sua confiança! Você perdeu " + getImpactoConfianca() + " pontos de confiança.";
    }
}