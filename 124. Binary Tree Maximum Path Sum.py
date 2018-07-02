'''
每个node有四种情况分析，node, node + left, node.val + right, node + left + right,
前三种是需要取max返回作为上一个node的left or right，最后一个需要去和前三个的max和结果比较。
每个node必须有四个情况分析是因为题目要求必须取一个
'''

def maxPathSum(root):
    """
    :type root: TreeNode
    :rtype: int
    """
    def getBothSum(node):
        if not node:
            return 0
        left = getBothSum(node.left)
        right = getBothSum(node.right)
        cur = max(node.val + left, node.val + right, node.val)
        res[0] = max(res[0], cur, left+right+ node.val)
        return cur
    res = [root.val]
    getBothSum(root)
    return res[0]