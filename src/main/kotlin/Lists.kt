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

// Merge two sorted linked lists and return it as a sorted list.
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    val dummyHead = ListNode(0) // Placeholder node to simplify list construction
    var current : ListNode? = dummyHead // Pointer for building the merged list
    var p1 = list1
    var p2 = list2

    // Traverse both lists while elements remain
    while (p1 != null && p2 != null) {
        if (p1.value <= p2.value) { // Choose smaller element
            current?.next = p1
            p1 = p1.next
        } else {
            current?.next = p2
            p2 = p2.next
        }
        current = current?.next // Move forward in the merged list
    }

    // Append remaining elements (if any)
    current?.next = p1 ?: p2

    return dummyHead.next // Return the merged sorted list, ignoring dummy head
}

// Reorder list such that
// the nodes are in the order: L0 → L1 → … → Ln - 1 → Ln
// reorder it to: L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
// Uses slow fast pointer technique
fun reorderList(head: ListNode?): Unit {
    if (head == null || head.next == null) return

    // Step 1: Find the middle of the linked list
    var slow = head
    var fast = head
    while (fast?.next != null && fast.next?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }

    // Step 2: Reverse the second half of the list
    var prev: ListNode? = null
    var curr = slow?.next
    slow?.next = null // Disconnect the first half from the second half
    while (curr != null) {
        val nextNode = curr.next
        curr.next = prev
        prev = curr // Move prev forward
        curr = nextNode // Move curr forward
    }

    // Step 3: Merge the two halves in alternating order
    var first = head
    var second = prev
    while (second != null) {
        // Store the next nodes of both halves
        val temp1 = first?.next
        val temp2 = second.next
        // Reorder the nodes
        first?.next = second
        second.next = temp1
        // Move the pointers forward
        first = temp1
        second = temp2
    }
}
