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

/**
 * Returns the number of ways the input digit string can be decoded into letters.
 * Mapping: "1" -> 'A', ..., "26" -> 'Z'
 * Key Observations:
 * 	•	We decode one or two digits at a time.
 * 	•	A 0 cannot be decoded alone; it must be part of a valid two-digit code like "10" or "20".
 * 	•	For each character at index i, we look back at:
 * 	•	The previous single character s[i]
 * 	•	The previous two characters s[i-1..i]
 *
 * Dynamic Programming:
 * 	•	We track only the last two results (dpPrev, dpPrevPrev) to save space.
 * 	•	dpPrev is the total number of ways to decode up to the previous character.
 * 	•	dpPrevPrev is the total number of ways to decode up to the character before that.
 * 	•	For each step, we calculate the current value based on valid one-digit and two-digit decodings.
 * 	time o(n)
 */
fun numDecodings(s: String): Int {
    // Edge case: if the string is empty or starts with '0', it's invalid
    if (s.isEmpty() || s[0] == '0') return 0

    // dpPrevPrev: ways to decode up to i - 2
    // dpPrev: ways to decode up to i - 1
    var dpPrevPrev = 1
    var dpPrev = 1

    for (i in 1 until s.length) {
        var current = 0

        // Single-digit decode is valid if s[i] != '0'
        if (s[i] != '0') {
            current += dpPrev
        }

        // Two-digit decode is valid if substring s[i-1..i] is between "10" and "26"
        val twoDigit = s.substring(i - 1, i + 1).toInt()
        if (twoDigit in 10..26) {
            current += dpPrevPrev
        }

        // Update dp values for next iteration
        dpPrevPrev = dpPrev
        dpPrev = current
    }

    return dpPrev
}

/**
 * Returns the maximum profit from a single buy and sell operation.
 * If no profit is possible, returns 0.
 * 	•	You want to buy at the lowest price seen so far, and sell at a higher price later.
 * 	•	As you scan through the array:
 * 	•	You update minPrice if a new lower price is found.
 * 	•	You compute the profit for selling today using priceToday - minPrice.
 * 	•	You update maxProfit if the new profit is higher than the current best.
 * 	 time - o(n)
 */
fun maxProfit(prices: IntArray): Int {
    if (prices.isEmpty()) return 0

    var minPrice = prices[0]   // Track the lowest price seen so far (best buy day)
    var maxProfit = 0          // Track the max profit achievable

    // Traverse prices starting from day 1
    for (i in 1 until prices.size) {
        val priceToday = prices[i]

        // If today's price is lower than the current minPrice, update minPrice
        if (priceToday < minPrice) {
            minPrice = priceToday
        } else {
            // Calculate profit if sold today, and update maxProfit if it's better
            val profit = priceToday - minPrice
            maxProfit = maxOf(maxProfit, profit)
        }
    }

    return maxProfit
}

/**
 * Determines if the string s can be segmented into one or more words from the wordDict.
 * 	•	dp[i] means whether s.substring(0, i) can be broken into valid words.
 * 	•	We check all previous positions j where:
 * 	•	dp[j] is true (prefix is valid), and
 * 	•	s.substring(j, i) is a word in wordDict.
 */
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    // Convert wordDict to a HashSet for O(1) lookup
    val wordSet = wordDict.toSet()

    // dp[i] = true if s[0..i-1] can be segmented using wordDict
    val dp = BooleanArray(s.length + 1)
    dp[0] = true // Base case: empty string can be segmented

    // Loop through the string
    for (i in 1..s.length) {
        // Try every possible split point j < i
        for (j in 0 until i) {
            // If s[0..j-1] is segmentable and s[j..i-1] is a word
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true
                break // No need to check further if we found a valid split
            }
        }
    }

    // Final answer: can we segment the entire string?
    return dp[s.length]
}


/**
 * NumMatrix class supports O(1) sumRegion queries after O(m*n) preprocessing.
 * You’re given a 2D grid of integers and asked to efficiently compute the sum of elements in any subrectangle defined by:
 * 	•	Top-left corner: (row1, col1)
 * 	•	Bottom-right corner: (row2, col2)
 *
 * This operation will be called multiple times, so performance matters.
 *
 *  Efficient Approach: 2D Prefix Sum (a.k.a. Integral Image)
 *
 * Step 1: Preprocess with Prefix Sum Matrix
 *
 * Create a prefixSum matrix where:
 * 	•	prefixSum[i][j] stores the sum of all elements in the submatrix from (0,0) to (i-1, j-1).
 *
 * Why (i-1, j-1)? We add an extra row and column at index 0 to simplify boundary conditions.
 *
 * Step 2: Use Inclusion-Exclusion to Compute Region Sums in O(1)
 *
 * Once we have prefixSum, the sum of elements in the region (row1, col1) to (row2, col2) is:
 *   prefixSum[row2+1][col2+1]
 * - prefixSum[row1][col2+1]
 * - prefixSum[row2+1][col1]
 * + prefixSum[row1][col1]
 *
 * This is based on:
 * 	•	Taking the large area up to (row2, col2)
 * 	•	Subtracting the top and left parts
 * 	•	Adding back the overlapping top-left region (which was subtracted twice)
 */
class NumMatrix(matrix: Array<IntArray>) {

    // 2D prefix sum array (1 row/col larger than input for easier math)
    private val prefixSum: Array<IntArray>

    init {
        val rows = matrix.size
        val cols = matrix[0].size

        // Create prefixSum with extra row and column at index 0
        prefixSum = Array(rows + 1) { IntArray(cols + 1) }

        // Build the prefix sum matrix
        for (i in 1..rows) {
            for (j in 1..cols) {
                prefixSum[i][j] =
                    matrix[i - 1][j - 1] +                      // current cell
                            prefixSum[i - 1][j] +                      // top
                            prefixSum[i][j - 1] -                      // left
                            prefixSum[i - 1][j - 1]                    // top-left overlap
            }
        }
    }

    /**
     * Returns the sum of the elements inside the rectangle
     * defined by its upper left (row1, col1) and lower right (row2, col2).
     */
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        // Use the prefix sum formula with 1-based indexing
        return prefixSum[row2 + 1][col2 + 1] -
                prefixSum[row1][col2 + 1] -
                prefixSum[row2 + 1][col1] +
                prefixSum[row1][col1]
    }
}
