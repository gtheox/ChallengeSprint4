package br.com.fiap.main;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.dao.VeiculoDAO;

public class TesteCRUDVeiculo {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        try {
            VeiculoDAO dao = new VeiculoDAO();
            String opcao = texto("Escolha uma operação:\n1. Inserir\n2. Ler\n3. Atualizar\n4. Deletar\n5. Listar todos");

            switch (opcao) {
                case "1":
                    Veiculo veiculo = new Veiculo();
                    veiculo.setPlaca(texto("Informe a placa do veículo"));
                    veiculo.setMarca(texto("Informe a marca do veículo"));
                    veiculo.setModelo(texto("Informe o modelo do veículo"));
                    veiculo.setAno(inteiro("Informe o ano do veículo"));
                    veiculo.setIdCliente(inteiro("Informe o ID do cliente"));
                    System.out.println(dao.inserir(veiculo));
                    break;
                case "2":
                    String placaLer = texto("Informe a placa do veículo para ler");
                    Veiculo veiculoLido = dao.ler(placaLer);
                    if (veiculoLido != null) {
                        JOptionPane.showMessageDialog(null, veiculoLido.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Veículo não encontrado.");
                    }
                    break;
                case "3":
                    Veiculo veiculoAtualizar = new Veiculo();
                    veiculoAtualizar.setPlaca(texto("Informe a placa do veículo"));
                    veiculoAtualizar.setMarca(texto("Informe a nova marca do veículo"));
                    veiculoAtualizar.setModelo(texto("Informe o novo modelo do veículo"));
                    veiculoAtualizar.setAno(inteiro("Informe o novo ano do veículo"));
                    veiculoAtualizar.setIdCliente(inteiro("Informe o ID do cliente"));
                    System.out.println(dao.atualizar(veiculoAtualizar));
                    break;
                case "4":
                    String placaDeletar = texto("Informe a placa do veículo para deletar");
                    System.out.println(dao.deletar(placaDeletar));
                    break;
                case "5":
                    List<Veiculo> veiculos = dao.listar();
                    StringBuilder sb = new StringBuilder();
                    for (Veiculo v : veiculos) {
                        sb.append(v.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
