package org.example

import java.util.*

fun main(){
    maxHeap()
}

fun array() {
    // Creating an array of integers - O(1) for initialization
    val numbers = arrayOf(1, 2, 3, 4, 5)

    // Accessing elements by index - O(1)
    val firstNumber = numbers.first()  // O(1)
    val secondNumber = numbers[1]     // O(1)
    val lastNumber = numbers.last()   // O(1)

    // Modifying an element at a specific index - O(1)
    numbers[3] = 10

    // Length of the array - O(1)
    val length = numbers.size

    // Checking if the array is empty - O(1)
    val isEmpty = numbers.isEmpty()

    // Iterating over the elements - O(n)
    for (number in numbers) {
        println(number)
    }

    // Finding the index of a specific element - O(n)
    val index = numbers.indexOf(4)

    // Checking if an element exists in the array - O(n)
    val containsElement = numbers.contains(3)

    // Adding an element to the array - O(n) (creates new array)
    val newArray = numbers.plus(6)

    // Removing an element from the array - O(n) (creates new array)
    val removedArray = numbers.dropLast(1)

    // Printing the array - O(n)
    println(numbers.contentToString())
}

fun linkedList() {
    // Create a LinkedList - O(1)
    val linkedList = LinkedList<Int>()

    // Add elements to the LinkedList - O(1) per operation
    linkedList.add(10)  // addLast - O(1)
    linkedList.add(20)
    linkedList.add(30)

    // Get the size of the LinkedList - O(1)
    val size = linkedList.size
    println(size) // Output: 3

    // Accessing elements by index - O(n)
    val firstElement = linkedList[0]  // O(1) for first/last, O(n) for others
    println("$firstElement") // Output: 10

    // Modify an element at a specific index - O(n)
    linkedList[2] = 35
    println("$linkedList") // [10, 20, 35]

    // Remove an element at a specific index - O(n)
    val removedElement = linkedList.removeAt(1)
    println("$removedElement") // Output: 20

    // Add an element at the beginning - O(1)
    linkedList.addFirst(5)
    println("$linkedList") // Output: [5, 10, 35]

    // Add an element at the end - O(1)
    linkedList.addLast(40)
    println("$linkedList") // Output: [5, 10, 35, 40]

    // Check if contains a specific element - O(n)
    val containsElement = linkedList.contains(35)
    println("$containsElement") // Output: true

    // Iterate over the elements - O(n)
    for (element in linkedList) {
        println(element)
    } //Output: 5, 10, 35, 40
}

fun arrayList() {
    // Create an ArrayList - O(1)
    val arrayList = ArrayList<Int>()

    // Add elements to the ArrayList - amortized O(1)
    arrayList.add(10)  // add to end - O(1) amortized
    arrayList.add(20)
    arrayList.add(30)

    // Get the size - O(1)
    val size = arrayList.size
    println(size) // Output: 3

    // Accessing elements - O(1)
    val firstElement = arrayList[0]
    println(firstElement)

    // Modifying an element - O(1)
    arrayList[1] = 25
    println(arrayList)

    // Remove an element at index - O(n)
    val removedElement = arrayList.removeAt(1)
    println(removedElement) // Output: 25

    // Add an element at beginning - O(n)
    arrayList.add(0, 5)
    println(arrayList) // Output: [5, 10, 30]

    // Add an element at end - O(1) amortized
    arrayList.add(40)

    // Check if contains element - O(n)
    val containsElement = arrayList.contains(30)
    println(containsElement) // Output: true

    // Iterate over elements - O(n)
    for (element in arrayList) {
        println(element)
    } // Output: 5, 10, 30, 40
}

fun stack() {
    // Note: ArrayDeque is not thread-safe
    // For thread-safe stack, use ConcurrentLinkedDeque
    val stack = ArrayDeque<Int>()

    // Push elements - O(1)
    stack.push(10)  // addFirst - O(1)
    stack.push(20)
    stack.push(30)

    // Accessing top element - O(1)
    val topElement = stack.peek()  // peekFirst - O(1)
    println(topElement) // Output: 30

    // Popping elements - O(1)
    val poppedElement = stack.pop()  // removeFirst - O(1)
    println(poppedElement) // Output: 30

    // Check if empty - O(1)
    val isEmpty = stack.isEmpty()
    println(isEmpty) // Output: false

    // Print remaining elements - O(n)
    println(stack) // Output: [20, 10]
}

fun queue() {
    // Note: ArrayDeque is not thread-safe
    // For thread-safe queue, use ConcurrentLinkedQueue
    val queue = ArrayDeque<Int>()

    // Enqueue operations - O(1)
    queue.addLast(10)  // O(1)
    queue.addLast(20)
    queue.addLast(30)

    // Accessing front element - O(1)
    val frontElement = queue.first()
    println(frontElement) // Output: 10

    // Dequeue operations - O(1)
    val dequeuedElement = queue.removeFirst()
    println(dequeuedElement) // Output: 10

    // Check if empty - O(1)
    val isEmpty = queue.isEmpty()
    println(isEmpty) // Output: false

    // Print remaining elements - O(n)
    println(queue) // Output: [20, 30]
}

fun hashMap() {
    // Note: HashMap is not thread-safe
    // For thread-safe version, use ConcurrentHashMap

    // Creating a HashMap - O(1) initialization
    val fruitMap = hashMapOf(
        "apple" to 5,   // O(1) per put
        "banana" to 10,
        "orange" to 3
    )

    // Accessing elements - O(1) average case
    println(fruitMap["apple"]) // Output: 5
    println(fruitMap["banana"]) // Output: 10
    println(fruitMap["orange"]) // Output: 3

    // Modifying value - O(1) average case
    fruitMap["banana"] = 15
    println(fruitMap["banana"]) // Output: 15

    // Iterating over elements - O(n)
    for ((fruit, quantity) in fruitMap) {
        println("$fruit: $quantity")
    }
    // Output order is undefined in HashMap
}

