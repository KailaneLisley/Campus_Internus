// Source code is decompiled from a .class file using FernFlower decompiler.
package classes;

import classes.negativas.AutocriticaExcessiva;
import classes.negativas.DuvidaParalisante;
import classes.negativas.MedoDeExposicao;
import classes.negativas.PensamentoNegativo;
import classes.negativas.Perigo;
import classes.positivas.ChaveDaAutoestima;
import classes.positivas.ConquistaAcademica;
import classes.positivas.ConquistaPessoal;
import classes.positivas.PequenaConquista;
import classes.positivas.SuperacaoDeDesafio;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Labirinto {
   private List<PequenaConquista> pequenasConquistasDisponiveis;
   private List<PensamentoNegativo> pensamentosNegativos;
   private Set<Integer> salasTrancadas;
   private List<List<String>> estrutura;
   private int tamanho;
   private List<Perigo> perigos;
   private Integer salaSaida = null;

   public List<PequenaConquista> getPequenasConquistasDisponiveis() {
      return this.pequenasConquistasDisponiveis;
   }

   public Labirinto(int var1) {
      this.tamanho = var1;
      this.pequenasConquistasDisponiveis = new ArrayList();
      this.pensamentosNegativos = new ArrayList();
      this.salasTrancadas = new HashSet();
      this.estrutura = new ArrayList();
      this.perigos = new ArrayList();
      this.inicializarLabirinto();
      this.gerarLabirinto();
   }

   private void inicializarLabirinto() {
      this.pequenasConquistasDisponiveis.add(new ConquistaAcademica("Elogio do Professor", 3));
      this.pequenasConquistasDisponiveis.add(new ConquistaPessoal("Pausa para Relaxar", 7));
      this.pequenasConquistasDisponiveis.add(new SuperacaoDeDesafio("Entregar trabalho difÃ\u00adcil", 10));
      this.pequenasConquistasDisponiveis.add(new ChaveDaAutoestima("AceitaÃ§Ã£o das ImperfeiÃ§Ãµes", 5, 12));
      this.pensamentosNegativos.add(new AutocriticaExcessiva(4));
      this.pensamentosNegativos.add(new MedoDeExposicao(5));
      this.pensamentosNegativos.add(new DuvidaParalisante(6));
      this.salasTrancadas.add(12);
   }

   public void visualizarLabirinto(Estudante var1) {
      int var2 = var1.getLocalizacao();
      String var10000 = var1.getNome();
      String var3 = "\u001b[31m" + var10000.substring(0, 1).toUpperCase() + "\u001b[0m";

      for(int var4 = 0; var4 < this.tamanho; ++var4) {
         for(int var5 = 0; var5 < this.tamanho; ++var5) {
            int var6 = var4 * this.tamanho + var5;
            if (var6 == var2) {
               System.out.print(var3 + " ");
            } else if (!this.salaLiberada(var6)) {
               System.out.print("L ");
            } else if (this.encontrarPequenaConquistaNaPosicao(var6) != null) {
               System.out.print("\u001b[32mC\u001b[0m ");
            } else if (this.encontrarPerigoNaPosicao(var6) != null) {
               System.out.print("\u001b[35mP\u001b[0m ");
            } else if (this.salaSaida != null && var6 == this.salaSaida) {
               System.out.print("\u001b[36mS\u001b[0m ");
            } else {
               System.out.print("= ");
            }
         }

         System.out.println();
      }

   }

   public boolean isPosicaoValida(int var1) {
      return var1 >= 0 && var1 < this.tamanho * this.tamanho && this.salaLiberada(var1);
   }

   public PequenaConquista encontrarPequenaConquistaNaPosicao(int var1) {
      Iterator var2 = this.pequenasConquistasDisponiveis.iterator();

      PequenaConquista var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (PequenaConquista)var2.next();
      } while(var3.getLocalizacao() != var1);

      return var3;
   }

   public PensamentoNegativo encontrarPensamentoNegativoNaPosicao(int var1) {
      Iterator var2 = this.pensamentosNegativos.iterator();

      PensamentoNegativo var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (PensamentoNegativo)var2.next();
      } while(var3.getLocalizacao() != var1);

      return var3;
   }

   public void removerPequenaConquista(PequenaConquista var1) {
      this.pequenasConquistasDisponiveis.remove(var1);
   }

   public void adicionarPequenaConquista(PequenaConquista var1) {
      this.pequenasConquistasDisponiveis.add(var1);
   }

   public boolean salaLiberada(int var1) {
      int var2 = var1 / this.tamanho;
      int var3 = var1 % this.tamanho;
      return ((String)((List)this.estrutura.get(var2)).get(var3)).equals("*") && !this.salasTrancadas.contains(var1);
   }

   public void desbloquearSala(int var1) {
      this.salasTrancadas.remove(var1);
      System.out.println("A sala " + var1 + " foi desbloqueada!");
   }

   public void adicionarPensamentoNegativo(PensamentoNegativo var1) {
      this.pensamentosNegativos.add(var1);
   }

   public void removerPensamentoNegativo(PensamentoNegativo var1) {
      this.pensamentosNegativos.remove(var1);
   }

   public int getTamanho() {
      return this.tamanho;
   }

   public Perigo encontrarPerigoNaPosicao(int var1) {
      Iterator var2 = this.perigos.iterator();

      Perigo var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (Perigo)var2.next();
      } while(var3.getLocalizacao() != var1);

      return var3;
   }

   public void adicionarPerigo(Perigo var1) {
      this.perigos.add(var1);
   }

   public void removerPerigo(Perigo var1) {
      this.perigos.remove(var1);
   }
