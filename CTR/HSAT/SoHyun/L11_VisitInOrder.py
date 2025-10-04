n, m = map(int, input().split())

board = []
for i in range(n):
    board.append(list(map(int, input().split())))

points = []
for _ in range(m):
    x, y = map(int, input().split())
    points.append((x-1, y-1))

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]
v = [[0] * n for _ in range(n)]
answer = 0

def dfs(r, c, depth):
    global answer
    if depth == m:
        answer += 1
        return
    elif (r, c) == points[-1]:
        return

    for d in range(4):
        nr = r + dr[d]
        nc = c + dc[d]

        if -1 < nr < n and -1 < nc < n and not board[nr][nc] and not v[nr][nc]:
            v[nr][nc] = 1
            if points[depth] == (nr, nc):
                dfs(nr, nc, depth+1)
            else:
                dfs(nr, nc, depth)
            v[nr][nc] = 0

v[points[0][0]][points[0][1]] = 1
dfs(points[0][0], points[0][1], 1)
print(answer)