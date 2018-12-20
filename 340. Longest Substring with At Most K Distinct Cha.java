340. Longest Substring with At Most K Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given s = “eceba” and k = 2,
return "ece".
same: 159. Longest Substring with At Most Two Distinct Characters

def lengthOfLongestSubstringKDistinct(self, s, k):
    record = collections.defaultdict(int)
    start = end = res = 0
    while end < len(s):
        record[s[end]] += 1
        while len(record) > k:
            record[s[start]] -= 1
            if record[s[start]] == 0:
                record.pop(s[start])
            start += 1
        res = max(res, end -start + 1)
        end += 1
    return res

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int start = 0, end = 0, len = 0, max = 0;
    int[] count = new int[128];
    while (end < s.length()) {
        if (count[s.charAt(end++)]++ == 0)  len++;
        while (len > k) 
            if (count[s.charAt(start++)]-- == 1)    len--;
        max = Math.max(max, end - start);
    }
    return max;
}


********变种*******
返回string

public String longestSubstringKDistinct(String s, int k) {
	String res = "";
    int start = 0, end = 0, len = 0, max = 0;
    int[] count = new int[128];
    while (end < s.length()) {
        if (count[s.charAt(end++)]++ == 0)  len++;
        while (len > k) 
            if (count[s.charAt(start++)]-- == 1)    len--;
        if (end - start > max) {
        	res = s.substring(start, end);
            max = end - start;
        }
    }
    return res;
}
