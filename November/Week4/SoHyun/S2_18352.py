import sys
input = sys.stdin.readline
from collections import deque

n, m, k, x = map(int, input().split())
graph = [[] for _ in range(n+1)]
d = [0] * (n+1)
v = [False] * (n+1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    
def bfs(x, k):
    res = []
    q = deque([x])
    v[x] = True
    
    while q:
        x = q.popleft()
        for i in graph[x]:
            if not v[i]:
                q.append(i)
                v[i] = True
                d[i] = d[x] + 1
                
                if d[i] == k:
                    res.append(i)
                    
    if len(res) == 0:
        print(-1)
    else:
        res.sort()
        for i in range(len(res)):
            print(res[i])
            
bfs(x, k)