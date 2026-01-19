import sys
input = sys.stdin.readline

n = int(input())
board = []
for i in range(n):
    string = list(input().rstrip())
    board.append(string)
    
quad_tree = []

def divide(a, b, n):
    tmp = board[a][b]
    for i in range(a, a+n):
        for j in range(b, b+n):
            if board[i][j] != tmp:
                quad_tree.append('(')
                divide(a, b, n//2)
                divide(a, b+n//2, n//2)
                divide(a+n//2, b, n//2)
                divide(a+n//2, b+n//2, n//2)
                quad_tree.append(')')
                return
    quad_tree.append(tmp)
    
divide(0, 0, n)
print(*quad_tree, sep = "")