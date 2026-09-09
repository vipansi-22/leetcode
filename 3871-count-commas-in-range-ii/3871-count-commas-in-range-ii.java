class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000;

        while (start <= n) {
            long count = n - start + 1;
            totalCommas += count;
            start *= 1000;
        }

        return totalCommas;
    }
}