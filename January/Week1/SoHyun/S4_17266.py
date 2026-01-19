n = int(input())
m = int(input())
x = list(map(int, input().split()))

result = 0

if m == 1:
    result = max(x[0], n - x[0])
else:
    for i in range(m):
        if i == 0:
            a = x[i]
        elif i == m-1:
            a = n - x[i]
        else:
            d = x[i] - x[i-1]
            
            if d % 2 == 0:
                a = d // 2
            else:
                a = d // 2 + 1
                
        result = max(a, result)

print(result)