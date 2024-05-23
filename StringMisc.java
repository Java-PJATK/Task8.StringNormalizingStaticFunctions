public class StringMisc {
    public static String norm(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
    }

    public static String init(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] parts = name.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(part.substring(0, 1).toUpperCase()).append(". ");
            }
        }
        return result.toString().trim();
    }

    public static String tr(String s, String from, String to) {
        if (s == null || from == null || to == null || from.length() != to.length()) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            int index = from.indexOf(c);
            result.append(index >= 0 ? to.charAt(index) : c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(norm("   caravaggio"));
        System.out.println(norm("   VERMEER     "));

        System.out.println(init("johann sebastian bach"));
        System.out.println(init("i. babeL"));
        System.out.println(init("jorge LUIS BORGES"));
        System.out.println(init("WOLFGANG a. mozart"));

        System.out.println(tr("Mississippi",
                "abcdefghijklmnopqrstuvwyz",
                "BCDEFGHIJKLMNOPQRSTUVWYZA"));
        System.out.println(tr("abcXYZ", "aZcX", "||Cx"));
    }
}