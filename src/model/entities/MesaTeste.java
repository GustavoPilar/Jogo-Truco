package model.entities;

import application.UI;

import java.io.IOException;
import java.util.List;

public class MesaTeste {

    private Baralho baralho = new Baralho();

    public MesaTeste() {};

    public void teste() {

        List<Carta> baralhoTeste2 = baralho.BaralhoTeste();

        Valor coringa = baralho.coringa();

        // Verificar coringa
        if (baralhoTeste2.get(2).getValor() == coringa) {
            System.out.println("Carta Jogador: " + baralhoTeste2.get(2).getValor());
            System.out.println("Coringa: " + coringa);
            System.out.println("São iguais: " + (baralhoTeste2.get(2).getValor() == coringa));

            if (baralhoTeste2.get(4).getValor() == coringa) {
                System.out.println("Carta Adversario: " + baralhoTeste2.get(4).getValor());
                System.out.println("Coringa: " + coringa);
                System.out.println("São iguais: " + (baralhoTeste2.get(4).getValor() == coringa));

                Nipe[] naipes = Nipe.values();
                int indiceNipeJogador = 0;
                int indiceNipeAdversario = 0;

                int indiceNipe = 0;
                for (Nipe n : naipes) {
                    if (baralhoTeste2.get(2).getNipe().equals(n)) {
                        indiceNipeJogador = indiceNipe;
                    }
                    if (baralhoTeste2.get(4).getNipe().equals(n)) {
                        indiceNipeAdversario = indiceNipe;
                    }
                    indiceNipe++;
                }

                if (indiceNipeAdversario > indiceNipeJogador) {
                    System.out.println("Jogador ganhou: " + baralhoTeste2.get(2));
                    System.out.println("Adversario perdeu: " + baralhoTeste2.get(4));
                }
                else {
                    System.out.println("Jogador perder: " + baralhoTeste2.get(2));
                    System.out.println("Adversario ganhou: " + baralhoTeste2.get(4));
                }
            }
        }
        else {
            System.out.println("Carta: " + baralho.baralhoTeste.get(2).getValor());
            System.out.println("Coringa: " + coringa);
            System.out.println("São iguais: " + (baralho.baralhoTeste.get(2).getValor() == coringa));;

        }
    }
}
