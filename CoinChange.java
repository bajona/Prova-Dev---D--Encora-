import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CoinChange {
    // Método principal
    public static void main(String[] args) {
        // Criando um scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Solicitando ao usuário o número de centavos
        System.out.print("Digite o número de centavos: ");
        //Passando o valor a variável N 
        int n = scanner.nextInt();
        
        // Chamando a função makeChange para calcular as combinações de moedas
        Set<int[]> result = makeChange(n);
        
        // Exibindo o resultado
        for (int[] combination : result) {
            System.out.println("Quarters: " + combination[0] + ", Dimes: " + combination[1] +
                    ", Nickels: " + combination[2] + ", Pennies: " + combination[3]);
        }
        
        // Fechando o scanner
        scanner.close();
    }

    // Função para calcular as combinações de moedas
    public static Set<int[]> makeChange(int n) {
        // Conjunto para armazenar as combinações de moedas
        Set<int[]> result = new HashSet<>();
        
        // Chamada ao método auxiliar para calcular as combinações
        makeChangeHelper(n, 0, new int[4], result);
        
        // Retornando o conjunto de combinações
        return result;
    }

    // Método auxiliar recursivo para calcular as combinações
    private static void makeChangeHelper(int remaining, int index, int[] current, Set<int[]> result) {
        // Caso base: se não há mais centavos restantes, adiciona a combinação atual ao conjunto
        if (remaining == 0) {
            result.add(current.clone());
            return;
        }

        // Caso base: se chegamos ao final da lista de moedas (quarters, dimes, nickels, pennies)
        if (index == 4) {
            return;
        }

        // Valor da moeda atual dependendo do índice
        int coinValue = 0;
        switch (index) {
            case 0:
                coinValue = 25;
                break;
            case 1:
                coinValue = 10;
                break;
            case 2:
                coinValue = 5;
                break;
            case 3:
                coinValue = 1;
                break;
        }

        // Loop para tentar diferentes quantidades da moeda atual
        for (int i = 0; i <= remaining / coinValue; i++) {
            current[index] = i; // Define a quantidade atual da moeda
            // Chamada recursiva com o valor restante e o próximo índice
            makeChangeHelper(remaining - i * coinValue, index + 1, current, result);
        }
    }
}
