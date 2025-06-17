package org.example

fun main(){
    // sort a string
    println("anbakjsfgboqahefhia".toCharArray().sorted().joinToString(""))

    // string to int
    println("1234".toInt())
}

/**
 * Problem: Sort characters in a string alphabetically
 * Solution: Convert to char array, sort, and convert back
 */
fun sortString(input: String): String {
    return input.toCharArray().sorted().joinToString("")
}

/**
 * Problem: Sort words in a string alphabetically
 * Solution: Split into words, sort, and rejoin
 */
fun sortWords(input: String): String {
    return input.split(" ").sorted().joinToString(" ")
}

/**
 * Problem: Sort array of strings by their length
 * Solution: Use sortedBy with length property
 */
fun sortByLength(strings: Array<String>): List<String> {
    return strings.sortedBy { it.length }
}

/**
 * Problem: Sort characters by frequency (most frequent first)
 * Solution: Create frequency map and sort by frequency
 */
fun sortByFrequency(input: String): String {
    val frequency = input.groupingBy { it }.eachCount()
    return input.toList()
        .sortedByDescending { frequency[it] }
        .joinToString("")
}

/**
 * Problem: Count the number of vowels in a string
 * Solution: Check each character against vowels
 */
fun countVowels(input: String): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    return input.lowercase().count { it in vowels }
}

/**
 * Problem: Capitalize the first letter of each word in a string
 * Solution: Split string and transform each word
 */
fun capitalizeWords(input: String): String {
    return input.split(" ").joinToString(" ") {
        it.replaceFirstChar { first -> first.uppercase() }
    }
}

/**
 * Problem: Remove all whitespace from a string
 * Solution: Filter out whitespace characters
 */
fun removeWhitespace(input: String): String {
    return input.filterNot { it.isWhitespace() }
}

/**
 * Problem: Count occurrences of a specific character in a string
 * Solution: Use count() function
 */
fun countChar(input: String, char: Char): Int {
    return input.count { it == char }
}

/**
 * Problem: Check if string contains only numeric digits
 * Solution: Use all() with isDigit()
 */
fun isNumeric(input: String): Boolean {
    return input.all { it.isDigit() }
}

/**
 * Problem: Reverse a string without using built-in reverse functions
 * Solution: Use two-pointer approach for O(n) time and O(1) space
 */
fun reverseString(input: String): String {
    // Convert to char array since Strings are immutable in Kotlin/Java
    val chars = input.toCharArray()
    var left = 0
    var right = chars.size - 1

    while (left < right) {
        // Swap characters
        chars[left] = chars[right].also { chars[right] = chars[left] }
        left++
        right--
    }

    return String(chars)
}

/**
 * Problem: Find the first non-repeating character in a string
 * Solution: Use a frequency map and then iterate through the string
 */
fun firstUniqueChar(s: String): Int {
    val frequency = mutableMapOf<Char, Int>()

    // Build frequency map
    s.forEach { char ->
        frequency[char] = frequency.getOrDefault(char, 0) + 1
    }

    // Find first character with frequency 1
    s.forEachIndexed { index, char ->
        if (frequency[char] == 1) {
            return index
        }
    }

    return -1 // No unique character found
}

/**
 * Problem: Check if two strings are anagrams (contain same characters in different orders)
 * Solution: Compare sorted strings or use frequency maps
 */
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    // Solution 1: Sort and compare (O(n log n) time)
    // return s.toCharArray().sorted() == t.toCharArray().sorted()

    // Solution 2: Frequency map (O(n) time)
    val frequency = IntArray(26)

    s.forEach { char ->
        frequency[char - 'a']++
    }

    t.forEach { char ->
        frequency[char - 'a']--
        if (frequency[char - 'a'] < 0) {
            return false
        }
    }

    return true
}

/**
 * Problem: Find length of longest substring without repeating characters
 * Solution: Sliding window approach with hash set
 * Expansion: right moves forward to include new characters
 * Contraction: left moves forward when duplicates are found
 */
fun lengthOfLongestSubstring2(s: String): Int {
    val chars = mutableSetOf<Char>()  // Tracks current unique characters in window
    var maxLength = 0                 // Stores maximum length found
    var left = 0                      // Left boundary of sliding window
    var maxSubstring = ""

    for (right in s.indices) {
        // Remove characters from left until current character is unique
        while (s[right] in chars) {
            chars.remove(s[left])
            left++
        }

        // Add current character to set
        chars.add(s[right])

        // Update max length if current window is larger
        maxLength = maxOf(maxLength, right - left + 1)
        maxSubstring = s.substring(left, right + 1)
    }

    return maxLength
}

/**
 * Problem: Group anagrams together from a list of strings
 * Solution: Use sorted string as key in a map
 */
fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    // Create a hash map to store groups of anagrams
    // Key: Sorted version of the string (or character count array)
    // Value: List of original strings that are anagrams of the key
    val anagramGroups = mutableMapOf<String, MutableList<String>>()

    for (str in strs) {
        // Generate a key by sorting the characters in the string
        val sortedStr = str.toCharArray().sorted().joinToString("")

        // If the key exists, add the string to its group; otherwise, create a new group
        if (anagramGroups.containsKey(sortedStr)) {
            anagramGroups[sortedStr]?.add(str)
        } else {
            anagramGroups[sortedStr] = mutableListOf(str)
        }
    }

    // Convert the map values (groups of anagrams) to a list and return
    return anagramGroups.values.toList()
}

/**
 * Problem: Compress string by replacing consecutive duplicates with count
 * Example: "aabcccccaaa" → "a2b1c5a3"
 * Solution: Iterate with count
 */
fun compressString(s: String): String {
    if (s.isEmpty()) return s

    val result = StringBuilder()
    var currentChar = s[0]
    var count = 1

    for (i in 1 until s.length) {
        if (s[i] == currentChar) {
            count++
        } else {
            result.append(currentChar).append(count)
            currentChar = s[i]
            count = 1
        }
    }

    // Append the last character and count
    result.append(currentChar).append(count)

    // Return original string if compressed isn't smaller
    return if (result.length < s.length) result.toString() else s
}

/**
 * Problem: Check if string has valid parentheses pairs (balanced)
 * Solution: Use stack to track opening brackets
 */
fun isValidParentheses(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val pairs = mapOf(
        ')' to '(',
        '}' to '{',
        ']' to '['
    )

    s.forEach { char ->
        when (char) {
            in pairs.values -> stack.addLast(char)
            in pairs.keys -> {
                if (stack.isEmpty() || stack.removeLast() != pairs[char]) {
                    return false
                }
            }
        }
    }

    return stack.isEmpty()
}

/**
 * Problem: Find the longest common prefix among array of strings
 * Solution: Vertical scanning - compare characters at same index
 */
fun longestCommonPrefix(strs: Array<String>): String {
    // Edge case: If the array is empty, return an empty string
    if (strs.isEmpty()) return ""

    // Start with the first string as the initial prefix
    var prefix = strs[0]

    // Iterate through the rest of the strings
    for (i in 1 until strs.size) {
        // Reduce the prefix until it matches the beginning of strs[i]
        while (!strs[i].startsWith(prefix)) {
            // Remove the last character from prefix
            prefix = prefix.substring(0, prefix.length - 1)

            // If prefix becomes empty, no common prefix exists
            if (prefix.isEmpty()) return ""
        }
    }
    return prefix // Return the longest common prefix found
}
