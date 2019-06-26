/*  2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
intersecting node. Note that the intersection is defined based on reference, not value. That is, if the
kth node of the first linked list is the exact same node (by reference) as the jth node of the second
linked list, then they are intersecting. */

/* 
1. be careful that two list might have different length
2. get length of each list, since we can also get to the last node of each list, compare the last node , if they are not the same node, directly return false
3. advance the pointer on the longer list to get to the same position as the start of the shorter list
4. advance both pointer and compare until we found the intersection
*/

LinkedListNode findIntersection(LinkedListNode n1, LinkedListNode n2){
	LinkedListNode p1 = n1, end1 = p1;
	LinkedListNode p2 = n2, end2 = p2;
	int len1 = 0, len2 = 0;

	while (p1 != null){
		len1++;
		p1 = p1.next;
		if (p1 == null) {
			end1 = p1;
		}
	}
	while (p2 != null){
		len2++;
		p2 = p2.next;
		if (p2 == null) {
			end2 = p2;
		}
	}

	if (end1 !=  end2){ //if two list has intersection, their last node must be the same
		return null; 
	}

	LinkedListNode longer = len1 > len2 ? n1 : n2;
	LinkedListNode shorter = len1 > len2 ? n1 : n2;

	for (int i = 0; i < Mat.abs(len1-len2); i++){ //advance the pointer on longer to the position where the length is the same as shorter's length
		longer = longer.next;
	}

	while (longer != shorter){
		longer = longer.next;
		shorter = shorter.next;
	}

	return longer; //return either longer or shorter
}