package rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("quote")
public class RestQuote
{

    @Context
    private UriInfo context;

    public RestQuote()
    {
    }

    private static Map<Integer, String> quotes = new HashMap()
    {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuotes()
    {
        return "{\"Quotes\":\"" + quotes + "\"}";
    }
    
    //Search by ID
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuoteById(@PathParam("id") int id)
    {
        return "{\"Quote\":\"" + quotes.get(id) + "\"}";
    }

    //GET random 
    @Path("random")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomQuote()
    {
        Random random = new Random();
        int low = 1;
        int heigh = quotes.size();
        int result = random.nextInt(heigh -low) + low;
        return "{\"quote\" :\"" + quotes.get(result) + "\"}";
    }

    //Create a new
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postQuoteId(String content)
    {
        quotes.put(quotes.size()+1, "text");
        return "{\"id\": " + quotes.size() + ", \"quote\": \" " + quotes.get(quotes.size()) + "\"}";
    }

    //EDIT by ID
    @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String putQuoteId(String content, @PathParam("id") int id, @QueryParam("quote") String quote)
    {
        quotes.put(id, quote);
        return "{\"id\": " + id + ", \"quote\": \" " + quotes.get(id) + "\"}";
    }

    //DELETE BY ID
    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String putQuoteId(String content, @PathParam("id") int id)
    {
        quotes.remove(id);
        return "{\"id\": " + id + ", \"quote\": \" " + quotes.get(id) + "\"} "+ "quotes: "+quotes;
    }
}
