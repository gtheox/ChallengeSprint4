package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;

public class TesteCRUDCliente {

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
            ClienteDAO dao = new ClienteDAO();
            String opcao = JOptionPane.showInputDialog("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(inteiro("Informe o ID do cliente"));
                    cliente.setCpf(texto("Informe o CPF do cliente"));
                    cliente.setNome(texto("Informe o nome do cliente"));
                    cliente.setEmail(texto("Informe o e-mail do cliente"));
                    cliente.setTelefone(texto("Informe o telefone do cliente"));
                    cliente.setEnderecoLogradouro(texto("Informe o logradouro do cliente"));
                    cliente.setEnderecoNumero(inteiro("Informe o número do endereço"));
                    cliente.setEnderecoComplemento(texto("Informe o complemento do endereço"));
                    cliente.setEnderecoBairro(texto("Informe o bairro do cliente"));
                    cliente.setEnderecoCep(texto("Informe o CEP do cliente"));
                    cliente.setEnderecoCidade(texto("Informe a cidade do cliente"));
                    cliente.setEnderecoEstado(texto("Informe o estado do cliente"));
                    System.out.println(dao.inserir(cliente));
                    break;
                case "2":
                    int idLer = inteiro("Informe o ID do cliente para ler");
                    Cliente clienteLido = dao.ler(idLer);
                    if (clienteLido != null) {
                        JOptionPane.showMessageDialog(null, clienteLido.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                    break;
                case "3":
                    Cliente clienteAtualizar = new Cliente();
                    clienteAtualizar.setIdCliente(inteiro("Informe o ID do cliente"));
                    clienteAtualizar.setCpf(texto("Informe o novo CPF do cliente"));
                    clienteAtualizar.setNome(texto("Informe o novo nome do cliente"));
                    clienteAtualizar.setEmail(texto("Informe o novo e-mail do cliente"));
                    clienteAtualizar.setTelefone(texto("Informe o novo telefone do cliente"));
                    clienteAtualizar.setEnderecoLogradouro(texto("Informe o novo logradouro do cliente"));
                    clienteAtualizar.setEnderecoNumero(inteiro("Informe o novo número do endereço"));
                    clienteAtualizar.setEnderecoComplemento(texto("Informe o novo complemento do endereço"));
                    clienteAtualizar.setEnderecoBairro(texto("Informe o novo bairro do cliente"));
                    clienteAtualizar.setEnderecoCep(texto("Informe o novo CEP do cliente"));
                    clienteAtualizar.setEnderecoCidade(texto("Informe a nova cidade do cliente"));
                    clienteAtualizar.setEnderecoEstado(texto("Informe o novo estado do cliente"));
                    System.out.println(dao.atualizar(clienteAtualizar));
                    break;
                case "4":
                    int idDeletar = inteiro("Informe o ID do cliente para deletar");
                    System.out.println(dao.deletar(idDeletar));
                    break;
                case "5":
                    List<Cliente> clientes = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (Cliente c : clientes) {
                        sb.append(c.toString()).append("\n");
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
