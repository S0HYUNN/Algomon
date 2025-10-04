H, K, R = map(int, input().split())

tasks = [list(map(int, input().split())) for _ in range(2**H)]

orders = [x for x in range(2**H)]
stack = [[[] for j in range(2**i)] for i in range(H+1)]
stack[0][0] = orders
for i in range(H):
    for j in range(2**i):
        tmpStack = stack[i][j]
        for k in range(2**(H-i)):
            if i % 2 == 0:
                if k % 2:
                    stack[i+1][2*j].append(tmpStack[k])
                else:
                    stack[i+1][2*j+1].append(tmpStack[k])
            else:
                if k % 2:
                    stack[i+1][2*j+1].append(tmpStack[k])
                else:
                    stack[i+1][2*j].append(tmpStack[k])

resultStack = stack[H]
dp = [0 for _ in range(H+2**H*K)]
index = H
for i in range(K):
    for num in resultStack:
        dp[index] = tasks[num[0]][i]
        index += 1

for i in range(H+2**H*K-1):
    dp[i+1] += dp[i]

if H + 2**H * K > R - 1:
    print(dp[R-1])
else:
    print(dp[-1])
