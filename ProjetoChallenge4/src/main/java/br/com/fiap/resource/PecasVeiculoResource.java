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

import br.com.fiap.beans.PecasVeiculo;
import br.com.fiap.bo.PecasVeiculoBO;

@Path("/pecas")
public class PecasVeiculoResource {

    private PecasVeiculoBO pecasVeiculoBO;

    public PecasVeiculoResource() {
        try {
            pecasVeiculoBO = new PecasVeiculoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todas as peças
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPecas() {
        try {
            List<PecasVeiculo> pecas = pecasVeiculoBO.listarPecas();
            return Response.ok(pecas).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar peças").build();
        }
    }

    // Buscar peça específica pelo ID
    @GET
    @Path("/{id_peca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPeca(@PathParam("id_peca") int idPeca) {
        try {
            PecasVeiculo peca = pecasVeiculoBO.buscarPeca(idPeca);
            if (peca == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Peça não encontrada").build();
            }
            return Response.ok(peca).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar peça").build();
        }
    }

    // Inserir nova peça
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirPeca(PecasVeiculo peca, @Context UriInfo uriInfo) {
        try {
            String resultado = pecasVeiculoBO.inserirPeca(peca);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(peca.getIdPeca()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir peça").build();
        }
    }

    // Atualizar peça existente
    @PUT
    @Path("/{id_peca}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(PecasVeiculo peca, @PathParam("id_peca") int idPeca) {
        try {
            peca.setIdPeca(idPeca);
            String resultado = pecasVeiculoBO.atualizarPeca(peca);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar peça").build();
        }
    }

    // Deletar peça pelo ID
    @DELETE
    @Path("/{id_peca}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarPeca(@PathParam("id_peca") int idPeca) {
        try {
            String resultado = pecasVeiculoBO.deletarPeca(idPeca);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar peça").build();
        }
    }
}
