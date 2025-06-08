//Classe principal

import java.util.Scanner;

import classes.Estudante;
import classes.Labirinto;
import classes.negativas.PensamentoNegativo;
import classes.negativas.Perigo;
import classes.positivas.PequenaConquista;

public class Campus_Internus {

    public static void digitando(String texto, int tempo_atraso) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(tempo_atraso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        digitando("Bem-vindo(a) ao Campus Internus: A jornada de um universitário!", 30);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Começar Jornada");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");
            if (sc.hasNextInt()) {
                op = sc.nextInt();
            } else {
                sc.next();
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }
            sc.nextLine();

            switch (op) {
                case 1:
                    digitando("Iniciando sua jornada de autoconhecimento...", 20);

                    System.out.print("Digite o nome do(a) estudante: ");
                    String nomeEstudante = sc.nextLine();
                    String vermelho = "\u001B[31m";
                    String reset =  "\u001B[0m";

                    // Escolha de característica
                    System.out.println("\nEscolha uma característica que define seu personagem:");
                    System.out.println("1 - Maturidade (Conquistas melhores e narrativa mais reflexiva)");
                    System.out.println("2 - Ansiedade (Perigos mais intensos e narrativa mais tensa)");
                    System.out.println("3 - Sabedoria (Jogo mais fácil)");
                    System.out.print("Digite o número correspondente à característica: ");
                    int escolha = sc.nextInt();
                    sc.nextLine();

                    String caracteristica = "";
                    int confiancaInicial = 20;

                    switch (escolha) {
                        case 1:
                            caracteristica = "maturidade";
                            confiancaInicial += 10; // Conquistas darão mais confiança
                            digitando("Você escolheu Maturidade. Sua jornada será profunda e suas conquistas mais significativas.", 30);
                            break;
                        case 2:
                            caracteristica = "ansiedade";
                            confiancaInicial -= 10; // Perigos mais fortes
                            digitando("Você escolheu Ansiedade. A jornada será tensa e os perigos mais desafiadores, mas você é forte!", 30);
                            break;
                        case 3:
                            caracteristica = "sabedoria";
                            confiancaInicial += 15; // Começa mais preparado
                            digitando("Você escolheu Sabedoria. Sua experiência o ajudará a tomar decisões mais acertadas no labirinto.", 30);
                            break;
                        default:
                            caracteristica = "neutra";
                            digitando("Nenhuma característica especial foi escolhida. Boa sorte na sua jornada!", 30);
                    }

                    Estudante estudante = new Estudante(nomeEstudante, 0, confiancaInicial);
                    estudante.setCaracteristica(caracteristica); // Supondo que você tenha ou vá criar esse setter

                    digitando("Olá, "+ vermelho + estudante.getNome() + reset + ". Você se encontra em um labirinto que reflete seus desafios internos.", 20);
                    digitando("Sua confiança atual é: " + estudante.getPontosDeConfianca(), 20);
                    digitando("Colete 'Pequenas Conquistas' para aumentar sua confiança e enfrente 'Pensamentos Negativos'.", 20);

                    Labirinto labirinto = new Labirinto(8);

                    digitando("Gerando labirinto... " , 20);

                    labirinto.gerarLabirinto();

                    digitando("O labirinto foi gerado. A exploração começa agora! Quantidade de conquistas no labirinto: " + labirinto.getPequenasConquistasDisponiveis().size(), 20);

                    // Simula interações com conquistas e perigos (você pode adaptar conforme a mecânica)
                    PequenaConquista conquista = labirinto.encontrarPequenaConquistaNaPosicao(3);
                    if (conquista != null) {
                        digitando("Você encontrou uma Pequena Conquista! " + conquista.getNome(), 40);
                        if (caracteristica.equals("maturidade")) {
                            estudante.adicionarPequenaConquista(conquista);
                            estudante.adicionarConfianca(5); // bônus extra
                            digitando("Sua maturidade permitiu absorver ainda mais dessa conquista. +5 de confiança extra!", 30);
                        } else {
                            estudante.adicionarPequenaConquista(conquista);
                        }
                        labirinto.removerPequenaConquista(conquista);
                        digitando("Restam " + labirinto.getPequenasConquistasDisponiveis().size() + " conquistas no labirinto.", 20);
                        digitando("Confiança atual: " + estudante.getPontosDeConfianca(), 20);
                    }

                    PensamentoNegativo pensamento = labirinto.encontrarPensamentoNegativoNaPosicao(4);
                    if (pensamento != null) {
                        if (caracteristica.equals("ansiedade")) {
                            pensamento.aumentarImpactoConfianca();
                        }
                        digitando(pensamento.efeito(estudante), 20);
                        digitando("Confiança atual: " + estudante.getPontosDeConfianca(), 20);
                    }

                    digitando("\nLembre-se, " + vermelho + estudante.getNome() + reset + ", cada passo aqui é um passo em direção a reconhecer seu valor.", 50);
                    digitando("Foco, resiliência e autocompaixão serão seus guias.", 50);

                    //aqui começa o jogo msm
                    int posAtual = estudante.getLocalizacao();
                    boolean jogoAtivo = true;

                    while (jogoAtivo) {
                        labirinto.visualizarLabirinto(estudante);
                        digitando("\nVocê está na posição " + posAtual + ". O que deseja fazer?", 30);
                        System.out.println("1 - Cima");
                        System.out.println("2 - Baixo");
                        System.out.println("3 - Esquerda");
                        System.out.println("4 - Direita");
                        System.out.println("5 - Sair");

                        System.out.print("Escolha sua ação: ");
                        int acao = sc.nextInt();
                        sc.nextLine();

                        int novaPos = posAtual;

                        switch (acao) {
                            case 1:
                                novaPos = posAtual - labirinto.getTamanho(); // andar "para frente" - subir linha
                                break;
                            case 2:
                                novaPos = posAtual + labirinto.getTamanho(); // andar "para trás" - descer linha
                                break;
                            case 3:
                                novaPos = posAtual - 1; // andar para esquerda - diminuir coluna
                                break;
                            case 4:
                                novaPos = posAtual + 1; // andar para direita - aumentar coluna
                                break;
                            case 5:
                                digitando("Você decidiu sair da jornada. Até a próxima!", 40);
                                jogoAtivo = false;
                                continue;
                            default:
                                digitando("Opção inválida. Tente novamente.", 30);
                                continue;
                        }

                        // Verifica se novaPos é válida
                        if (novaPos < 0 || novaPos >= labirinto.getTamanho() * labirinto.getTamanho() || !labirinto.salaLiberada(novaPos)) {
                            digitando("Você bateu numa parede invisível do seu medo. Não é possível seguir por aqui.", 40);
                            continue;
                        }

                        posAtual = novaPos;
                        estudante.setLocalizacao(posAtual);

                        // Verificar se tem pequena conquista na nova posição
                         conquista = labirinto.encontrarPequenaConquistaNaPosicao(posAtual);
                        if (conquista != null) {
                            digitando("Você encontrou uma Pequena Conquista: " + conquista.getNome(), 40);

                            
                            estudante.adicionarPequenaConquista(conquista);
                            labirinto.removerPequenaConquista(conquista);
                            digitando("Restam " + labirinto.getPequenasConquistasDisponiveis().size() + " conquistas no labirinto.", 20);

                            digitando("Sua confiança aumentou para: " + estudante.getPontosDeConfianca(), 30);
                        }

                        // Verificar se tem pensamento negativo
                         pensamento = labirinto.encontrarPensamentoNegativoNaPosicao(novaPos);
                        if (pensamento != null) {
                            digitando("Um pensamento negativo surgiu em sua mente...", 40);
                            digitando(pensamento.efeito(estudante), 40);
                            digitando("Sua confiança agora é: " + estudante.getPontosDeConfianca(), 30);
                        }

                        // Verificar se tem perigo
                        Perigo perigo = labirinto.encontrarPerigoNaPosicao(posAtual);
                        if (perigo != null) {
                            digitando("Você se depara com um perigo: " + perigo.getDescricao(), 40);
                            // Exemplo de impacto do perigo: diminui confiança
                            estudante.diminuiConfianca(perigo.getImpacto());
                            digitando("O perigo abalou sua confiança para: " + estudante.getPontosDeConfianca(), 30);
                        }

                        // Você pode adicionar condição para terminar o jogo (confiança zero ou sair do labirinto)
                        if (estudante.getPontosDeConfianca() <= 0) {
                            digitando("Sua confiança chegou a zero. O labirinto interno ficou muito difícil... Mas nunca desista de você!", 50);
                            jogoAtivo = false;
                            digitando("\nFim de jogo. Obrigado por jogar Campus Internus!", 50);

                        }
                        
                        if (labirinto.todasConquistasColetadas() && labirinto.getSalaSaida() == null) {
                            labirinto.gerarSalaSaida();
                        }

                        if (labirinto.getSalaSaida() != null && estudante.getLocalizacao() == labirinto.getSalaSaida()) {
                            System.out.println(" Você encontrou a saída e venceu o jogo! Parabéns! ");
                            System.exit(0);
                        }
                    }

                    break;

                case 2:
                    digitando("Saindo do jogo. Lembre-se da sua força! Até a próxima!", 50);
                    sc.close();
                    return;

                default:
                    digitando("Opção inválida. Tente novamente.", 50);
            }
        }
    }
}
