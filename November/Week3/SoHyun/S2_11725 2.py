import sys
from collections import deque
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())
v = [[0] for _ in range(n+1)]
p = [0] * (n+1)

for _ in range(n-1):
    a, b = map(int, input().split())
    v[a].append(b)
    v[b].append(a)
    
p[1] = 0
q = deque()
q.append(1)

while q:
    c = q.popleft()
    for i in v[c]:
        if p[i] == 0:
            p[i] = c
            q.append(i)
            
print('\n'.join(map(str, p[2:])))