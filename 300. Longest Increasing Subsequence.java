300. Longest Increasing Subsequence
// https://leetcode.com/problems/longest-increasing-subsequence/
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

Solution 1: DP 
Time: O(n^2)

注意，是返回max不是返回数组最后一个，所以中间要维护这个max变量

public int lengthOfLIS(int[] nums) {
	int[] dp = new int[nums.length];
	Arrays.fill(dp, 1);
	int max = 1;
	for (int i = 1; i < nums.length; i++)
		for (int j = 0; j < i; j++) 
			if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
				dp[i] = dp[j] + 1;
				max = Math.max(max, dp[i]);
			}
	return max;
}

tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6
We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.

Each time we only do one of the two:

(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]
Doing so will maintain the tails invariant. The the final answer is just the size.

def lengthOfLIS(self, nums):
    tails = [0] * len(nums)
    size = 0
    for x in nums:
        i, j = 0, size
        while i != j:
            m = (i + j) / 2
            if tails[m] < x:
                i = m + 1
            else:
                j = m
        tails[i] = x
        size = max(i + 1, size)
    return size

Solution 2: DP with Binary Search
// The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, 
// for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. 
// Having this info, for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. 
// The final answer is the length of the longest subsequence we found so far.
Time: O(nlogn)

public int lengthOfLIS(int[] nums) {
	int[] dp = new int[nums.length];
	int len = 0;
	for (int n : nums) {
		int i = Arrays.binarySearch(dp, 0, len, n);
		if (i < 0)	i = - (i + 1);
		dp[i] = n;
		if (i == len)	len++;
	}
	return len;
}
// Arrays.binarySearch() returns ( - insertion_index - 1) in cases where the element was not found in the array. 