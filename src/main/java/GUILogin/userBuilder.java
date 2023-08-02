package GUILogin;

import java.util.TreeMap;

public class userBuilder extends GUILogin{

    public static char ch;
    public static boolean capitalFlag = false;
    public static boolean lowerFlag = false;
    public static boolean numberFlag = false;
    public static boolean specialFlag = false;

    // username and password
    // check the length minimum of 8 characters
    public static boolean lengthCheck(String s) {
        return s.length() >= 8;
    }

    // username checker
    // does not need a special character
    public static boolean usernameChecker(String str) {

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);

            if (Character.isSpaceChar(ch)) { // checks if there is a space
                break;
            }

            if (Character.isDigit(ch)) { // checks for digit
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) { // checks for upper case
                capitalFlag = true;
            }
            else if (Character.isLowerCase(ch)) { // checks for lower case
                lowerFlag = true;
            }
            if (numberFlag && capitalFlag && lowerFlag) {
                return true;
            }
        }
        return false;
    }

    // password checker
    public static boolean passwordChecker(String str) {

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);

            if (Character.isSpaceChar(ch)) { // checks if there is a space
                break;
            }

            if (Character.isDigit(ch)) { // checks for digit
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) { // checks for upper case
                capitalFlag = true;
            }
            else if (Character.isLowerCase(ch)) { // checks for lower case
                lowerFlag = true;
            }
            else if (!Character.isLetter(ch)) { // checks for special character
                specialFlag = true;
            }
            if (numberFlag && capitalFlag && lowerFlag && specialFlag) {
                return true;
            }
        }
        return false;
    }

}
