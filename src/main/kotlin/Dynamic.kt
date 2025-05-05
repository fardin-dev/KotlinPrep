package org.example

/**
 * Returns the longest palindromic substring in the input string s.
 */
fun longestPalindrome(s: String): String {
    if (s.length < 2) return s // Single char or empty string is a palindrome

    var start = 0   // Start index of the longest palindrome
    var end = 0     // End index (inclusive) of the longest palindrome

    // Helper function to expand around center
    fun expandAroundCenter(left: Int, right: Int): Pair<Int, Int> {
        var l = left
        var r = right
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }
        return Pair(l + 1, r - 1) // return valid range after breaking
    }

    for (i in s.indices) {
        // Odd-length palindrome (center at i)
        val (l1, r1) = expandAroundCenter(i, i)
        // Even-length palindrome (center between i and i+1)
        val (l2, r2) = expandAroundCenter(i, i + 1)

        // Update longest if found
        if (r1 - l1 > end - start) {
            start = l1
            end = r1
        }
        if (r2 - l2 > end - start) {
            start = l2
            end = r2
        }
    }

    return s.substring(start, end + 1)
}

/**
 * Returns the length of the longest valid (well-formed) parentheses substring.
 * Pass 1: Left to Right
 * 	•	Count ( with left, and ) with right.
 * 	•	When left == right, update maxLen.
 * 	•	If right > left, reset both counters (invalid prefix).
 *
 * Pass 2: Right to Left
 * 	•	Do the same but in reverse.
 * 	•	This handles cases like "(()" where unmatched '(' is at the start.
 */
fun longestValidParentheses(s: String): Int {
    var left = 0
    var right = 0
    var maxLen = 0

    // Left to right pass
    for (c in s) {
        if (c == '(') left++
        else right++

        if (left == right) {
            maxLen = maxOf(maxLen, 2 * right)
        } else if (right > left) {
            left = 0
            right = 0
        }
    }

    // Reset counters for right-to-left pass
    left = 0
    right = 0

    for (i in s.lastIndex downTo 0) {
        if (s[i] == '(') left++
        else right++

        if (left == right) {
            maxLen = maxOf(maxLen, 2 * left)
        } else if (left > right) {
            left = 0
            right = 0
        }
    }

    return maxLen
}