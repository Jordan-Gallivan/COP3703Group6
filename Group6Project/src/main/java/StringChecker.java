public class StringChecker {

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
        if (s.length() > 10) return false;
        if (s.equalsIgnoreCase("male")) return true;
        if (s.equalsIgnoreCase("female")) return true;
        return s.equalsIgnoreCase("other");
    }

    public static boolean stateCheck(String s) {
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
        if (s.equalsIgnoreCase("critical")) return true;
        if (s.equalsIgnoreCase("stable")) return true;
        return s.equalsIgnoreCase("fair");
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
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean dateCheck(String s) {
        // Needs to be MM-DD-YYYY
        // 1<= MM <= 12
        // 1<= DD <= 31
        // 1900 <= YYYY <= 2500
        if (s.length() != 10) return false;
        try {
            int m = Integer.parseInt(s.substring(0,2));
            if (m < 1 || m > 12) return false;
            int d = Integer.parseInt(s.substring(3,5));
            if (d <1 || d > 31) return false;
            int y = Integer.parseInt(s.substring(6,10));
            if (y < 1900 || y > 2500) return false;
            return (s.charAt(2) == '-' && s.charAt(5) == '-');

        } catch (Exception e) {
            return false;
        }
    }
    public static boolean timeCheck(String s) {
        // Needs to be HHMM (24hour clock)
        // 00 <= HH <= 23
        // 00 <= MM <= 59
        if (s.length() != 4) return false;
        try {
            int h = Integer.parseInt(s.substring(0,2));
            if (h < 0 || h > 23 ) return false;
            int m = Integer.parseInt(s.substring(2,4));
            return !(m < 0 || m > 59);

        } catch (Exception e) {
            return false;
        }
    }

    // Procedure Checks
    public static boolean procedureNumberCheck(String s) {
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
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Prescription Checks



    
}
