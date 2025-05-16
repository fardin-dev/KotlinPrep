package org.example

import kotlin.math.abs

fun reverseArray(arr: Array<Int>): Array<Int> {
    var start = 0
    var end = arr.size - 1
    while (start < end) {
        val temp = arr[start]
        arr[start] = arr[end]
        arr[end] = temp
        start++
        end--
    }
    return arr
}

fun findMax(arr: Array<Int>): Int {
    var max = Int.MIN_VALUE
    for (element in arr) {
        if (element > max) {
            max = element
        }
    }
    return max
}

fun searchElement(arr: Array<Int>, target: Int): Boolean {
    for (element in arr) {
        if (element == target) {
            return true
        }
    }
    return false
}

fun removeDuplicates(arr: Array<Int>): List<Int> {
    return arr.distinct()
}

fun rotateArray(arr: Array<Int>, k: Int): Array<Int> {
    val n = arr.size
    val rotations = k % n
    val rotatedArray = Array(n) { 0 }

    for (i in 0 until n) {
        rotatedArray[(i + rotations) % n] = arr[i]
    }
    return rotatedArray
}

fun runningSum(nums: IntArray): IntArray {
    val result = IntArray(nums.size)
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
        result[i] = sum
    }
    return result
}

// Locating pairs of numbers in an array that add up to a given target value.
fun findPairsWithSum(arr: Array<Int>, target: Int): List<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            if (arr[i] + arr[j] == target) {
                pairs.add(Pair(arr[i], arr[j]))
            }
        }
    }
    return pairs
}

fun mergeSortedArrays(arr1: Array<Int>, arr2: Array<Int>): Array<Int> {
    val mergedArray = Array(arr1.size + arr2.size) { 0 }
    var i = 0
    var j = 0
    var k = 0

    while (i < arr1.size && j < arr2.size) {
        if (arr1[i] <= arr2[j]) {
            mergedArray[k++] = arr1[i++]
        } else {
            mergedArray[k++] = arr2[j++]
        }
    }

    while (i < arr1.size) {
        mergedArray[k++] = arr1[i++]
    }

    while (j < arr2.size) {
        mergedArray[k++] = arr2[j++]
    }

    return mergedArray
}

// Given an array nums and a target target, return the indices of the two numbers that add up to target.
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for ((i, num) in nums.withIndex()) {
        val complement = target - num
        if (map.containsKey(complement)) {
            return intArrayOf(map[complement]!!, i)
        }
        map[num] = i
    }
    return intArrayOf()
}

// Given stock prices on each day (prices), return the maximum profit from one buy and sell.
fun maxProfit2(prices: IntArray): Int {
    var minPrice = Int.MAX_VALUE
    var maxProfit = 0
    for (price in prices) {
        if (price < minPrice) {
            minPrice = price
        } else if (price - minPrice > maxProfit) {
            maxProfit = price - minPrice
        }
    }
    return maxProfit
}

// Given an array nums, move all 0s to the end while maintaining the relative order of non-zero elements.
fun moveZeroes(nums: IntArray) {
    var nonZeroIndex = 0
    for (num in nums) {
        if (num != 0) {
            nums[nonZeroIndex++] = num
        }
    }
    for (i in nonZeroIndex until nums.size) {
        nums[i] = 0
    }
}

// Given an array nums, return true if any value appears at least twice.
fun containsDuplicate(nums: IntArray): Boolean {
    val seen = mutableSetOf<Int>()
    for (num in nums) {
        if (seen.contains(num)) return true
        seen.add(num)
    }
    return false
}

// Rotate the array nums to the right by k steps.
fun rotate(nums: IntArray, k: Int) {
    val steps = k % nums.size
    reverse(nums, 0, nums.size - 1)
    reverse(nums, 0, steps - 1)
    reverse(nums, steps, nums.size - 1)
}
fun reverse(nums: IntArray, start: Int, end: Int) {
    var left = start
    var right = end
    while (left < right) {
        val temp = nums[left]
        nums[left] = nums[right]
        nums[right] = temp
        left++
        right--
    }
}

// Given an array nums, return an array where each element is the product of all elements except nums[i].
fun productExceptSelf2(nums: IntArray): IntArray {
    val result = IntArray(nums.size) { 1 }
    var prefix = 1
    for (i in nums.indices) {
        result[i] = prefix
        prefix *= nums[i]
    }
    var suffix = 1
    for (i in nums.size - 1 downTo 0) {
        result[i] *= suffix
        suffix *= nums[i]
    }
    return result
}

// Given an array where 1 ≤ nums[i] ≤ n, return all duplicates (appears twice).
fun findDuplicates(nums: IntArray): List<Int> {
    val duplicates = mutableListOf<Int>()
    for (num in nums) {
        val index = abs(num) - 1
        if (nums[index] < 0) {
            duplicates.add(abs(num))
        } else {
            nums[index] = -nums[index]
        }
    }
    return duplicates
}

// Given an array nums containing n distinct numbers in [0, n], return the missing number.
fun missingNumber(nums: IntArray): Int {
    var sum = nums.size * (nums.size + 1) / 2
    for (num in nums) {
        sum -= num
    }
    return sum
}

// Given an array of intervals intervals, merge overlapping intervals.
fun mergeOverlap(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.isEmpty()) return emptyArray()
    intervals.sortBy { it[0] }
    val merged = mutableListOf<IntArray>()
    var current = intervals[0]
    for (interval in intervals) {
        if (interval[0] <= current[1]) {
            current[1] = maxOf(current[1], interval[1])
        } else {
            merged.add(current)
            current = interval
        }
    }
    merged.add(current)
    return merged.toTypedArray()
}