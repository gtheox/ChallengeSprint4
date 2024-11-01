package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Cliente;
import br.com.fiap.dao.ClienteDAO;

public class ClienteBO {

    private ClienteDAO clienteDAO;

    public ClienteBO() throws ClassNotFoundException, SQLException {
        clienteDAO = new ClienteDAO();
    }

    // Método para selecionar todos os clientes
    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listar();
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarCliente(int idCliente) throws SQLException {
        return clienteDAO.ler(idCliente);
    }

    // Método para inserir um cliente
    public String inserirCliente(Cliente cliente) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return clienteDAO.inserir(cliente);
    }

    // Método para atualizar um cliente
    public String atualizarCliente(Cliente cliente) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return clienteDAO.atualizar(cliente);
    }

    // Método para deletar um cliente pelo ID
    public String deletarCliente(int idCliente) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return clienteDAO.deletar(idCliente);
    }
}
