package pl.marceen.learningjavaee.adambien.microservices;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author Marcin Zaremba
 */
public class PipelineTest {
    private final static Logger logger = LoggerFactory.getLogger(PipelineTest.class);

    @Test
    public void pipeline() throws Exception {
        CompletableFuture.supplyAsync(this::message)
                .thenApply(this::beautyfy)
                .thenAccept(this::consumeMessage)
                .thenRun(this::finalAction);
    }

    private String message() {
        return "hey duke " + System.currentTimeMillis();
    }

    private String beautyfy(String input) {
        return "+ " + input + " +";
    }

    private void consumeMessage(String message) {
        logger.info("message = {}", message);
    }

    private void finalAction() {
        logger.info("Clean up");
    }
}
