import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

n = int(input())

tree = [[] for _ in range(n+1)]

for _ in range(n-1):
    p, c, d = map(int, input().split())
    tree[p].append((c, d))
    tree[c].append((p, d))
    
def dfs(start, distance):
    for next, next_d in tree[start]:
        if v[next] == -1:
            v[next] = distance + next_d
            dfs(next, distance + next_d)
            
v = [-1] * (n+1)
v[1] = 0
dfs(1, 0)

last_node = v.index(max(v))
v = [-1] * (n+1)
v[last_node] = 0
dfs(last_node, 0)

print(max(v))