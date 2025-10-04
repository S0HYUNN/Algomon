N = int(input())
sequence = list(map(int, input().split()))

cnt = 0

for i in range(N):
    result = 0
    for j in range(i+1, N):
        if sequence[i] < sequence[j]:
            result += 1
        elif sequence[i] > sequence[j]:
            cnt += result

print(cnt)