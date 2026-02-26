h, w, x, y = map(int, input().split())

a = [[0] * w for _ in range(h)]
b = [[0] * (w + y) for _ in range(h+x)]

for i in range(h+x):
    row = input().split()
    for j in range(w+y):
        b[i][j] = int(row[j])
        
for i in range(h):
    for j in range(w):
        a[i][j] = b[i][j]
        
for i in range(x, h):
    for j in range(y, w):
        a[i][j] -= a[i-x][j-y]
        
for r in a:
    print(*r)