# recursive + memorization
def findMaxValue(values):
    return helper(values, 0, len(values)-1, {}) > 0

def helper(values, start, end, cache):
    if (start, end) in cache:
        return cache[(start, end)]
    first = values[start] - helper(values, start + 1, end, cache)
    last = values[end] - helper(values, start, end - 1, cache)
    result = max(first, last)
    cache[(start, end)] = result
    return result

# DP
def findMaxValue(values):
    if not values:
        return False
    length = len(values)
    dp = [[0 for i in range(length)] for j in range(length)]
    for i in range(length):
        dp[i][i] = values[i]
    for i in range(1, length):
        for start in range(length - i):
            end = i + start
            # we subtract our opponent's optimal score from the pot we take
            dp[start][end] = max(values[start] - dp[start+1][end], values[end] - dp[start][end-1])
    return dp[0, length-1] > 0
