import sys
sys.setrecursionlimit(20000)
input = sys.stdin.readline

def dfs(start, group):
    global error
    
    if error:
        return
    
    visited[start] = group
    
    for i in graph[start]:
        if not visited[i]:
            dfs(i, -group)
        elif visited[start] == visited[i]:
            error = True
            return

t = int(input())

for _ in range(t):
    v, e = map(int, input().split())
    graph = [[] for _ in range(v+1)]
    visited = [False] * (v+1)
    error = False
    
    for _ in range(e):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
        
    for i in range(1, v+1):
        if not visited[i]:
            dfs(i, 1)
            
            if error:
                break
            
    if error:
        print('NO')
    else:
        print('YES')