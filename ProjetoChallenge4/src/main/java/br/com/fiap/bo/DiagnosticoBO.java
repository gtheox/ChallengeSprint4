package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Diagnostico;
import br.com.fiap.dao.DiagnosticoDAO;

public class DiagnosticoBO {

    private DiagnosticoDAO diagnosticoDAO;

    public DiagnosticoBO() throws ClassNotFoundException, SQLException {
        diagnosticoDAO = new DiagnosticoDAO();
    }

    // Método para listar todos os diagnósticos
    public List<Diagnostico> listarDiagnosticos() throws SQLException {
        return diagnosticoDAO.listar();
    }

    // Método para buscar um diagnóstico pelo ID
    public Diagnostico buscarDiagnostico(int idProblema) throws SQLException {
        return diagnosticoDAO.ler(idProblema);
    }

    // Método para inserir um diagnóstico
    public String inserirDiagnostico(Diagnostico diagnostico) throws SQLException {
        // Regras de negócios para inserção podem ser aplicadas aqui
        return diagnosticoDAO.inserir(diagnostico);
    }

    // Método para atualizar um diagnóstico
    public String atualizarDiagnostico(Diagnostico diagnostico) throws SQLException {
        // Regras de negócios para atualização podem ser aplicadas aqui
        return diagnosticoDAO.atualizar(diagnostico);
    }

    // Método para deletar um diagnóstico pelo ID
    public String deletarDiagnostico(int idProblema) throws SQLException {
        // Regras de negócios para exclusão podem ser aplicadas aqui
        return diagnosticoDAO.deletar(idProblema);
    }
}
