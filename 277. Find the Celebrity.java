277. Find the Celebrity

def findCelebrity(self, n):
    cand = 0
    for i in range(n)[1:]:
        if knows(cand, i):
            cand = i
    for i in range(n):
        if i != cand and (not knows(i, cand) or knows(cand, i)):
            return -1
    return cand

public int findCelebrity(int n) {
    int candidate = 0;
    for (int i = 1; i < n; i++) 
        if (knows(candidate, i))    candidate = i;
    for (int i = 0; i < n; i++) {
        if (i < candidate && (!knows(i, candidate) || knows(candidate, i)))  return -1;
        if (i > candidate && !knows(i, candidate))  return -1;
    }
    return candidate;
}

// The first loop is to find the candidate as the author explains. In detail, suppose the candidate after the first for loop is person k, 
// it means 0 to k-1 cannot be the celebrity, because they know a previous or current candidate. Also, 
// since k knows no one between k+1 and n-1, k+1 to n-1 can not be the celebrity either. Therefore, 
// k is the only possible celebrity, if there exists one.


