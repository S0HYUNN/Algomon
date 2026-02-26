from collections import deque
import sys
input = sys.stdin.readline

def bfs(usado, start):
    q = deque()
    q.append(start)
    visited = [0] * (n+1)
    result = 0
    
    while q:
        c = q.popleft()
        
        for nx, value in graph[c]:
            if visited[nx] == 0 and nx != start:
                if value >= usado:
                    result += 1
                    q.append(nx)
                    visited[nx] = 1
                    
    return result

n, q = map(int, input().split())
graph = [[] for _ in range(n+1)]

for _ in range(n-1):
    a, b, u = map(int, input().split())
    graph[a].append((b, u))
    graph[b].append((a, u))
    
for _ in range(q):
    k, v = map(int, input().split())
    
    print(bfs(k, v))