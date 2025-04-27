package org.example

fun main(){

}

fun lengthOfLongestSubstring(s: String): Int {
    val charIndexMap = mutableMapOf<Char, Int>() // Tracks the last seen index of each character
    var maxLength = 0
    var left = 0 // Left boundary of the sliding window

    for ((right, char) in s.withIndex()) {
        // If the character is already in the map and within the current window, move left
        if (char in charIndexMap && charIndexMap[char]!! >= left) {
            left = charIndexMap[char]!! + 1
        }
        // Update the last seen index of the character
        charIndexMap[char] = right
        // Update maxLength if current window is larger
        maxLength = maxOf(maxLength, right - left + 1)
    }

    return maxLength
}

fun longestSubstringWithoutRepeatingChars(s: String): String {
    val charIndexMap = mutableMapOf<Char, Int>() // Tracks the last seen index of each character
    var left = 0 // Left boundary of the sliding window
    var maxStart = 0 // Start index of the longest substring
    var maxLength = 0 // Length of the longest substring

    for ((right, char) in s.withIndex()) {
        // If the character is already in the map and within the current window, move left
        if (char in charIndexMap && charIndexMap[char]!! >= left) {
            left = charIndexMap[char]!! + 1
        }
        // Update the last seen index of the character
        charIndexMap[char] = right

        // Update maxStart and maxLength if current window is larger
        if (right - left + 1 > maxLength) {
            maxStart = left
            maxLength = right - left + 1
        }
    }

    return s.substring(maxStart, maxStart + maxLength)
}

fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort() // Sort the array to enable two-pointer technique
    val result = mutableListOf<List<Int>>()

    // Iterate through the array up to the second-to-last element
    // Since we're looking for triplets (nums[i], nums[left], nums[right]),
    // we need at least 2 elements after i to form a valid triplet.
    for (i in 0 until nums.size - 2) {
        // Skip duplicates for the first number
        // i > 0: Ensures we don’t skip the first element (i=0).
        // continue: Skips the rest of the loop body for this i, moving to the next unique nums[i] to prevent duplicate processing.
        if (i > 0 && nums[i] == nums[i - 1]) continue

        var left = i + 1 // Left pointer
        var right = nums.size - 1 // Right pointer

        // Two-pointer approach
        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            // Check if the sum is zero, add to result, and skip duplicates
            when {
                sum == 0 -> {
                    result.add(listOf(nums[i], nums[left], nums[right]))

                    // Skip duplicates on the left side (move `left` forward)
                    while (left < right && nums[left] == nums[left + 1]) left++
                    // Skip duplicates on the right side (move `right` backward)
                    while (left < right && nums[right] == nums[right - 1]) right--

                    // 3. Move both pointers inward to explore new pairs.
                    left++
                    right--
                }
                sum < 0 -> left++ // Need a larger sum
                else -> right--   // Need a smaller sum
            }
        }
    }

    return result
}

fun productExceptSelf(nums: IntArray): IntArray {
    val n = nums.size
    val answer = IntArray(n)

    // Calculate prefix products (left to right)
    // In the prefix product pass, we calculate the product of all elements to the left of nums[i].
    // For the first element (nums[0]), there are no elements to its left.
    answer[0] = 1
    for (i in 1 until n) {
        answer[i] = answer[i - 1] * nums[i - 1]
    }

    // Calculate suffix products (right to left) and combine with prefix
    // In the suffix product pass, we calculate the product of all elements to the right of nums[i].
    // For the last element (nums[n-1]), there are no elements to its right.
    var suffixProduct = 1
    for (i in n - 1 downTo 0) {
        // Combine prefix and suffix products to get the final answer
        answer[i] = answer[i] * suffixProduct
        // Update suffix product
        suffixProduct *= nums[i]
    }

    return answer
}