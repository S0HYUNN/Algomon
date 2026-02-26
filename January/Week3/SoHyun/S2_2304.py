import sys
input = sys.stdin.readline

n = int(input())
m = 0
m_idx = 0
p = [0 for _ in range(1001)]

for _ in range(n):
    l, h = map(int, input().split())
    p[l] = h
    if m < h:
        m_idx = l
        m = h
        
cur = 0
answer = 0

for i in range(m_idx+1):
    cur = max(cur, p[i])
    answer += cur
    
cur = 0

for i in range(1000, m_idx, -1):
    cur = max(cur, p[i])
    answer += cur
print(answer)