import sys
input = sys.stdin.readline

king, stone, n = input().split()
move = {'R' : (0, 1),  'L' : (0,-1), 'B' : (1,0), 'T' : (-1,0), 'RT' : (-1,1), 'LT' : (-1,-1), 'RB' : (1,1), 'LB' : (1,-1)}
chess = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H']
k1, k2 = (8 - (int(king[1])), chess.index(king[0]))
s1, s2 =  (8 - (int(stone[1])), chess.index(stone[0]))

for _ in range(int(n)):
    a, b = move[input().strip()]
    if 0 <= k1 + a < 8 and 0 <= k2 + b < 8:
        k1, k2 = k1 + a, k2 + b
        if k1 == s1 and k2 == s2:
            s1, s2 = s1 + a, s2 + b
    if s1 < 0 or s1 >= 8 or s2 < 0 or s2 >= 8:
        k1, k2 = k1 - a, k2 - b
        s1, s2 = s1 - a, s2 - b
        
print(chess[k2], 8 - k1, sep = '')
print(chess[s2], 8 - s1, sep = '')