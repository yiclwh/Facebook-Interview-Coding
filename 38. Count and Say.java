38. Count and Say
// https://leetcode.com/problems/count-and-say
The count-and-say sequence is the sequence of integers beginning as follows: 1, 11, 21, 1211, 111221, ...
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

def countAndSay(self, n):
    res, count = '1', 1
    while count < n:
        res = self.say(res)
        count += 1
    return res

def say(self, res):
    temp, i = '', 0
    while i < len(res):
        count = 1
        while i+1 < len(res) and res[i] == res[i+1]:
            i += 1
            count += 1
        temp += str(count) + res[i]
        i += 1
    return temp

public String countAndSay(int n) {
    String res = "1";
    for (int i = 0; i < n - 1; i++) // total (n - 1) iterations
        res = generateCountAndSay(res);
    return res;
}
private String generateCountAndSay(String s) {
    StringBuilder sb = new StringBuilder();
    char[] chs = s.toCharArray();
    for (int i = 0; i < chs.length; i++) {
        int count = 1;
        while (i < chs.length - 1 && chs[i] == chs[i + 1]) {
            count++;
            i++;
        }
        sb.append(count).append(chs[i]);
    }
    return sb.toString();
}