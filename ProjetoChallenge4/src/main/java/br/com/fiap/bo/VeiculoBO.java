package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.dao.VeiculoDAO;

public class VeiculoBO {

    private VeiculoDAO veiculoDAO;

    public VeiculoBO() throws ClassNotFoundException, SQLException {
        veiculoDAO = new VeiculoDAO();
    }

    // Método para listar todos os veículos
    public List<Veiculo> listarVeiculos() throws SQLException {
        return veiculoDAO.listar();
    }

    // Método para buscar um veículo pela placa
    public Veiculo buscarVeiculo(String placa) throws SQLException {
        return veiculoDAO.ler(placa);
    }

    // Método para inserir um novo veículo
    public String inserirVeiculo(Veiculo veiculo) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return veiculoDAO.inserir(veiculo);
    }

    // Método para atualizar um veículo existente
    public String atualizarVeiculo(Veiculo veiculo) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return veiculoDAO.atualizar(veiculo);
    }

    // Método para deletar um veículo pela placa
    public String deletarVeiculo(String placa) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return veiculoDAO.deletar(placa);
    }
}
