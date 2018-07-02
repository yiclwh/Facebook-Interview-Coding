Run Length Encoding
Given an input string, write a function that returns the Run Length Encoded string for the input string.

For example, if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6”.

time: O(n) encode string

def runLength(s):
    res =''
    count = 1
    itr = s[0]
    for i in eange(1, len(s)):
        if s[i] == itr:
            count += 1
        else:
            res.append(itr+str(count))
            count = 1
            itr = s[i]
    res.append(itr+str(count))
    return ''.join(res)

public String runLength(String s){
    if(s == null || s.length() == 0)    return "";
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 0; i < s.length(); i++) {
        while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
            count++;
            i++;
        }  
        sb.append(s.charAt(i)).append(count);
        count = 1;
    }
    return sb.toString();
}
