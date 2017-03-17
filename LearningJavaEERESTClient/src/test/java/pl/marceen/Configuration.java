package pl.marceen;

public class Configuration {
    private final static String DOCKER_HOST = "http://10.0.75.1:8282";

    public final static String HOST = DOCKER_HOST + "/api/rest";

    public static final String HELLO_WORLD_CURRENT_TIME_URL = Configuration.HOST + "/helloWorld/currentTime";
}
