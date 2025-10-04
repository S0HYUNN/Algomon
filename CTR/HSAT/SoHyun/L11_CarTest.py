n, q = map(int, input().split())
efficiency = list(map(int, input().split()))

efficiency.sort()

dic = {}

for i, m in enumerate(efficiency):
    dic[m] = i * (n - i - 1)

for i in range(q):
    m = int(input())

    if m in dic:
        print(dic[m])
    else:
        print(0)