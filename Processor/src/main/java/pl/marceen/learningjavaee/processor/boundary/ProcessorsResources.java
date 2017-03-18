package pl.marceen.learningjavaee.processor.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Marcin Zaremba
 */
@Path("processors")
public class ProcessorsResources {
    private final static Logger logger = LoggerFactory.getLogger(ProcessorsResources.class);

    @POST
    @Path("beautifications")
    public String process(String input) {
        String msg = "+" + input + "+";
        logger.info(msg);

        return msg;
    }
}
