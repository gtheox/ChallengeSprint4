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

import br.com.fiap.beans.Servico;
import br.com.fiap.bo.ServicoBO;

@Path("/servicos")
public class ServicoResource {

    private ServicoBO servicoBO;

    public ServicoResource() {
        try {
            servicoBO = new ServicoBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os serviços
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarServicos() {
        try {
            List<Servico> servicos = servicoBO.listarServicos();
            return Response.ok(servicos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar serviços").build();
        }
    }

    // Buscar serviço específico pelo ID
    @GET
    @Path("/{id_servico}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarServico(@PathParam("id_servico") int idServico) {
        try {
            Servico servico = servicoBO.buscarServico(idServico);
            if (servico == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Serviço não encontrado").build();
            }
            return Response.ok(servico).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar serviço").build();
        }
    }

    // Inserir novo serviço
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirServico(Servico servico, @Context UriInfo uriInfo) {
        try {
            String resultado = servicoBO.inserirServico(servico);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(servico.getIdServico()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir serviço").build();
        }
    }

    // Atualizar serviço existente
    @PUT
    @Path("/{id_servico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarServico(Servico servico, @PathParam("id_servico") int idServico) {
        try {
            servico.setIdServico(idServico);
            String resultado = servicoBO.atualizarServico(servico);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar serviço").build();
        }
    }

    // Deletar serviço pelo ID
    @DELETE
    @Path("/{id_servico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarServico(@PathParam("id_servico") int idServico) {
        try {
            String resultado = servicoBO.deletarServico(idServico);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar serviço").build();
        }
    }
}
