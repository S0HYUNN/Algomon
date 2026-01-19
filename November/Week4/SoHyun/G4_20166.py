import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

n, m, k = map(int, input().split())
ans, arr, res = {}, [], []
dx = [1, 0, -1, 0, 1, 1, -1, -1]
dy = [0, 1, 0, -1, -1, 1, 1, -1]
ans_lst = []

for i in range(n):
    arr.append(list(input().strip()))

for _ in range(k):
    data = input().strip()
    ans[data] = 0
    ans_lst.append(data)
    
def solve(x, y, cnt, string):
    if cnt > 5:
        return
    
    if string in ans:
        ans[string] += 1
        
    for i in range(8):
        nx, ny = (x + n + dx[i]) % n, (y + m + dy[i]) % m
        solve(nx, ny, cnt + 1, string + arr[nx][ny])
        
for i in range(n):
    for j in range(m):
        start = ''
        solve(i, j, 1, start + arr[i][j])
        
for k in ans_lst:
    if k in ans:
        print(ans[k])