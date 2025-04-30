package org.example

// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
// Backtracking with a visited array
// This prevents duplicates and ensures that each number is placed only once per permutation.
// The currentPermutation list holds the elements dynamically without modifying nums.
// When the size reaches nums.size, we store the result.
// - After recursion, we remove the last added element to try other possibilities
// O(n!) due to generating n! permutations.
fun permute(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>() // Stores all valid permutations
    val currentPermutation = mutableListOf<Int>() // Stores the current state of permutation
    val visited = BooleanArray(nums.size) // Tracks used elements

    generatePermutations(nums, visited, currentPermutation, result)
    return result
}

fun generatePermutations(
    nums: IntArray,
    visited: BooleanArray,
    currentPermutation: MutableList<Int>,
    result: MutableList<List<Int>>
) {
    if (currentPermutation.size == nums.size) {
        result.add(currentPermutation.toList()) // Found a valid permutation, add to result
        return
    }

    for (i in nums.indices) {
        if (!visited[i]) { // Ensure each number is used only once per permutation
            visited[i] = true
            currentPermutation.add(nums[i]) // Add number to current permutation
            generatePermutations(nums, visited, currentPermutation, result) // Recursive call
            currentPermutation.removeAt(currentPermutation.size - 1) // Backtrack
            visited[i] = false // Reset visited status for next iteration
        }
    }
}