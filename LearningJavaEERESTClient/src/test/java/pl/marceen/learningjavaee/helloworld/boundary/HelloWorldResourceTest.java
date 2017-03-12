package pl.marceen.learningjavaee.helloworld.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.Configuration;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloWorldResourceTest {
    private static final String HELLO_WORLD_CURRENT_TIME_URL = Configuration.HOST + "/helloWorld/currentTime";
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(HELLO_WORLD_CURRENT_TIME_URL);
    private Logger logger = LoggerFactory.getLogger(HelloWorldResourceTest.class);

    @Test
    public void currentTime() throws Exception {
        logger.info("url: {}", HELLO_WORLD_CURRENT_TIME_URL);

        Response response = provider.target().request(MediaType.TEXT_PLAIN).get();

        String payload = response.readEntity(String.class);

        logger.info("Response: {}", payload);
    }
}
