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


// Approach: Backtracking + HashSet for Uniqueness
//- Instead of generating duplicate permutations, we use a set (usedIndices) to prevent reusing numbers at the same recursion level.
//- This ensures that duplicates in nums are only chosen once per recursive call, guaranteeing unique permutations.
//- Recursive exploration builds permutations step-by-step, and backtracking removes elements to explore alternative paths.
fun permuteUnique(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>() // Stores all unique permutations
    val currentPermutation = mutableListOf<Int>() // Tracks ongoing permutation
    val visited = BooleanArray(nums.size) // Keeps track of used elements

    nums.sort() // Sorting ensures duplicate numbers appear consecutively, simplifying handling

    generateUniquePermutations(nums, visited, currentPermutation, result)
    return result // Return the final list of unique permutations
}

/**
 * Recursively generates unique permutations using backtracking.
 *
 * @param nums - The input array of numbers.
 * @param visited - Boolean array tracking whether a number is used.
 * @param currentPermutation - Temporary holder for the ongoing permutation.
 * @param result - Stores all unique permutations found.
 */
fun generateUniquePermutations(
    nums: IntArray,
    visited: BooleanArray,
    currentPermutation: MutableList<Int>,
    result: MutableList<List<Int>>
) {
    // Base case: When the permutation reaches the full length, store it
    if (currentPermutation.size == nums.size) {
        result.add(ArrayList(currentPermutation)) // Store a copy to prevent mutation issues
        return
    }

    for (i in nums.indices) {
        // Skip used numbers from the same level OR already visited elements
        if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue

        // Mark the element as used and add to the current permutation
        visited[i] = true
        currentPermutation.add(nums[i])

        generateUniquePermutations(nums, visited, currentPermutation, result) // Recursive call

        // Backtrack: Remove last element and reset visited state to explore other possibilities
        currentPermutation.removeAt(currentPermutation.size - 1)
        visited[i] = false
    }
}

/**
 * Generates all possible subsets (power set) of a given unique integer array.
 *
 * @param nums - The input array of unique integers.
 * @return List of all subsets, each represented as a list of integers.
 */

// Approach: Backtracking
//- We generate subsets by recursively adding elements to a temporary list and exploring all possible combinations.
//- At each step, we have the choice to include or exclude a given element.
//- The base case is when we process all elements, at which point we store the subset.
//- This ensures all unique subsets are created efficiently.

// Key Explanations
//- Base Case (Adding Subset to result)- Each time generateSubsets is called, the current subset is stored.
//- Ensures all unique subsets are added without duplicates.
//
//- Recursive Choice (Include or Exclude Element)- Add element → Create a new subset variation.
//- Recursively process next element → Explore new possibilities.
//- Remove last element (Backtracking) → Undo choice and continue.
//
//- Backtracking for Exploration- Ensures all possible subsets are explored without modifying previous choices.

fun subsets(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>() // Stores all unique subsets
    val currentSubset = mutableListOf<Int>() // Temporary storage for building subsets

    generateSubsets(nums, 0, currentSubset, result) // Start recursive backtracking
    return result // Return the list of all subsets
}

/**
 * Recursively generates subsets using backtracking.
 *
 * @param nums - The input array.
 * @param index - The current position in the array.
 * @param currentSubset - Temporary list to build subsets.
 * @param result - Stores all valid subsets.
 */
fun generateSubsets(
    nums: IntArray,
    index: Int,
    currentSubset: MutableList<Int>,
    result: MutableList<List<Int>>
) {
    // Store the current subset in the result (copy ensures immutability)
    result.add(ArrayList(currentSubset))

    // Iterate over remaining elements to form new subsets
    for (i in index until nums.size) {
        currentSubset.add(nums[i]) // Include element in subset
        // By using i + 1, we make sure that each recursive call only adds numbers appearing later in the array.
        generateSubsets(nums, i + 1, currentSubset, result) // Recursive call
        currentSubset.removeAt(currentSubset.size - 1) // Backtrack: Remove last element
    }
}