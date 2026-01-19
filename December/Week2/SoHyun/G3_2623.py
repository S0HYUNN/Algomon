from collections import deque

n, m = map(int, input().split())
indegree = [0] * (n + 1)
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    arr = list(map(int, input().split()))
    for i in range(1, len(arr) - 1):
        graph[arr[i]].append(arr[i+1])
        indegree[arr[i+1]] += 1
        
def topology_sort():
    q = deque()
    result = []
    
    for i in range(1, n+1):
        if indegree[i] == 0:
            q.append(i)
            
    while q:
        now = q.popleft()
        result.append(now)
        
        for i in graph[now]:
            indegree[i] -= 1
        
            if indegree[i] == 0:
                q.append(i)
                
    if len(result) != n:
        print(0)
    else:
        for singer in result:
            print(singer)
            
topology_sort()