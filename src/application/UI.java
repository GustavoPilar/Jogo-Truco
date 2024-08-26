package application;

import model.entities.*;

import java.io.IOException;

public final class UI {

    public static Jogador jogador = new Jogador(0);
    public static Adversario adversario = new Adversario(0);
    public static Baralho baralho = new Baralho();

    public static void init() throws IOException, InterruptedException {
        Integer opcao;
        do {
            clearScreen();
            System.out.println("|----------------|");
            System.out.println("| [1] - Começar  |");
            System.out.println("| [2] - Teste    |");
            System.out.println("| [3] - Sair     |");
            System.out.println("|----------------|");
            System.out.print("Digite 1 para começar o jogo: ");
            opcao = Main.sc.nextInt();

            switch (opcao) {
                case 1 -> jogar();
                case 2 -> teste();
                case 3 -> System.out.println("Saindo...");
                default -> System.out.println("Valor inválido.");
            }
        } while (!opcao.equals(2));
    }

    public static void jogar() throws IOException, InterruptedException {
        Mesa mesa = new Mesa(baralho, jogador, adversario);

        mesa.iniciar();
    }

    public static void teste() {
        MesaTeste mesaTeste = new MesaTeste();

        mesaTeste.teste();
    }

    public static void clearScreen() throws IOException, InterruptedException {
        if(System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
        }
    }

    public static void pause() throws InterruptedException {
        System.out.println("Aperta qualquer tecla para continuar...");
        char tecla = Main.sc.next().charAt(0);

        for (int i = 1; i < 4; i++) {
            System.out.print(i + " ");
            Thread.sleep(1000);
        }
        System.out.println();
    }
}
