package dk.delfinen.gruppe5.adapter.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    @BeforeEach
    void setUp() {
        var parser = new InputHandler();
    }

    @Test
    void invalid_input_returns_failure() {
        var result = parser("abc");

        assertFalse(result.isSuccess());
    }
    @Test
    void valid_number_returns_success() {
        var result = parser.parseInt("42");

        assertTrue(result.isSuccess());
        assertEquals(42, result.getValue());
    }
}
