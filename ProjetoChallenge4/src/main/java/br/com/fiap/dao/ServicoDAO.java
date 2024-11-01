package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Servico;
import br.com.fiap.conexoes.ConexaoFactory;

public class ServicoDAO {
    private Connection minhaConexao;

    public ServicoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO tbl_servico (id_servico, nome, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, servico.getIdServico());
            stmt.setString(2, servico.getNome());
            stmt.setString(3, servico.getDescricao());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por id_servico)
    public Servico ler(int idServico) throws SQLException {
        String sql = "SELECT * FROM tbl_servico WHERE id_servico = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Servico servico = new Servico();
                    servico.setIdServico(rs.getInt("id_servico"));
                    servico.setNome(rs.getString("nome"));
                    servico.setDescricao(rs.getString("descricao"));
                    return servico;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os serviços)
    public List<Servico> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_servico";
        List<Servico> servicos = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("id_servico"));
                servico.setNome(rs.getString("nome"));
                servico.setDescricao(rs.getString("descricao"));
                servicos.add(servico);
            }
        }
        return servicos;
    }

    // Atualizar
    public String atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE tbl_servico SET nome = ?, descricao = ? WHERE id_servico = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setInt(3, servico.getIdServico());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Serviço não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idServico) throws SQLException {
        String sql = "DELETE FROM tbl_servico WHERE id_servico = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Serviço não encontrado.";
        }
    }
}
