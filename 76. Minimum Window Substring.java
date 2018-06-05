76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

//from collections import Counter
def minWindow(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        def valid(record):
            for k, v in record.items():
                if v > 0:
                    return False
            return True
        if not t or not s:
            return ''
        need = Counter(t)
        res = ''
        minLength = float('inf')
        j = 0
        for i, c in enumerate(s):
            if c not in need:
                continue
            while j < len(s) and not valid(need):
                if s[j] in need:
                    need[s[j]] -= 1
                j += 1
            if valid(need) and j - i < minLength:
                res = s[i:j]
                minLength = j - i
            need[c] += 1
        return res

public String minWindow(String s, String t) {
    int[] count = new int[128];
    for (char c : t.toCharArray())
        count[c]++;
    String res = "";
    int start = 0, end = 0, len = t.length(), min = s.length();
    while (end < s.length()) {
        if (count[s.charAt(end++)]-- > 0)   len--; // valid
        while (len == 0) {
            if (end - start <= min) { // update min & res
                min = end - start;
                res = s.substring(start, end);
            }
            if (count[s.charAt(start++)]++ == 0)    len++; // make invalid
        }
    }
    return res;
}

扣76,   样的是T给的是set,  会有重复,  样瞬秒， 开始问我时间复杂度，我说O(n)他 开始 顿扯，我说T的size 并 影响，最后 他同意
最后要我写test case, 后来当T 是空的时候会有问题， 我 上改 bug就结束让我问问题 
