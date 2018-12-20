161. One Edit Distance
// https://leetcode.com/problems/one-edit-distance/

#重要的是边界条件！相等和长度相差大于一都不行！！！
def isOneEditDistance(self, s, t):
    if abs(len(s) - len(t)) > 1 or s == t:
        return False
    for i in range(min(len(s), len(t))):
        if s[i] != t[i]:
            if len(s) == len(t):
                return s[i+1:] == t[i+1:]
            elif len(s) > len(t):
                return s[i+1:] == t[i:]
            else:
                return s[i:] == t[i+1:]
    return True

Tests: 1.replace one char, 2.delete one char in s, 3.delete one char in t
corner cases: "" (len = 0)

public boolean isOneEditDistance(String s, String t) {
    int len = Math.min(s.length(), t.length());
    for (int i = 0; i < len; i++) {
        if (s.charAt(i) != t.charAt(i)) {
            if (s.length() == t.length())   return s.substring(i + 1).equals(t.substring(i + 1)); // replace
            else if (s.length() < t.length())   return s.substring(i).equals(t.substring(i + 1)); // delete t
            else    return s.substring(i + 1).equals(t.substring(i)); // delete s
        }
    }
    return Math.abs(s.length() - t.length()) == 1; // corner case: ""
}

原题 但是不能用substring ====>>>   要一个字符一个字符比较