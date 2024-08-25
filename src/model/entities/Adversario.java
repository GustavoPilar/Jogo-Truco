package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Adversario extends Jogador {

    private final List<Carta> cartaAdversario = new ArrayList<>();

    public Adversario(Integer pontos) {
        super(pontos);
    }

    public List<Carta> getCartaAdversario() {
        return cartaAdversario;
    }

    @Override
    public void pegarCarta(List<Carta> baralho) {
        cartaAdversario.add(baralho.get(3));
        cartaAdversario.add(baralho.get(4));
        cartaAdversario.add(baralho.get(5));
    }

    @Override
    public void removerCarta(int indice) {
        cartaAdversario.remove(indice);
    }

    @Override
    public String mostrarMao() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Carta c : cartaAdversario) {
            sb.append("[" + (i) + "] - " + c);
            i++;
        }

        return sb.toString();
    }

    @Override
    public int jogarCarta() {
        return new Random().nextInt(cartaAdversario.size());
    }
}
