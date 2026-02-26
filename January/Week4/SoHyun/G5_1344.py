import sys
input = sys.stdin.readline
from itertools import combinations

a = int(input())
b = int(input())
check = [2, 3, 5, 7, 11, 13, 17]
c = [i for i in range(1, 19)]

pera = a / 100.0
perb = b / 100.0
sa = sb = 0

for i in range(len(check)):
    comb = len(list(combinations(c, check[i])))
    sa += comb * pow(pera, check[i]) * pow(1.0 - pera, 18 - check[i])
    sb += comb * pow(perb, check[i]) * pow(1.0 - perb, 18 - check[i])
    
print(sa + sb - sa * sb)