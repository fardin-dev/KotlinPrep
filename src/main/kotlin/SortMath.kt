package org.example

import kotlin.math.abs


// Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
fun divide(dividend: Int, divisor: Int): Int {
    // Edge case: If divisor is zero (not valid for division)
    if (divisor == 0) throw IllegalArgumentException("Divisor cannot be zero")

    // Handle overflow case (-2^31 / -1 = 2^31, which is out of range)
    if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE

    // Determine sign of result (negativity check)
    val isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)

    // Convert both numbers to positive values to simplify subtraction
    var absDividend = abs(dividend.toLong())
    val absDivisor = abs(divisor.toLong())

    var quotient = 0L // Tracks how many times we subtract divisor

    // Repeatedly subtract divisor while the dividend is still larger
    while (absDividend >= absDivisor) {
        var tempDivisor = absDivisor
        var count = 1

        // Reduce dividend using repeated subtraction in chunks
        while (absDividend >= tempDivisor + tempDivisor) {
            tempDivisor += tempDivisor // Increase divisor chunk size
            count += count // Increase corresponding count
        }

        absDividend -= tempDivisor // Subtract largest chunk
        quotient += count // Add respective count to quotient
    }

    // Apply sign to quotient and ensure result is within bounds
    val finalQuotient = if (isNegative) -quotient else quotient
    return finalQuotient.coerceIn(Int.MIN_VALUE.toLong(), Int.MAX_VALUE.toLong()).toInt()
}