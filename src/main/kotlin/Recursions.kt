package org.example

//fun MutableList<Int>.swap(i: Int, j: Int) {
//    val temp = this[i]
//    this[i] = this[j]
//    this[j] = temp
//}

// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
// We recursively build permutations by swapping elements.
// Backtracking Approach: We recursively build permutations by swapping elements.
//- After processing, we swap elements back to restore the original order.
//- Base Case (index == nums.size)- When all elements have been placed, store the current permutation.
//- Swapping for Permutations- Swap nums[index] with each element ahead.
//- Restore state after recursion to avoid incorrect ordering.
fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>() // Stores all valid permutations
    generatePermutations(nums.toMutableList(), 0, result) // Start backtracking
    return result
}

fun generatePermutations(nums: MutableList<Int>, index: Int, result: MutableList<List<Int>>) {
    if (index == nums.size) {
        result.add(nums.toList()) // Found a valid permutation, add to result
        return
    }

    for (i in index until nums.size) {
        nums.swap(index, i) // Swap to create a new permutation state
        generatePermutations(nums, index + 1, result) // Recur for next position
        nums.swap(index, i) // Swap back to restore original state (backtracking)
    }
}