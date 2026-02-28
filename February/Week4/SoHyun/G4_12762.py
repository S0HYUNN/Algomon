import sys

input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

dec = [1] * n
for i in range(n):
    for j in range(i):
        if arr[j] > arr[i]:
            dec[i] = max(dec[i], dec[j] + 1)
            
inc = [1] * n
for i in range(n-1, -1, -1):
    for j in range(n-1, i, -1):
        if arr[j] > arr[i]:
            inc[i] = max(inc[i], inc[j] + 1)
            
answer = 0
for i in range(n):
    answer = max(answer, dec[i] + inc[i] - 1)
    
if answer == 1:
    print(1)
else:
    print(answer)