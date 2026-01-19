import copy

n, m = map(int, input().split())
cctv = []
graph = []
mode = [
    [],
    [[0], [1], [2], [3]],
    [[0, 2], [1, 3]],
    [[0, 1], [1, 2], [2, 3], [0, 3]],
    [[0, 1, 2], [0, 1, 3], [1, 2, 3], [0, 2, 3]],
    [[0, 1, 2, 3]],
]

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(n):
    data = list(map(int, input().split()))
    graph.append(data)
    for j in range(m):
        if data[j] in [1, 2, 3, 4, 5]:
            cctv.append([data[j], i, j])
            
def fill(board, mm, x, y):
    for i in mm:
        nx = x
        ny = y
        
        while True:
            nx += dx[i]
            ny += dy[i]
            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                break
            if board[nx][ny] == 6:
                break
            elif board[nx][ny] == 0:
                board[nx][ny] = 7
                
def dfs(depth, arr):
    global min_value
    
    if depth == len(cctv):
        count = 0
        for i in range(n):
            count += arr[i].count(0)
        min_value = min(min_value, count)
        return
    
    temp = copy.deepcopy(arr)
    cctv_num, x, y = cctv[depth]
    
    for i in mode[cctv_num]:
        fill(temp, i, x, y)
        dfs(depth + 1, temp)
        temp = copy.deepcopy(arr)
        
min_value = int(1e9)
dfs(0, graph)
print(min_value)