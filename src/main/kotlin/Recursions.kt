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

// Return all possible letter combinations that the number could represent. Return the answer in any order.
// Backtracking approach
// At each step, pick a letter corresponding to the current digit.
// Recursively move forward until a full combination is formed.
// Use backtracking (deleteCharAt(...)) to remove the last choice and explore new paths.
// - When we've processed all digits, store the current combination

//-  Base Case (Stopping Condition)- When index == digits.length, it means we've formed a complete letter combination.
// - We store it in result and return.
// - Recursive Calls- Each digit maps to a set of letters, and we iterate through them.
// - We recursively call generateCombinations for the next digit.
// - Backtracking (Undoing Choices)- After adding a letter, we remove it (deleteCharAt) before the next iteration.
//- This ensures all combinations are explored without affecting past choices.
fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList() // Edge case: Return empty list for no input

    // Mapping of digits to corresponding letters, similar to a phone keypad
    val phoneMap = mapOf(
        '2' to "abc", '3' to "def", '4' to "ghi",
        '5' to "jkl", '6' to "mno", '7' to "pqrs",
        '8' to "tuv", '9' to "wxyz"
    )

    val result = mutableListOf<String>() // Stores all valid letter combinations
    val currentCombination = StringBuilder() // Temporary storage for building combinations

    // Start backtracking from index 0
    generateCombinations(digits, 0, currentCombination, result, phoneMap)

    return result // Return the final list of letter combinations
}

/**
 * Recursively generates letter combinations using backtracking.
 *
 * @param digits - The input string of digits.
 * @param index - Current position in the digits string.
 * @param currentCombination - Temporary holder for the ongoing letter combination.
 * @param result - Stores all valid combinations found.
 * @param phoneMap - Mapping from digits to corresponding letters.
 */
fun generateCombinations(
    digits: String,
    index: Int,
    currentCombination: StringBuilder,
    result: MutableList<String>,
    phoneMap: Map<Char, String>
) {
    // Base case: When we've processed all digits, store the combination.
    if (index == digits.length) {
        result.add(currentCombination.toString()) // Store the valid combination
        return
    }

    // Get the possible letters for the current digit
    val letters = phoneMap[digits[index]] ?: return

    // Iterate over each letter and explore possibilities
    for (char in letters) {
        currentCombination.append(char) // Choose a letter and add it to the current combination
        generateCombinations(digits, index + 1, currentCombination, result, phoneMap) // Recursive call
        currentCombination.deleteCharAt(currentCombination.length - 1) // Backtrack: Remove last letter to try another
    }
}