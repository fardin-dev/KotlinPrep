package org.example

class ListNode(var value: Int) {
    var next: ListNode? = null
}

// Description: Adds two numbers represented by linked lists.
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var p1 = l1 // Pointer to traverse first list
    var p2 = l2 // Pointer to traverse second list
    var carry = 0 // Variable to track carry-over from addition
    val dummyHead = ListNode(0) // Dummy node to simplify result construction
    var current = dummyHead // Pointer for building the resulting linked list

    while (p1 != null || p2 != null || carry != 0) {
        val sum = (p1?.value ?: 0) + (p2?.value ?: 0) + carry
        carry = sum / 10 // Compute carry for next addition
        current.next = ListNode(sum % 10) // Store last digit of sum as new node
        current = current.next!! // Move to next node

        // Move pointers forward, if nodes exist
        p1 = p1?.next
        p2 = p2?.next
    }

    return dummyHead.next // Return head of the resulting linked list
}
