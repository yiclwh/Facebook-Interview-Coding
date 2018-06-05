# Given two strings S and T, return if they are equal when both \
# are typed into empty text editors. # means a backspace character.

如果不能用stack的话 O(1) space
关键是对string的预处理 从后往前 str[index] == '#' or count > 0 的时候要往前

def backspaceCompare(self, S, T):
    """
    :type S: str
    :type T: str
    :rtype: bool
    """
    def processString(str, index):
        if index < 0 or str[index] != '#':
            return index
        count = 0
        while index >= 0 and (str[index] == '#' or count > 0):
            if str[index] == '#':
                count += 1
            else:
                count -= 1
            index -= 1
        return index
    
    e1 = len(S) - 1
    e2 = len(T) - 1
    while True:
        e1 = processString(S, e1)
        e2 = processString(T, e2)
        if e1 < 0 or e2 < 0 or S[e1] != T[e2]:
            return e1 == -1 and e2 == -1
        e1-=1
        e2-=1

print(backspaceCompare('ab##', 'c#d#'))   #'bxj##tw', 'bxo#j##tw'))