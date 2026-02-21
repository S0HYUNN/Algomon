import sys

input = sys.stdin.readline

N, M = map(int, input().split())
d = list(map(int, input().split()))
t = [list(map(int, input().split())) for _ in range(N)]

dp = [-2] * (M + 1)
dp[0] = -1  #

for i, t_i in enumerate(t):
    for t_ij in t_i:
        for v in range(M, -1, -1):
            if v >= t_ij and dp[v - t_ij] >= i - 1:
                dp[v] = i

answer = -1
for v in range(M, -1, -1):
    if dp[v] == N - 1:
        answer = v
        break

print(answer)