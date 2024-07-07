package org.codechallenge.patternrecognition;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.codechallenge.patternrecognition.rest.impl.AppController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 *
 *  @author Marco Pappalardo
 *  @see AppController
 *  @since 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatternRecognitionAppTest {
    @Autowired
    AppController appController;

    @Test
    void contextLoads() {
        Assertions.assertThat(appController).isNotNull();
    }
}