public void gerarLabirinto() {
    Random random = new Random();
    int totalSalas = this.tamanho * this.tamanho;

    this.estrutura.clear();
    this.salasTrancadas.clear();

    // Geração inicial da estrutura do labirinto
    for (int i = 0; i < this.tamanho; i++) {
        ArrayList<String> linha = new ArrayList<>();

        for (int j = 0; j < this.tamanho; j++) {
            int index = i * this.tamanho + j;
            if (random.nextInt(100) < 70) {
                linha.add("*");
            } else {
                linha.add("X");
                this.salasTrancadas.add(index);
            }
        }

        this.estrutura.add(linha);
    }

    // Garante que a sala 0 (início do jogador) esteja sempre liberada
    this.estrutura.get(0).set(0, "*");
    this.salasTrancadas.remove(0);

    // Tentativas de posicionamento acessível de conquistas
    boolean sucesso = false;
    int tentativas = 0;
    int maxTentativas = 1000;

    while (tentativas < maxTentativas && !sucesso) {
        tentativas++;
        this.pequenasConquistasDisponiveis.clear();
        this.pensamentosNegativos.clear();

        Set<Integer> ocupadas = new HashSet<>();

        // Lista temporária de conquistas
        List<PequenaConquista> conquistas = new ArrayList<>();
        conquistas.add(new ConquistaAcademica("Elogio do Professor", 3));
        conquistas.add(new ConquistaPessoal("Pausa para Relaxar", 7));
        conquistas.add(new SuperacaoDeDesafio("Entregar trabalho difícil", 10));
        conquistas.add(new ChaveDaAutoestima("Aceitação das Imperfeições", 5, 12));

        // Posiciona conquistas
        for (PequenaConquista conquista : conquistas) {
            int posicao;
            do {
                posicao = random.nextInt(totalSalas);
            } while (this.salasTrancadas.contains(posicao) || ocupadas.contains(posicao));

            conquista.setLocalizacao(posicao);
            this.pequenasConquistasDisponiveis.add(conquista);
            ocupadas.add(posicao);
        }

        // Lista de pensamentos negativos
        List<PensamentoNegativo> pensamentos = new ArrayList<>();
        pensamentos.add(new AutocriticaExcessiva(4));
        pensamentos.add(new MedoDeExposicao(5));
        pensamentos.add(new DuvidaParalisante(6));

        // Posiciona pensamentos
        for (PensamentoNegativo pn : pensamentos) {
            int posicao;
            do {
                posicao = random.nextInt(totalSalas);
            } while (this.salasTrancadas.contains(posicao) || ocupadas.contains(posicao));

            pn.setLocalizacao(posicao);
            this.pensamentosNegativos.add(pn);
            ocupadas.add(posicao);
        }

        // Verifica se todas as conquistas são acessíveis a partir da sala 0
        sucesso = true;
        for (PequenaConquista conquista : this.pequenasConquistasDisponiveis) {
            if (!this.ehAcessivel(0, conquista.getLocalizacao())) {
                sucesso = false;
                break;
            }
        }
    }

    if (!sucesso) {
        throw new RuntimeException("Não foi possível gerar um labirinto acessível após várias tentativas.");
    }
}

   

   public boolean todasConquistasColetadas() {
      return this.pequenasConquistasDisponiveis.isEmpty();
   }

   private boolean ehAcessivel(int var1, int var2) {
      if (this.salaLiberada(var1) && this.salaLiberada(var2)) {
         HashSet var3 = new HashSet();
         LinkedList var4 = new LinkedList();
         var4.add(var1);
         var3.add(var1);

         while(!var4.isEmpty()) {
            int var5 = (Integer)var4.poll();
            if (var5 == var2) {
               return true;
            }

            int var6 = var5 / this.tamanho;
            int var7 = var5 % this.tamanho;
            int[][] var8 = new int[][]{{var6 - 1, var7}, {var6 + 1, var7}, {var6, var7 - 1}, {var6, var7 + 1}};
            int[][] var9 = var8;
            int var10 = var8.length;

            for(int var11 = 0; var11 < var10; ++var11) {
               int[] var12 = var9[var11];
               int var13 = var12[0];
               int var14 = var12[1];
               if (var13 >= 0 && var13 < this.tamanho && var14 >= 0 && var14 < this.tamanho) {
                  int var15 = var13 * this.tamanho + var14;
                  if (this.isPosicaoValida(var15) && !var3.contains(var15)) {
                     var3.add(var15);
                     var4.add(var15);
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void gerarSalaSaida() {
      Random var1 = new Random();
      int var2 = this.tamanho * this.tamanho;

      int var3;
      do {
         do {
            var3 = var1.nextInt(var2);
         } while(!this.isPosicaoValida(var3));
      } while(var3 == 0);

      this.salaSaida = var3;
      System.out.println("A sala de saída foi revelada! Va até a sala " + this.salaSaida + " para vencer!");
   }

   public Integer getSalaSaida() {
      return this.salaSaida;
   }
}
