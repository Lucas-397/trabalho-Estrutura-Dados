import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String usuario = "";
        String senha = "";
        Scanner scanner = new Scanner(System.in);


        do{
            System.out.println("Usuario não logado");
            System.out.println("Insira o Usuario: ");
            usuario = scanner.nextLine();

            System.out.println("Insira a Senha: ");
            senha = scanner.nextLine();

            
        }while(!(senha.equals("admin") && usuario.equals("12345")));

        System.out.println("Logado: ");

    }
}

