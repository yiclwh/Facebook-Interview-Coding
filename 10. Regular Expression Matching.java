10. Regular Expression Matching

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

我说DP，但他偏说喜欢递归。。。幸好时间 多 ，没让写代码，  从 没写过递归版本的。。。

Solution: DP 
[YouTube] // https://www.youtube.com/watch?v=l3hda49XcDE

dp[i][j] = dp[i - 1][j - 1]	, p(j - 1) != '*' && s(i - 1) = p(j - 1)
         = dp[i][j - 2]		, p(j - 1) == '*' && matches empty
         = dp[i - 1][j] 	, p(j - 1) == '*' && s(i - 1) = p(j - 2), 'x' repeats >= 1 times 

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    // p[0.., j - 3, j - 2, j - 1] matches empty iff p[j - 1] is '*' and p[0..j - 3] matches empty
    for (int j = 1; j < n; j += 2) 
        if (p.charAt(j) == '*')     dp[0][j + 1] = dp[0][j - 1];
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) 
            if (p.charAt(j - 1) != '*')
                dp[i][j] = dp[i - 1][j - 1] && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
            else
                dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
    return dp[m][n];
}
private boolean isCharMatch(char s, char p) {
    return p == '.' || s == p;
}


******************************Follow Up: 优化空间*******************************

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    for (int j = 1; j < n; j += 2) 
        if (p.charAt(j) == '*')     dp[j + 1] = dp[j - 1];
    for (int i = 1; i <= m; i++) {
        boolean pre = dp[0];
        dp[0] = false;
        for (int j = 1; j <= n; j++) {
            boolean tmp = dp[j];
            if (p.charAt(j - 1) != '*')
                dp[j] = pre && isCharMatch(s.charAt(i - 1), p.charAt(j - 1));
            else
                dp[j] = dp[j - 2] || dp[j] && isCharMatch(s.charAt(i - 1), p.charAt(j - 2));
            pre = tmp;
        }
    }
    return dp[n];
}




-----------------------

44. Wildcard Matching

We define the state P[i][j] to be whether s[0..i) matches p[0..j). The state equations are as follows:
P[i][j] = P[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '?'), if p[j - 1] != '*';
P[i][j] = P[i][j - 1] || P[i - 1][j], if p[j - 1] == '*'.

// Equation 1). means that if p[j-1] is not *, f(i,j) is determined by if s[0:i-2] matches p[0:j-2] and if (s[i-1]==p[j-1] or p[j-1]=='?').
// Equation 2). means that if p[j-1] is *, f(i,j) is true if either f(i,j-1) is true: s[0:i-1] matches p[0:j-2] and * is not used here; 
// or f(i-1,j) is true: s[0:i-2] matches p[0:j-1] and * is used to match s[i-1].

public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for(int j = 1; j <= n && p.charAt(j-1) == '*'; j++) 
        dp[0][j] = true;
    for (int i = 1; i <= m; i++) 
        for (int j = 1; j <= n; j++) 
            if (p.charAt(j - 1) != '*') 
                dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
            else 
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
    return dp[m][n];
}


class Solution(object):
    def isMatch(self, s, p):
        # The DP table and the string s and p use the same indexes i and j, but
        # table[i][j] means the match status between p[:i] and s[:j], i.e.
        # table[0][0] means the match status of two empty strings, and
        # table[1][1] means the match status of p[0] and s[0]. Therefore, when
        # refering to the i-th and the j-th characters of p and s for updating
        # table[i][j], we use p[i - 1] and s[j - 1].

        # Initialize the table with False. The first row is satisfied.
        table = [[False] * (len(s) + 1) for _ in range(len(p) + 1)]

        # Update the corner case of matching two empty strings.
        table[0][0] = True

        # Update the corner case of when s is an empty string but p is not.
        # Since each '*' can eliminate the charter before it, the table is
        # vertically updated by the one before previous. [test_symbol_0]
        for i in range(2, len(p) + 1):
            table[i][0] = table[i - 2][0] and p[i - 1] == '*'

        for i in range(1, len(p) + 1):
            for j in range(1, len(s) + 1):
                if p[i - 1] != "*":
                    # Update the table by referring the diagonal element.
                    table[i][j] = table[i - 1][j - 1] and \
                                  (p[i - 1] == s[j - 1] or p[i - 1] == '.')
                else:
                    # Eliminations (referring to the vertical element)
                    # Either refer to the one before previous or the previous.
                    # I.e. * eliminate the previous or count the previous.
                    # [test_symbol_1]
                    table[i][j] = table[i - 2][j] or table[i - 1][j]

                    # Propagations (referring to the horizontal element)
                    # If p's previous one is equal to the current s, with
                    # helps of *, the status can be propagated from the left.
                    # [test_symbol_2]
                    if p[i - 2] == s[j - 1] or p[i - 2] == '.':
                        table[i][j] |= table[i][j - 1]

        return table[-1][-1]


class TestSolution(unittest.TestCase):
    def test_none_0(self):
        s = ""
        p = ""
        self.assertTrue(Solution().isMatch(s, p))

    def test_none_1(self):
        s = ""
        p = "a"
        self.assertFalse(Solution().isMatch(s, p))

    def test_no_symbol_equal(self):
        s = "abcd"
        p = "abcd"
        self.assertTrue(Solution().isMatch(s, p))

    def test_no_symbol_not_equal_0(self):
        s = "abcd"
        p = "efgh"
        self.assertFalse(Solution().isMatch(s, p))

    def test_no_symbol_not_equal_1(self):
        s = "ab"
        p = "abb"
        self.assertFalse(Solution().isMatch(s, p))

    def test_symbol_0(self):
        s = ""
        p = "a*"
        self.assertTrue(Solution().isMatch(s, p))

    def test_symbol_1(self):
        s = "a"
        p = "ab*"
        self.assertTrue(Solution().isMatch(s, p))

    def test_symbol_2(self):
        # E.g.
        #   s a b b
        # p 1 0 0 0
        # a 0 1 0 0
        # b 0 0 1 0
        # * 0 1 1 1
        s = "abb"
        p = "ab*"
        self.assertTrue(Solution().isMatch(s, p))


if __name__ == "__main__":
    unittest.main()







