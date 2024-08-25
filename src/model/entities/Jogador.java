package model.entities;

import application.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jogador {
    private Integer pontos;

    private final List<Carta> cartasJogador = new ArrayList<>();

    public Jogador (Integer pontos) {
        this.pontos = pontos;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public void pegarCarta(List<Carta> baralho) {
        cartasJogador.add(baralho.getFirst());
        cartasJogador.add(baralho.get(1));
        cartasJogador.add(baralho.get(2));
    }

    public void removerCarta(int indice) {
        cartasJogador.remove(indice);
    }

    public List<Carta> getCartasJogador() {
        return cartasJogador;
    }

    public String mostrarMao() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Carta c : cartasJogador) {
            sb.append("[" + (i) + "] - " + c + " ");
            i++;
        }

        return sb.toString();
    }

    public int jogarCarta() {
        int escolha = 0;

        System.out.println("Suas cartas: " + mostrarMao());

        while (escolha <= 0 || escolha > cartasJogador.size()) {
            System.out.print("Escolha uma carta pelo seu indice: ");
            escolha = Main.sc.nextInt();
        }

        return (escolha - 1);
    }
}
