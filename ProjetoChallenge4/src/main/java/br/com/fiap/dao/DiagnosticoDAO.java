package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Diagnostico;
import br.com.fiap.conexoes.ConexaoFactory;

public class DiagnosticoDAO {
    private Connection minhaConexao;

    public DiagnosticoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Diagnostico diagnostico) throws SQLException {
        String sql = "INSERT INTO tbl_diagnostico (id_problema, descricao, gravidade, tipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, diagnostico.getIdProblema());
            stmt.setString(2, diagnostico.getDescricao());
            stmt.setInt(3, diagnostico.getGravidade());
            stmt.setString(4, diagnostico.getTipo());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por id_problema)
    public Diagnostico ler(int idProblema) throws SQLException {
        String sql = "SELECT * FROM tbl_diagnostico WHERE id_problema = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idProblema);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setIdProblema(rs.getInt("id_problema"));
                    diagnostico.setDescricao(rs.getString("descricao"));
                    diagnostico.setGravidade(rs.getInt("gravidade"));
                    diagnostico.setTipo(rs.getString("tipo"));
                    return diagnostico;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os diagnósticos)
    public List<Diagnostico> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_diagnostico";
        List<Diagnostico> diagnosticos = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico();
                diagnostico.setIdProblema(rs.getInt("id_problema"));
                diagnostico.setDescricao(rs.getString("descricao"));
                diagnostico.setGravidade(rs.getInt("gravidade"));
                diagnostico.setTipo(rs.getString("tipo"));
                diagnosticos.add(diagnostico);
            }
        }
        return diagnosticos;
    }

    // Atualizar
    public String atualizar(Diagnostico diagnostico) throws SQLException {
        String sql = "UPDATE tbl_diagnostico SET descricao = ?, gravidade = ?, tipo = ? WHERE id_problema = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, diagnostico.getDescricao());
            stmt.setInt(2, diagnostico.getGravidade());
            stmt.setString(3, diagnostico.getTipo());
            stmt.setInt(4, diagnostico.getIdProblema());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Diagnóstico não encontrado.";
        }
    }

    // Deletar
    public String deletar(int idProblema) throws SQLException {
        String sql = "DELETE FROM tbl_diagnostico WHERE id_problema = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idProblema);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Diagnóstico não encontrado.";
        }
    }
}
