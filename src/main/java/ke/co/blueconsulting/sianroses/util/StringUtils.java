package ke.co.blueconsulting.sianroses.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static final String SPACE_CHAR = " ";
	
	/**
	 * Checks if a string input is blank
	 *
	 * @param stringInput String to check if is blank
	 * @return <b>True</b> if string input is blank <b>False</b> if string input
	 * is not blank
	 */
	public static boolean isBlank(String stringInput) {
		return null == stringInput || SPACE_CHAR.equals(stringInput);
	}
	
	/**
	 * Checks if a string input is null or empty
	 *
	 * @param stringInput String to check if is blank
	 * @return <b>True</b> if string input is null or empty <b>False</b> if
	 * string input is not null or empty
	 */
	public static boolean isNullOrEmpty(String stringInput) {
		return stringInput == null || stringInput.trim().isEmpty();
	}
	
	/**
	 * Insert line breaks into long string.
	 *
	 * @param stringInput The string to break
	 * @param charLimit   The numbers of characters to be used to count to break
	 *                    the long string
	 * @return <b>String</b> The broken concatenated string
	 */
	private static String breakLongString(String stringInput, int charLimit) {
		StringBuilder output = new StringBuilder();
		String rest = stringInput;
		int i = 0;
		
		if (rest.length() < charLimit) {
			output = new StringBuilder(rest);
		} else if ((rest != null) &&!rest.equals("")) // safety precaution
		{
			do {
				i = rest.lastIndexOf(" ", charLimit) + 1;
				if (i == -1) {
					i = charLimit;
				}
				if (i > rest.length()) {
					i = rest.length();
				}
				output.append(rest, 0, i).append("\n");
				rest = rest.substring(i);
			} while ((rest.length() > charLimit));
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
	
	public static String getString(String key) {
		String string = null;
		try {
			string = ResourceBundle.getBundle("strings").getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	public static boolean isValidEmailAddress(String emailAddress) {
		
		Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		Matcher regMatcher = regexPattern.matcher(emailAddress);
		return regMatcher.matches();
	}
}
