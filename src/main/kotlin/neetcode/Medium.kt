package org.example.neetcode

import java.util.PriorityQueue

/* Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different. */

/**
 * Strategy:
 * - Normalize each string `s` to a canonical key by sorting its characters.
 * - Use that key in a map: key -> list of strings that share the same sorted form.
 * - Finally, return the map's values (each is a group of anagrams).
 */
fun groupAnagrams(strs: List<String>): List<List<String>> {
    // Map from normalized key (sorted characters) to the bucket of original strings
    val buckets = mutableMapOf<String, MutableList<String>>()

    for (s in strs) {
        // Sorting characters gives a stable canonical representation for anagrams
        // Example: "eat" -> "aet", "tea" -> "aet", so both end up in the same bucket.
        val key = s.toCharArray().sorted().joinToString("")

        // Append string to its corresponding bucket; create bucket if it doesn't exist
        buckets.getOrPut(key) { mutableListOf() }.add(s)
    }

    // Return the grouped anagrams (order of groups and elements is unspecified)
    return buckets.values.toList()
}

/* Given an integer array nums and an integer k, return the k most frequent elements within the array.
The test cases are generated such that the answer is always unique.
You may return the output in any order. */

/* Strategy:
- Traverse the array once and build a frequency map: element -> frequency.
- Use a min-heap (priority queue)
- The heap stores pairs (element, frequency).
- The heap is ordered by frequency (smallest at the top).
- Iterate over the frequency map:
- Push each entry into the heap.
- If the heap size exceeds k, pop the smallest frequency element.
(This ensures the heap always contains the k most frequent elements seen so far.)
- Extract results
- After processing all entries, the heap contains exactly k elements with the highest frequencies.
- Collect their keys into the result list.*/

fun topKFrequentHeap(nums: IntArray, k: Int): List<Int> {
    // val freqMap = nums.groupBy { it }.mapValues { it.value.size } // freq map strategy 1
    val freqMap = nums.toList().groupingBy { it }.eachCount() // freq map strategy 2

    // Min-heap ordered by frequency
    val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    for ((num, freq) in freqMap) {
        minHeap.add(num to freq) // Pair<Int, Int>
        if (minHeap.size > k) minHeap.poll()
    }

    return minHeap.map { it.first  }
}




