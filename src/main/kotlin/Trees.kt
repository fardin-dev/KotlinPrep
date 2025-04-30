package org.example

import java.util.*

class TreeNode(
    var `val`: Int,
    var neighbors: MutableList<TreeNode?> = mutableListOf()
) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

// Inorder Stack-Based Traversal:
// Push all left nodes onto the stack.
// Pop nodes and compare with prev.
// If current.val<=prev, return false`.traversal
// Time complexity is O(n) because we visit each node once.
// Space complexity is O(h) where h is the height of the tree.
// In the worst case (unbalanced tree), h = n, so space complexity is O(n).
// In the best case (balanced tree), h = log n, so space complexity is O(log n).
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
// Time complexity is O(n) because we visit each node once.
// Space complexity is O(h) where h is the height of the tree.
// In the worst case (unbalanced tree), h = n, so space complexity is O(n).
// In the best case (balanced tree), h = log n, so space complexity is O(log n).
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

// Returns the LCA of nodes p and q in the binary tree.
// - O(n) in the worst case (if the tree is unbalanced).
//- O(log n) in the best case (if it's a balanced binary tree).
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    // Base case: if root is null or matches p or q, return root
    // If root is null or matches p/q, it's the LCA.
    if (root == null || root == p || root == q) {
        return root
    }

    // Recursively search left and right subtrees
    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)

    // If both left and right are non-null, root is the LCA
    return if (left != null && right != null) {
        root
    } else {
        left ?: right  // Return the non-null side (if one is null)
    }
}


// Returns number of islands in a 1 (land), 0 (water) matrix grid.

/*Key Takeaways for Easy Memorization:
- Flood Fill Technique:- When a '1' (land) is found, we "flood" it with '0' to mark it as visited.
- This prevents counting the same island multiple times.

- DFS Traversal:- Recursively visits all four directions (up, down, left, right).
- Ensures all connected land is explored before moving to another island.

- Stopping Conditions (Base Case):- Stop if:- Out of grid boundaries (avoids ArrayIndexOutOfBounds errors).
- Cell is '0' (already visited or water).*/
fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty()) return 0 // Edge case: return 0 if the grid is empty

    val rows = grid.size // Number of rows in the grid
    val cols = grid[0].size // Number of columns in the grid
    var islandCount = 0 // Stores the total number of islands found

    // Iterate through every cell in the grid
    for (r in 0 until rows) {
        for (c in 0 until cols) {
            // If the current cell is land ('1'), it's part of a new island
            if (grid[r][c] == '1') {
                islandCount++ // Increment the island count
                dfsTraversal(grid, r, c) // Explore and mark the entire island
            }
        }
    }

    return islandCount // Return the total number of islands found
}

fun dfsTraversal(grid: Array<CharArray>, r: Int, c: Int) {
    val rows = grid.size
    val cols = grid[0].size

    // Base case: stop if out of bounds or if the cell is water ('0')
    if (r !in 0 until rows || c !in 0 until cols || grid[r][c] == '0') return

    // Mark the current land cell as visited by changing '1' to '0'
    grid[r][c] = '0'

    // Recursively visit all 4 adjacent cells (up, down, left, right)
    dfsTraversal(grid, r + 1, c) // Down
    dfsTraversal(grid, r - 1, c) // Up
    dfsTraversal(grid, r, c + 1) // Right
    dfsTraversal(grid, r, c - 1) // Left
}

// Cloning a graph
// Use a HashMap<Node, Node> to map original nodes to their clones, avoiding cycles and redundant work.
// Breadth-First Search (BFS):
// Traverse the graph level by level, cloning nodes and their neighbors.
// Ensure each neighbor is cloned only once by checking the map.
fun cloneGraph(node: TreeNode?): TreeNode? {
    // Edge case: If the input graph is empty, return null
    if (node == null) return null

    // Map to track original nodes and their clones
    // Key: Original Node, Value: Cloned Node
    val visited = HashMap<TreeNode, TreeNode>()

    // Queue for BFS traversal (uses original nodes)
    val queue = ArrayDeque<TreeNode>()

    // Start with the root node
    queue.add(node)

    // Clone the root node and add it to the visited map
    visited[node] = TreeNode(node.`val`)

    // Process nodes level by level
    while (queue.isNotEmpty()) {
        // Get the next node to process
        val current = queue.removeFirst()

        // Iterate over all neighbors of the current node
        for (neighbor in current.neighbors) {
            if (neighbor != null) {
                // If the neighbor hasn't been cloned yet
                if (!visited.containsKey(neighbor)) {
                    // Clone the neighbor and add it to the visited map
                    visited[neighbor] = TreeNode( neighbor.`val`)

                    // Add the original neighbor to the queue for BFS processing
                    queue.add(neighbor)
                }

                // Link the cloned neighbor to the cloned current node
                // visited[current] = clone of current node
                // visited[neighbor] = clone of neighbor node
                visited[current]?.neighbors?.add(visited[neighbor])
            }
        }
    }

    // Return the clone of the root node
    return visited[node]
}

