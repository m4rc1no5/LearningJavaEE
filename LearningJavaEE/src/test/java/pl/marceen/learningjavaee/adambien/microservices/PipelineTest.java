package pl.marceen.learningjavaee.adambien.microservices;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

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

    @Test
    public void combiningPipelines() throws Exception {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(this::message).thenApplyAsync(this::beautyfy);
        CompletableFuture<String> second = CompletableFuture.supplyAsync(this::greetings).thenApplyAsync(this::beautyfy);

        first.thenCombine(second, this::combinator)
                .thenAccept(this::consumeMessage)
                .thenRun(this::finalAction);
    }

    @Test
    public void composingPipelines() throws Exception {
        CompletableFuture.supplyAsync(this::message)
                .thenCompose(this::compose)
                .thenAccept(this::consumeMessage);
    }

    private CompletionStage<String> compose(String input) {
        return CompletableFuture.supplyAsync(() -> input).thenApply(this::beautyfy);
    }

    private String greetings() {
        return "good morning";
    }

    private String combinator(String first, String second) {
        return first + " -- " + second;
    }

    private String message() {
        return "hey duke " + System.currentTimeMillis();
    }

    private String beautyfy(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "+ " + input + " +";
    }

    private void consumeMessage(String message) {
        logger.info("message = {}", message);
    }

    private void finalAction() {
        logger.info("Clean up");
    }
}
