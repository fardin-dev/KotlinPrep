package org.example

import kotlin.math.abs


fun inorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    fun traverse(node: TreeNode?) {
        if (node == null) return
        traverse(node.left)
        result.add(node.`val`)
        traverse(node.right)
    }
    traverse(root)
    return result
}

fun preorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    fun traverse(node: TreeNode?) {
        if (node == null) return
        result.add(node.`val`)
        traverse(node.left)
        traverse(node.right)
    }
    traverse(root)
    return result
}

fun postorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    fun traverse(node: TreeNode?) {
        if (node == null) return
        traverse(node.left)
        traverse(node.right)
        result.add(node.`val`)
    }
    traverse(root)
    return result
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
}

fun isSymmetric(root: TreeNode?): Boolean {
    fun isMirror(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        return (left.`val` == right.`val`) &&
                isMirror(left.left, right.right) &&
                isMirror(left.right, right.left)
    }
    return isMirror(root?.left, root?.right)
}

fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) return false
    // Check if current node is a leaf and matches remaining sum
    if (root.left == null && root.right == null && root.`val` == targetSum) return true
    // Recurse with updated target sum
    return hasPathSum(root.left, targetSum - root.`val`) ||
            hasPathSum(root.right, targetSum - root.`val`)
}

fun countNodes(root: TreeNode?): Int {
    if (root == null) return 0
    return 1 + countNodes(root.left) + countNodes(root.right)
}

fun sumOfLeftLeaves(root: TreeNode?): Int {
    if (root == null) return 0
    var sum = 0
    // Check if the left child is a leaf
    if (root.left != null && root.left?.left == null && root.left?.right == null) {
        sum += root.left?.`val` ?: 0
    }
    sum += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right)
    return sum
}

// BFS traversal
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    if (root == null) return result

    val queue = ArrayDeque<TreeNode>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val levelSize = queue.size
        val currentLevel = mutableListOf<Int>()

        repeat(levelSize) {
            val node = queue.removeFirst()
            currentLevel.add(node.`val`)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        result.add(currentLevel)
    }

    return result
}

fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return null

    // Swap left and right subtrees
    val temp = root.left
    root.left = invertTree(root.right)
    root.right = invertTree(temp)

    return root
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    return p.`val` == q.`val` &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right)
}

fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (subRoot == null) return true  // An empty subtree is always valid
    if (root == null) return false   // Non-empty subtree can't be in an empty tree

    return isSameTree(root, subRoot) ||
            isSubtree(root.left, subRoot) ||
            isSubtree(root.right, subRoot)
}

fun minDepth(root: TreeNode?): Int {
    if (root == null) return 0

    // If left is null, go right
    if (root.left == null) return 1 + minDepth(root.right)
    // If right is null, go left
    if (root.right == null) return 1 + minDepth(root.left)

    // If both subtrees exist, take the minimum
    return 1 + minOf(minDepth(root.left), minDepth(root.right))
}

fun isBalanced(root: TreeNode?): Boolean {
    fun checkHeight(node: TreeNode?): Int {
        if (node == null) return 0

        val leftHeight = checkHeight(node.left)
        if (leftHeight == -1) return -1

        val rightHeight = checkHeight(node.right)
        if (rightHeight == -1) return -1

        if (abs(leftHeight - rightHeight) > 1) return -1

        return 1 + maxOf(leftHeight, rightHeight)
    }

    return checkHeight(root) != -1
}

// Given the root of a binary tree, return all root-to-leaf paths.
fun binaryTreePaths(root: TreeNode?): List<String> {
    val paths = mutableListOf<String>()

    fun dfs(node: TreeNode?, path: String) {
        if (node == null) return

        val newPath = if (path.isEmpty()) "${node.`val`}" else "$path->${node.`val`}"

        if (node.left == null && node.right == null) {
            paths.add(newPath)
            return
        }

        dfs(node.left, newPath)
        dfs(node.right, newPath)
    }

    dfs(root, "")
    return paths
}

fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || root == p || root == q) return root

    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)

    return when {
        left != null && right != null -> root
        left != null -> left
        else -> right
    }
}