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
      Random var1 = new Random();
      int var2 = this.tamanho * this.tamanho;
      this.estrutura.clear();
      this.salasTrancadas.clear();

      for(int var3 = 0; var3 < this.tamanho; ++var3) {
         ArrayList var4 = new ArrayList();

         for(int var5 = 0; var5 < this.tamanho; ++var5) {
            int var6 = var3 * this.tamanho + var5;
            if (var1.nextInt(100) < 70) {
               var4.add("*");
            } else {
               var4.add("X");
               this.salasTrancadas.add(var6);
            }
         }

         this.estrutura.add(var4);
      }

      boolean var10 = false;

      while(true) {
         while(!var10) {
            this.pequenasConquistasDisponiveis.clear();
            this.pensamentosNegativos.clear();
            HashSet var11 = new HashSet();
            ArrayList var12 = new ArrayList();
            var12.add(new ConquistaAcademica("Elogio do Professor", 3));
            var12.add(new ConquistaPessoal("Pausa para Relaxar", 7));
            var12.add(new SuperacaoDeDesafio("Entregar trabalho difÃ\u00adcil", 10));
            var12.add(new ChaveDaAutoestima("AceitaÃ§Ã£o das ImperfeiÃ§Ãµes", 5, 12));
            Iterator var13 = var12.iterator();

            while(var13.hasNext()) {
               PequenaConquista var7 = (PequenaConquista)var13.next();

               int var8;
               do {
                  var8 = var1.nextInt(var2);
               } while(this.salasTrancadas.contains(var8) || var11.contains(var8));

               var7.setLocalizacao(var8);
               this.pequenasConquistasDisponiveis.add(var7);
               var11.add(var8);
            }

            ArrayList var14 = new ArrayList();
            var14.add(new AutocriticaExcessiva(4));
            var14.add(new MedoDeExposicao(5));
            var14.add(new DuvidaParalisante(6));
            Iterator var15 = var14.iterator();

            while(var15.hasNext()) {
               PensamentoNegativo var16 = (PensamentoNegativo)var15.next();

               int var9;
               do {
                  var9 = var1.nextInt(var2);
               } while(this.salasTrancadas.contains(var9) || var11.contains(var9));

               var16.setLocalizacao(var9);
               this.pensamentosNegativos.add(var16);
               var11.add(var9);
            }

            var10 = true;
            var15 = this.pequenasConquistasDisponiveis.iterator();

            while(var15.hasNext()) {
               PequenaConquista var17 = (PequenaConquista)var15.next();
               if (!this.ehAcessivel(0, var17.getLocalizacao())) {
                  var10 = false;
                  break;
               }
            }
         }

         return;
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
      System.out.println("âš  A sala de saÃ\u00adda foi revelada! VÃ¡ atÃ© a sala " + this.salaSaida + " para vencer!");
   }

   public Integer getSalaSaida() {
      return this.salaSaida;
   }
}
