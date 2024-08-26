package model.entities;

import application.UI;

import java.io.IOException;

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
        System.out.println("Jogador: " + this.jogador.getPontos());
        System.out.println("Adversario: " + this.adversario.getPontos());

        System.out.println("\n\nAdversário");
        System.out.println("|----------------|");
        System.out.println("| " + adversario);
        System.out.println("| ");
        System.out.println("| " + baralho.getCoringa());
        System.out.println("|");
        System.out.println("| " + jogador);
        System.out.println("|----------------|");
        System.out.println(" Jogador\n");
    }

    public void iniciar() throws IOException, InterruptedException {
        boolean gameOver = false;

        do {

            this.baralho.embaralhar();
            this.jogador.pegarCarta(baralho.getBaralho());
            this.adversario.pegarCarta(baralho.getBaralho());

            UI.clearScreen();

            System.out.println("Jogador: " + this.jogador.getPontos());
            System.out.println("Adversario: " + this.adversario.getPontos());

            System.out.println("\n\n Adversário");
            System.out.println("|----------------|");
            System.out.println("| ");
            System.out.println("| ");
            System.out.println("| " + baralho.getCoringa());
            System.out.println("|");
            System.out.println("| ");
            System.out.println("|----------------|");
            System.out.println(" Jogador\n");

            boolean venceuRodada = jogadorComeca();
            int subPontosJogador = 0;
            int subPontosAdversario = 0;

            do {

                if (venceuRodada) {
                    subPontosJogador++;
                    System.out.println("Você venceu.");
                    System.out.println("Subpontos: " + subPontosJogador + " x " + subPontosAdversario);

                    if (subPontosJogador == 2){
                        System.out.println("Você ganhou esta rodada.");
                        jogador.setPontos(jogador.getPontos() + 1);
                        jogador.devolverCartas();
                        adversario.devolverCartas();
                        UI.pause();
                        break;
                    }

                    venceuRodada = jogadorComeca();
                }
                else {
                    subPontosAdversario++;
                    System.out.println("Você perdeu.");
                    System.out.println("Subpontos: " + subPontosJogador + " x " + subPontosAdversario);

                    if (subPontosAdversario == 2) {
                        System.out.println("Adversario ganhou esta rodada.");
                        adversario.setPontos(adversario.getPontos() + 1);
                        jogador.devolverCartas();
                        adversario.devolverCartas();
                        UI.pause();
                        break;
                    }
                    venceuRodada = adversarioComeca();
                }

            } while (subPontosJogador != 2 && subPontosAdversario != 2);

            if (jogador.getPontos() >= 12 || adversario.getPontos() >= 12) {
                gameOver = true;
            }

        } while (!gameOver);

    }

    public boolean jogadorComeca() throws IOException, InterruptedException {
        Carta cartaEscolhida = jogador.jogarCarta();

        Carta cartaAdversario = adversario.jogarCarta();

        UI.clearScreen();
        mostrarMesa(cartaEscolhida, cartaAdversario);

        return maior(cartaEscolhida, cartaAdversario, baralho.coringa());
    }

    public boolean adversarioComeca() throws IOException, InterruptedException {
        Carta cartaAdversario = adversario.jogarCarta();

        System.out.println("Coringa: " + baralho.getCoringa());
        System.out.println("Carta escolhida do adversario: " + cartaAdversario);

        Carta cartaEscolhida = jogador.jogarCarta();

        UI.clearScreen();
        mostrarMesa(cartaEscolhida, cartaAdversario);

        return maior(cartaEscolhida, cartaAdversario, baralho.getCoringa());
    }

    public boolean maior(Carta jogador, Carta adversario, Valor coringa) {
        Nipe[] naipes = Nipe.values();
        int indiceNipeJogador = 0;
        int indiceNipeAdversario = 0;

        int indiceNipe = 0;
        for (Nipe n : naipes) {
            if (jogador.getNipe().equals(n)) {
                indiceNipeJogador = indiceNipe;
            }
            if (adversario.getNipe().equals(n)) {
                indiceNipeAdversario = indiceNipe;
            }
            indiceNipe++;
        }

        Valor[] valores = Valor.values();
        int indiceValorJogador = 0;
        int indiceValorAdversario = 0;

        int inidiceValor = 0;
        for (Valor v : valores) {
            if (jogador.getValor().equals(v)) {
                indiceValorJogador = inidiceValor;
            }
            if (adversario.getValor().equals(v)) {
                indiceValorAdversario = inidiceValor;
            }
            inidiceValor++;
        }

        // VERIFICAR CORINGA
        if (jogador.getValor() == coringa) {

            if (adversario.getValor() != coringa) {
                return true;
            }
            else {
                return indiceNipeAdversario > indiceNipeJogador;
            }
        }

        // VERIFICAR A MAIOR
        if (indiceValorJogador > indiceValorAdversario) {
            return true;
        }
        else if (indiceValorJogador == indiceValorAdversario){
            return indiceNipeAdversario > indiceNipeJogador;
        }
        else {
            return false;
        }
    }
}
