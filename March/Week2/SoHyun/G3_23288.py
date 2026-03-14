from collections import deque

def get_score(score, x, y):
    v = [[False] * m for _ in range(n)]
    q = deque()
    q.append((x, y))
    v[x][y] = True
    
    score_sum = 0
    
    while q:
        x, y = q.popleft()
        score_sum += score
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if 0 <= nx < n and 0 <= ny < m and not v[nx][ny]:
                if data[nx][ny] == score:
                    q.append((nx, ny))
                    v[nx][ny] = True
                    
    return score_sum

def turn(dir, x, y):
    global dice
    
    if dir == 0:
        dice = [dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]]
    elif dir == 1:
        dice = [dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]]
    elif dir == 2:
        dice = [dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]]
    else:
        dice = [dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]]
        
    if dice[-1] > data[x][y]:
        dir = (dir + 1) % 4
    elif dice[-1] < data[x][y]:
        dir = (dir - 1) % 4
        
    return dir

n, m, k = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(n)]
answer = 0

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

dice = [1, 2, 3, 4, 5, 6]
x, y, dir = 0, 0, 0

for _ in range(k):
    nx = x + dx[dir]
    ny = y + dy[dir]
    
    if 0 > nx or nx >= n or 0 > ny or ny >= m:
        nx = x + dx[dir] * (-1)
        ny = y + dy[dir] * (-1)
        dir = (dir + 2) % 4
        
    dir = turn(dir, nx, ny)
    answer += get_score(data[nx][ny], nx, ny)

    x, y = nx, ny
    
print(answer)        