package sudoku;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        while (true) {

            System.out.println("\n1 - Inserir número");
            System.out.println("2 - Remover número");
            System.out.println("3 - Mostrar tabuleiro");
            System.out.println("4 - Verificar status");
            System.out.println("5 - Resetar");
            System.out.println("6 - Sair");

            int op = sc.nextInt();

            switch (op) {

                case 1:
                    System.out.print("Linha (0-8): ");
                    int row = sc.nextInt();

                    System.out.print("Coluna (0-8): ");
                    int col = sc.nextInt();

                    System.out.print("Valor (1-9): ");
                    int val = sc.nextInt();

                    if (!board.insert(row, col, val)) {
                        System.out.println("Jogada inválida!");
                    }
                    break;

                case 2:
                    System.out.print("Linha: ");
                    row = sc.nextInt();

                    System.out.print("Coluna: ");
                    col = sc.nextInt();

                    board.remove(row, col);
                    break;

                case 3:
                    board.printBoard();
                    break;

                case 4:
                    if (board.hasErrors()) {
                        System.out.println("Tem erros!");
                    } else if (board.isFinished()) {
                        System.out.println("Você venceu!");
                    } else {
                        System.out.println("Tudo certo até agora");
                    }
                    break;

                case 5:
                    board.reset();
                    System.out.println("Resetado!");
                    break;

                case 6:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}