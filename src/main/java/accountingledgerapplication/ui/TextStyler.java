package accountingledgerapplication.ui;

public class TextStyler {

    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Add more styles if needed
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";

    /**
     * Returns the input text wrapped with the given color code.
     *
     * @param text  The text to color.
     * @param color The ANSI color code (e.g., TextStyler.RED).
     * @return Colored text.
     */
    public static String colorText(String text, String color) {
        return color + text + RESET;
    }

    /**
     * Returns the input text with both color and style.
     *
     * @param text  The text to format.
     * @param color The ANSI color code.
     * @param style The ANSI style code (e.g., BOLD or UNDERLINE).
     * @return Formatted text.
     */
    public static String styledText(String text, String color, String style) {
        return style + color + text + RESET;
    }

    // Example main method to demonstrate usage
    public static void main(String[] args) {
        System.out.println(colorText("Hello in red!", RED));
        System.out.println(colorText("This is green text!", GREEN));
        System.out.println(styledText("Bold blue text!", BLUE, BOLD));
        System.out.println(styledText("Underlined yellow text!", YELLOW, UNDERLINE));
    }
}
