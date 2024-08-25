package model.entities;

public final class Carta {

    private Valor valor;
    private Nipe nipe;

    public Carta(Valor valor, Nipe nipe) {
        this.valor = valor;
        this.nipe = nipe;
    }

    public Valor getValor() {
        return this.valor;
    }

    public Nipe getNipe() {
        return this.nipe;
    }

    @Override
    public String toString() {
        return getValor() + " DE " + getNipe();
    }
}
