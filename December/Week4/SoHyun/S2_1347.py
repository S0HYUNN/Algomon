n = int(input())
c = input()

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
v = [['#' for j in range(101)] for i in range(101)]

x, y, d = 50, 50, 2
ex = ey = sx = sy = 50
v[x][y] = '.'

for i in c:
    if (i == 'L'):
        d = (d + 3) % 4
    elif (i == 'R'):
        d = (d + 1) % 4
    else:
        x = x + dx[d]
        y = y + dy[d]
        v[x][y] = '.'
        sy, ey, sx, ex = min(sy, y), max(ey, y), min(sx, x), max(ex, x)
        
for i in range(sx, ex + 1):
    print(''.join(v[i][sy:ey+1]))