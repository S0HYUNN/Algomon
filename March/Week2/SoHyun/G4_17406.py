from itertools import permutations
from copy import deepcopy

n, m, k = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
rcs = [list(map(int, input().split())) for _ in range(k)]

ans = 987654321

for p in permutations(rcs, k):
    copy_a = deepcopy(a)
    for r, c, s in p:
        r -= 1
        c -= 1
        for n in range(s, 0, -1):
            tmp = copy_a[r-n][c+n]
            copy_a[r-n][c-n+1:c+n+1] = copy_a[r-n][c-n:c+n]
            for row in range(r-n, r+n):
                copy_a[row][c-n] = copy_a[row+1][c-n]
            copy_a[r+n][c-n:c+n] = copy_a[r+n][c-n+1:c+n+1]
            for row in range(r+n, r-n, -1):
                copy_a[row][c+n] = copy_a[row-1][c+n]
            copy_a[r-n+1][c+n] = tmp
            
    for row in copy_a:
        ans = min(ans, sum(row))
        
print(ans)