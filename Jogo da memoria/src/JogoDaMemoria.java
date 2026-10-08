import java.util.Random;
import java.util.Scanner;

public class JogoDaMemoria {

    static void embaralhar(String[] cartas) {
        Random random = new Random();
        for (int i = cartas.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
    }

    static void mostrarTabuleiro(String[] cartas, boolean[] reveladas) {
        for (int i = 0; i < cartas.length; i++) {
            if (reveladas[i]) {
                System.out.print("[" + cartas[i] + "] ");
            } else {
                System.out.print("[" + i + "] ");
            }
        }
        System.out.println();
    }

    static int lerPosicao(Scanner scanner, boolean[] reveladas, int primeira, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                int pos = Integer.parseInt(entrada.trim());
                if (pos < 0 || pos >= reveladas.length) {
                    System.out.println("Posição invalida. Escolha entre 0 e " + (reveladas.length - 1) + ".");
                } else if (reveladas[pos]) {
                    System.out.println("Essa carta já foi encontrada.");
                } else if (pos == primeira) {
                    System.out.println("Escolha uma carta diferente da primeira.");
                } else {
                    return pos;
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas o número da posição.");
            }
        }
    }

    static void limparTela() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cartas = { "A", "A", "B", "B", "C", "C", "D", "D" };
        boolean[] reveladas = new boolean[cartas.length];

        embaralhar(cartas);

        int TotalPares = cartas.length / 2;
        int paresEncontrados = 0;
        int tentativas = 0;

        while (paresEncontrados < TotalPares) {
            mostrarTabuleiro(cartas, reveladas);

            int pos1 = lerPosicao(scanner, reveladas, -1, "Primeira carta: ");
            int pos2 = lerPosicao(scanner, reveladas, pos1, "Segunda carta: ");
            tentativas++;

            reveladas[pos1] = true;
            reveladas[pos2] = true;
            mostrarTabuleiro(cartas, reveladas);

            if (cartas[pos1].equals(cartas[pos2])) {
                System.out.println("Acertou!! Você encontrou o par!");
                paresEncontrados++;
            } else {
                System.out.println("Não é um par. Memorize as posições!");
                reveladas[pos1] = false;
                reveladas[pos2] = false;
                System.out.print("Pressione Enter para continuar...");
                scanner.nextLine();
                limparTela();
            }
        }

        System.out.println("Parabens!! Você encontru todos os pares em " + tentativas + " tentativas.");
        scanner.close();
    }
}
