


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
