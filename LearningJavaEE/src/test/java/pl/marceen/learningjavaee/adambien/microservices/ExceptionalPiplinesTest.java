package pl.marceen.learningjavaee.adambien.microservices;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author Marcin Zaremba
 */
public class ExceptionalPiplinesTest {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionalPiplinesTest.class);

    @Test
    public void handle() throws Exception {
        CompletableFuture.supplyAsync(this::exceptional)
                .exceptionally(this::transform)
                .thenAccept(this::consume)
                .get()
        ;
    }

    @Test
    public void handleVersion2() throws Exception {
        CompletableFuture.supplyAsync(this::exceptional)
                .handle(this::handle)
                .thenAccept(this::consume)
                .get()
        ;
    }

    private String handle(String valid, Throwable throwable) {
        return valid + " - " + throwable.toString();
    }

    private String exceptional() {
        throw new IllegalStateException("happens");
    }

    private String transform(Throwable throwable) {
        return throwable.toString();
    }

    private void consume(String s) {
        logger.info("message = {}", s);
    }
}
