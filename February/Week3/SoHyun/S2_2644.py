import sys
input = sys.stdin.readline

n = int(input())
a, b = map(int, input().split())
m = int(input())
family = [[] for i in range(n+1)]

for i in range(m):
    x, y = map(int, input().split())
    family[x].append(y)
    family[y].append(x)

v = [False for _ in range(n+1)]
result = []

def dfs(x, count):
    global flag
    v[x] = True
    
    if x == b:
        flag = True
        print(count)
    for val in family[x]:
        if v[val] == False:
            dfs(val, count+1)
            
flag = False
dfs(a, 0)

if flag == False:
    print(-1)