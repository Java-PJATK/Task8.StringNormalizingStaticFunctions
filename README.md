# Task8.StringNormalizingStaticFunctions

May 19, 2024
Deadline: Jun 3 (inclusive)

Task 8

Write static functions operating on and returning Strings:

```java
1 public static String norm(String name)
2 public static String init(String name)
3 public static String tr(String s, String from, String to)
```

where

• the first returns a string which is similar to name, but the first letter is always in upper case and all the remaining characters are in lower case (e.g., "jOhN" → "John");

• the second takes a full name, i.e., first name, optionally a middle name, and the lastname, separated by exactly one space; it returns a string in which first and middle name are replaced by the initials (upper case, with a dot after the letter), and the last name starts with a capital letter with all the remaining letters in lower case (e.g. "john richard doe" → "J. R. Doe"). 

Note: method split from class String splits a string into parts separated by a separator which is passed as an argument and returns them as an array of Strings, e.g., after `String[] a = "abc def ghi".split(" ");` the array a will contain three elements: "abc", "def" and "ghi".

• the third returns a `String` in which all characters from s that are present in from are replaced by the corresponding (on the same position) characters from to. For this to make sense, all characters in from must be different and from and to should be of the same length. For example, if from is "abc" and to "XXY", then all occurrences of ’a’ and ’b’ should be replaced by ’X’ and ’c’ by ’Y’.

For example, the program

```java
public class StringMisc {
    public static String norm(String name) { ... }
    public static String init(String name) { ... }
    public static String tr(String s,
    String from, String to) { ... }

    public static void main (String[] args) {

        System.out.println(norm("caravaggio"));
        System.out.println(norm("VERMEER"));
        System.out.println(init("johann sebastian bach"));
        System.out.println(init("i. babeL"));
        System.out.println(init("jorge LUIS BORGES"));
        System.out.println(init("WOLFGANG a. mozart"));
        System.out.println(tr("Mississippi",
        "abcdefghijklmnopqrstuvwyz",
        "BCDEFGHIJKLMNOPQRSTUVWYZA"));
        System.out.println(tr("abcXYZ","aZcX","||Cx"));
    }
}
```

should print

```
Caravaggio
Vermeer
J. S. Bach
I. Babel
J. L. Borges
W. A. Mozart
MJTTJTTJQQJ
|bCxY|
```

Do not use any classes except those in the package `java.lang`.

## Solution

```java
public class StringMisc {
    public static String norm(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static String init(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String[] parts = name.split(" ");
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
        System.out.println(norm("caravaggio"));
        System.out.println(norm("VERMEER"));

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
```

norm(String name):
* This function takes a string name as input.
* It returns a new string where the first letter is capitalized, and all remaining letters are in lowercase.
* For example, “jOhN” becomes “John”.

init(String name):
* This function takes a full name (first name, optional middle name, and last name) separated by a single space.
* It returns a string where the first and middle names are replaced by their initials (uppercase, with a dot after each letter), and the last name starts with a capital letter followed by lowercase letters.
* For example, “john richard doe” becomes “J. R. Doe”.

tr(String s, String from, String to):
* This function replaces characters in the input string s based on the mappings provided by from and to.
* The characters in from must be different, and from and to should be of the same length.
* For example, if from is “abc” and to is “XXY”, then ‘a’ and ‘b’ should be replaced by ‘X’, and ‘c’ by ‘Y’.
