import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def dfs(node, cnt):
    v[node] = 1
    
    for c_node in graph[node]:
        if v[c_node] == 0:
            cnt = dfs(c_node, cnt+1)
            
    return cnt

t = int(input())
for _ in range(t):
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    v = [0] * (n+1)
    
    for _ in range(m):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
        
    print(dfs(1, 0))