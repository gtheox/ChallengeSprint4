package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.Mecanica;
import br.com.fiap.dao.MecanicaDAO;

public class TesteCRUDMecanica {

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
            MecanicaDAO dao = new MecanicaDAO();
            String opcao = JOptionPane.showInputDialog("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    Mecanica mecanica = new Mecanica();
                    mecanica.setIdOficina(inteiro("Informe o ID da mecânica"));
                    mecanica.setNome(texto("Informe o nome da mecânica"));
                    mecanica.setTelefone(texto("Informe o telefone da mecânica"));
                    mecanica.setHorarioAbertura(texto("Informe o horário de abertura"));
                    mecanica.setHorarioFechamento(texto("Informe o horário de fechamento"));
                    mecanica.setEnderecoLogradouro(texto("Informe o logradouro do endereço"));
                    mecanica.setEnderecoNumero(inteiro("Informe o número do endereço"));
                    mecanica.setEnderecoComplemento(texto("Informe o complemento do endereço"));
                    mecanica.setEnderecoBairro(texto("Informe o bairro do endereço"));
                    mecanica.setEnderecoCep(texto("Informe o CEP do endereço"));
                    mecanica.setEnderecoCidade(texto("Informe a cidade do endereço"));
                    mecanica.setEnderecoEstado(texto("Informe o estado do endereço"));
                    System.out.println(dao.inserir(mecanica));
                    break;
                case "2":
                    int idLer = inteiro("Informe o ID da mecânica para ler");
                    Mecanica mecanicaLida = dao.ler(idLer);
                    if (mecanicaLida != null) {
                        JOptionPane.showMessageDialog(null, mecanicaLida.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Mecânica não encontrada.");
                    }
                    break;
                case "3":
                    Mecanica mecanicaAtualizar = new Mecanica();
                    mecanicaAtualizar.setIdOficina(inteiro("Informe o ID da mecânica"));
                    mecanicaAtualizar.setNome(texto("Informe o novo nome da mecânica"));
                    mecanicaAtualizar.setTelefone(texto("Informe o novo telefone da mecânica"));
                    mecanicaAtualizar.setHorarioAbertura(texto("Informe o novo horário de abertura"));
                    mecanicaAtualizar.setHorarioFechamento(texto("Informe o novo horário de fechamento"));
                    mecanicaAtualizar.setEnderecoLogradouro(texto("Informe o novo logradouro do endereço"));
                    mecanicaAtualizar.setEnderecoNumero(inteiro("Informe o novo número do endereço"));
                    mecanicaAtualizar.setEnderecoComplemento(texto("Informe o novo complemento do endereço"));
                    mecanicaAtualizar.setEnderecoBairro(texto("Informe o novo bairro do endereço"));
                    mecanicaAtualizar.setEnderecoCep(texto("Informe o novo CEP do endereço"));
                    mecanicaAtualizar.setEnderecoCidade(texto("Informe a nova cidade do endereço"));
                    mecanicaAtualizar.setEnderecoEstado(texto("Informe o novo estado do endereço"));
                    System.out.println(dao.atualizar(mecanicaAtualizar));
                    break;
                case "4":
                    int idDeletar = inteiro("Informe o ID da mecânica para deletar");
                    System.out.println(dao.deletar(idDeletar));
                    break;
                case "5":
                    List<Mecanica> mecanicas = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (Mecanica m : mecanicas) {
                        sb.append(m.toString()).append("\n");
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
