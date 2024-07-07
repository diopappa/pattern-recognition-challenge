package org.codechallenge.patternrecognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main class.
 *
 * @author Marco Pappalardo
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan("org.codechallenge.patternrecognition")
public class PatternRecognitionApp {
    public static void main(String[] args) { SpringApplication.run(PatternRecognitionApp.class, args); }
}
