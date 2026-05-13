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
            "5.6",
            "2147483648",
            "-2147483649",
            "infinity"
    })

    void invalid_int_throws_exception(String input) {
        InputParser parser = new InputParser(new Scanner(input + "\n"));
        assertThrows(InvalidInputException.class, parser::parseInt);
    }
    @ParameterizedTest
    @CsvSource({
            "0",
            "-1",
            "1",
            "2147483647",
            "-2147483648"
    })
    void valid_int_does_not_throw(String input) throws InvalidInputException {
        InputParser parser = new InputParser(new Scanner(input + "\n"));
        assertDoesNotThrow(parser::parseInt);
    }
//    @ParameterizedTest
//    @CsvSource({
//            "''",
//            "\uD83C\uDF55", //pizza emoji
//            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
//    })
//
//    void invalid_string_throws_exception(String input) {
//        InputParser parser = new InputParser(new Scanner(input + "\n"));
//        assertThrows(InvalidInputException.class, parser::parseString);
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "Anders",
//            "Carl Emil",
//            })
//
//    void valid_string_does_not_throw(String input) throws InvalidInputException {
//        InputParser parser = new InputParser(new Scanner(input + "\n"));
//        assertDoesNotThrow(parser::parseString);
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "ja",
//            "j",
//            "nej",
//            "n",
//            "JA",
//            "yes",
//            "y",
//            "no",
//            "true",
//            "false",
//    })
//
//    void invalid_boolean_throws_exception(String input) {
//        InputParser parser = new InputParser(new Scanner(input + "\n"));
//        assertThrows(InvalidInputException.class, parser::parseBoolean);
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "''",
//            "måske",
//            "1",
//    })
//
//    void valid_boolean_does_not_throw(String input) throws InvalidInputException {
//        InputParser parser = new InputParser(new Scanner(input + "\n"));
//        assertDoesNotThrow(parser::parseBoolean);
//    }
//
}
