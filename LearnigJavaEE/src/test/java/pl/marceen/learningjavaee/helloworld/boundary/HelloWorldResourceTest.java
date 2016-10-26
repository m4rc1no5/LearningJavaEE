package pl.marceen.learningjavaee.helloworld.boundary;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldResourceTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    private HelloWorldResource sut;

    @Before
    public void setUp() throws Exception {
        sut = new HelloWorldResource();
    }

    @Test
    public void currentTime() throws Exception {

        //when
        String response = sut.currentTime();

        //then
        logger.info("Response: {}", response);
        assertThat(response).contains("Current time:");
    }

}