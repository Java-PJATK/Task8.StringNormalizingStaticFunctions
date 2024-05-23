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

The decision to make the methods `norm`, `init`, and `tr` **static** in the `StringMisc` class serves a specific purpose. Let's explore why:

1. **Static Methods**:
   - Static methods belong to the class itself, rather than an instance of the class.
   - They can be called directly on the class (e.g., `StringMisc.norm("caravaggio")`) without creating an object of the class.
   - In your case, making these methods static allows you to use them without instantiating a `StringMisc` object.

2. **Utility Functions**:
   - The methods you've defined (`norm`, `init`, and `tr`) are utility functions that operate on input data (strings) and return modified strings.
   - Utility functions are often used for common tasks that don't require maintaining state or accessing instance-specific data.
   - By making them static, you emphasize their purpose as general-purpose utilities rather than methods tied to specific instances.

3. **No Instance State**:
   - These methods don't rely on any instance-specific data (fields or properties).
   - They don't need access to instance variables because they work solely with the input parameters (`name`, `s`, `from`, and `to`).
   - Therefore, making them static simplifies their implementation.

4. **Convenience and Clarity**:
   - Using static methods provides a convenient way to organize related functionality within a class.
   - It also makes the code more readable and self-contained. When you see `StringMisc.norm(...)`, you immediately know it's a utility function provided by the `StringMisc` class.

Making these methods static allows you to use them directly, emphasizes their utility nature, and avoids unnecessary instance creation. 

## Explanation

The line `String[] parts = name.split(" ");` in your method splits the input string `name` into an array of substrings, using a single space `" "` as the delimiter. Here’s a detailed explanation of how this works and why it is important:

### How `split(" ")` Works

- **Delimiter**: The `" "` (space) character is used as the delimiter for splitting the string.
- **Resulting Array**: The method returns an array of substrings, where each substring is a portion of the input string that was separated by the delimiter.
- **Empty Substrings**: Consecutive spaces in the input string will result in empty substrings in the resulting array.

### Example

Consider the input string `"John Doe"`:

```java
String name = "John Doe";
String[] parts = name.split(" ");
```

The resulting array `parts` will be:
```java
parts[0] = "John";
parts[1] = "Doe";
```

### Handling Multiple Spaces

For an input string with multiple spaces, such as `"   John   Doe  "`:

```java
String name = "   John   Doe  ";
String[] parts = name.split(" ");
```

The resulting array `parts` will contain empty strings for the extra spaces:
```java
parts[0] = "";
parts[1] = "";
parts[2] = "";
parts[3] = "John";
parts[4] = "";
parts[5] = "";
parts[6] = "Doe";
parts[7] = "";
parts[8] = "";
```

### Importance of Handling Empty Strings

The loop `for (String part : parts)` iterates over each element in the array `parts`. The check `if (!part.isEmpty())` ensures that only non-empty substrings are processed:

```java
for (String part : parts) {
    if (!part.isEmpty()) {
        result.append(part.substring(0, 1).toUpperCase()).append(". ");
    }
}
```

This prevents adding periods for empty parts, ensuring that multiple spaces are handled gracefully.

### Robust Handling of Edge Cases

Using `split(" ")` directly may not be the best approach for all cases, especially when there are multiple spaces. A better approach is to use `split("\\s+")`, which splits the string by one or more whitespace characters (spaces, tabs, etc.):

```java
String[] parts = name.split("\\s+");
```

This handles multiple spaces and other whitespace characters more effectively, ensuring no empty strings in the resulting array. Here's the modified version of the method:

```java
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
```

### Summary

- **`split(" ")`**: Splits the string by single spaces, resulting in empty strings for multiple spaces.
- **`split("\\s+")`**: Splits the string by one or more whitespace characters, avoiding empty strings.
- **Check for `isEmpty()`**: Ensures only non-empty parts are processed, handling multiple spaces effectively.

Using `split("\\s+")` and checking for non-empty parts makes the method more robust and ensures it handles various input cases correctly.