import java.lang.String;

public class Translator {
    private static final String OPENING_TAG = "<";
    private static final String CLOSING_TAG = ">";
    private String firstComponent;
    private String secondComponent;
    private String copula;
    private String translatedCopula;

    public void setComponents(String text) {
        // Example: <{tim} --> (/,livingIn,_,{graz})>.
        // Output: Tim is living in Graz.

        // Verify string is valid
        if (text == null || text.isEmpty()) {
            return;
        }
        // Verify string starts with "<"
        if (!text.startsWith("<")) {
            return;
        }

        // Determine the first component
        int openingTagIndex = text.indexOf(OPENING_TAG);
        int firstComponentStartIndex = openingTagIndex + 1;
        if(text.charAt(firstComponentStartIndex) == '{') {
            firstComponentStartIndex++;
        }
        int i = 1;
        while (Character.isAlphabetic(text.charAt(firstComponentStartIndex + i))) {
            i++;
        }
        int firstComponentEndIndex = firstComponentStartIndex + i;
        firstComponent = text.substring(firstComponentStartIndex, firstComponentEndIndex);

        // Determine the copula
        int copulaStartIndex = firstComponentEndIndex + 1;
        if(text.charAt(copulaStartIndex) == ' ') {
            copulaStartIndex++;
        }
        i = 1;
        while (!Character.isWhitespace(text.charAt(copulaStartIndex + i))) {
            i++;
        }
        int copulaEndIndex = copulaStartIndex + i;
        copula = text.substring(copulaStartIndex, copulaEndIndex);

        // Determine the second component
        int secondComponentStartIndex = copulaEndIndex + 1;
        if(text.charAt(secondComponentStartIndex) == ' ') {
            secondComponentStartIndex++;
        }
        i = 1;
        while (Character.isAlphabetic(text.charAt(secondComponentStartIndex + i))) {
            i++;
        }
        int secondComponentEndIndex = secondComponentStartIndex + i;
        secondComponent = text.substring(secondComponentStartIndex, secondComponentEndIndex);
    }

    private void translateCopula(String copula) {
        switch (copula) {
            case "-->":
                translatedCopula = "is";
                break;
            default:
                translatedCopula = "INVALID_COPULA";
                break;
        }
    }

    public String translate(String text) {
        setComponents(text);
        translateCopula(copula);
        return firstComponent + " " + translatedCopula + " " + secondComponent + ".";
    }
}
