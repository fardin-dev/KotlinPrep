package org.example

fun main(){

}

// kadane's algorithm
fun maxSum(nums: IntArray): Int {
    var maxSum = Int.MIN_VALUE
    var currentSum = 0

    for (num in nums) {
        currentSum = maxOf(num, currentSum + num)
        maxSum = maxOf(maxSum, currentSum)
    }

    return maxSum
}

// Returns the length of the longest substring without repeating characters
// Uses sliding window technique
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

// Returns the longest substring without repeating characters.
// Uses sliding window technique
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

// Returns the triplets whose sum is zero.
// Uses two-pointer technique
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

// Returns the product of all elements except self.
// Uses prefix and suffix products
fun productExceptSelf(nums: IntArray): IntArray {
    val n = nums.size
    val answer = IntArray(n)

    // Calculate the prefix product and store it in the answer array
    var prefixProduct = 1 // Initialize prefix product to 1 for the first element since there's no previous element
    for (i in 0 until n) {
        answer[i] = prefixProduct
        prefixProduct *= nums[i]
    }

    // Calculate the suffix product and multiply it with the prefix product
    // to get the product of all elements except self
    var suffixProduct = 1
    for (i in n - 1 downTo 0) {
        answer[i] *= suffixProduct
        suffixProduct *= nums[i]
    }

    return answer
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var i = m - 1 // Pointer for the last element in nums1's valid portion
    var j = n - 1 // Pointer for the last element in nums2
    var k = m + n - 1 // Pointer for the last position in nums1

    while (j >= 0) { // Process nums2 elements
        if (i >= 0 && nums1[i] > nums2[j]) {
            nums1[k] = nums1[i]
            i--
        } else {
            nums1[k] = nums2[j]
            j--
        }
        k--
    }
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
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

// Returns the total number of continuous subarrays whose sum equals k.
// Uses prefix sum and hashmap strategy
fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    var sum = 0 // running sum
    val prefixSumMap = hashMapOf<Int, Int>()
    prefixSumMap[0] = 1 //  to handle cases where a subarray starts at index 0.

    // Iterate through the array, calculating prefix sums
    for (num in nums) {
        sum += num

        // Check if there exists a prefix sum that, when subtracted, equals k
        count += prefixSumMap.getOrDefault(sum - k, 0)

        // Store the current prefix sum in the map
        prefixSumMap[sum] = prefixSumMap.getOrDefault(sum, 0) + 1
    }

    return count
}

// Returns the length of the longest substring with at most k distinct characters.
// Uses sliding window technique
fun longestSubstringKDistinct(s: String, k: Int): Int {
    if (k == 0) return 0 // If k is 0, we can't have any distinct characters, so return 0.

    var maxLength = 0 // Stores the maximum length of a valid substring.
    var left = 0 // Left pointer for the sliding window.
    val charFrequency = mutableMapOf<Char, Int>() // HashMap to track character frequencies in the current window.

    // Expand the window by moving the right pointer.
    for (right in s.indices) {
        // Add current character to the frequency map.
        charFrequency[s[right]] = charFrequency.getOrDefault(s[right], 0) + 1

        // If the window contains more than k distinct characters, shrink it from the left.
        while (charFrequency.size > k) {
            // Reduce the frequency of the leftmost character.
            charFrequency[s[left]] = charFrequency[s[left]]!! - 1

            // If frequency becomes 0, remove the character from the map.
            if (charFrequency[s[left]] == 0) {
                charFrequency.remove(s[left])
            }

            // Move the left pointer forward to reduce distinct characters in the window.
            left++
        }

        // Update the maximum length of a valid substring.
        maxLength = maxOf(maxLength, right - left + 1)
    }

    return maxLength // Return the longest valid substring length found.
}
