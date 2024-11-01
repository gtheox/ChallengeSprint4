package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.beans.Veiculo;
import br.com.fiap.bo.VeiculoBO;

@Path("/veiculos")
public class VeiculoResource {

    private VeiculoBO veiculoBO;

    public VeiculoResource() {
        try {
            veiculoBO = new VeiculoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os veículos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoBO.listarVeiculos();
            return Response.ok(veiculos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar veículos").build();
        }
    }

    // Buscar veículo pelo placa
    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculo(@PathParam("placa") String placa) {
        try {
            Veiculo veiculo = veiculoBO.buscarVeiculo(placa);
            if (veiculo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado").build();
            }
            return Response.ok(veiculo).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar veículo").build();
        }
    }

    // Inserir novo veículo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirVeiculo(Veiculo veiculo, @Context UriInfo uriInfo) {
        try {
            String resultado = veiculoBO.inserirVeiculo(veiculo);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(veiculo.getPlaca());  // Assuming 'getPlaca()' returns the vehicle's license plate
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir veículo").build();
        }
    }

    // Atualizar veículo existente
    @PUT
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Veiculo veiculo, @PathParam("placa") String placa) {
        try {
            veiculo.setPlaca(placa);  // Assuming 'setPlaca()' is available
            String resultado = veiculoBO.atualizarVeiculo(veiculo);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar veículo").build();
        }
    }

    // Deletar veículo pela placa
    @DELETE
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("placa") String placa) {
        try {
            String resultado = veiculoBO.deletarVeiculo(placa);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar veículo").build();
        }
    }
}
