import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arvore.Arvore;

public class Menu {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean loginBemSucedido = false;


        while (!loginBemSucedido) {
            System.out.print("Login: ");
            String login = scanner.nextLine().trim();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (autenticacaoValida(login, senha)) {
                System.out.println("Login realizado com sucesso!");
                loginBemSucedido = true;
            } else {
                System.out.println("Usuário inválido ou senha incorreta. Tente novamente.\n");
            }
        }

        Arvore produtos = new Arvore();
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Contagem por categoria");
            System.out.println("4 - Listar produtos por faixa de preço");
            System.out.println("5 - Listar menor preço");
            System.out.println("6 - Listar maior preço  ");
            System.out.println("7 - Excluir produto");
            System.out.println("8 - Atualizar dados do produto");
            System.out.println("9 - Sair");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                scanner.next(); 
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); 
            boolean validation = false;

            switch (opcao) {
                case 1:
                produtos.inserir();  
                break;
                
                case 2:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    validation = false;
                    while(!validation){
                        System.err.println("Insira o nome do produto a ser buscado: ");

                        if(!scanner.nextLine().trim().isEmpty()){
                            produtos.mostrarItemSupermercado(scanner.nextLine());
                            validation = true;
                        }else{
                            System.out.println(" O nome do produto pesquisado não pode ser vazio");
                            scanner.nextLine();
                        }
                    }

                    break;

                case 3:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    produtos.mostraQuantidadeItemCategoria();
                    break;

                case 4:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    int max;
                    int min;
                    validation = false;

                    while(validation){//validação para garantir que o usuário insira valores inteiros
                        System.out.print("Digite o valor mínimo: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Digite um número válido para o valor mínimo: ");
                            scanner.next();
                        }
                        min = scanner.nextInt();

                        System.out.print("Digite o valor máximo: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Digite um número válido para o valor máximo: ");
                            scanner.next();
                        }
                        max = scanner.nextInt();

                        if (min < max) {
                            produtos.filtraProdutosPreco(min, max);
                            validation = true;
                        } else {
                            System.out.println("O valor mínimo deve ser menor que o valor máximo. Tente novamente.");
                        }
                    }
                    

                case 5:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    produtos.exibeMenorPreco();
                    break;

                case 6:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    produtos.exibeMaiorPreco();
                    break;

                case 7:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    validation = false;
                    while(!validation){
                        System.out.print("Insira o nome do produto a ser excluído: ");
                        if(!scanner.nextLine().trim().isEmpty()){
                            produtos.removerProduto(scanner.nextLine());
                            validation = true;
                        }else{
                            System.out.println("O nome do produto a ser excluído não pode ser vazio.");
                            scanner.nextLine(); 
                        }   
                    }
                    break;

                case 8:
                    if(produtos.getRaiz() == null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    validation = false;
                    while(!validation){
                        System.out.print("Insira o nome do produto a ser atualizado: ");
                        if(!scanner.nextLine().trim().isEmpty()){
                            produtos.changeProdutoData(scanner.nextLine());
                            validation = true;
                        }else{
                            System.out.println("O nome do produto a ser atualizado não pode ser vazio.");
                            scanner.nextLine();
                        }
                    }
                    break;
                
                case 9:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 3);

        scanner.close();
    }


    public static boolean autenticacaoValida(String login, String senha) {
        String loginPadrao = "123456";
        String senhaPadrao = "admin";

        return loginPadrao.equals(login) && senhaPadrao.equals(senha);
    }
}

