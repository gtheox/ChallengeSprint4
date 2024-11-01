package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.Servico;
import br.com.fiap.dao.ServicoDAO;

public class TesteCRUDServicos {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        try {
            ServicoDAO dao = new ServicoDAO();
            String opcao = texto("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    Servico servico = new Servico();
                    servico.setIdServico(inteiro("Informe o ID do serviço"));
                    servico.setNome(texto("Informe o nome do serviço"));
                    servico.setDescricao(texto("Informe a descrição do serviço"));
                    System.out.println(dao.inserir(servico));
                    break;
                case "2":
                    int idLer = inteiro("Informe o ID do serviço para ler");
                    Servico servicoLido = dao.ler(idLer);
                    if (servicoLido != null) {
                        JOptionPane.showMessageDialog(null, servicoLido.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Serviço não encontrado.");
                    }
                    break;
                case "3":
                    Servico servicoAtualizar = new Servico();
                    servicoAtualizar.setIdServico(inteiro("Informe o ID do serviço"));
                    servicoAtualizar.setNome(texto("Informe o novo nome do serviço"));
                    servicoAtualizar.setDescricao(texto("Informe a nova descrição do serviço"));
                    System.out.println(dao.atualizar(servicoAtualizar));
                    break;
                case "4":
                    int idDeletar = inteiro("Informe o ID do serviço para deletar");
                    System.out.println(dao.deletar(idDeletar));
                    break;
                case "5":
                    List<Servico> servicos = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (Servico s : servicos) {
                        sb.append(s.toString()).append("\n");
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
