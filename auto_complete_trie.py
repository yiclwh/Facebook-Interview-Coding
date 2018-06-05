class Trie():
    def __init__(self):
        self.isword = False
        self.children = {}
    
    def addword(self, word):
        if not word:
            return
        node = self
        for c in word:
            if c not in node.children:
                n = Trie()
                node.children[c] = n
            node = node.children[c]
        node.isword = True
    
    def searchall(self, prefix):
        if not prefix:
            return []
        node = self
        for c in prefix:
            if c not in node.children:
                return []
            node = node.children[c]
        return self.getAllTrieNodes(node, prefix)

    def getAllTrieNodes(self, node, prefix):
        res = []
        if node.isword:
            res.append(prefix)
        subtries = []
        self.dfs(node, [], subtries)
        subtries = [prefix+x for x in subtries]
        res.extend(subtries)
        return res

    def dfs(self, node, path, res):
        if not node.children:
            if path:
                res.append(''.join(path))
            return
        if path and node.isword:
            res.append(''.join(path))
        for c, n in node.children.items():
            self.dfs(n, path+[c], res)

'''
1. corner case 2.invalid case 3. all possible valid case 4.scale
'''
t = Trie()
t.addword('abc')
t.addword('a')
t.addword('abcdefg')
t.addword('abcasd')
t.addword('adcs')
t.addword('abcdefgsad')
t.addword('dfghjbg')
for l in t.searchall(''):
    print(l)
