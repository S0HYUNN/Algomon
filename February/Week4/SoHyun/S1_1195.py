import sys

input = sys.stdin.readline

long_str = input().rstrip()
short_str = input().rstrip()
if len(short_str) > len(long_str):
    long_str, short_str = short_str, long_str
    
result = len(short_str) + len(long_str)
switch = True

for i in range(len(short_str)):
    for j in range(i+1):
        if long_str[j] == '2' and short_str[(-1) * (i+1) + j] == '2':
            switch = False
            break
        
    if switch:
        result = min(result, len(long_str) + len(short_str) - (i+1))
        
    switch = True
    
    for j in range(i+1):
        if long_str[len(long_str)-1-j] == '2' and short_str[i-j] == '2':
            switch = False
            break
        
    if switch:
        result = min(result, len(long_str) + len(short_str) - (i+1))
    switch = True
    
switch = True
for i in range(len(long_str) - len(short_str)):
    for j in range(len(short_str)):
        if short_str[j] == '2' and long_str[j+i] == '2':
            switch = False
            break
    
    if switch:
        result = min(result, len(long_str))
    switch = True
    
print(result)