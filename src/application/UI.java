package application;

import model.entities.*;

public final class UI {

    public static Jogador jogador = new Jogador(0);
    public static Adversario adversario = new Adversario(0);
    public static Baralho baralho = new Baralho();

    public static void init() {
        Integer opcao;
        do {
            System.out.println("|----------------|");
            System.out.println("| [1] - Começar  |");
            System.out.println("| [2] - Sair     |");
            System.out.println("|----------------|");
            System.out.print("Digite 1 para começar o jogo: ");
            opcao = Main.sc.nextInt();

            switch (opcao) {
                case 1 -> jogar();
                case 2 -> System.out.println("Saindo...");
                default -> System.out.println("Valor inválido.");
            }
        } while (!opcao.equals(2));
    }

    public static void jogar() {
        baralho.embaralhar();
        Mesa mesa = new Mesa(baralho, jogador, adversario);

        mesa.iniciar();
    }
}
