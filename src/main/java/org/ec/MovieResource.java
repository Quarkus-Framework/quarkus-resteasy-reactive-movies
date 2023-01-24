package org.ec;

import io.smallrye.mutiny.Uni;
import org.ec.entity.Movie;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("movies")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
@Tag(name = "MovieResource", description = "Movie Resource")
public class MovieResource {

    public static List<Movie> movies = new ArrayList<>();

//    @GET
//    public Uni<List<Movie>> get() {
//        return Movie.listAll(Sort.by("name"));
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getMovies",
            summary ="Get Movies",
            description = "Get all movies inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @GET
    @Path("/size")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
            operationId = "countMovies",
            summary ="Count Movies",
            description = "Size of the list movies")
    @APIResponse(
            responseCode = "200",
            description = "Operation Completed",
            content = @Content(mediaType = MediaType.TEXT_PLAIN))
    public Integer countMovies() {
        return movies.size();
    }

    @GET
    @Path("/{id}")
    public Uni<Movie> getSingle(Long id) {
        return Movie.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "createMovies",
            summary ="Create a new Movie",
            description = "Create a new movie inside the list")
    @APIResponse(
            responseCode = "201",
            description = "Movie created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response createMovie(
            @RequestBody(
                description = "Movie to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Movie.class))
            )Movie movie) {
        movies.add(movie);
        return Response.status(Response.Status.CREATED).entity(movies).build();
    }

//    @POST
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
//    public Uni<Response> create(Movie movie) {
//        if (movie == null || movie.id != null) {
//            throw new WebApplicationException("Id was invalidly set on request.", 422);
//        }
//        return Panache.<Movie>withTransaction(movie::persist)
//                .map(inserted -> Response.created(URI.create("/movies/" + inserted.id)).build());
//    }

    @PUT
    @Path("{id}/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "updateMovies",
            summary ="Update an existing Movie",
            description = "Update a movie inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Movie updated",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response updateMovie(
            @Parameter(
	            description = "Movie id",
                required = true)
            @PathParam("id") Long iDs,
            @Parameter(
                    description = "Movie name",
                    required = true)
            @PathParam("name") String name) {
        movies = movies.stream().map(movie -> {
            if(movie.id.intValue() == iDs.intValue()) {
                movie.setName(name);
            }
            return movie;
        }).collect(Collectors.toList());
        return Response.ok(movies).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "deleteMovies",
            summary ="Delete an existing Movie",
            description = "Delete a movie inside the list")
    @APIResponse(
            responseCode = "200",
            description = "Movie deleted",
            content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response deleteMovie(
            @Parameter(
                    description = "Movie id",
                    required = true)
            @PathParam("id") Long iDs) {

        movies = movies.stream().
                filter(movie -> !(movie.id.intValue() == iDs.intValue()))
                .collect(Collectors.toList());

        return Response.ok(movies).build();
    }
}
