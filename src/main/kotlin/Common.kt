package org.example


import java.util.ArrayDeque
import kotlin.collections.*
import kotlin.io.*
import kotlin.ranges.*
import kotlin.text.*

/*
 * Complete the 'rotLeft' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY a
 *  2. INTEGER d
 */

fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
    val rotations = d % a.size
    return (a.drop(rotations) + a.take(rotations)).toTypedArray()
}

/*
 * Complete the 'insertNodeAtPosition' function below.
 *
 * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
 * The function accepts following parameters:
 *  1. INTEGER_SINGLY_LINKED_LIST llist
 *  2. INTEGER data
 *  3. INTEGER position
 */

class SinglyLinkedListNode(
    var data: Int,
    var next: SinglyLinkedListNode? = null
)


fun insertNodeAtPosition(llist: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode? {
    if (llist == null) return null // If the list is empty, return null

    // Create a new node with the given data
    val newNode = SinglyLinkedListNode(data)

    // If the position is 0, insert at the beginning of the list
    if (position == 0) {
        newNode.next = llist
        return newNode
    }

    // Traverse the list to find the node at the given position
    var current = llist
    var index = 0

    while (current != null && index < position - 1) {
        current = current.next
        index++
    }

    if (current != null) {
        newNode.next = current.next
        current.next = newNode
    }

    return llist
}

fun hasCycle(head: SinglyLinkedListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast?.next != null) {
        slow = slow?.next          // Moves one step at a time
        fast = fast.next?.next      // Moves two steps at a time

        if (slow == fast) {
            return true // Cycle detected
        }
    }

    return false // No cycle found
}

fun isBalanced(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val bracketPairs = mapOf('(' to ')', '{' to '}', '[' to ']')

    for (char in s) {
        if (char in bracketPairs.keys) {
            stack.push(char) // Push opening brackets onto the stack
        } else if (char in bracketPairs.values) {
            if (stack.isEmpty() || bracketPairs[stack.pop()] != char) {
                return false // Mismatch or excess closing bracket
            }
        }
    }
    return stack.isEmpty() // If stack is empty, the brackets are balanced
}

class QueueWithTwoStacks<T> {
    private val inStack = ArrayDeque<T>()
    private val outStack = ArrayDeque<T>()

    fun enqueue(item: T) {
        inStack.push(item)
    }

    fun dequeue(): T? {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.push(inStack.pop())
            }
        }
        return outStack.pop()
    }

    fun peek(): T? {
        if (outStack.isEmpty()) {
            while (inStack.isNotEmpty()) {
                outStack.push(inStack.pop())
            }
        }
        return outStack.peek()
    }

    fun isEmpty(): Boolean = inStack.isEmpty() && outStack.isEmpty()
}

fun icecreamParlor(m: Int, arr: Array<Int>): Array<Int> {
    val priceToIndex = mutableMapOf<Int, Int>()

    for ((index, price) in arr.withIndex()) {
        val complement = m - price
        if (priceToIndex.containsKey(complement)) {
            return arrayOf(priceToIndex[complement]!! + 1, index + 1) // Convert to 1-based index
        }
        priceToIndex[price] = index
    }

    return arrayOf() // No valid pair found
}

fun isColorful(number: Int): Boolean {
    val numStr = number.toString()
    val productSet = hashSetOf<Int>()

    for (i in numStr.indices) {
        var product = 1
        for (j in i until numStr.length) {
            product *= numStr[j] - '0' // Convert char to int
            if (!productSet.add(product)) {
                return false // Duplicate product found, NOT colorful
            }
        }
    }

    return true
}

/**
 * Inserts a new value into the binary search tree and returns the updated root.
 */
class Node(var data: Int, var left: Node? = null, var right: Node? = null)

fun insertIntoBST(root: Node?, data: Int): Node {
    // If the tree is empty, create a new node
    if (root == null) return Node(data)

    // Insert recursively based on the value
    if (data < root.data) {
        root.left = insertIntoBST(root.left, data)
    } else {
        root.right = insertIntoBST(root.right, data)
    }

    return root // Return the root of the updated tree
}

/**
 * Computes the height of a binary tree.
 * The height of a single-node tree is considered 0.
 */
fun getHeight(root: Node?): Int {
    if (root == null) return -1 // Base case: Empty tree has height -1

    val leftHeight = getHeight(root.left)
    val rightHeight = getHeight(root.right)

    return maxOf(leftHeight, rightHeight) + 1 // Height is the max depth of left/right subtree + 1
}

fun quickestWayUp(ladders: Array<Array<Int>>, snakes: Array<Array<Int>>): Int {
    val board = IntArray(101) { it } // Default: Each square leads to itself

    // Set up ladders
    for (ladder in ladders) {
        board[ladder[0]] = ladder[1] // Redirect start to end of ladder
    }

    // Set up snakes
    for (snake in snakes) {
        board[snake[0]] = snake[1] // Redirect mouth to tail
    }

    val queue = ArrayDeque<Pair<Int, Int>>() // (current position, roll count)
    queue.add(1 to 0) // Start from square 1 with 0 rolls
    val visited = BooleanArray(101) // Track visited squares
    visited[1] = true // Mark starting position as visited

    while (queue.isNotEmpty()) {
        val (currentSquare, rolls) = queue.removeFirst()

        if (currentSquare == 100) return rolls // Found the shortest path

        for (diceRoll in 1..6) {
            var nextSquare = currentSquare + diceRoll
            if (nextSquare > 100) continue // Don't go beyond the board

            // Apply ladders/snakes
            nextSquare = board[nextSquare]

            if (!visited[nextSquare]) {
                visited[nextSquare] = true
                queue.add(nextSquare to rolls + 1)
            }
        }
    }

    return -1 // If unreachable
}

fun main() {
    println(isBalanced("({[]})")) // true
    println(isBalanced("{[(])}")) // false
    println(isBalanced("((()))")) // true
}
