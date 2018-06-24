package ke.co.blueconsulting.sianroses.util;

public class StringUtils {
  private static final String SPACE_CHAR = " ";
  
  /**
   * Checks if a string input is blank
   *
   * @param stringInput String to check if is blank
   * @return <b>True</b> if string input is blank <b>False</b> if string input is not blank
   */
  public static boolean isBlank(String stringInput) {
    return null == stringInput || SPACE_CHAR.equals(stringInput);
  }
  
  /**
   * Checks if a string input is null or empty
   *
   * @param stringInput String to check if is blank
   * @return <b>True</b> if string input is null or empty <b>False</b> if string input is not null or empty
   */
  public static boolean isNullOrEmpty(String stringInput) {
    return stringInput == null || stringInput.isEmpty();
  }
  
  /**
   * Insert line breaks into long string.
   *
   * @param stringInput The string to break
   * @param charLimit   The numbers of characters to be used to count to break the long string
   * @return <b>String</b> The broken concatenated string
   */
  public static String breakLongString(String stringInput, int charLimit) {
    StringBuilder output = new StringBuilder();
    String rest = stringInput;
    int i = 0;
    
    if (rest.length() < charLimit) {
      output = new StringBuilder(rest);
    } else if (!rest.equals("") && (rest != null))  // safety precaution
    {
      do {
        i = rest.lastIndexOf(" ", charLimit) + 1;
        if (i == -1)
          i = charLimit;
        if (i > rest.length())
          i = rest.length();
        output.append(rest.substring(0, i)).append("\n");
        rest = rest.substring(i);
      }
      while ((rest.length() > charLimit));
      output.append(rest);
    }
    
    return output.toString();
  }
  
  /**
   * Insert line breaks into long string.
   *
   * @param stringInput The string to break
   * @return <b>String</b> The broken concatenated string
   */
  public static String breakLongString(String stringInput) {
    return breakLongString(stringInput, 240);
  }
}
