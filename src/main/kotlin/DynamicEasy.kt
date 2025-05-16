package org.example

fun fib(n: Int): Int {
    if (n <= 1) return n
    val dp = IntArray(n + 1)
    dp[0] = 0
    dp[1] = 1
    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    return dp[n]
}

// You are climbing a staircase with n steps. You can take either 1 or 2 steps at a time. Return the number of distinct ways to reach the top.
fun climbStairs(n: Int): Int {
    if (n <= 2) return n
    var a = 1
    var b = 2
    for (i in 3..n) {
        val temp = a + b
        a = b
        b = temp
    }
    return b
}

// Given an array nums representing money in each house, return the maximum amount you can rob without alerting the police (no two adjacent houses can be robbed).
fun rob(nums: IntArray): Int {
    var prev = 0
    var curr = 0
    for (num in nums) {
        val temp = maxOf(prev + num, curr)
        prev = curr
        curr = temp
    }
    return curr
}

// Given an array coins and an amount, return the fewest number of coins needed to make up that amount (or -1 if impossible).
fun coinChange(coins: IntArray, amount: Int): Int {
    val dp = IntArray(amount + 1) { amount + 1 }
    dp[0] = 0

    for (i in 1..amount) {
        for (coin in coins) {
            if (coin <= i) {
                dp[i] = minOf(dp[i], dp[i - coin] + 1)
            }
        }
    }

    return if (dp[amount] > amount) -1 else dp[amount]
}

// Given an integer array nums, return the length of the longest strictly increasing subsequence.
fun lengthOfLIS(nums: IntArray): Int {
    val dp = IntArray(nums.size) { 1 }
    var maxLen = 1

    for (i in 1 until nums.size) {
        for (j in 0 until i) {
            if (nums[i] > nums[j]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
        maxLen = maxOf(maxLen, dp[i])
    }

    return maxLen
}

// Given weights wt, values val, and a knapsack capacity W, return the maximum value that can be carried.
fun knapsack(W: Int, wt: IntArray, `val`: IntArray): Int {
    val n = wt.size
    val dp = Array(n + 1) { IntArray(W + 1) }

    for (i in 1..n) {
        for (w in 1..W) {
            if (wt[i - 1] <= w) {
                dp[i][w] = maxOf(`val`[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w])
            } else {
                dp[i][w] = dp[i - 1][w]
            }
        }
    }

    return dp[n][W]
}
