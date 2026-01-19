import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]
cnt = []

def divide(x, y, n):
    color = matrix[x][y]
    
    for i in range(x, x+n):
        for j in range(y, y+n):
            
            if color != matrix[i][j]:
                for a in range(3):
                    for b in range(3):
                        divide(x + (n//3) * a, y + (n//3) * b, n//3)
                        
                return
            
    if color == 1:
        cnt.append(1)
    elif color == -1:
        cnt.append(-1)
    else:
        cnt.append(0)
        
divide(0, 0, n)
print(cnt.count(-1), cnt.count(0), cnt.count(1), sep = '\n')