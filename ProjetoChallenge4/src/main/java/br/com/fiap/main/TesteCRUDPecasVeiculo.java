package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.PecasVeiculo;
import br.com.fiap.dao.PecasVeiculoDAO;

public class TesteCRUDPecasVeiculo {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        try {
            PecasVeiculoDAO dao = new PecasVeiculoDAO();
            String opcao = texto("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    PecasVeiculo peca = new PecasVeiculo();
                    peca.setIdPeca(inteiro("Informe o ID da peça"));
                    peca.setNome(texto("Informe o nome da peça"));
                    peca.setDescricaoPeca(texto("Informe a descrição da peça"));
                    System.out.println(dao.inserir(peca));
                    break;
                case "2":
                    int idLer = inteiro("Informe o ID da peça para ler");
                    PecasVeiculo pecaLida = dao.ler(idLer);
                    if (pecaLida != null) {
                        JOptionPane.showMessageDialog(null, pecaLida.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Peça não encontrada.");
                    }
                    break;
                case "3":
                    PecasVeiculo pecaAtualizar = new PecasVeiculo();
                    pecaAtualizar.setIdPeca(inteiro("Informe o ID da peça"));
                    pecaAtualizar.setNome(texto("Informe o novo nome da peça"));
                    pecaAtualizar.setDescricaoPeca(texto("Informe a nova descrição da peça"));
                    System.out.println(dao.atualizar(pecaAtualizar));
                    break;
                case "4":
                    int idDeletar = inteiro("Informe o ID da peça para deletar");
                    System.out.println(dao.deletar(idDeletar));
                    break;
                case "5":
                    List<PecasVeiculo> pecas = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (PecasVeiculo p : pecas) {
                        sb.append(p.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
