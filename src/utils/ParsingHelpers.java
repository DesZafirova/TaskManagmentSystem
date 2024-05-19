package utils;

import static utils.ValidationHelpers.NO_SUCH_ENUM;

public class ParsingHelpers {
    private static final String INT_PARSE_ERROR = "Provided value %s can not be cast to Integer.%n";

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static int tryParseInt(String valueToParse) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INT_PARSE_ERROR.formatted(valueToParse));
        }
    }


    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }
}
