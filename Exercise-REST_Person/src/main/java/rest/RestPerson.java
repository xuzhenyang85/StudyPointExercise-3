package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import deploy.DeploymentConfiguration;
import entity.Persons;
import facade.FacadePerson;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("person")
public class RestPerson
{

    private FacadePerson fp;
    private Gson gson;

    @Context
    private UriInfo context;

    public RestPerson()
    {
        fp = new FacadePerson();
        fp.addEntityManagerFactory(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        gson = new Gson();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons()
    {
        return new Gson().toJson(fp.getPersons());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") Long id)
    {
        Persons person = fp.getPerson(id);
        if (person != null)
        {
            return new Gson().toJson(person);
        }
        return "{}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postPerson(String content)
    {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        String PersonFirstName = "";
        String PersonlastName = "";
        String PhoneNumber = "";
        
        if(body.has("fName")){
            PersonFirstName = body.get("fName").getAsString();
        }
        if(body.has("lName")){
            PersonlastName = body.get("lName").getAsString();
        }
        if(body.has("phone")){
            PhoneNumber = body.get("phone").getAsString();
        }
        Persons person = new Persons(PersonFirstName,PersonlastName,PhoneNumber);
        fp.addPerson(person);
        
        return new Gson().toJson(person);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putPerson(String content)
    {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        Persons person = fp.getPerson(body.get("id").getAsLong());
        
        if(body.has("fName"))
        {
            person.setfName(body.get("fName").getAsString());
        }
        if(body.has("lName"))
        {
            person.setlName(body.get("lName").getAsString());
        }
        if(body.has("phone"))
        {
            person.setPhone(body.get("phone").getAsString());
        }       

        fp.editPerson(person);
        
        return new Gson().toJson(person);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String deletePerson(@PathParam("id") Long id)
    {
        Persons person = fp.deletePerson(id);
        
        return new Gson().toJson(person);
    }
}
