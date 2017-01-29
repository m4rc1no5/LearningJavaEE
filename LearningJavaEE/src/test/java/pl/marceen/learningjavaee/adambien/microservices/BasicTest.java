package pl.marceen.learningjavaee.adambien.microservices;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author Marcin Zaremba
 */
public class BasicTest {
    private final static Logger logger = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void references() {
        Runnable runnable = this::display;

        new Thread(runnable).start();
    }

    @Test
    public void callable() throws Exception {
        Callable<String> messageProvider = this::message;
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> futureResult = threadPool.submit(messageProvider);
            futures.add(futureResult);
        }

        for (Future<String> future : futures) {
            String result = future.get();
            logger.info("result: {}", result);
        }
    }

    @Test
    public void backpressure() throws Exception {
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(2);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, queue, this::onOverload);
        long startTime = System.currentTimeMillis();
        threadPool.submit(this::display);
        duration(startTime);
        threadPool.submit(this::display);
        duration(startTime);
        threadPool.submit(this::display);
        duration(startTime);
        threadPool.submit(this::display);
        duration(startTime);
    }

    private void onOverload(Runnable r, ThreadPoolExecutor executor) {
        logger.info("-- runnable {} executor {}", r, executor.getActiveCount());
    }

    private void duration(long startTime) {
        logger.info("-- took: {}", System.currentTimeMillis() - startTime);
    }

    private String message() {
        return "Hey " + System.currentTimeMillis();
    }

    private void display() {
        logger.info("hello duke");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
