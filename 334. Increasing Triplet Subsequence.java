334. Increasing Triplet Subsequence
// https://leetcode.com/problems/increasing-triplet-subsequence/

Given [1, 2, 3, 4, 5],
return true.
Given [5, 4, 3, 2, 1],
return false.

def increasingTriplet(self, nums):
    one, two = float('inf'), float('inf')
    for num in nums:
        if num <= one:
            one = num
        elif num <= two:
            two = num
        else:
            return True
    return False

229. Majority Element II
def majorityElement(self, nums):
    if not nums:
        return []
    count1, count2, candidate1, candidate2 = 0, 0, 0, 1
    for n in nums:
        if n == candidate1:
            count1 += 1
        elif n == candidate2:
            count2 += 1
        elif count1 == 0:
            candidate1, count1 = n, 1
        elif count2 == 0:
            candidate2, count2 = n, 1
        else:
            count1, count2 = count1 - 1, count2 - 1
    return [n for n in (candidate1, candidate2) if nums.count(n) > len(nums) // 3]

问怎么test code，要求考虑corner cases。


public boolean increasingTriplet(int[] nums) {
    int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
    for(int num : nums){
        if(num <= min)      min = num; // important
        else if(num <= secondMin)    secondMin = num;// important
        else    return true;
    }
    return false;
}

