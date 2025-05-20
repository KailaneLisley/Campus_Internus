package classes.negativas;

import classes.Estudante;

public class DuvidaParalisante  extends PensamentoNegativo {
    public DuvidaParalisante( int impactoConfianca) {
        super("Dúvida Paralisante", impactoConfianca);
    }

    @Override
    public String efeito(Estudante estudante) {
        super.efeito(estudante);
        return "A Dúvida Paralisante te fez hesitar. Você perdeu " + getImpactoConfianca() + " pontos de confiança e talvez um turno para refletir."; // Efeito de perder turno precisa ser implementado na lógica do jogo
    }
}