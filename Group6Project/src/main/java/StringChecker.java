public class StringChecker {

    /*
        Manderas and Jacob, complete the methods below in accordance
        with the assignment requirements.  No other methods should be
        required.
        Patient ID check is provided as an example.
     */


    // patient information checks
    public static boolean patientIDCheck(String s) {
        if(s.length() != 9) return false;
        if (s.charAt(0) != 'P') return false;
        for (int i = 1; i < s.length(); i++ ) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean SSNCheck(String s) {
        // complete
        return true;
    }
    public static boolean phoneCheck(String s) {
        // complete
        return true;
    }
    public static boolean DOBCheck(String s) {
        // complete
        return true;
    }
    public static boolean sexCheck(String s) {
        // complete
        return true;
    }
    public static boolean stateCheck(String s) {
        // complete
        return true;
    }
    public static boolean zipCheck(String s) {
        // complete
        return true;
    }
    public static boolean conditionCheck(String s) {
        // complete
        return true;
    }

    // Dr Checks
    public static boolean drIDCheck(String s) {
        // complete
        return true;
    }

    // Department Checks
    public static boolean deptCodeChec(String s) {
        // complete
        return true;
    }
    public static boolean officeNumCheck(String s) {
        // complete
        return true;
    }

    // Interaction Checks
    public static boolean dateTimeCheck(String s) {
        // complete
        return true;
    }

    // Procedure Checks
    public static boolean procedureNumberCheck(String s) {
        // complete
        return true;
    }

    // Prescription Checks
    public static boolean dateCheck(String s) {
        // complete
        return true;
    }


    
}
