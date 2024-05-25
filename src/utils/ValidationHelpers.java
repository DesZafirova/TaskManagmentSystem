package utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelpers {
    public static final String NO_SUCH_ENUM = "There is no %s in %s.";

    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";
    private static final String INVALID_RANGE_OF_ARGUMENTS = "Invalid number of arguments. Expected between: %d and %d, received %d.";

    public static void validateIntRange(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(INVALID_RANGE_OF_ARGUMENTS.formatted(min, max, value));
        }
    }

    public static void validateDecimalRange(double value, double min, double max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateValue(stringToValidate.length(), minLength, maxLength, errorMessage);
    }


    public static <T extends Comparable<T>> boolean validateValue(T value, T min, T max, String errorMessage) {
        boolean flag = value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
        if(!flag){
            throw new IllegalArgumentException(errorMessage);
        }
        return flag;
    }
    public static <E extends Enum<E>> void validateEnum(String valueToParse, Class<E> type) {
        try {
            Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }

}
