public class StringChecker {

    public static boolean patientIDCheck(String s) {
        if (s.charAt(0) != 'P') return false;
        for (int i = 1; i < s.length(); i++ ) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
}
