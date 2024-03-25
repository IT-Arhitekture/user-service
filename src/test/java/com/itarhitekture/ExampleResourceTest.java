package com.itarhitekture;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void alwaysPassTest() {
        assertTrue(true);
    }
}