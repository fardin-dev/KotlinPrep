package org.example

import java.util.*

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// Inorder Stack-Based Traversal:
// Push all left nodes onto the stack.
// Pop nodes and compare with prev.
// If current.val<=prev, return false`.traversal
fun isValidBST(root: TreeNode?): Boolean {
    var prev: Int? = null
    val stack = ArrayDeque<TreeNode>()
    var current = root

    while (current != null || stack.isNotEmpty()) {
        // Traverse to the leftmost node
        while (current != null) {
            stack.push(current)
            current = current.left
        }
        current = stack.pop()
        // Check if current value > previous value
        prev?.let { if (current.`val` <= it) return false }
        prev = current.`val`
        current = current.right
    }
    return true
}

class MaxPathSumTracker {
    var maxSum = Int.MIN_VALUE
}


// Calculates the maximum path sum in a binary tree.
// The path may start and end at any node in the tree.

// Using DFS since a path must be contiguous, we need to traverse deep into each branch before making decisions.
// BFS is great for shortest path problems, but here, we need to evaluate full paths before making a decision.
// DFS fully explores each subtree before moving up
fun maxPathSum(root: TreeNode?): Int {
    val tracker = MaxPathSumTracker() // Create an object to track maxSum
    dfsTraversal(root, tracker) // Call DFS separately
    return tracker.maxSum
}

fun dfsTraversal(node: TreeNode?, tracker: MaxPathSumTracker): Int {
    if (node == null) return 0 // Base case: null nodes contribute 0

    // Compute max path sum for left and right subtrees, ignoring negatives
    val leftMax = maxOf(dfsTraversal(node.left, tracker), 0)
    val rightMax = maxOf(dfsTraversal(node.right, tracker), 0)

    // Calculate max path sum considering current node as root of a valid path
    val currentPathSum = node.`val` + leftMax + rightMax

    // Update global max path sum
    tracker.maxSum = maxOf(tracker.maxSum, currentPathSum)

    // Return max sum when continuing to parent node (only one side can be used)
    return node.`val` + maxOf(leftMax, rightMax)
}