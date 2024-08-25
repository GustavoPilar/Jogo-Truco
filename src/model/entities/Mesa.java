package model.entities;

public final class Mesa {

    private final Baralho baralho;
    private final Jogador jogador;
    private final Adversario adversario;

    public Mesa(Baralho baralho, Jogador jogador, Adversario adversario) {
        this.baralho = baralho;
        this.jogador = jogador;
        this.adversario = adversario;
    }

    public void mostrarMesa(Carta jogador, Carta adversario) {
        System.out.println("\n\nAdversário");
        System.out.println("|----------------|");
        System.out.println("| " + adversario);
        System.out.println("| ");
        System.out.println("| " + baralho.coringa());
        System.out.println("|");
        System.out.println("| " + jogador);
        System.out.println("|----------------|");
        System.out.println(" Jogador\n");
    }

    public void iniciar() {
        boolean gameOver = false;

        this.baralho.embaralhar();
        this.jogador.pegarCarta(baralho.mostrarBaralho());
        this.adversario.pegarCarta(baralho.mostrarBaralho());

        do {
            boolean venceuRodada = jogadorComeca();
            int subPontosJogador = 0;
            int subPontosAdversario = 0;

            System.out.println("Adversario: " + adversario.getPontos());
            System.out.println("Jogador: " + jogador.getPontos());
            do {

                if (venceuRodada) {
                    subPontosJogador++;
                    System.out.println("Você venceu.");
                    System.out.println("Subpontos: " + subPontosJogador + " x " + subPontosAdversario);
                    venceuRodada = jogadorComeca();
                }
                else {
                    subPontosAdversario++;
                    System.out.println("Você perdeu.");
                    System.out.println("Subpontos: " + subPontosJogador + " x " + subPontosAdversario);
                    venceuRodada = adversarioComeca();
                }

                if (subPontosAdversario == 2) {
                    adversario.setPontos(adversario.getPontos() + 1);
                }
                else if (subPontosJogador == 2){
                    jogador.setPontos(jogador.getPontos() + 1);
                }

            } while (subPontosJogador != 2 && subPontosAdversario != 2);

            if (jogador.getPontos() >= 12 || adversario.getPontos() >= 12) {
                gameOver = true;
            }

        } while (!gameOver);


    }

    public boolean jogadorComeca() {
        Carta cartaEscolhida = jogador.getCartasJogador().get(jogador.jogarCarta());
        jogador.removerCarta(jogador.getCartasJogador().indexOf(cartaEscolhida));

        Carta cartaAdversario = adversario.getCartaAdversario().get(adversario.jogarCarta());
        adversario.removerCarta(adversario.getCartaAdversario().indexOf(cartaAdversario));

        mostrarMesa(cartaEscolhida, cartaAdversario);

        return maior(cartaEscolhida, cartaAdversario, baralho.mostrarBaralho().getLast());
    }

    public boolean adversarioComeca() {
        Carta cartaAdversario = adversario.getCartaAdversario().get(adversario.jogarCarta());
        adversario.removerCarta(adversario.getCartaAdversario().indexOf(cartaAdversario));

        System.out.println("Coringa: " + baralho.coringa());
        System.out.println("Carta escolhida do adversario: " + cartaAdversario);

        Carta cartaEscolhida = jogador.getCartasJogador().get(jogador.jogarCarta());
        jogador.removerCarta(jogador.getCartasJogador().indexOf(cartaEscolhida));

        mostrarMesa(cartaEscolhida, cartaAdversario);

        return maior(cartaEscolhida, cartaAdversario, baralho.mostrarBaralho().getLast());
    }

    public boolean maior(Carta jogador, Carta adversario, Carta coringa) {
        Nipe[] naipes = Nipe.values();
        int indiceNipeJogador = 0;
        int indiceNipeAdversario = 0;

        int indiceNipe = 0;
        for (Nipe n : naipes) {
            if (jogador.getValor().equals(n)) {
                indiceNipeJogador = indiceNipe;
            }
            if (adversario.getValor().equals(n)) {
                indiceNipeAdversario = indiceNipe;
            }
            indiceNipe++;
        }

        Valor[] valores = Valor.values();

        int indiceValorJogador = 0;
        int indiceValorAdversario = 0;
        int indiceValorCoringa = 0;

        int inidiceValor = 0;
        for (Valor v : valores) {
            if (jogador.getValor().equals(v)) {
                indiceValorJogador = inidiceValor;
            }
            if (adversario.getValor().equals(v)) {
                indiceValorAdversario = inidiceValor;
            }
            if (coringa.getValor().equals(v)) {
                indiceValorCoringa = inidiceValor;
            }
            inidiceValor++;
        }

        // Verificar coringa
        if (indiceValorJogador == indiceValorCoringa) {
            if (indiceValorAdversario != indiceValorCoringa) {
                return true;
            }
            else {
                if (indiceNipeJogador < indiceNipeAdversario) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        // Verificar carta maior
        if (indiceValorJogador > indiceValorAdversario) {
            return true;
        }
        else {
            return false;
        }
    }
}
