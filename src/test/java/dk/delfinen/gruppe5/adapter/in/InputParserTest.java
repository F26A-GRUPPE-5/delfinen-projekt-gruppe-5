package dk.delfinen.gruppe5.adapter.in;

import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputParserTest {

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @CsvSource({
            "abc",
            "ABC",
            "''",
            "   "
    })

    void invalid_input_throws_exception(String input) {
        InputParser parser = new InputParser(new Scanner(input + "\n"));
        assertThrows(InvalidInputException.class, parser::parseInt);
    }
//    @Test
//    void valid_number_returns_success() {
//        var result = parser.parseInt("42");
//
//        assertTrue(result.isSuccess());
//        assertEquals(42, result.getValue());
//    }
}
