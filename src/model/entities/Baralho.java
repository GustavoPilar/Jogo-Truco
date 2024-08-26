package model.entities;

import java.util.*;

public final class Baralho {

    private Valor coringa;

    public List<Carta> baralhoTeste = Arrays.asList(
            new Carta(Valor.TRES, Nipe.COPAS),
            new Carta(Valor.DAMA, Nipe.PAUS),
            new Carta(Valor.SETE, Nipe.ESPADAS),
            new Carta(Valor.REI, Nipe.COPAS),
            new Carta(Valor.SETE, Nipe.OUROS),
            new Carta(Valor.AS, Nipe.PAUS)
    );

    private List<Carta> baralho = new ArrayList<>();

    public Baralho() {
    }

    public List<Carta> BaralhoTeste() {return baralhoTeste;}

    public List<Carta> getBaralho() {
        return baralho;
    }

    public Valor getCoringa() {
        return coringa;
    }

    public List<Carta> mostrarBaralho() {
        System.out.println("primeiro: " + baralho.getFirst() + " , " + baralho.get(0));
        System.out.println("Segundo: " + baralho.get(1));
        System.out.println("Terceiro: " + baralho.get(2));
        System.out.println("Quarto: " + baralho.get(3));
        System.out.println("Quinto: " + baralho.get(4));
        System.out.println("Sexto: " + baralho.get(5));
        System.out.println("Coringa: " + coringa());
        System.out.println("Tamanho: " + baralho.size());
        return baralho;
    }

    public void embaralhar() {
        Random random = new Random();
        Nipe[] naipes = Nipe.values();
        Valor[] valores = Valor.values();
        Set<Carta> conjunto = new HashSet<>();

        for (int i = 0; i < 6; i++) {
            Carta carta = new Carta(valores[random.nextInt(valores.length)], naipes[random.nextInt(naipes.length)]);

            while (conjunto.contains(carta)) {
                carta = new Carta(valores[random.nextInt(valores.length)], naipes[random.nextInt(naipes.length)]);
            }

            conjunto.add(carta);
        }

        baralho = new ArrayList<>(conjunto);
        this.coringa = coringa();
    }

    public Valor coringa() {
        Valor[] valores = Valor.values();
        return valores[new Random().nextInt(valores.length)];
    }

}
