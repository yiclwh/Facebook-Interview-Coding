Subarray Sum
只需要判断是否存在，返回boolean
hashset

public boolean subArraySum(int[] nums, int k) {
    int sum = 0;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (sum == k || map.containsKey(sum - k))	return true;
        set.add(sum);
    }
    return false;
}


209. Minimum Size Subarray Sum

public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0)   return 0;
    int sum = 0, i = 0, j = 0, min = Integer.MAX_VALUE;
    while (j < nums.length) {
        sum += nums[j++];
        while (sum >= s) {
            min = Math.min(min, j - i);
            sum -= nums[i++];
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}



325. Maximum Size Subarray Sum Equals k

累加然后查找差在不在，要注意最后相同的sum只保存首次出现的那一个
def maxSubArrayLen(nums, k):
    map = {}
    sum = res = 0
    for i in range(len(nums)):
        sum += nums[i]
        if sum == k:
            res = max(res, i+1)
        elif sum - k in map:
            res = max(res, i - map[sum - k])
        if sum not in map:
            map[sum] = i
    return res

这两道题的解法完全不同：这道题由于是求“equal”，所以用“hash”；上一题是求“大于等于”，所以是用two pointers尺取法。
而且由于两道题的要求不同，它们的输入数据也不同：这道题的输入数据可正可负；上一题却只能是非负数。


560. Subarray Sum Equals K

def subarraySum(self, nums, k):
    """
    :type nums: List[int]
    :type k: int
    :rtype: int
    """
    record = {0:1}
    res = sum = 0
    for i in range(len(nums)):
        sum += nums[i]
        if sum - k in record:
            res += record[sum-k]
        if sum in record:
            record[sum] += 1
        else:
            record[sum] = 1
    return res












