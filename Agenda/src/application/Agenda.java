package application;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import agenda.entities.Usuarios;
import agenda.entities.Tarefa;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Agenda {

    private static Scanner sc = new Scanner(System.in);
    private static List<Usuarios> registros = new ArrayList<>();
    private static List<Tarefa> tarefas = new ArrayList<>();

    public static void main(String[] args) {

        //Connection();
        boolean menuPrincipal = true;

        do {
            System.out.println("******************");
            System.out.println("* MENU PRINCIPAL *");
            System.out.println("******************");
            System.out.println("[1] Cadastrar novo usuário");
            System.out.println("[2] Fazer login");
            System.out.println("[3] Encerrar");
            System.out.print("Opção: ");
            char opcao1 = sc.next().charAt(0);

            switch (opcao1) { //Menu principal

                case '1': //Cadastrar novo usuário
                    CadastroUsuario();
                    System.out.println("\nPressione enter para voltar ao menu inicial\n");
                    sc.nextLine();
                    break;

                case '2': //Fazer login
                    Login();
                    break;

                case '3': //Encerrar
                    menuPrincipal = false;
                    break;

                default:
                    System.out.print("Opção inválida!");
                    menuPrincipal = true;
                    break;
            }
        } while (menuPrincipal);

        System.out.println("Programa encerrado!");
    }

    private static void CadastroUsuario() {

        try {
            //Conectar ao banco de dados
            Driver driver = new Driver();

            DriverManager.registerDriver(driver);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            //Preparar inserção do novo usuário
            PreparedStatement cadastrarUsuario = conn.prepareStatement("Insert INTO cadastrodeusuarios.usuarios"
                    + "(des_email, enc_senha) VALUES (?, ?)");

            //Início captura do e-mail
            boolean emailCorreto;
            String email;

            System.out.println("\n* CADASTRO DE NOVO USUÁRIO *");

            do {
                System.out.print("Digite o e-mail: ");
                sc.nextLine();
                email = sc.nextLine();

                if (email.contains("@") && email.length() > 1) {
                    emailCorreto = true;
                } else {
                    emailCorreto = false;
                    System.out.print("E-mail inválido! Digite novamente!");
                }

            } while (!emailCorreto);
            //Fim captura do e-amil

            //Início captura da senha
            boolean tamanhoCorreto;
            String senha;

            do {

                System.out.print("Digite a senha (mínimo 6, máximo 15 caracteres): ");
                senha = sc.nextLine();

                if (senha.length() < 6 || senha.length() > 15) {
                    tamanhoCorreto = false;
                    System.out.println("ATENCÃO! Tamanho da senha não confere, tente novamente");
                } else {
                    tamanhoCorreto = true;
                }

            } while (!tamanhoCorreto);

            System.out.print("Confirme a senha: ");
            String confirmacao = sc.nextLine();

            Boolean senhaIgual = senha.equals(confirmacao);

            if (!senhaIgual) {
                do {
                    System.out.println("Senhas inseridas estão diferentes!");
                    System.out.println("Digite novamente as senhas!");

                    System.out.print("\nDigite a senha: ");
                    senha = sc.nextLine();

                    System.out.print("Confirme a senha: ");
                    confirmacao = sc.nextLine();

                    senhaIgual = senha.equals(confirmacao);
                } while (!senhaIgual);
            }
            //Fim captura da senha

            //Início persistência no banco de dados
            cadastrarUsuario.setString(1, email);
            cadastrarUsuario.setString(2, senha);

            int confirmarCadastro = cadastrarUsuario.executeUpdate();

            if (confirmarCadastro > 0) {
                System.out.println("\nUsuário cadastrado com sucesso!");
            } else {
                System.out.println("\nOcoreu um erro! Tente novamente ou contate o administrador do sistema.");
            }
            //Fim da persistência no banco de dados

        } catch (Exception e) {
            System.out.println("Problemas na conexão com o banco de dados!");
        }
    }

    private static void Login() {

        try {
            //Conectar ao banco de dados
            Driver driver = new Driver();

            DriverManager.registerDriver(driver);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            boolean logado = false;

            System.out.println("\n*********");
            System.out.println("* LOGIN *");
            System.out.println("*********\n");

            while (!logado) {
                PreparedStatement login = conn.prepareStatement("Select * from cadastrodeusuarios.usuarios WHERE des_email = ?");

                System.out.print("E-mail: ");
                sc.nextLine();
                String email = sc.nextLine();

                System.out.print("Senha: ");
                String senha = sc.nextLine();

                login.setString(1, email);

                ResultSet rs = login.executeQuery();

                while (rs.next()) {
                    String emailCapturado = rs.getString("des_email");
                    String senhaCapturada = rs.getString("enc_senha");
                    int id = rs.getInt("id_usuario");

                    if (email.equals(emailCapturado) && senha.equals(senhaCapturada)) {
                        logado = true;

                        System.out.println("\nLogado\n");
                        
                        Tarefas(id);
                    } else {
                        System.out.println("E-mail ou senha incorretos! Deseja tentar novamente?");
                        System.out.println("[1] Sim");
                        System.out.println("[2] Não");
                        char tentar = sc.next().charAt(0);

                        if (tentar == '1') {
                            logado = false;
                        } else {
                            logado = true;
                            System.out.println("Retornando ao menu principal");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um problema no login! Tente novamente ou contate o administrador do sistema");
            System.out.println("Erro: " + e);
        }

    }

    private static void Tarefas(int id) {
        try {
            //Conectar ao banco de dados
            Driver driver = new Driver();

            DriverManager.registerDriver(driver);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");

            boolean homepage = true;

            while (homepage) {
                System.out.println("\n*********");
                System.out.println("* Home Page *");
                System.out.println("*********\n");

                System.out.println("[1] Listar tarefas");
                System.out.println("[2] Mostrar tarefas finalizadas");
                System.out.println("[3] Mostrar tarefas pendentes");
                System.out.println("[4] Adicionar nova tarefa");
                System.out.println("[5] Finalizar tarefa");
                System.out.println("[6] Remover tarefa");
                System.out.println("[7] Logout");
                System.out.print("Opção: ");
                char ophp = sc.next().charAt(0);

                switch (ophp) {

                    case '1': //listar tarefas
                        PreparedStatement listaDeTarefas = conn.prepareStatement("Select * from cadastrodeusuarios.tarefas WHERE id_usuario = ?");
                        listaDeTarefas.setInt(1, id);
                        ResultSet taskList = listaDeTarefas.executeQuery();

                        System.out.println("\nLista de tarefas: ");

                        while (taskList.next()) {
                            int idTarefa = taskList.getInt("id_tarefa");
                            String descricao = taskList.getString("des_descricao");
                            int finalizada = taskList.getInt("boo_finalizada");

                            System.out.println("\n\tId da tarefa:\t" + idTarefa);
                            System.out.println("\tDescrição:\t" + descricao);

                            if (finalizada == 1) {
                                System.out.println("\tFinalizada:\tSim");
                            } else {
                                System.out.println("\tFinalizada:\tNão");
                            }
                        }

                        break;

                    case '2': //Mostrar tarefas finalizadas
                        PreparedStatement tarefasFinalizadas = conn.prepareStatement("Select * from cadastrodeusuarios.tarefas WHERE id_usuario = ? AND boo_finalizada = ?");
                        tarefasFinalizadas.setInt(1, id);
                        tarefasFinalizadas.setInt(2, 1);
                        ResultSet doneList = tarefasFinalizadas.executeQuery();

                        System.out.println("\nTarefas finalizadas:");

                        while (doneList.next()) {
                            int idTarefa = doneList.getInt("id_tarefa");
                            String descricao = doneList.getString("des_descricao");

                            System.out.println("\n\tId da tarefa:\t" + idTarefa);
                            System.out.println("\tDescrição:\t" + descricao);
                        }

                        break;

                    case '3': //Mostrar tarefas pendentes
                        PreparedStatement tarefasPendentes = conn.prepareStatement("Select * from cadastrodeusuarios.tarefas WHERE id_usuario = ? AND boo_finalizada = ?");
                        tarefasPendentes.setInt(1, id);
                        tarefasPendentes.setInt(2, 0);
                        ResultSet undoneList = tarefasPendentes.executeQuery();

                        System.out.println("\nTarefas pendentes:");

                        while (undoneList.next()) {
                            int idTarefa = undoneList.getInt("id_tarefa");
                            String descricao = undoneList.getString("des_descricao");

                            System.out.println("\n\tId da tarefa:\t" + idTarefa);
                            System.out.println("\tDescrição:\t" + descricao);
                        }

                        break;

                    case '4': //Adicionar nova tarefa
                        PreparedStatement tarefaNova = conn.prepareStatement("INSERT INTO `cadastrodeusuarios`.`tarefas` (`des_descricao`, `boo_finalizada`, `id_usuario`) VALUES (?, ?, ?)");

                        System.out.println("\nNOVA TAREFA");

                        System.out.print("Descrição: ");
                        sc.nextLine();
                        String descricao = sc.nextLine();
                        int booFinalizada = 0;

                        boolean validar = false;
                        while (!validar) {
                            System.out.print("Tarefa já finalizada? [0] Não ou [1] Sim");
                            booFinalizada = sc.nextInt();

                            if (booFinalizada == 0 || booFinalizada == 1) {
                                validar = true;
                            } else {
                                System.out.println("Valor inválido, tente novamente!");
                            }
                        }

                        tarefaNova.setString(1, descricao);
                        tarefaNova.setInt(2, booFinalizada);
                        tarefaNova.setInt(3, id);

                        int novaTarefa = tarefaNova.executeUpdate();

                        if (novaTarefa > 0) {
                            System.out.println("Tarefa cadastrada com sucesso!");
                        } else {
                            System.out.println("Houve um erro! Tente novamente ou contate o administrador do sistema");
                        }

                        break;

                    case '5': //Finalizar tarefa
                        PreparedStatement finalizarTarefa = conn.prepareStatement("UPDATE `cadastrodeusuarios`.`tarefas` SET `boo_finalizada` = ? WHERE (`id_tarefa` = ?)");

                        System.out.print("\nDigite o ID da tarefa que deseja finalizar:");
                        int tarefaAlvo = sc.nextInt();

                        finalizarTarefa.setInt(1, 1);
                        finalizarTarefa.setInt(2, tarefaAlvo);

                        int tarefaFinalizada = finalizarTarefa.executeUpdate();

                        if (tarefaFinalizada > 0) {
                            System.out.println("Tarefa finalizada com sucesso!");
                        } else {
                            System.out.println("Houve um erro! Tente novamente ou contate o administrador do sistema");
                        }

                        break;

                    case '6': //Remover tarefa
                        PreparedStatement removerTarefa = conn.prepareStatement("DELETE FROM `cadastrodeusuarios`.`tarefas` WHERE (`id_tarefa` = ?)");

                        System.out.print("\nDigite o ID da tarefa que deseja remover: ");
                        int tarefaAlvo2 = sc.nextInt();

                        removerTarefa.setInt(1, tarefaAlvo2);

                        int tarefaRemovida = removerTarefa.executeUpdate();

                        if (tarefaRemovida > 0) {
                            System.out.println("Tarefa removida com sucesso!");
                        } else {
                            System.out.println("Houve um erro! Tente novamente ou contate o administrador do sistema");
                        }

                        break;

                    case '7': //Logout
                        homepage = false;
                        break;

                    default:
                        System.out.println("\nOpção inválida! Tente novamente ou consulte o administrador do sistema!");
                        break;

                }

                System.out.println("\nTecle enter para continuar...");
                sc.nextLine();
                sc.nextLine();
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um problema! Tente novamente ou contate um administrador do sistema");
            System.out.println("Erro: " + e);
        }
    }
}
