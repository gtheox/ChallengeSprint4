package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cliente;
import br.com.fiap.conexoes.ConexaoFactory;

public class ClienteDAO {
    private Connection minhaConexao;

    public ClienteDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO tbl_cliente (id_cliente, nome, cpf, email, telefone, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cep, endereco_cidade, endereco_estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEnderecoLogradouro());
            stmt.setInt(7, cliente.getEnderecoNumero());
            stmt.setString(8, cliente.getEnderecoComplemento());
            stmt.setString(9, cliente.getEnderecoBairro());
            stmt.setString(10, cliente.getEnderecoCep());
            stmt.setString(11, cliente.getEnderecoCidade());
            stmt.setString(12, cliente.getEnderecoEstado());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por id_cliente)
    public Cliente ler(int idCliente) throws SQLException {
        String sql = "SELECT * FROM tbl_cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
                    cliente.setEnderecoNumero(rs.getInt("endereco_numero"));
                    cliente.setEnderecoComplemento(rs.getString("endereco_complemento"));
                    cliente.setEnderecoBairro(rs.getString("endereco_bairro"));
                    cliente.setEnderecoCep(rs.getString("endereco_cep"));
                    cliente.setEnderecoCidade(rs.getString("endereco_cidade"));
                    cliente.setEnderecoEstado(rs.getString("endereco_estado"));
                    return cliente;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os clientes)
    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
                cliente.setEnderecoNumero(rs.getInt("endereco_numero"));
                cliente.setEnderecoComplemento(rs.getString("endereco_complemento"));
                cliente.setEnderecoBairro(rs.getString("endereco_bairro"));
                cliente.setEnderecoCep(rs.getString("endereco_cep"));
                cliente.setEnderecoCidade(rs.getString("endereco_cidade"));
                cliente.setEnderecoEstado(rs.getString("endereco_estado"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // Atualizar
    public String atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE tbl_cliente SET nome = ?, cpf = ?, email = ?, telefone = ?, endereco_logradouro = ?, endereco_numero = ?, endereco_complemento = ?, endereco_bairro = ?, endereco_cep = ?, endereco_cidade = ?, endereco_estado = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEnderecoLogradouro());
            stmt.setInt(6, cliente.getEnderecoNumero());
            stmt.setString(7, cliente.getEnderecoComplemento());
            stmt.setString(8, cliente.getEnderecoBairro());
            stmt.setString(9, cliente.getEnderecoCep());
            stmt.setString(10, cliente.getEnderecoCidade());
            stmt.setString(11, cliente.getEnderecoEstado());
            stmt.setInt(12, cliente.getIdCliente());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Cliente não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idCliente) throws SQLException {
        String sql = "DELETE FROM tbl_cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Cliente não encontrado.";
        }
    }
}
