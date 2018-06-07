def subarraySum(nums, k):
    """
    :type nums: List[int]
    :type k: int
    :rtype: int
    """
    sum = res = 0
    record = {0:1}
    for num in nums:
        sum += num
        if sum - k in record:
            res += record[sum - k]
        if sum in record:
            record[sum] += 1
        else:
            record[sum] = 1
    return res
    
 # time out solution
  def subarraySum(self, nums, k):
    """
    :type nums: List[int]
    :type k: int
    :rtype: int
    """
    record = [0 for i in range(len(nums) + 1)]
    res = 0
    for i in range(1, len(nums) + 1):
        record[i] = record[i-1] + nums[i-1]
        for j in range(i):
            if record[i] - record[j] == k:
                res += 1
    return res