fun hashSet() {
    // Note: HashSet is not thread-safe
    // For thread-safe version, use Collections.synchronizedSet()
    val hashSet = HashSet<Int>()

    // Add elements - O(1) average case
    hashSet.add(10)
    hashSet.add(20)
    hashSet.add(30)

    // Check contains - O(1) average case
    val containsElement = hashSet.contains(20)
    println(containsElement) // Output: true

    // Remove element - O(1) average case
    val removedElement = hashSet.remove(10)
    println(removedElement)

    // Iterate over elements - O(n)
    for (element in hashSet) {
        println(element)
    }
    // Output order is undefined in HashSet
}

fun maxHeap() {
    // Note: PriorityQueue is not thread-safe
    // Create max-heap with reverse order - O(1) initialization
    val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

    // Adding elements - O(log n) per insertion
    maxHeap.add(10)
    maxHeap.add(20)
    maxHeap.add(30)
    maxHeap.add(5)
    maxHeap.add(15)

    // Accessing max element - O(1)
    val maxElement = maxHeap.peek()
    println(maxElement) // Output: 30

    // Removing max element - O(log n)
    val removedElement = maxHeap.poll()
    println(removedElement) // Output: 30

    // Print remaining elements - O(n)
    println(maxHeap) // Output: [20, 15, 10, 5]

    // Check if empty - O(1)
    val isEmpty = maxHeap.isEmpty()
    println(isEmpty) // Output: false

    // Iteration order is undefined - O(n)
    for (element in maxHeap) {
        println(element)
    }
}

fun minHeap() {
    // Create min-heap - O(1) initialization
    val minHeap = PriorityQueue<Int>()

    // Adding elements - O(log n) per insertion
    minHeap.add(10)
    minHeap.add(20)
    minHeap.add(30)
    minHeap.add(5)
    minHeap.add(15)

    // Accessing min element - O(1)
    val minElement = minHeap.peek()
    println(minElement) // Output: 5

    // Removing min element - O(log n)
    val removedElement = minHeap.poll()
    println(removedElement) // Output: 5

    // Print remaining elements - O(n)
    println(minHeap) // Output: [10, 15, 20, 30]

    // Check if empty - O(1)
    val isEmpty = minHeap.isEmpty()
    println(isEmpty) // Output: false

    // Iteration order is undefined - O(n)
    for (element in minHeap) {
        println(element)
    }

}

// Graph implementation in Kotlin
class Graph {
    private val adjacencyList: MutableMap<Int, MutableList<Int>> = mutableMapOf()

    fun addVertex(vertex: Int) {
        adjacencyList.getOrPut(vertex) { mutableListOf() }
    }

    fun addEdge(vertex1: Int, vertex2: Int) {
        adjacencyList.getOrPut(vertex1) { mutableListOf() }.add(vertex2)
        adjacencyList.getOrPut(vertex2) { mutableListOf() }.add(vertex1) // For undirected graph
    }

    fun getNeighbors(vertex: Int): List<Int>? {
        return adjacencyList[vertex]
    }

    fun printGraph() {
        for ((vertex, neighbors) in adjacencyList) {
            println("$vertex -> $neighbors")
        }
    }

    // BFS - use for shortest path
    // Breadth-First Search (BFS) starting from a given vertex
    fun bfs(startVertex: Int) {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>() // Optimized queue for BFS traversal

        visited.add(startVertex)
        queue.add(startVertex)

        val result = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst() // Dequeue the first element
            result.add(currentVertex)

            // Traverse neighbors and enqueue unvisited ones
            adjacencyList[currentVertex]?.forEach { neighbor ->
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }

        println("BFS Traversal: ${result.joinToString(" ")}")
    }


    // DFS - use for connected components
    fun dfs(startVertex: Int): List<Int> {
        val result = mutableListOf<Int>() // Stores traversal order
        val visited = mutableSetOf<Int>() // Tracks visited nodes to prevent cycles
        dfsRecursive(startVertex, visited, result)
        return result // Returns the full DFS traversal sequence
    }

    private fun dfsRecursive(vertex: Int, visited: MutableSet<Int>, result: MutableList<Int>) {
        // Mark the current vertex as visited
        visited.add(vertex)
        result.add(vertex) // Add vertex to result list for traversal order

        // Iterate over unvisited neighbors and apply recursive DFS
        adjacencyList[vertex]?.forEach { neighbor ->
            if (neighbor !in visited) { // Avoid revisiting nodes
                dfsRecursive(neighbor, visited, result)
            }
        }
    }

    fun dfsIterative(startVertex: Int) {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>() // Using stack for DFS traversal

        stack.add(startVertex)

        val result = mutableListOf<Int>()

        while (stack.isNotEmpty()) {
            val currentVertex = stack.removeLast() // Pop from stack (LIFO)

            if (currentVertex !in visited) {
                visited.add(currentVertex)
                result.add(currentVertex)

                // Push unvisited neighbors onto the stack
                adjacencyList[currentVertex]?.reversed()?.forEach { neighbor ->
                    if (neighbor !in visited) {
                        stack.add(neighbor)
                    }
                }
            }
        }

        println("DFS (Iterative) Traversal: ${result.joinToString(" ")}")
    }


}

