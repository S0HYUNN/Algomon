import sys
input = sys.stdin.readline

n1, n2 = map(int, input().split())

ant1 = list(map(str, input().rstrip("\n")))
ant2 = list(map(str, input().rstrip("\n")))
t = int(input())

ant1.reverse()
totalAnt = ant1 + ant2

for _ in range(t):
    for i in range(len(totalAnt) - 1):
        if totalAnt[i] in ant1 and totalAnt[i+1] in ant2:
            totalAnt[i], totalAnt[i+1] = totalAnt[i+1], totalAnt[i]
            
            if totalAnt[i+1] == ant1[-1]:
                break
            
print("".join(totalAnt))