// Diameter of binary tree
// The longest path between any two nodes.
// Uses DFS
// Time complexity is O(n) because we visit each node once.
// Space complexity is O(h) where h is the height of the tree.
// In the worst case (unbalanced tree), h = n, so space complexity is O(n).
// In the best case (balanced tree), h = log n, so space complexity is O(log n).

class TreeDiameterTracker {
    var maxDiameter = 0 // Stores the longest path found between any two nodes
}

fun diameterOfBinaryTree(root: TreeNode?): Int {
    val tracker = TreeDiameterTracker() // Object to track maxDiameter globally
    calculateHeight(root, tracker) // Start DFS traversal from the root
    return tracker.maxDiameter // Return the longest path found
}

fun calculateHeight(node: TreeNode?, tracker: TreeDiameterTracker): Int {
    if (node == null) return 0 // Base case: null nodes have height 0

    // Recursively calculate the height of left and right subtrees
    val leftHeight = calculateHeight(node.left, tracker)
    val rightHeight = calculateHeight(node.right, tracker)

    // The diameter at this node is the sum of left and right heights
    tracker.maxDiameter = maxOf(tracker.maxDiameter, leftHeight + rightHeight)

    // Return the height of this subtree (1 + max child height)
    return 1 + maxOf(leftHeight, rightHeight)
}

// Flatten binary tree to linked list
// Uses preorder traversal
// Time complexity is O(n) because we visit each node once.
// Space complexity is O(h) where h is the height of the tree.
// In the worst case (unbalanced tree), h = n, so space complexity is O(n).
// In the best case (balanced tree), h = log n, so space complexity is O(log n).

class TreeFlattenTracker {
    var prev: TreeNode? = null // Stores the last visited node for linking
}

fun flatten(root: TreeNode?): Unit {
    val tracker = TreeFlattenTracker() // Object to track previous node
    preorderTraversal(root, tracker) // Start pre-order traversal to flatten the tree
}

// Process node first then left subtree then right subtree
fun preorderTraversal(node: TreeNode?, tracker: TreeFlattenTracker) {
    if (node == null) return // Base case: Stop when encountering a null node

    // Store the left and right children before modifying structure
    val left = node.left
    val right = node.right

    // Flatten current node by linking it to the previous processed node
    if (tracker.prev != null) {
        tracker.prev!!.right = node // Set previous node's right to current node
        tracker.prev!!.left = null  // Ensure left pointers are always null
    }

    // Update the previous pointer to the current node
    tracker.prev = node

    // Process left subtree first to maintain pre-order traversal order
    preorderTraversal(left, tracker)
    // Process right subtree next
    preorderTraversal(right, tracker)
}

// Given a list of accounts where each element accounts[i] is a list of strings,
// where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
// Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common
// email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people
// could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
// After merging the accounts, return the accounts in the following format: the first element of each account is the name,
// and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

// Treat emails as connected components and merge accounts sharing common emails.
// Each email is stored along with its account name
// Find the root representative for each email and group emails belonging to the same merged account.
// Emails are sorted lexicographic
class UnionFind(size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 1 }

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x]) // Path compression
        }
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY
            } else {
                parent[rootY] = rootX
                rank[rootX]++
            }
        }
    }
}

fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
    val emailToIndex = mutableMapOf<String, Int>() // Maps email to union-find index
    val emailToName = mutableMapOf<String, String>() // Maps email to account name
    val uf = UnionFind(accounts.size)

    // Step 1: Build union-find relationships for emails
    for ((i, account) in accounts.withIndex()) {
        val name = account[0]
        for (email in account.drop(1)) {
            if (email in emailToIndex) {
                uf.union(i, emailToIndex[email]!!) // Merge accounts with shared emails
            } else {
                emailToIndex[email] = i // Assign email to its first account index
            }
            emailToName[email] = name // Store name corresponding to email
        }
    }

    // Step 2: Group emails by their root parent in union-find
    val indexToEmails = mutableMapOf<Int, MutableList<String>>()
    for (email in emailToIndex.keys) {
        val rootIndex = uf.find(emailToIndex[email]!!)
        indexToEmails.getOrPut(rootIndex) { mutableListOf() }.add(email)
    }

    // Step 3: Format the result
    val result = mutableListOf<List<String>>()
    for ((index, emails) in indexToEmails) {
        val name = accounts[index][0] // Retrieve account name from index
        result.add(listOf(name) + emails.sorted()) // Sort emails and format output
    }

    return result
}