def bfs(si, sj):
    q = []
    v = [[0] * n for _ in range(n)]
    tlst = []
    
    q.append((si, sj))
    v[si][sj] = 1
    eat = 0
    
    while q:
        ci, cj = q.pop(0)
        if eat == v[ci][cj]:
            return tlst, eat-1
        
        for di, dj in ((-1, 0), (1, 0), (0, -1), (0, 1)):
            ni, nj = ci + di, cj + dj
            if 0 <= ni < n and 0 <= nj < n and v[ni][nj] == 0 and shark >= arr[ni][nj]:
                q.append((ni, nj))
                v[ni][nj] = v[ci][cj] + 1
                
                if shark > arr[ni][nj] > 0:
                    tlst.append((ni, nj))
                    eat = v[ni][nj]
                    
    return tlst, eat-1

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]

for i in range(n):
    for j in range(n):
        if arr[i][j] == 9:
            ci, cj = i, j
            arr[i][j] = 0
            
shark = 2
cnt, ans = 0, 0
while True:
    tlst, dist = bfs(ci, cj)
    
    if len(tlst) == 0:
        break
    
    tlst.sort(key = lambda x: (x[0], x[1]))
    ci, cj = tlst[0]
    arr[ci][cj] = 0
    cnt += 1
    ans += dist
    
    if shark == cnt:
        shark += 1
        cnt = 0
        
print(ans)