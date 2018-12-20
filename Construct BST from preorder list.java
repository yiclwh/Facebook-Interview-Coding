Construct BST from preorder list

// 这个方法是“Construct BST from given preorder traversal”的O(n)解法，使用 MIN-MAX 思想，此题还有O(n^2)解法。
// 参见 http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/

class TreeNode(object):
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

def bstFromPreorder(nums):
    def construct(minVal, maxVal):
        if index[0] >= len(nums):
            return None
        val = nums[index[0]]
        if val < minVal or val > maxVal:
            return None
        node = TreeNode(val)
        index[0] += 1
        node.left = construct(minVal, val)
        node.right = construct(val, maxVal)
        return node
    index = [0]
    return construct(float('-inf'), float('inf'))
bstFromPreorder([10,5,1,7,40,50])

public int idx = 0;
private TreeNode constructBST(int[] pre) {
    return constructBSTfromPreorder(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
}
private TreeNode constructBSTfromPreorder(int[] pre, int min, int max) {
    if (idx >= pre.length)  return null;
    if (pre[idx] <= min || pre[idx] >= max)     return null;
    TreeNode root = new TreeNode(pre[idx++]);
    root.left = constructBSTfromPreorder(pre, min, root.val);
    root.right = constructBSTfromPreorder(pre, root.val, max);
    return root;
}

// For example, if the given traversal is {10, 5, 1, 7, 40, 50}, 
// then the output should be root of following tree.
//      10
//    /   \
//   5     40
//  /  \      \
// 1    7      50

331. Verify Preorder Serialization of a Binary Tree
The key here is, when you see two consecutive "#" characters on stack, pop both of them and replace the topmost element on the stack with "#". For example,
preorder = 1,2,3,#,#,#,#
Pass 1: stack = [1]
Pass 2: stack = [1,2]
Pass 3: stack = [1,2,3]
Pass 4: stack = [1,2,3,#]
Pass 5: stack = [1,2,3,#,#] -> two #s on top so pop them and replace top with #. -> stack = [1,2,#]
Pass 6: stack = [1,2,#,#] -> two #s on top so pop them and replace top with #. -> stack = [1,#]
Pass 7: stack = [1,#,#] -> two #s on top so pop them and replace top with #. -> stack = [#]
If there is only one # on stack at the end of the string then return True else return False.

def isValidSerialization(self, preorder):
    preorder, stack = preorder.split(","), []
    for node in preorder:
        while stack and node == stack[-1] == "#":
            stack.pop()
            if not stack: return False
            stack.pop()
        stack.append(node)
    return stack == ["#"]


Kinda simulate the traversal, keeping a stack of nodes (just their values) of which we're still in the left subtree. If the next number is smaller than the last stack value, then we're still in the left subtree of all stack nodes, so just push the new one onto the stack. But before that, pop all smaller ancestor values, as we must now be in their right subtrees (or even further, in the right subtree of an ancestor). Also, use the popped values as a lower bound, since being in their right subtree means we must never come across a smaller number anymore.

255. Verify Preorder Sequence in Binary Search Tree
def verifyPreorder(self, preorder):
    stack = []
    low = float('-inf')
    for p in preorder:
        if p < low:
            return False
        while stack and p > stack[-1]:
            low = stack.pop()
        stack.append(p)
    return True