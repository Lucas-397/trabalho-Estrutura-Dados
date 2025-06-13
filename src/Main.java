
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loginBemSucedido = false;


        while (!loginBemSucedido) {
            System.out.print("Login: ");
            String login = scanner.nextLine().trim();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (autenticacaoValida(login, senha)) {
                System.out.println("✅ Login realizado com sucesso!");
                loginBemSucedido = true;
            } else {
                System.out.println("❌ Usuário inválido ou senha incorreta. Tente novamente.\n");
            }
        }

        List<String> produtos = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Consultar Produtos");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido (1, 2 ou 3): ");
                scanner.next(); 
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do produto: ");
                    String produto = scanner.nextLine();
                    produtos.add(produto);
                    System.out.println("✅ Produto cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n📦 Produtos cadastrados:");
                    if (produtos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println((i + 1) + " - " + produtos.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("👋 Saindo do sistema. Até logo!");
                    break;

                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 3);

        scanner.close();
    }


    public static boolean autenticacaoValida(String login, String senha) {
        final String LOGIN_PADRAO = "admin";
        final String SENHA_PADRAO = "senha";

        return LOGIN_PADRAO.equals(login) && SENHA_PADRAO.equals(senha);
    }
}
