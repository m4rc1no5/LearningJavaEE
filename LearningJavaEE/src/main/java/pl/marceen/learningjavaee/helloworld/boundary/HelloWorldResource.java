package pl.marceen.learningjavaee.helloworld.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("helloWorld")
public class HelloWorldResource {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Path("currentTime")
    public String currentTime() {
        String msg = String.format("Current time: %s", System.currentTimeMillis());
        logger.info(msg);

        return msg;
    }

    @POST
    @Path("currentTime")
    public void message(String message) {
        logger.info("Message: {}", message);
    }

}
