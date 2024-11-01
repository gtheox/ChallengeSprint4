package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.Diagnostico;
import br.com.fiap.dao.DiagnosticoDAO;

public class TesteCRUDDiagnostico {

    // Método para entrada de texto
    static String texto(String mensagem) {
        return JOptionPane.showInputDialog(mensagem);
    }

    // Método para entrada de inteiro com validação
    static int inteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, insira um número inteiro.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            DiagnosticoDAO dao = new DiagnosticoDAO();
            String opcao = JOptionPane.showInputDialog("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setIdProblema(inteiro("Informe o ID do diagnóstico"));
                    diagnostico.setDescricao(texto("Informe a descrição do diagnóstico"));
                    diagnostico.setGravidade(inteiro("Informe a gravidade do diagnóstico"));
                    diagnostico.setTipo(texto("Informe o tipo do diagnóstico"));
                    System.out.println(dao.inserir(diagnostico));
                    break;
                case "2":
                    int idLer = inteiro("Informe o ID do diagnóstico para ler");
                    Diagnostico diagnosticoLido = dao.ler(idLer);
                    if (diagnosticoLido != null) {
                        JOptionPane.showMessageDialog(null, diagnosticoLido.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Diagnóstico não encontrado.");
                    }
                    break;
                case "3":
                    Diagnostico diagnosticoAtualizar = new Diagnostico();
                    diagnosticoAtualizar.setIdProblema(inteiro("Informe o ID do diagnóstico"));
                    diagnosticoAtualizar.setDescricao(texto("Informe a nova descrição do diagnóstico"));
                    diagnosticoAtualizar.setGravidade(inteiro("Informe a nova gravidade do diagnóstico"));
                    diagnosticoAtualizar.setTipo(texto("Informe o novo tipo do diagnóstico"));
                    System.out.println(dao.atualizar(diagnosticoAtualizar));
                    break;
                case "4":
                    int idDeletar = inteiro("Informe o ID do diagnóstico para deletar");
                    System.out.println(dao.deletar(idDeletar));
                    break;
                case "5":
                    List<Diagnostico> diagnosticos = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (Diagnostico d : diagnosticos) {
                        sb.append(d.toString()).append("\n");
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
