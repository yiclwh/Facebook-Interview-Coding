class Solution(object):

    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if not nums or len(nums) < 3:
            return []
        nums.sort()
        res = []
        for i, num in enumerate(nums):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            j, k = i+1, len(nums) - 1
            while j < k:
                sum = nums[i] + nums[j] + nums[k]
                if sum == 0:
                    res.append([nums[i], nums[j], nums[k]])
                    while j + 1 <k and nums[j] == nums[j + 1]:
                        j += 1
                    j += 1
                    while k - 1 > j and nums[k] == nums[k - 1]:
                        k -= 1
                    k -= 1
                elif sum < 0:
                    j += 1
                else: # sum > 0
                    k -= 1
        return res
    
        # sol 2:
        # runtime: 1227ms
        # if len(nums) < 3:
        #     return []
        # nums.sort() # [-4, -1, -1, 0, 1, 2]
        # res = set() # use set() takes less space than list.
        # for i, v in enumerate(nums[:-2]): # n=[-4,-1,-1,0]
        #     if i >= 1 and v == nums[i-1]: # i=1, v=-1, n[1-1]=n[0]=-4 //;i=2, v=-1, n[2-1]=n[1]=-1
        #         continue
        #     d = {} 
        #     for x in nums[i+1:]: # x in n[1+1]=n[2:]=[-1,0]
        #         if x not in d: # 
        #             d[-v-x] = 1 # d[-1-(-1)]=d[0]=1; 
        #         else:
        #             res.add((v, -v-x, x)) # res=(-1, 0, -1)
        # return map(list, res)
    
        # sol 3:
        # runtime: 580ms
        counter = {}
        for num in nums:
            if num not in counter:
                counter[num] = 0
            counter[num] += 1

        if 0 in counter and counter[0] > 2:
            rst = [[0, 0, 0]]
        else:
            rst = []

        uniques = counter.keys()  

        pos = sorted(p for p in uniques if p > 0) 
        neg = sorted(n for n in uniques if n < 0)

        
        for p in pos:
            for n in neg:
                inverse = -(p + n)  
                if inverse in counter:
                    if inverse == p and counter[p] > 1:
                        rst.append([n, p, p])
                    elif inverse == n and counter[n] > 1:
                        rst.append([n, n, p])
                    elif n<inverse< p or inverse == 0:
                        rst.append([n, inverse, p])
        return rst

# 4sum
class Solution(object):
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        if not nums or len(nums) < 4:
            return []
        nums.sort()
        res = []
        for i in range(len(nums)):
            if i > 0 and nums[i] == nums[i-1]:
                continue
            for j in range(i+1, len(nums)):
                if j > i+1 and nums[j] == nums[j-1]:
                    continue
                k, l = j + 1, len(nums)-1
                while k< l:
                    s = nums[i] + nums[j] + nums[k] + nums[l]
                    if s == target:
                        res.append([nums[i], nums[j], nums[k], nums[l]])
                        while k < l and nums[k] == nums[k+1]:
                            k += 1
                        k += 1
                        while l > k and nums[l] == nums[l-1]:
                            l-=1
                        l-= 1
                    elif s < target:
                        k += 1
                    else: # > target
                         l -= 1
        return res
    
    # 一个最优解
    def fourSum(self, nums, target):
        def findNsum(nums, target, N, result, results):
            if len(nums) < N or N < 2 or target < nums[0]*N or target > nums[-1]*N:  # early termination
                return
            if N == 2: # two pointers solve sorted 2-sum problem
                l,r = 0,len(nums)-1
                while l < r:
                    s = nums[l] + nums[r]
                    if s == target:
                        results.append(result + [nums[l], nums[r]])
                        l += 1
                        while l < r and nums[l] == nums[l-1]:
                            l += 1
                    elif s < target:
                        l += 1
                    else:
                        r -= 1
            else: # recursively reduce N
                for i in range(len(nums)-N+1):
                    if i == 0 or (i > 0 and nums[i-1] != nums[i]):
                        findNsum(nums[i+1:], target-nums[i], N-1, result+[nums[i]], results)
    results = []
    findNsum(sorted(nums), target, 4, [], results)
    return results
                    
