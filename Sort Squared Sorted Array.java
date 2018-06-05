360. Sort Transformed Array
// https://leetcode.com/problems/sort-transformed-array/
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
Result: [3, 9, 15, 33]
nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
Result: [-23, -5, 1, 7]

思路：a > 0 时res从右往左填充 f(x) 值大的那个，a < 0 时从左往右填充 f(x) 值小的那个。
use two pointers i, j and do a merge-sort like process. depending on sign of a, you may want to start from the beginning or end of the transformed array. 
For a==0 case, it does not matter what b‘s sign is.

def sortTransformedArray(self, nums, a, b, c):
    nums = [x*x*a + x*b + c for x in nums]
    ret = [0] * len(nums)
    p1, p2 = 0, len(nums) - 1
    i, d = (p1, 1) if a < 0 else (p2, -1)
    while p1 <= p2:
        if nums[p1] * -d > nums[p2] * -d:
            ret[i] = nums[p1]
            p1 += 1
        else:
            ret[i] = nums[p2]
            p2 -=1
        i += d
    return ret

Test:
a >= 0, a < 0

public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int[] res = new int[nums.length];
    int low = 0, high = nums.length - 1;
    if (a >= 0) {
        int idx = high;
        while (idx >= 0) {
            if (getValue(nums[low], a, b, c) >= getValue(nums[high], a, b, c))    
                res[idx--] = getValue(nums[low++], a, b, c);
            else    res[idx--] = getValue(nums[high--], a, b, c);
    } else if (a < 0){
        int idx = low;
        while (idx < res.length) 
            if (getValue(nums[low], a, b, c) <= getValue(nums[high], a, b, c))    
                res[idx++] = getValue(nums[low++], a, b, c);
            else    res[idx++] = getValue(nums[high--], a, b, c);
    }
    return res;
}
private int getValue(int x, int a, int b, int c) {
    return a * x * x + b * x + c;
}


简单版：对sorted array的数字平方后排序

public int[] sortSquaredSortedArray(int[] nums) {
	int left = 0, right = nums.length - 1, idx = right;
	int[] res = new int[nums.length];
	while (idx >= 0) {
		if (nums[left] * nums[left] < nums[right] * nums[right])
			res[idx--] = nums[right] * nums[right--];
		else 	res[idx--] = nums[left] * nums[left++];
	}
	return res;
}