import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
net = int(input())
graph = [[] for i in range(n+1)]
v = [0] * (n+1)

for i in range(net):
    a, b = map(int, input().split())
    graph[a] += [b]
    graph[b] += [a]
    
v[1] = 1
q = deque([1])

while q:
    c = q.popleft()
    for nx in graph[c]:
        if v[nx] == 0:
            q.append(nx)
            v[nx] = 1
            
print(sum(v)-1)