A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) 
into a land. Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]

二维数组可以用一维表示;这个检查neibgbors的写法很多地方都用到了,要记起来;
首先把island加一,这样后来union就可以直接减一;union的时候看root是否是一样的,否则不用union;union不用DFS逐个改,只改第一个!

还可以优化的办法: union by rank & path compression
union by rank: 可以考虑在已经有的4个方向有root的中间取一个最小值
               或者保留一个union的size,这样每次size小的往size大的去union
path compression 只用加一行
def findRoot(index):
        while roots[index] != index:
            roots[index] = roots[roots[index]]
            index = roots[index]
        return index

def numIslands2(self, m, n, positions):
        """
        :type m: int rows
        :type n: int cols
        :type positions: List[List[int]]
        :rtype: List[int]
        """
        def findRoot(index):
            while roots[index] != index:
                index = roots[index]
            return index
        roots = [-1 for i in range(m*n)]
        result = []
        dirs = [[0,1], [1, 0], [-1, 0], [0, -1]]
        count = 0
        for p in positions:
            pos = p[0]*n + p[1]
            roots[pos] = pos
            count += 1
            for d in dirs:
                nr, nc = d[0] + p[0], d[1] + p[1]
                nindex = nr*n+nc
                if 0 <= nr < m and 0<= nc < n and roots[nindex] > -1:
                    root = findRoot(nindex)
                    if root != roots[pos]:
                        roots[pos] = root
                        pos = root
                        count -= 1
            result.append(count)
        return result
