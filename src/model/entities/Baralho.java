package model.entities;

import java.util.*;

public final class Baralho {

    private List<Carta> baralho = new ArrayList<>();

    public Baralho() {
    }

    public List<Carta> mostrarBaralho() {
        return baralho;
    }

    public void embaralhar() {
        Random random = new Random();
        Nipe[] naipes = Nipe.values();
        Valor[] valores = Valor.values();
        Set<Carta> conjunto = new HashSet<>();

        for (int i = 0; i < 7; i++) {
            Carta carta = new Carta(valores[random.nextInt(valores.length)], naipes[random.nextInt(naipes.length)]);

            while (conjunto.contains(carta)) {
                carta = new Carta(valores[random.nextInt(valores.length)], naipes[random.nextInt(naipes.length)]);
            }

            conjunto.add(carta);
        }

        baralho = new ArrayList<>(conjunto);
    }

    public Valor coringa() {
        return baralho.getLast().getValor();
    }

}
