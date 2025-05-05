package org.example

import kotlin.math.abs


// Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
fun divide(dividend: Int, divisor: Int): Int {
    // Edge case: If divisor is zero (not valid for division)
    if (divisor == 0) throw IllegalArgumentException("Divisor cannot be zero")

    // Handle overflow case (-2^31 / -1 = 2^31, which is out of range)
    if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE

    // Determine sign of result (negativity check)
    val isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)

    // Convert both numbers to positive values to simplify subtraction
    var absDividend = abs(dividend.toLong())
    val absDivisor = abs(divisor.toLong())

    var quotient = 0L // Tracks how many times we subtract divisor

    // Repeatedly subtract divisor while the dividend is still larger
    while (absDividend >= absDivisor) {
        var tempDivisor = absDivisor
        var count = 1

        // Reduce dividend using repeated subtraction in chunks
        while (absDividend >= tempDivisor + tempDivisor) {
            tempDivisor += tempDivisor // Increase divisor chunk size
            count += count // Increase corresponding count
        }

        absDividend -= tempDivisor // Subtract largest chunk
        quotient += count // Add respective count to quotient
    }

    // Apply sign to quotient and ensure result is within bounds
    val finalQuotient = if (isNegative) -quotient else quotient
    return finalQuotient.coerceIn(Int.MIN_VALUE.toLong(), Int.MAX_VALUE.toLong()).toInt()
}

/**
 * Function to search for a target in a rotated sorted array.
 * Returns the index of the target if found, otherwise returns -1.
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.lastIndex

    // Binary search loop - runs in O(log n)
    while (left <= right) {
        // Get the middle index
        val mid = left + (right - left) / 2

        // If the middle element is the target, return the index
        if (nums[mid] == target) return mid

        // Determine which half is sorted
        if (nums[left] <= nums[mid]) {
            // Left half is sorted
            if (target >= nums[left] && target < nums[mid]) {
                // Target is in the left half
                right = mid - 1
            } else {
                // Target is in the right half
                left = mid + 1
            }
        } else {
            // Right half is sorted
            if (target > nums[mid] && target <= nums[right]) {
                // Target is in the right half
                left = mid + 1
            } else {
                // Target is in the left half
                right = mid - 1
            }
        }
    }

    // Target not found
    return -1
}

/**
 * Calculates x raised to the power n (i.e., x^n).
 * Handles both positive and negative exponents efficiently using binary exponentiation.
 * 	•	Binary Exponentiation breaks the exponentiation down by using the binary representation of n.
 * 	•	At each step:
 * 	•	If the current bit in n is 1, multiply the result by the base.
 * 	•	Square the base and shift n one bit to the right (i.e., divide by 2).
 * 	•	Handles negative exponents by inverting the base.
 */
fun myPow(x: Double, n: Int): Double {
    // Convert n to a Long to safely handle cases like n = Int.MIN_VALUE
    var base = x
    var exponent = n.toLong()

    // If the exponent is negative, invert the base and make the exponent positive
    if (exponent < 0) {
        base = 1 / base
        exponent = -exponent
    }

    var result = 1.0

    // Loop until exponent becomes 0
    while (exponent > 0) {
        // If the current bit is set (i.e., exponent is odd), multiply the result with base
        if (exponent % 2 == 1L) {
            result *= base
        }

        // Square the base for the next bit
        base *= base

        // Move to the next bit (divide exponent by 2)
        exponent /= 2
    }

    return result
}

/**
 * Finds a peak element in the array and returns its index.
 * A peak is defined as an element strictly greater than its neighbors.
 * The function runs in O(log n) time using binary search.
 */
fun findPeakElement(nums: IntArray): Int {
    var left = 0
    var right = nums.lastIndex

    // Binary search to find a peak element
    while (left < right) {
        // Find the middle index
        val mid = left + (right - left) / 2

        // Compare middle element with its next neighbor
        if (nums[mid] > nums[mid + 1]) {
            // Peak must be on the left half (including mid)
            right = mid
        } else {
            // Peak must be on the right half (excluding mid)
            left = mid + 1
        }
    }

    // At the end of the loop, left == right, which is a peak element
    return left
}

/**
 * Returns the intersection of two arrays, including duplicates.
 * Each element in the result appears as many times as it shows in both arrays.
 */
fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    val freqMap = mutableMapOf<Int, Int>() // Map to store frequency of elements in nums1
    val result = mutableListOf<Int>()      // List to store intersection elements

    // Count occurrences of each number in nums1
    for (num in nums1) {
        freqMap[num] = freqMap.getOrDefault(num, 0) + 1
    }

    // Check each number in nums2
    for (num in nums2) {
        val count = freqMap.getOrDefault(num, 0)
        if (count > 0) {
            result.add(num)            // Add to result if found in nums1
            freqMap[num] = count - 1   // Decrement the count in the map
        }
    }

    // Convert result list to IntArray
    return result.toIntArray()
}
