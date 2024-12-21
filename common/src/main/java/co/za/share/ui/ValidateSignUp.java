package co.za.share.ui;

public class ValidateSignUp {

    public boolean isEmailValid(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean isPhoneNumberValid(String number) {
        if (number.contains("+27") && number.length() == 12) {
            return true;
        }
        return false;
    }
}
