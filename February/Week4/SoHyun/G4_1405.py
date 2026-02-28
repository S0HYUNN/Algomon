import sys

input = sys.stdin.readline

def dfs(x, y, v, cnt, p):
    global answer
    
    if cnt == n:
        answer += p
        return
    
    v[x][y] = True
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if not v[nx][ny]:
            dfs(nx, ny, v, cnt+1, p * prob[i])
            v[nx][ny] = False
            
line = list(map(int, input().split()))
n, prob = line[0], list(map(lambda x : x/100, line[1:]))
answer = 0
dx, dy = [0, 0, 1, -1], [1, -1, 0, 0]

v = [[False] * (100) for _ in range(100)]
dfs(50, 50, v, 0, 1)
print(answer)