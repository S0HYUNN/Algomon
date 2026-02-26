import sys
input = sys.stdin.readline

n = int(input())
budgets = list(map(int, input().split()))
m = int(input())

start = 0
end = max(budgets)
answer = 0

while start <= end:
    mid = (start + end) // 2
    total_budgets = 0
    
    for budget in budgets:
        total_budgets += min(budget, mid)
        
    if total_budgets > m:
        end = mid - 1
    else:
        answer = mid
        start = mid + 1
        
print(answer)