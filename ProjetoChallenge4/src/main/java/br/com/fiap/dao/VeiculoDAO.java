package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.conexoes.ConexaoFactory;

public class VeiculoDAO {
    private Connection minhaConexao;

    public VeiculoDAO() throws ClassNotFoundException, SQLException {
        super();
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Criar (Inserir)
    public String inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO tbl_veiculo (placa, marca, modelo, ano, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAno());
            stmt.setInt(5, veiculo.getIdCliente());
            stmt.execute();
            return "Cadastrado com sucesso!";
        }
    }

    // Ler (por placa)
    public Veiculo ler(String placa) throws SQLException {
        String sql = "SELECT * FROM tbl_veiculo WHERE placa = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setIdCliente(rs.getInt("id_cliente"));
                    return veiculo;
                } else {
                    return null;
                }
            }
        }
    }

    // Ler (todos os veículos)
    public List<Veiculo> listar() throws SQLException {
        String sql = "SELECT * FROM tbl_veiculo";
        List<Veiculo> veiculos = new ArrayList<>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setIdCliente(rs.getInt("id_cliente"));
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    // Atualizar
    public String atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE tbl_veiculo SET marca = ?, modelo = ?, ano = ?, id_cliente = ? WHERE placa = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getIdCliente());
            stmt.setString(5, veiculo.getPlaca());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? "Atualizado com sucesso!" : "Veículo não encontrado.";
        }
    }

    // Deletar
    public String deletar(String placa) throws SQLException {
        String sql = "DELETE FROM tbl_veiculo WHERE placa = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, placa);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0 ? "Deletado com sucesso!" : "Veículo não encontrado.";
        }
    }
}
