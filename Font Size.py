'''
最大font fit sentence on screen. 是有两个api给你：一个可以返回某font的高度；一个返回和某font的宽度（不同字母同字号宽度不一样高度一样）。
'''
def isValid(word, i, H, W):
    hasH = H
    hasW = W
    j = 0
    while j < len(word):
        charH = getHeight(i, word[j])
        charW = getHeight(i, word[j])
        if charH <= hasH and charW <= hasW:
            hasW -= charW
            j += 1
        elif charW > hasW and charH <= hasH:
            hasW = W
            charH -= charH
        else:
            return False
    return True


'''
https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
这一题类似题 LC 418, 但是用上面的方法超时了 This is a Greedy approach
Given there are n characters in sentence[] 
Time complexity: O(rows + n)
Space complexity: O(n)
'''
def wordsTyping(self, sentence, rows, cols):
    """
    :type sentence: List[str]
    :type rows: int
    :type cols: int
    :rtype: int
    """
    s = ' '.join(sentence) + ' '
    start = 0
    for r in range(rows):
        start += cols
        if s[start % len(s)] == ' ':
            start += 1
        elif s[start % len(s) - 1] != ' ':
            while start > 0 and s[start % len(s) - 1] != ' ':
                start -= 1
    return start // len(s)
