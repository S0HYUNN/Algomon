from itertools import permutations
import sys

input = sys.stdin.readline
n = int(input())

max_point = 0
innings = []
for _ in range(n):
    innings.append(list(map(int, input().split())))
    
def play_game(player_order):
    score = 0
    p = 0
    for i in range(n):
        out = 0
        one = 0
        two = 0
        three = 0
        while out < 3:
            if innings[i][player_order[p]] == 0:
                out += 1
            elif innings[i][player_order[p]] == 1:
                score += three
                three = two
                two = one
                one = 1
            elif innings[i][player_order[p]] == 2:
                score += two + three
                three = one
                two = 1
                one = 0
            elif innings[i][player_order[p]] == 3:
                score += three + two + one
                one, two, three = 0, 0, 1
            elif innings[i][player_order[p]] == 4:
                score += 1 + one + two + three
                one, two, three = 0, 0, 0
            p = (p + 1) % 9
            
    return score

for player in permutations(range(1, 9), 8):
    player_order = list(player[:3]) + [0] + list(player[3:])
    max_point = max(play_game(player_order), max_point)
    
print(max_point)