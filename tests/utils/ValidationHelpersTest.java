package utils;

import model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationHelpersTest {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;
    private static final int VALID_VALUE = 5;
    private static final int INVALID_VALUE = 20;

    @BeforeEach
    void setUp() {
    }

    @Test
    void validateIntRange() {
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateIntRange(INVALID_VALUE, MIN_VALUE, MAX_VALUE));
        assertDoesNotThrow(() -> ValidationHelpers.validateIntRange(VALID_VALUE, MIN_VALUE, MAX_VALUE));

    }

    @Test
    void validateDecimalRange() {
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateDecimalRange(INVALID_VALUE, MIN_VALUE, MAX_VALUE, "message"));
        assertDoesNotThrow(() -> ValidationHelpers.validateDecimalRange(VALID_VALUE, MIN_VALUE, MAX_VALUE, "message"));
    }

    @Test
    void validateArgumentsCount() {
        List<String> args = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateArgumentsCount( args, 3));
        assertDoesNotThrow(() -> ValidationHelpers.validateArgumentsCount( args, 0));
    }

    @Test
    void validateStringLength() {
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateStringLength("ab".repeat(20), MIN_VALUE, MAX_VALUE, "message"));
        assertDoesNotThrow(() -> ValidationHelpers.validateStringLength("ab", MIN_VALUE, MAX_VALUE, "message"));

    }

    @Test
    void validateValue() {
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateValue(INVALID_VALUE, MIN_VALUE, MAX_VALUE, "error"));
        assertDoesNotThrow(() -> ValidationHelpers.validateValue(VALID_VALUE, MIN_VALUE, MAX_VALUE, "error"));

    }

    @Test
    void validateEnum() {
        assertDoesNotThrow(() -> ValidationHelpers.validateEnum("ACTIVE", Status.class));
        assertThrows(IllegalArgumentException.class, () -> ValidationHelpers.validateEnum("STATUS", Status.class));
    }
}