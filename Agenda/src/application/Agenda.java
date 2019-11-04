package application;

import java.util.Scanner;
import agenda.entities.Usuarios;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Usuarios> registros = new ArrayList<>();

        System.out.println("Quanto usuários deseja cadastrar?");
        System.out.print("Quantidade: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nUsuário " + i);
            
            System.out.print("Digite o nome do usuário: ");
            String nome = sc.nextLine();
            
            System.out.print("Digite o e-mail: ");
            String email = sc.nextLine();
            
            System.out.print("Digite a senha: ");
            String senha = sc.nextLine();
            
            System.out.print("Digite a senha novamente");
            String confirmacao = sc.nextLine();
            
            Boolean senhaIgual = senha.equals(confirmacao);
            
            if (!senhaIgual) {
                do {
                    System.out.println("Senhas inseridas estão diferentes!");
                    System.out.println("Digite novamente as senhas!");
                    
                    System.out.print("\nDigite a senha: ");
                    senha = sc.nextLine();
                    
                    System.out.print("Digite a senha novamente");
                    confirmacao = sc.nextLine();
                    
                    senhaIgual = senha.equals(confirmacao);
                } while (!senhaIgual);
            }

            Usuarios novoRegistro = new Usuarios(nome, email, senha);

            registros.add(novoRegistro);
        }

        System.out.println();

        for (Usuarios reg : registros) {
            System.out.println(reg);
        }

        sc.close();
    }

}
