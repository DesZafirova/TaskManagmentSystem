package utils;

import java.math.BigDecimal;

public class FormattingHelpers {
    public static final String PURPLE = "\033[35m";
    public static final String RESET = "\033[0m";
    public static String colorCode = PURPLE;


    public static String removeTrailingZerosFromDouble(double number) {
        BigDecimal num = BigDecimal.valueOf(number).stripTrailingZeros();
        return num.toPlainString();
    }

}
