package rest;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.services.FilmService;
import domain.Comment;
import domain.Film;





@Path("/films") 
public class FilmResources {
	private FilmService db = new FilmService();
	
	@GET //wysiwetlenie filmow
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAll(){
		return db.getAll();
	}	
	
	@GET //wyswietlenie filmu o id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Film result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	@POST //dodanie filmu
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Film film) {
		db.add(film);
	return Response.ok(film.getId()).build();
	}
	
	@PUT // zmiana danych filmu
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Film f) {
		Film result = db.get(id);
		if(result==null)
			return Response.status(404).build();
		f.setId(id);
		db.update(f);
		return Response.ok().build();
	}

	@DELETE // usuniecie filmu o id
	@Path("/{id}")
	public Response delete (@PathParam("id")int id) {
		Film result = db.get(id);
		if(result==null)
			return Response.status(404).build();
		db.delete(result);
	return Response.ok().build();	
	}
	
	
	@DELETE // komentarza o id filmu o filmId
	@Path("{filmId}/comments/{id}")
	public Response deleteComment (@PathParam("id")int id,@PathParam("filmId")int filmId) {
		Film result = db.get(filmId);
		List<Comment> comments =new ArrayList<Comment>();
			comments=result.getComments();
		comments.remove(id);
		result.setComments(comments);
		if(result==null)
			return Response.status(404).build();
		db.update(result);
	return Response.ok().build();	
	}
	
	@POST //dodanie komentarza do filmu
	@Path("/{filmId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("filmId") int filmId,Comment comment) {
		Film result = db.get(filmId);
		if(result ==null)
			return Response.status(404).build();
		if(result.getComments()==null)
			result.setComments(new ArrayList<Comment>());
		comment.setId(result.getComcounter());
		result.setComcounter(result.getComcounter()+1);
		result.getComments().add(comment);
		return Response.ok().build();
	}
	
	@GET // lista komentarzy filmu o Id
	@Path("/{filmId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("filmId") int filmId){
		Film result = db.get(filmId);
		if(result==null)
			return null;
		if(result.getComments()==null)
			result.setComments(new ArrayList<Comment>());
		return result.getComments();
	}
	
	@GET // ocena filmu o podanym id
	@Path("/{filmId}/score")
	@Produces(MediaType.APPLICATION_JSON)
	public float getScore(@PathParam("filmId") int filmId){
		Film result = db.get(filmId);
	
		
		if(result==null)
			return 0;
		List<Comment>coms=result.getComments();
		float score=0;
		for(Comment c:coms) {
			score=score+c.getScore();
		}
		score=score/coms.size();
		return score;
	}
	
}
