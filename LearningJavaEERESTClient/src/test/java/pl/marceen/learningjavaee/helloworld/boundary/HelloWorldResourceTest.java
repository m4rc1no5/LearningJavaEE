package pl.marceen.learningjavaee.helloworld.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.Configuration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class HelloWorldResourceTest {
    private Logger logger = LoggerFactory.getLogger(HelloWorldResourceTest.class);

    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(Configuration.HELLO_WORLD_CURRENT_TIME_URL);

    @Rule
    public JAXRSClientProvider processorProvider = JAXRSClientProvider.buildWithURI(Configuration.PROCESSOR_BEAUTIFICATION_URL);

    @Test
    public void currentTime() throws Exception {
        logger.info("url: {}", Configuration.HELLO_WORLD_CURRENT_TIME_URL);

        Response response = provider.target().request(MediaType.TEXT_PLAIN).get();

        String payload = response.readEntity(String.class);

        logger.info("Response: {}", payload);
    }

    @Test
    public void currentTimeWithSupplier() throws Exception {
        Supplier<String> messageSupplier = () -> provider.target().request(MediaType.TEXT_PLAIN).get(String.class);
        CompletableFuture.supplyAsync(messageSupplier)
                .thenApply(this::process)
                .thenAccept(this::consume)
                .get();
    }

    private String process(String input) {
        Response response = processorProvider.target().request().post(Entity.text(input));
        return response.readEntity(String.class);
    }

    private void consume(String message) {
        provider.target().request().post(Entity.text(message));
    }
}
