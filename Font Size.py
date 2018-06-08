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
