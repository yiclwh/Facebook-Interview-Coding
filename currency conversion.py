LC 399
def calcEquation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """
        def bfs(s, t, value):
            if s not in record or t not in record:
                return -1
            visited = set()
            deque = collections.deque()
            deque.append((s, value))
            while deque:
                n, v = deque.popleft()
                if n == t:
                    return v
                for nb in record[n]:
                    if nb[0] not in visited:
                        deque.append((nb[0], nb[1]*v))
                visited.add(n)
            return -1
            
        record = collections.defaultdict(list)
        for i in range(len(equations)):
            record[equations[i][0]].append((equations[i][1], values[i]))
            record[equations[i][1]].append((equations[i][0], 1/values[i]))
        return [float(bfs(x[0], x[1], 1)) for x in queries]

def calcEquation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """
        def dfs(v, record, s, t, value):
            if s not in record:
                return -1
            v.add(s)
            if t in record[s]:
                return value * record[s][t]
            else:
                for k in record[s]:
                    if k not in v:
                        check = dfs(set(v), record, k, t, value * record[s][k])
                        if check != -1:
                            return check
                return -1
            
        record = collections.defaultdict(dict)
        for i in range(len(equations)):
            record[equations[i][0]][equations[i][1]] = values[i]
            record[equations[i][1]][equations[i][0]] =  1/values[i]
        res = []
        for q in queries:
            visited = set()
            res.append(dfs(visited, record, q[0], q[1], 1))
        return res

https://www.dailycodingproblem.com/blog/how-to-find-arbitrage-opportunities-in-python/
###########   follow up ############
# 关键你需要的是走一圈回到原来的币种最终的比例，就等同于每条边的汇率取log然后求寻找weight sum的负数的loop了
we need to find a cycle whose edge weights product is greater than 1.

If we take the negative log of the edge weights, 
the problem of finding a cumulative product that’s greater than 1 turns into 
the problem of finding a negative sum cycle!

if we have a negative cost cycle, if means that the product of the weighted edges is bigger than 1.
The Bellman-Ford algorithm can detect negative cycles. 

The main idea of Bellman-Ford is this:
Since the longest path in any graph has at most |V| - 1 edges, 
if we take all the direct edges from our source node, 
then we have all the one-edged shortest paths; 
once we take edges from there, we have all the two-edged shortest paths; 
all the way until |V| - 1 sized paths.

If, after |V| - 1 iterations of this, 
we can still find a smaller path, then there must be a negative cycle in the graph. 
We can start our algorithm on any vertex in our graph – since our graph is 
connected (by virtue of it being complete), 
then if there’s a negative cycle in our graph, we’ll find it.

from math import log

def arbitrage(table):
    transformed_graph = [[-log(edge) for edge in row] for row in graph]

    # Pick any source vertex -- we can run Bellman-Ford from any vertex and
    # get the right result
    source = 0
    n = len(transformed_graph)
    min_dist = [float('inf')] * n

    min_dist[source] = 0

    # Relax edges |V - 1| times
    for i in range(n - 1):
        for v in range(n):
            for w in range(n):
                if min_dist[w] > min_dist[v] + transformed_graph[v][w]:
                    min_dist[w] = min_dist[v] + transformed_graph[v][w]

    # If we can still relax edges, then we have a negative cycle
    for v in range(n):
        for w in range(n):
            if min_dist[w] > min_dist[v] + transformed_graph[v][w]:
                return True

    return False


# http://etrain.github.io/finance/2013/06/08/currency-arbitrage-in-python
import json, urllib2, sys, math
import networkx as nx

def grab_files(url="http://fx.priceonomics.com/v1/rates/"):
    try:
        x = urllib2.urlopen(url).read()
        res = json.loads(x)
    except Exception, e:
        print >>sys.stderr, "Error getting rates:", e
        sys.exit(1)
    return res

def parse_point(t):
    tn = t[0].split("_")
    return (tn[0], tn[1], -1.0 * math.log(float(t[1])))

def build_graph(parsed_points):
    dg = nx.DiGraph()
    dg.add_weighted_edges_from(parsed_points)
    return dg

def find_path(digraph, start="USD"):
    path = nx.bellman_ford(digraph, start, return_negative_cycle=True)
    return path

def output_path(path, g, start="USD"):
    visited = set(start)
    tot=1.0
    pred = path[start]
    x = start
    while pred not in visited:
        print pred, "-->", x, math.exp(-g[pred][x]['weight'])
        tot*=math.exp(-g[pred][x]['weight'])
        visited.add(pred)
        x = pred
        pred = path[pred]
    
    tot *= math.exp(-g[start][x]['weight'])
    print start, "-->", x, math.exp(-g[start][x]['weight'])
    print "Total:", tot
    if tot < 1.0:
        print "Note: no arbitrage opportunity detected."

def main():
    obj = grab_files()
    parsed_points = map(parse_point, obj.items())
    dg = build_graph(parsed_points)
    path = find_path(dg)
    output_path(path, dg)

if __name__ == "__main__":
  main()