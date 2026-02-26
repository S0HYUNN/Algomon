from collections import deque

n, k = map(int, input().split())
q = deque()

q.append(n)
v = [-1] * 100001
v[n] = 0

while q:
    x = q.popleft()
    
    if x == k:
        print(v[x])
        break
    
    for i in [x-1, x+1, x*2]:
        if 0 <= i <= 100000 and v[i] == -1:
            v[i] = v[x] + 1
            q.append(i)