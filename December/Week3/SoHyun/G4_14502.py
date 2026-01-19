from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = []

di = [0, 1, 0, -1]
dj = [1, 0, -1, 0]

def bfs():
    q = deque()
    narr = [x[:] for x in arr]
    for i in range(n):
        for j in range(m):
            if narr[i][j] == 2:
                q.append((i, j))
                
    while q:
        ci, cj = q.popleft()
        
        for i in range(4):
            ni = ci + di[i]
            nj = cj + dj[i]
            
            if 0 <= ni < n and 0 <= nj < m:
                if narr[ni][nj] == 0:
                    narr[ni][nj] = 2
                    q.append((ni, nj))
                    
    global answer
    cnt = 0
    
    for i in range(n):
        cnt += narr[i].count(0)
        
        answer = max(answer, cnt)
        
def makeWall(cnt):
    if cnt == 3:
        bfs()
        return
    
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 0:
                arr[i][j] = 1
                makeWall(cnt+1)
                arr[i][j] = 0
                
for i in range(n):
    arr.append(list(map(int, input().split())))
    
answer = 0
makeWall(0)
print(answer)