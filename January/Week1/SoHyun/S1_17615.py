import sys
input = sys.stdin.readline

n = int(input().rstrip())
balls = input().rstrip()

red = balls.count('R')
blue = n - red

res = min(red, blue)

cnt = 1
for i in range(1, n):
    if balls[i] == balls[i-1]:
        cnt += 1
    else:
        break
    
if balls[0] == 'R':
    res = min(res, red - cnt)
else:
    res = min(res, blue - cnt)
    
cnt = 1
for i in range(n-2, -1, -1):
    if balls[i] == balls[i+1]:
        cnt += 1
    else:
        break
    
if balls[-1] == 'R':
    res = min(res, red - cnt)
else:
    res = min(res, blue - cnt)
    
print(res)