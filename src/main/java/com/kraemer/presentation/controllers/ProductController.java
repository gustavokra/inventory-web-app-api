package com.kraemer.presentation.controllers;

import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.ModeloImportacaoProdutosDTO;
import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.ProductService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/product")
public class ProductController {

    @Inject
    private ProductService service;

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@HeaderParam EnumDBImpl dbImpl) {
        var allProducts = service.findAll(dbImpl);

        return Response.ok(allProducts).build();
    }

    @POST
    @RolesAllowed({ "ADMIN" })
    public Response create(ProductDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdProduct = service.create(dto, dbImpl);

        return Response.ok(createdProduct).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            ProductDTO dto,
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {
        var productUpdated = service.update(dto, id, dbImpl);

        return Response.ok(productUpdated).build();
    }

    @DELETE
    @RolesAllowed({ "ADMIN" })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam Long id, @HeaderParam EnumDBImpl dbImpl) {
        service.delete(id, dbImpl);

        return Response.ok().build();
    }

    @POST
    @Path("/import")
    @RolesAllowed({ "ADMIN" })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response importar(List<ModeloImportacaoProdutosDTO> data, @HeaderParam EnumDBImpl dbImpl) {
        try {
            service.importar(data, dbImpl);

            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao processar os dados do arquivo XLSX.")
                           .build();
        }
    }
}

