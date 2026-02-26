import sys
from collections import deque
input = sys.stdin.readline

n, k = map(int, input().split())

q = deque()
q.append(n)
way = [0] * 100001
cnt, result = 0, 0

while q:
    x = q.popleft()
    tmp = way[x]
    
    if x == k:
        result = tmp
        cnt += 1
        continue
    
    for i in [x-1, x+1, x*2]:
        if 0 <= i < 100001 and (way[i] == 0 or way[i] == way[x] + 1):
            way[i] = way[x] + 1
            q.append(i)
            
print(result)
print(cnt)