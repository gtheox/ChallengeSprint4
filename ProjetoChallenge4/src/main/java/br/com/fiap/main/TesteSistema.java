package br.com.fiap.main;

import javax.swing.JOptionPane;
import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Mecanica;
import br.com.fiap.beans.Veiculo;
import br.com.fiap.beans.Diagnostico;
import br.com.fiap.beans.PecasVeiculo;
import br.com.fiap.beans.Servico;

public class TesteSistema {

    // Métodos utilitários para simplificar o código
    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }
    
    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }
    
    static double real(String j) {
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        
        // Criação de objetos com os dados fornecidos pelo usuário
        
        // Cliente
        Cliente objCliente = new Cliente(
                inteiro("Informe o ID do Cliente"),
                texto("Informe o CPF do cliente"),
                texto("Informe o nome do cliente"),
                texto("Informe o email do cliente"),
                texto("Informe o telefone do cliente"),
                texto("Informe o logradouro do endereço"),
                inteiro("Informe o número do endereço"),
                texto("Informe o complemento do endereço"),
                texto("Informe o bairro do endereço"),
                texto("Informe o CEP do endereço"),
                texto("Informe a cidade do endereço"),
                texto("Informe o estado do endereço"));

        // Mecanica
        Mecanica objMecanica = new Mecanica(
                inteiro("Informe o ID da oficina"),
                texto("Informe o nome da oficina"),
                texto("Informe o telefone da oficina"),
                texto("Informe o horário de abertura"),
                texto("Informe o horário de fechamento"),
                texto("Informe o logradouro do endereço"),
                inteiro("Informe o número do endereço"),
                texto("Informe o complemento do endereço"),
                texto("Informe o bairro do endereço"),
                texto("Informe o CEP do endereço"),
                texto("Informe a cidade do endereço"),
                texto("Informe o estado do endereço"));

        // Veiculo
        Veiculo objVeiculo = new Veiculo(
                texto("Informe a placa do veículo"),
                texto("Informe a marca do veículo"),
                texto("Informe o modelo do veículo"),
                inteiro("Informe o ano do veículo"),
                inteiro("Informe o ID do cliente"));

        // Problema
        Diagnostico objDiagnostico = new Diagnostico(
                inteiro("Informe o ID do problema"),
                texto("Informe a descrição do problema"),
                inteiro("Informe a gravidade do problema"),
                texto("Informe o tipo do problema"));

        // PecasVeiculo
        PecasVeiculo objPecasVeiculo = new PecasVeiculo(
                inteiro("Informe o ID da peça"),
                texto("Informe o nome da peça"),
                texto("Informe a descrição da peça"));

        // Servico
        Servico objServico = new Servico(
                inteiro("Informe o ID do serviço"),
                texto("Informe o nome do serviço"),
                texto("Informe a descrição do serviço"));

        // Exibindo as informações no console
        System.out.println("Informações do Cliente:" +
                        "\nID Cliente: " + objCliente.getIdCliente() +
                        "\nCPF: " + objCliente.getCpf() +
                        "\nNome: " + objCliente.getNome() +
                        "\nEmail: " + objCliente.getEmail() +
                        "\nTelefone: " + objCliente.getTelefone() +
                        "\nEndereço Logradouro: " + objCliente.getEnderecoLogradouro() +
                        "\nEndereço Número: " + objCliente.getEnderecoNumero() +
                        "\nEndereço Complemento: " + objCliente.getEnderecoComplemento() +
                        "\nEndereço Bairro: " + objCliente.getEnderecoBairro() +
                        "\nEndereço CEP: " + objCliente.getEnderecoCep() +
                        "\nEndereço Cidade: " + objCliente.getEnderecoCidade() +
                        "\nEndereço Estado: " + objCliente.getEnderecoEstado() +
                        "\n\nInformações da Mecânica:" +
                        "\nID Oficina: " + objMecanica.getIdOficina() +
                        "\nNome: " + objMecanica.getNome() +
                        "\nTelefone: " + objMecanica.getTelefone() +
                        "\nHorário Abertura: " + objMecanica.getHorarioAbertura() +
                        "\nHorário Fechamento: " + objMecanica.getHorarioFechamento() +
                        "\nEndereço Logradouro: " + objMecanica.getEnderecoLogradouro() +
                        "\nEndereço Número: " + objMecanica.getEnderecoNumero() +
                        "\nEndereço Complemento: " + objMecanica.getEnderecoComplemento() +
                        "\nEndereço Bairro: " + objMecanica.getEnderecoBairro() +
                        "\nEndereço CEP: " + objMecanica.getEnderecoCep() +
                        "\nEndereço Cidade: " + objMecanica.getEnderecoCidade() +
                        "\nEndereço Estado: " + objMecanica.getEnderecoEstado() +
                        "\n\nInformações do Veículo:" +
                        "\nPlaca: " + objVeiculo.getPlaca() +
                        "\nMarca: " + objVeiculo.getMarca() +
                        "\nModelo: " + objVeiculo.getModelo() +
                        "\nAno: " + objVeiculo.getAno() +
                        "\nID Cliente: " + objVeiculo.getIdCliente() +
                        "\n\nInformações do Problema:" +
                        "\nID Problema: " + objDiagnostico.getIdProblema() +
                        "\nDescrição: " + objDiagnostico.getDescricao() +
                        "\nGravidade: " + objDiagnostico.getGravidade() +
                        "\nTipo: " + objDiagnostico.getTipo() +
                        "\n\nInformações das Peças:" +
                        "\nID Peça: " + objPecasVeiculo.getIdPeca() +
                        "\nNome: " + objPecasVeiculo.getNome() +
                        "\nDescrição: " + objPecasVeiculo.getDescricaoPeca() +
                        "\n\nInformações do Serviço:" +
                        "\nID Serviço: " + objServico.getIdServico() +
                        "\nNome: " + objServico.getNome() +
                        "\nDescrição: " + objServico.getDescricao());
    }
}
