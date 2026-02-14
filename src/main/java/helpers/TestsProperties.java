package helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("file:src/main/resources/tests.properties")

public interface TestsProperties extends Config {

    @Config.Key("chromeDriver.endpoint")
    String endpointChromeDriver();

    @Config.Key("stepik.url")
    String stepikUrl();
}
