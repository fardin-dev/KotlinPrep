package org.example

fun main(){

    // test quick sort
    val list = listOf(5, 2, 9, 1, 5, 6)
    println("Original list: $list")

    val sortedList = quickSort(list)
    println("Quick sorted list: $sortedList")

    val list2 = mutableListOf(5, 2, 9, 1, 5, 6)
    insertionSort(list2)
    println("Insertion sorted list: $list2")

    val list3 = listOf(5, 2, 9, 1, 5, 6)
    val sortedList3 = mergeSort(list3)
    println("Merge sorted list: $sortedList3")

}

fun MutableList<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun insertionSort(list: MutableList<Int>) {
    for (i in 1 until list.size) {
        var j = i
        while (j > 0 && list[j] < list[j - 1]) {
            list.swap(j, j - 1)
            j--
        }
    }
}

fun insertionSort2(n: Int, arr: Array<Int>): Unit {
    // Iterate through the array, starting from the second element
    for (i in 1 until n) {
        val key = arr[i] // Element to be inserted in the sorted part
        var j = i - 1

        // Move elements that are greater than key one position ahead
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key // Insert key at the correct position
    }
}

fun mergeSort(list: List<Int>): List<Int> {
    if (list.size <= 1) return list

    val mid = list.size / 2
    val left = mergeSort(list.subList(0, mid))
    val right = mergeSort(list.subList(mid, list.size))

    return merge(left, right)
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    var i = 0
    var j = 0

    while (i < left.size && j < right.size) {
        if (left[i] <= right[j]) {
            result.add(left[i])
            i++
        } else {
            result.add(right[j])
            j++
        }
    }

    result.addAll(left.subList(i, left.size))
    result.addAll(right.subList(j, right.size))

    return result
}

fun quickSort(list: List<Int>): List<Int> {
    // Base case: If the list has 1 or fewer elements, it is already sorted.
    if (list.size <= 1) return list

    // Select the pivot as the middle element of the list.
    val pivot = list[list.size / 2]

    // Partition the list into three parts:
    // - Left: Elements smaller than pivot.
    // - Middle: Elements equal to the pivot.
    // - Right: Elements greater than pivot.
    val left = list.filter { it < pivot }
    val middle = list.filter { it == pivot }
    val right = list.filter { it > pivot }

    // Recursively sort the left and right partitions and combine all parts.
    return quickSort(left) + middle + quickSort(right)
}


fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(arr, low, high)
        quickSort(arr, low, pivotIndex - 1) // Sort left partition
        quickSort(arr, pivotIndex + 1, high) // Sort right partition
    }
}

/**
 * Partition the array using Lomuto's partition scheme.
 * It places the pivot in its correct position and rearranges elements accordingly.
 */
fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high] // Choosing the last element as pivot
    var i = low - 1 // Index for smaller element

    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            arr.swap(i, j) // Swap if current element is smaller than or equal to pivot
        }
    }
    arr.swap(i + 1, high) // Place pivot in correct position
    return i + 1
}

/**
 * Swap utility function for swapping two elements in the array.
 */
fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}




