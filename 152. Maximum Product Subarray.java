152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/


def maxProduct(self, nums):
    if not nums:
        return 0
    pos = neg = res = nums[0]
    for num in nums[1:]:
        # multiplied by a negative makes big number smaller, small number bigger
        # so we redefine the extremums by swapping them
        if num < 0:
            pos, neg = neg, pos
        # max/min product for the current number is either the current number itself
        # or the max/min by the previous number times the current one
        pos = max(num, pos*num)
        neg = min(num, neg*num)
        res = max(pos, res)
    return res

public int maxProduct(int A[], int n) {
    if (n == 0) return 0;
    int maxProduct = A[0], minProduct = A[0], maxRes = A[0];
    for (int i = 1; i < n; i++) {
        if (A[i] >= 0) {
            maxProduct = max(maxProduct * A[i], A[i]);
            minProduct = min(minProduct * A[i], A[i]);
        } else {
            int temp = maxProduct;
            maxProduct = max(minProduct * A[i], A[i]);
            minProduct = min(temp * A[i], A[i]);
        }
        maxRes = max(maxRes, maxProduct);
    }
    return maxRes;
}