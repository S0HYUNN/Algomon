import sys
import math
from collections import deque
input = sys.stdin.readline

n, r, d, x, y = map(int, input().split())
top_list = []
have_en = []
attack_top_list = []
answer_count = 0

for i in range(n):
    tx, ty = map(int, input().split())
    top_list.append([tx, ty])
    have_en.append(True)
    
def distance(x1, y1, x2, y2):
    return math.sqrt(((x1 - x2) ** 2) + ((y1 - y2) ** 2))

def bfs(x, y, d):
    global answer_count
    q = deque([(x, y, d)])
    
    while q:
        cx, cy, e = q.popleft()
        for i in range(n):
            if have_en[i] == True and distance(cx, cy, top_list[i][0], top_list[i][1]) <= r:
                answer_count += e
                have_en[i] = False
                q.append((top_list[i][0], top_list[i][1], e/2))
                
bfs(x, y, d)
print(answer_count)