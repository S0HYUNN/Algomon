import sys
input = sys.stdin.readline

r, c, k = map(int, input().split())
graph = []
for _ in range(r):
    graph.append(list(input().strip()))
    
move = [[-1, 0], [0, 1], [1, 0], [0, -1]]

graph[r-1][0] = 1

result = 0

def dfs(row, col):
    global result
    
    if row == 0 and col == c-1:
        if graph[row][col] == k:
            result += 1
            return
        else:
            return
    
    for i in move:
        nrow = row + i[0]
        ncol = col + i[1]
        if nrow >= 0 and ncol >= 0 and nrow < r and ncol < c:
            if graph[nrow][ncol] == '.':
                graph[nrow][ncol] = graph[row][col] + 1
                dfs(nrow, ncol)
                graph[nrow][ncol] = '.'
                
dfs(r-1, 0)
print(result)