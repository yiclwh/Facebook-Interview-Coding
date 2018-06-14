Shortest Knight Move

就是在象棋棋盘上给你两个点A，B，问 个Knight( 哥说，就是骑 那个哈)最少要 步从A跳到B。 珠从来没玩过 国际象棋，于是问Knight咋 。Turns out只要 任意朝向的L形就好。具体来说，如coordinate是(x,y) 那么在这 的 只knight可以跳到  个position中任何 个: (x-2,y-1); (x-2,y+1);(x+2,y-1);(x+2,y+1);(x-1,y+2);(x-1,y-2);(x+1,y-2);(x+1,y+2).

Solution: BFS

此题不需要建立matrix棋盘矩阵，直接使用坐标BFS即可。

public int shortestKnightMove(int a1, int a2, int b1, int b2) {
	int[][] dirs = new int[][]{{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
	Queue<int[]> q = new LinkedList<>();
	boolean[][] visited = new boolean[8][8];
	q.offer(new int[]{a1, a2});
	visited[a1][a2] = true;
	int dist = 0;
	while (!q.isEmpty()) {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			int[] pos = q.poll();
			for (int[] d : dirs) {
				int x = pos[0] + d[0], y = pos[1] + d[1];
				if (x == b1 && y == b2)		return dist + 1;
				if (x < 0 || x >= 8 || y < 0 || y >= 8 || visited[x][y])	continue;
				visited[x][y] = true;
				q.offer(new int[]{x, y});
			}
		}
		dist++;
	}
	return -1;
}


# BFS
import collections
def moveKnight(a1, a2, b1, b2):
    def isvalid(node):
        return 0 <= node[0] < 8 and 0 <= node[1] < 8
    directions = [[1,2], [1,-2], [-1, 2], [-1, -2], [2,1], [-2, 1], [2, -1], [-2, -1]]
    visited = [[False] * 8] * 8
    deque = collections.deque()
    deque.append([a1, a2])
    res = 1
    while deque:
        length = len(deque)
        for i in range(length):
            node = deque.popleft()
            if not isvalid(node) or visited[node[0]][node[1]]:
                continue
            visited[node[0]][node[1]] = True
            if node[0] == b1 and node[1] == b2:
                return res
            for d in directions:
                deque.append([node[0]+d[0], node[1]+d[1]])
        res += 1
    return -1


def knightProbability(self, N, K, r, c):
	"""
	:type N: int
	:type K: int
	:type r: int
	:type c: int
	:rtype: float
	"""
	directions = [(1, 2), (-1, 2), (1, -2), (-1, -2), (2, 1), (2, -1), (-2, -1), (-2, 1)]
	knightDict, tempDict = [[0] * N for _ in range(N)], [[0] * N for _ in range(N)]
	knightDict[r][c] = 1
	for k in range(K):
		for i in range(N):
			for j in range(N):
				if knightDict[i][j] > 0:
					for d in directions:
						if 0 <= i+d[0] < N and 0 <= j+d[1] < N:
							tempDict[i+d[0]][j+d[1]] += knightDict[i][j]
		knightDict = tempDict[:][:]
		tempDict = [[0] * N for _ in range(N)]
	return float(sum(map(sum, knightDict)))/(8 ** K)
