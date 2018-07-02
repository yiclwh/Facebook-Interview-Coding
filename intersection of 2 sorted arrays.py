def findIntersection(a, b):
    i = j = 0
    res = []
    while i < len(a) and j < len(b):
        if a[i] < b[j]:
            i += 1
        elif a[i] < b[j]:
            j += 1
        else:
            res.append(a[i])
            i += 1
            j += 1
    return res

# print(findIntersection([x for x in range(100)], [x for x in range(90, 120)]))

def binarySearch(arr, target):
    start, end = 0, len(arr) - 1
    while start <= end:
        mid = start + (end - start)//2
        if arr[mid] == target:
            return mid
        elif arr[mid] > target:
            end = mid -1
        else:
            start = mid + 1
    return start 

print(binarySearch([1,2,3,8,10], 90))