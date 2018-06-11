class Solution(object):
    # time O(M*N*L) space O(M)
    def addBoldTag(self, s, dict):
        """
        :type s: str
        :type dict: List[str]
        :rtype: str
        """
        if not s or not dict:
            return s
        record = [False] * len(s)
        end = 0
        for i in range(len(s)):
            for word in dict:
                if s.startswith(word, i):
                    end = max(end, i + len(word))
            record[i] = end > i
        res = ''
        prev = False
        for i in range(len(s)):
            if record[i]:
                if not prev:
                    res += '<b>'
                prev = True
            else:
                if prev:
                    res += '</b>'
                prev = False
            res += s[i]
        if prev:
            res += '</b>'
        return res


    def addBoldTag(self, s, dict):
        """
        :type s: str
        :type dict: List[str]
        :rtype: str
        """
        # 直接用纯扫描线做不容imp, 用'Merge Interval'来做
        intervals = [] 
        # d = {i for i in dict}  
        for d in dict:
            idx = s.find(d)      #用find(), not index()!
            while idx != -1:
                intervals.append([idx, idx+len(d)-1])
                idx = s.find(d, idx+1)    #从s[idx+1:]开始往后找
        # return intervals        
        if not intervals:
            return s       
        intervals.sort()   #sort as start time
        last = [intervals[0]]   #stack
        for interv in intervals:
            if interv[0] <= last[-1][1] + 1:   #overlap, 或者相邻!(题目要求的)
                last[-1][1] = max(last[-1][1], interv[1])   #!先取max,再extend!
            else:
                last.append(interv)    
        res = s[:last[0][0]]
        for i in range(len(last)):
            if i > 0:             # !need first add gap between two intervals
                res += s[last[i-1][1]+1:last[i][0]] 
            res += '<b>' + s[last[i][0]:last[i][1]+1] + '</b>'
        res += s[last[i][1]+1:]    #最后要收尾!
        return  res  #, last
        