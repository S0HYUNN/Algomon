n, m = map(int, input().split())

mtr = []
cnt = []

for i in range(n):
    mtr.append(input())

for a in range(n-7):
    for b in range(m-7):    
        white = 0           
        black = 0           
        for i in range(a, a+8):
            for j in range(b, b+8):
                if (i+j) %2 == 0:   
                    if mtr[i][j] != 'W':    
                        white += 1          
                    else:                
                        black += 1         
                else:             
                    if mtr[i][j] != 'W':  
                        black += 1          
                    else:                   
                        white += 1             
        cnt.append(white)   
        cnt.append(black)   
print(min(cnt))