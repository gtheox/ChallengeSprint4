package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Mecanica;
import br.com.fiap.conexoes.ConexaoFactory;

public class MecanicaDAO {
    private Connection minhaConexao;

    public MecanicaDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Mecanica mecanica) throws SQLException {
        String sql = "INSERT INTO tbl_oficina (id_oficina, nome, telefone, horario_abertura, horario_fechamento, lougradouro, numero, complemento, bairro, cep, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, mecanica.getIdOficina());
            stmt.setString(2, mecanica.getNome());
            stmt.setString(3, mecanica.getTelefone());
            stmt.setString(4, mecanica.getHorarioAbertura());
            stmt.setString(5, mecanica.getHorarioFechamento());
            stmt.setString(6, mecanica.getEnderecoLogradouro());
            stmt.setInt(7, mecanica.getEnderecoNumero());
            stmt.setString(8, mecanica.getEnderecoComplemento());
            stmt.setString(9, mecanica.getEnderecoBairro());
            stmt.setString(10, mecanica.getEnderecoCep());
            stmt.setString(11, mecanica.getEnderecoCidade());
            stmt.setString(12, mecanica.getEnderecoEstado());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por id_oficina)
    public Mecanica ler(int idOficina) throws SQLException {
        String sql = "SELECT * FROM tbl_oficina WHERE id_oficina = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idOficina);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Mecanica mecanica = new Mecanica();
                    mecanica.setIdOficina(rs.getInt("id_oficina"));
                    mecanica.setNome(rs.getString("nome"));
                    mecanica.setTelefone(rs.getString("telefone"));
                    mecanica.setHorarioAbertura(rs.getString("horario_abertura"));
                    mecanica.setHorarioFechamento(rs.getString("horario_fechamento"));
                    mecanica.setEnderecoLogradouro(rs.getString("lougradouro"));
                    mecanica.setEnderecoNumero(rs.getInt("numero"));
                    mecanica.setEnderecoComplemento(rs.getString("complemento"));
                    mecanica.setEnderecoBairro(rs.getString("bairro"));
                    mecanica.setEnderecoCep(rs.getString("cep"));
                    mecanica.setEnderecoCidade(rs.getString("cidade"));
                    mecanica.setEnderecoEstado(rs.getString("estado"));
                    return mecanica;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os oficinas)
    public List<Mecanica> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_oficina";
        List<Mecanica> oficinas = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Mecanica mecanica = new Mecanica();
                mecanica.setIdOficina(rs.getInt("id_oficina"));
                mecanica.setNome(rs.getString("nome"));
                mecanica.setTelefone(rs.getString("telefone"));
                mecanica.setHorarioAbertura(rs.getString("horario_abertura"));
                mecanica.setHorarioFechamento(rs.getString("horario_fechamento"));
                mecanica.setEnderecoLogradouro(rs.getString("lougradouro"));
                mecanica.setEnderecoNumero(rs.getInt("numero"));
                mecanica.setEnderecoComplemento(rs.getString("complemento"));
                mecanica.setEnderecoBairro(rs.getString("bairro"));
                mecanica.setEnderecoCep(rs.getString("cep"));
                mecanica.setEnderecoCidade(rs.getString("cidade"));
                mecanica.setEnderecoEstado(rs.getString("estado"));
                oficinas.add(mecanica);
            }
        }
        return oficinas;
    }

    // Atualizar
    public String atualizar(Mecanica mecanica) throws SQLException {
        String sql = "UPDATE tbl_oficina SET nome = ?, telefone = ?, horario_abertura = ?, horario_fechamento = ?, lougradouro = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? WHERE id_oficina = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, mecanica.getNome());
            stmt.setString(2, mecanica.getTelefone());
            stmt.setString(3, mecanica.getHorarioAbertura());
            stmt.setString(4, mecanica.getHorarioFechamento());
            stmt.setString(5, mecanica.getEnderecoLogradouro());
            stmt.setInt(6, mecanica.getEnderecoNumero());
            stmt.setString(7, mecanica.getEnderecoComplemento());
            stmt.setString(8, mecanica.getEnderecoBairro());
            stmt.setString(9, mecanica.getEnderecoCep());
            stmt.setString(10, mecanica.getEnderecoCidade());
            stmt.setString(11, mecanica.getEnderecoEstado());
            stmt.setInt(12, mecanica.getIdOficina());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Oficina não encontrada.";
        }
    }

    // Deletar
    public String deletar(int idOficina) throws SQLException {
        String sql = "DELETE FROM tbl_oficina WHERE id_oficina = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idOficina);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Oficina não encontrada.";
        }
    }
}
