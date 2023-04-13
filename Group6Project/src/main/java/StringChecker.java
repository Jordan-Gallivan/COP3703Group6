public class StringChecker {

    /*
        Fix:
            o sex check
            o state check
            o condition check
            o interaction check
            o date check
            o time check
            o procedure number check
            o procedure duration check
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
        if (s.length() != 9) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean phoneCheck(String s) {
        if (s.length() != 10) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean sexCheck(String s) {
        //FIX
        if (s.length() > 10) return false;
        if (s.equalsIgnoreCase("male") == false) return false;
        if (s.equalsIgnoreCase("female") == false) return false;
        if (s.equalsIgnoreCase("other") == false) return false;
        return true;
    }
    public static boolean stateCheck(String s) {
        // FIX
        if (s.length() != 2) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean zipCheck(String s) {
        if (s.length() != 5) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean conditionCheck(String s) {
        // FIX
        if (s.length() > 8) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }

    // Dr Checks
    public static boolean drIDCheck(String s) {
        if(s.length() != 9) return false;
        if (s.charAt(0) != 'D') return false;
        for (int i = 1; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // Department Checks
    public static boolean deptCodeCheck(String s) {
        if(s.length() > 4) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean officeNumCheck(String s) {
        if(s.length() != 4) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // Interaction Checks
    public static boolean interactionIDCheck(String s) {
        // verify integer
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        return true;
    }
    public static boolean dateCheck(String s) {
        // Needs to be MM-DD-YYYY
        // 1<= MM <= 12
        // 1<= DD <= 31
        // 1900 <= YYYY <= 2500
                
        return true;
    }
    public static boolean timeCheck(String s) {
        // Needs to be HHMM (24hour clock)
        // 00 <= HH <= 23
        // 00 <= MM <= 59
        
        return true;
    }

    // Procedure Checks
    public static boolean procedureNumberCheck(String s) {
        // FIX
        if(s.length() != 7) return false;
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        for (int i = 3; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    public static boolean procedureDurationCheck(String s) {
        // verify positive number (double precision floating point)
        
        return true;
    }

    // Prescription Checks



    
}
