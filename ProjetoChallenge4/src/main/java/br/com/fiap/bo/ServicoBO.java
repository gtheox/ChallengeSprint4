package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Servico;
import br.com.fiap.dao.ServicoDAO;

public class ServicoBO {

    private ServicoDAO servicoDAO;

    public ServicoBO() throws ClassNotFoundException, SQLException {
        servicoDAO = new ServicoDAO();
    }

    // Método para listar todos os serviços
    public List<Servico> listarServicos() throws SQLException {
        return servicoDAO.listar();
    }

    // Método para buscar um serviço pelo ID
    public Servico buscarServico(int idServico) throws SQLException {
        return servicoDAO.ler(idServico);
    }

    // Método para inserir um novo serviço
    public String inserirServico(Servico servico) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return servicoDAO.inserir(servico);
    }

    // Método para atualizar um serviço existente
    public String atualizarServico(Servico servico) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return servicoDAO.atualizar(servico);
    }

    // Método para deletar um serviço pelo ID
    public String deletarServico(int idServico) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return servicoDAO.deletar(idServico);
    }
}
