import sys
from collections import deque
input = sys.stdin.readline

n, k = map(int, input().split())

v = [0] * 100001
check = [0] * 100001

def move(x):
    data = []
    tmp = x
    for _ in range(v[x] + 1):
        data.append(tmp)
        tmp = check[tmp]
    print(' '.join(map(str, data[::-1])))
    
def bfs():
    q = deque()
    q.append(n)
    
    while q:
        x = q.popleft()
        if x == k:
            print(v[x])
            move(x)
            return
        
        for nx in (x-1, x+1, x*2):
            if 0 <= nx <= 100000 and v[nx] == 0:
                v[nx] = v[x] + 1
                q.append(nx)
                check[nx] = x
                
bfs()