package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.PecasVeiculo;
import br.com.fiap.dao.PecasVeiculoDAO;

public class PecasVeiculoBO {

    private PecasVeiculoDAO pecasVeiculoDAO;

    public PecasVeiculoBO() throws ClassNotFoundException, SQLException {
        pecasVeiculoDAO = new PecasVeiculoDAO();
    }

    // Método para listar todas as peças
    public List<PecasVeiculo> listarPecas() throws SQLException {
        return pecasVeiculoDAO.listar();
    }

    // Método para buscar uma peça pelo ID
    public PecasVeiculo buscarPeca(int idPeca) throws SQLException {
        return pecasVeiculoDAO.ler(idPeca);
    }

    // Método para inserir uma nova peça
    public String inserirPeca(PecasVeiculo peca) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return pecasVeiculoDAO.inserir(peca);
    }

    // Método para atualizar uma peça existente
    public String atualizarPeca(PecasVeiculo peca) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return pecasVeiculoDAO.atualizar(peca);
    }

    // Método para deletar uma peça pelo ID
    public String deletarPeca(int idPeca) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return pecasVeiculoDAO.deletar(idPeca);
    }
}
