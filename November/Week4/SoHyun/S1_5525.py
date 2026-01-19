import sys
input = sys.stdin.readline

n = int(input())
m = int(input())
s = input()

p = 'IOI'

cnt, i, answer = 0, 0, 0

while i < (m - 1):
    if s[i : i+3] == p:
        i += 2
        cnt += 1
        
        if cnt == n:
            answer += 1
            cnt -= 1
            
    else:
        i += 1
        cnt = 0
        
print(answer)