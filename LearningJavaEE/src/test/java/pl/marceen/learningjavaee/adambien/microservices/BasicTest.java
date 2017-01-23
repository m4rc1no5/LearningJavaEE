package pl.marceen.learningjavaee.adambien.microservices;

import org.junit.Test;

/**
 * @author Marcin Zaremba
 */
public class BasicTest {

    @Test
    public void references() {
        Runnable runnable = this::display;

        new Thread(runnable).start();
    }

    private void display() {
        System.out.println("hello duke");
    }
}
