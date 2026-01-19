from collections import deque
import sys
input = sys.stdin.readline
            
n, k = map(int, input().split())
v = [0] * 100001

q = deque()
q.append(n)

while q:
    x = q.popleft()
    
    if x == k:
        print(v[x])
        break
    
    for nx in (x*2, x-1, x+1):
        if 0 <= nx <= 100000 and not v[nx]:
            if nx == x*2:
                v[nx] = v[x]
            else:
                v[nx] = v[x] + 1
                
            q.append(nx)