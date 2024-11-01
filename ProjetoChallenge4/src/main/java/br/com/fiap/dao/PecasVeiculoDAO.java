package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.PecasVeiculo;
import br.com.fiap.conexoes.ConexaoFactory;

public class PecasVeiculoDAO {
    private Connection minhaConexao;

    public PecasVeiculoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(PecasVeiculo peca) throws SQLException {
        String sql = "INSERT INTO tbl_peca (id_peca, nome, descricao_peca) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, peca.getIdPeca());
            stmt.setString(2, peca.getNome());
            stmt.setString(3, peca.getDescricaoPeca());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por id_peca)
    public PecasVeiculo ler(int idPeca) throws SQLException {
        String sql = "SELECT * FROM tbl_peca WHERE id_peca = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PecasVeiculo peca = new PecasVeiculo();
                    peca.setIdPeca(rs.getInt("id_peca"));
                    peca.setNome(rs.getString("nome"));
                    peca.setDescricaoPeca(rs.getString("descricao_peca"));
                    return peca;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os peças)
    public List<PecasVeiculo> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_peca";
        List<PecasVeiculo> pecas = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PecasVeiculo peca = new PecasVeiculo();
                peca.setIdPeca(rs.getInt("id_peca"));
                peca.setNome(rs.getString("nome"));
                peca.setDescricaoPeca(rs.getString("descricao_peca"));
                pecas.add(peca);
            }
        }
        return pecas;
    }

    // Atualizar
    public String atualizar(PecasVeiculo peca) throws SQLException {
        String sql = "UPDATE tbl_peca SET nome = ?, descricao_peca = ? WHERE id_peca = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, peca.getNome());
            stmt.setString(2, peca.getDescricaoPeca());
            stmt.setInt(3, peca.getIdPeca());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Peça não encontrada.";
        }
    }

    // Deletar
    public String deletar(int idPeca) throws SQLException {
        String sql = "DELETE FROM tbl_peca WHERE id_peca = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Peça não encontrada.";
        }
    }
}
