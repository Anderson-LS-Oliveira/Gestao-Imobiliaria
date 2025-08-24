package br.edu.univille.poo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        ImovelDAO imovelDAO = new ImovelDAO();
        ContratoDAO contratoDAO = new ContratoDAO();
        Relatorios relatorios = new Relatorios();

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Imóveis");
            System.out.println("3 - Gerenciar Contratos");
            System.out.println("4 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (op) {
                case 1:
                    System.out.println("\n-- CLIENTES --");
                    System.out.println("1 - Inserir");
                    System.out.println("2 - Deletar");
                    System.out.println("3 - Buscar por ID");
                    System.out.println("4 - Lista");
                    System.out.print("Escolha: ");
                    int sub1 = sc.nextInt();
                    sc.nextLine();
                    if (sub1 == 1) {
                        Cliente c = new Cliente();
                        System.out.print("Nome: ");
                        c.setNome(sc.nextLine());
                        System.out.print("Sobrenome: ");
                        c.setSobrenome(sc.nextLine());
                        System.out.print("Telefone: ");
                        c.setTelefone(sc.nextLine());
                        System.out.print("CPF: ");
                        c.setCpf(sc.nextLine());
                        clienteDAO.inserir(c);
                    } else if (sub1 == 2) {
                        System.out.print("ID para deletar: ");
                        long id = sc.nextLong();
                        clienteDAO.deletarPeloId(id);
                    } else if (sub1 == 3) {
                        System.out.print("ID para buscar: ");
                        long id = sc.nextLong();
                        clienteDAO.selectPeloId(id);
                    } else if (sub1 == 4) {
                        clienteDAO.listaCompleta();
                    }
                    break;

                case 2:
                    System.out.println("\n-- IMÓVEIS --");
                    System.out.println("1 - Inserir");
                    System.out.println("2 - Deletar");
                    System.out.println("3 - Buscar por ID");
                    System.out.println("4 - Lista");
                    System.out.print("Escolha: ");
                    int sub2 = sc.nextInt();
                    sc.nextLine();
                    if (sub2 == 1) {
                        Imovel i = new Imovel();
                        System.out.print("Endereço: ");
                        i.setEndereco(sc.nextLine());
                        System.out.print("Quartos: ");
                        i.setQuartos(sc.nextInt());
                        System.out.print("Valor: ");
                        i.setValor(new BigDecimal(sc.next()));
                        imovelDAO.inserir(i);
                    } else if (sub2 == 2) {
                        System.out.print("ID para deletar: ");
                        long id = sc.nextLong();
                        imovelDAO.deletarPeloId(id);
                    } else if (sub2 == 3) {
                        System.out.print("ID para buscar: ");
                        long id = sc.nextLong();
                        imovelDAO.selectPeloId(id);
                    } else if (sub2 == 4) {
                        imovelDAO.listaCompleta();
                    }
                    break;

                case 3:
                    System.out.println("\n-- CONTRATOS --");
                    System.out.println("1 - Inserir");
                    System.out.println("2 - Deletar");
                    System.out.println("3 - Buscar por ID");
                    System.out.println("4 - Lista");
                    System.out.print("Escolha: ");
                    int sub3 = sc.nextInt();
                    sc.nextLine();
                    if (sub3 == 1) {
                        Contrato c = new Contrato();
                        System.out.print("ID Cliente: ");
                        c.setIdCliente(sc.nextLong());
                        System.out.print("ID Imóvel: ");
                        c.setIdImovel(sc.nextLong());
                        System.out.print("Valor: ");
                        c.setValor(new BigDecimal(sc.next()));
                        System.out.print("Data Início (yyyy-mm-dd): ");
                        c.setDataInicio(LocalDate.parse(sc.next()));
                        System.out.print("Data Fim (yyyy-mm-dd): ");
                        c.setDataFim(LocalDate.parse(sc.next()));
                        contratoDAO.inserir(c);
                    } else if (sub3 == 2) {
                        System.out.print("ID para deletar: ");
                        long id = sc.nextLong();
                        contratoDAO.deletarPeloId(id);
                    } else if (sub3 == 3) {
                        System.out.print("ID para buscar: ");
                        long id = sc.nextLong();
                        contratoDAO.selectPeloId(id);
                    } else if (sub3 == 4) {
                        contratoDAO.listaCompleta();
                    }
                    break;

                case 4:
                    System.out.println("\n-- RELATÓRIOS --");
                    System.out.println("1 - Imóveis Disponíveis");
                    System.out.println("2 - Contratos Ativos");
                    System.out.println("3 - Clientes com Contratos");
                    System.out.println("4 - Contratos Expirando");
                    System.out.print("Escolha: ");
                    int sub4 = sc.nextInt();
                    sc.nextLine();
                    if (sub4 == 1) {
                        relatorios.imoveisDisponiveis();
                    } else if (sub4 == 2) {
                        relatorios.contratosAtivos();
                    } else if (sub4 == 3) {
                        relatorios.clientesContratos();
                    } else if (sub4 == 4) {
                        relatorios.contratosExpirando();
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
