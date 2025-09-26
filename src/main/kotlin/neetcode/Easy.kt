package org.example.neetcode

/* How it works:
- We use a HashSet to keep track of numbers we've already seen.
- As we iterate through the array, we check if the current number is already in the set.
- If it is, we return true—that means we’ve found a duplicate.
- Otherwise, we add it to the set and keep going.
- If we reach the end without finding a duplicate, we return false.
*/

fun containsDuplicate2(nums: IntArray): Boolean {
    val seen = HashSet<Int>()
    for (num in nums) {
        if (num in seen) return true
        seen.add(num)
    }
    return false
}

// Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
// An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

/* How it works:
- First, it checks if the strings are of equal length. If not, they can't be anagrams.
- Then it converts both strings to character arrays, sorts them, and compares the results.
- If the sorted arrays match, the strings are anagrams.*/

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    return s.toCharArray().sorted() == t.toCharArray().sorted()
}

/* Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
Return the answer with the smaller index first. */

/* How It Works
- We iterate through nums once, tracking each value’s index in a hash map.
- For each element value at currentIndex, we compute its complement = target - value.
- If the complement is already in the map, we’ve found our pair.
- We return [prevIndex, currentIndex], ensuring the smaller index comes first.
- Time complexity: O(n)
- Space complexity: O(n) */

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        val complement = target - num
        // Have we seen the complement before?
        if (map.containsKey(complement)) {
            val prevIndex = map[complement]!!
            // Return in increasing order of indices
            return intArrayOf(prevIndex, index)
        }
        // Record this value’s index for future complements
        map[num] = index
    }
    return intArrayOf()
}

