package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> providerGetRecourse() {
        return Stream.of(
                Arguments.of("java","Бесплатно","Русский"),
                Arguments.of("python","от 5000","Английский")
        );
    }
}
