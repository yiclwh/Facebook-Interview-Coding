173. Binary Search Tree Iterator
# in order
class BSTIterator(object):
    def __init__(self, root):
        self.root = root
        self.stack = []
        itr = self.root
        while itr:
            self.stack.append(itr)
            itr = itr.left

    def hasNext(self):
        return len(self.stack) > 0

    def next(self):
        node = self.stack.pop()
        if node.right:
            itr = node.right
            while itr:
                self.stack.append(itr)
                itr = itr.left
        return node.val

public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public int next() {
        TreeNode node = stack.pop();
        pushAll(node.right);
        return node.val;
    }   
    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
******变种******
写个BST的in-order iterator，要写的function有 next() 和 all(), all() return所有剩下的。
public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAll(root);
    }
    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public TreeNode next() {
        TreeNode node = stack.pop();
        pushAll(node.right);
        return node;
    }   
    public List<TreeNode> all() {
        List<TreeNode> res = new ArrayList<>();
        while (hasNext()) 
            res.add(next());
        return res;
    }
}

************Follow up******
改成 preorder 和 postorder。 我全用的stack

public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)   return res; // corner check
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.empty()) {
        res.add(stack.pop().val);
        if (root.right != null)     stack.push(root.right);
        if (root.left != null)      stack.push(root.left);
    }
    return res;
}

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.empty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        res.add(stack.pop().val);
        root = root.right;
    }
    return res;
}

public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode prev = null;
    while (root != null || !stack.empty()) {
        if (root != null) {
            stack.push(root);
            root = root.left;
        } else {
            TreeNode tmp = stack.peek();
            if (tmp.right != null && tmp.right != prev)
                root = tmp.right;
            else {
                stack.pop();
                res.add(tmp.val);
                prev = tmp;
            }
        }
    }
    return res;
}

# 删去一些node，求最后剩下的roots
class Solution {
    public List<Node> removedBST(Node root) {
        List<Node> newRoots = new ArrayList<>();
        preOrder(root, true, newRoots);
        return newRoots;
    }
    private void preOrder(Node node, boolean parentRemoved, List<Node> newRoots) {
        boolean selfRemove;
        if(shouldRemove(node)) selfRemove = true;
        if(parentRemoved && !selfRemove) newRoots.add(node);
        if(node.left != null) {
            preOrder(node.left, selfRemove, selfRemove);
        }
        if(node.right != null) {
            preOrder(node.right, selfRemove, selfRemove);
        }
    }
}