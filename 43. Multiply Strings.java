43. Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
Note:
The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.


def multiply(self, num1, num2):
    n1 = n2 = 0
    for n in num1:
        n1 = n1 * 10 + ord(n) - ord('0')
    for n in num2:
        n2 = n2 * 10 + ord(n) - ord('0')
    res = ''
    mul = n1 * n2
    if not mul:
        return '0'
    while mul > 0:
        res += str(mul % 10)
        mul //= 10
    return res[::-1]

public String multiply(String num1, String num2) {
    int m = num1.length(), n = num2.length();
    int[] posNum = new int[m + n]; // important
    for (int i = m - 1; i >= 0; i--)
        for (int j = n - 1; j >= 0; j--) {
            int multiple = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            int p1 = i + j, p2 = i + j + 1;
            int sum = multiple + posNum[p2];
            posNum[p1] += sum / 10; // important
            posNum[p2] = sum % 10; // important
        }
    StringBuilder sb = new StringBuilder();
    for (int num : posNum)
        if (!(sb.length() == 0 && num == 0))    sb.append(num);
    return sb.length() == 0 ? "0" : sb.toString();
}

     56
 *   22
---------
    112
   112
---------
