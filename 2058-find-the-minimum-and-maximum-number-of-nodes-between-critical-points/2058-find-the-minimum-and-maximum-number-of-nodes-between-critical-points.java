class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstIdx = -1;
        int prevIdx = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int idx = 1;

        while (curr.next != null) {
            ListNode next = curr.next;

            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {

                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    minDistance = Math.min(minDistance, idx - prevIdx);
                }
                prevIdx = idx;
            }

            prev = curr;
            curr = next;
            idx++;
        }

        if (firstIdx == -1 || prevIdx == firstIdx) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIdx - firstIdx;
        return new int[]{minDistance, maxDistance};
    }
}