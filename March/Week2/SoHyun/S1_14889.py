import sys

input = sys.stdin.readline
n = int(input())

status = [list(map(int, input().split())) for _ in range(n)]

v = [False] * n
result = sys.maxsize

def dfs(a, idx):
    global result
    
    if a == n // 2:
        team_start = 0
        team_link = 0
        
        for i in range(n):
            for j in range(n):
                if v[i] and v[j]:
                    team_start += status[i][j]
                elif not v[i] and not v[j]:
                    team_link += status[i][j]
                    
        result = min(result, abs(team_start - team_link))
        return
    
    else:
        for i in range(idx, n):
            if not v[i]:
                v[i] = True
                dfs(a+1, i+1)
                v[i] = False
                
dfs(0, 0)
print(result)