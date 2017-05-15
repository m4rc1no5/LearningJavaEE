import org.junit.Test;

import java.util.Optional;

/**
 * @author Marcin Zaremba
 */
public class OptionalTest {
    @Test
    public void testIfPresent() throws Exception {
        //given
        Optional<String> stringOptional = stringOptional();

        //when
        stringOptional.ifPresent(System.out::println);
    }

    private Optional<String> stringOptional() {
        return Optional.of("aaaa");
    }
}
