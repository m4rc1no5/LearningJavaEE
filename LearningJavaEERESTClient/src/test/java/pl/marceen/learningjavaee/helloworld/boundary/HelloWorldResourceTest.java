package pl.marceen.learningjavaee.helloworld.boundary;

import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class HelloWorldResourceTest {
    private Logger logger = LoggerFactory.getLogger(HelloWorldResourceTest.class);

    private WebTarget supplier;
    private WebTarget processor;

    @Before
    public void setUp() throws Exception {
        Client client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 100);
        client.property(ClientProperties.READ_TIMEOUT, 5000);

        supplier = client.target(Configuration.HELLO_WORLD_CURRENT_TIME_URL);
        processor = client.target(Configuration.PROCESSOR_BEAUTIFICATION_URL);
    }

    @Test
    public void currentTime() throws Exception {
        logger.info("url: {}", Configuration.HELLO_WORLD_CURRENT_TIME_URL);

        Response response = supplier.request(MediaType.TEXT_PLAIN).get();

        String payload = response.readEntity(String.class);

        logger.info("Response: {}", payload);
    }

    @Test
    public void currentTimeWithSupplier() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Supplier<String> messageSupplier = () -> supplier.request(MediaType.TEXT_PLAIN).get(String.class);
        CompletableFuture.supplyAsync(messageSupplier, pool)
                .thenApply(this::process)
                .thenAccept(this::consume)
                .get();
    }

    private String process(String input) {
        Response response = processor.request().post(Entity.text(input));
        return response.readEntity(String.class);
    }

    private void consume(String message) {
        supplier.request().post(Entity.text(message));
    }
}
