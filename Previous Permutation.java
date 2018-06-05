//https://www.nayuki.io/page/next-lexicographical-permutation-algorithm

Previous Permutation

2764135 （找最后一个逆序41）
2764135 （找4后面比4小的最后一个数3）
2763145 （交换3,4的位置）
2763541 （把3后面的1,4,5反转）

public void previousPermuation(int[] nums) {
	if (nums.length < 2)    return;
    int firstLarger = nums.length - 2;
    while (firstLarger >= 0) {
        if (nums[firstLarger] > nums[firstLarger + 1])    break;
        firstLarger--;
    }
    if (firstLarger == -1) { 
        reverse(nums, 0, nums.length - 1);
        return;
    }
    int firstSmaller = nums.length - 1;
    while (firstSmaller > firstLarger) {
        if (nums[firstSmaller] < nums[firstLarger])    break;
        firstSmaller--;
    }
    swap(nums, firstLarger, firstSmaller);
    reverse(nums, firstLarger + 1, nums.length - 1);
}
private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left++] = nums[right];
    nums[right--] = temp;
}
private void reverse(int[] nums, int left, int right) {
    while (left < right) 
        swap(nums, left++, right--);
}





31. Next Permutation
// https://leetcode.com/problems/next-permutation/

2763541 （找最后一个正序35）
2763541 （找3后面比3大的最后一个数4）
2764531 （交换3,4的位置）
2764135 （把4后面的5,3,1反转）

def nextPermutation(self, nums):
    """
    :type nums: List[int]
    :rtype: void Do not return anything, modify nums in-place instead.
    """
    l = len(nums)
    left = -1
    for i in range(l-1)[::-1]:
        if nums[i] < nums[i+1]:
            left = i
            break
    if left == -1:
        return nums.sort()
    right = -1
    for i in range(left+1, l)[::-1]:
        if nums[i] > nums[left]:
            right = i
            break
    nums[left], nums[right] = nums[right], nums[left]
    start, end = left + 1, l -1
    while start < end:
        nums[start], nums[end] = nums[end], nums[start]
        start += 1
        end -= 1

public void nextPermutation(int[] nums) {
    if (nums.length < 2)    return;
    int firstSmaller = nums.length - 2;
    while (firstSmaller >= 0) {
        if (nums[firstSmaller] < nums[firstSmaller + 1])    break;
        firstSmaller--;
    }
    if (firstSmaller == -1) {
        reverse(nums, 0, nums.length - 1);
        return;
    }
    int firstLarger = nums.length - 1;
    while (firstLarger > firstSmaller) {
        if (nums[firstLarger] > nums[firstSmaller])    break;
        firstLarger--;
    }
    swap(nums, firstSmaller, firstLarger);
    reverse(nums, firstSmaller + 1, nums.length - 1);
    return;
}
private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}
private void reverse(int[] nums, int start, int end) {
    for (int i = start; i <= (start + end) / 2; i++)
        swap(nums, i, start + end - i);
}



















