import sys
input = sys.stdin.readline
from collections import defaultdict

t = int(input())
n = int(input())
a = list(map(int, input().split()))
m = int(input())
b = list(map(int, input().split()))

ans = 0
a_dict = defaultdict(int)

for i in range(n):
    for j in range(i, n):
        a_dict[sum(a[i:j+1])] += 1
        
for i in range(m):
    for j in range(i, m):
        ans += a_dict[t - sum(b[i:j+1])]
        
print(ans)