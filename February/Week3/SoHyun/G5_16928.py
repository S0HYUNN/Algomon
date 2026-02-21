from collections import deque
import sys
input = sys.stdin.readline

n, m = map(int, input().split())

board = [0] * 101
v = [False] * 101

ladder = dict()
for _ in range(n):
    i, j = map(int, input().split())
    ladder[i] = j
    
snack = dict()
for _ in range(m):
    i, j = map(int, input().split())
    snack[i] = j
    
def bfs(start):
    q = deque()
    q.append(start)
    
    v[start] = True
    
    while q:
        cur = q.popleft()
        
        for i in range(1, 7):
            next = cur + i
    
            if 0 < next <= 100 and not v[next]:
                if next in ladder:
                    next = ladder[next]
                    
                if next in snack:
                    next = snack[next]
                    
                if not v[next]:
                    q.append(next)
                    v[next] = True
                    board[next] = board[cur] + 1
                    
bfs(1)
print(board[100])