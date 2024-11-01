package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Mecanica;
import br.com.fiap.dao.MecanicaDAO;

public class MecanicaBO {

    private MecanicaDAO mecanicaDAO;

    public MecanicaBO() throws ClassNotFoundException, SQLException {
        mecanicaDAO = new MecanicaDAO();
    }

    // Método para listar todas as oficinas
    public List<Mecanica> listarOficinas() throws SQLException {
        return mecanicaDAO.listar();
    }

    // Método para buscar uma oficina pelo ID
    public Mecanica buscarOficina(int idOficina) throws SQLException {
        return mecanicaDAO.ler(idOficina);
    }

    // Método para inserir uma nova oficina
    public String inserirOficina(Mecanica mecanica) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return mecanicaDAO.inserir(mecanica);
    }

    // Método para atualizar uma oficina existente
    public String atualizarOficina(Mecanica mecanica) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return mecanicaDAO.atualizar(mecanica);
    }

    // Método para deletar uma oficina pelo ID
    public String deletarOficina(int idOficina) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return mecanicaDAO.deletar(idOficina);
    }
}
