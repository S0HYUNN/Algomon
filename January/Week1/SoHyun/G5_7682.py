def tickto(arr, t):
    if t == arr[0] == arr[1] == arr[2]:
        return True
    if t == arr[3] == arr[4] == arr[5]:
        return True
    if t == arr[6] == arr[7] == arr[8]:
        return True
    if t == arr[0] == arr[4] == arr[8]:
        return True
    if t == arr[2] == arr[4] == arr[6]:
        return True
    if t == arr[0] == arr[3] == arr[6]:
        return True
    if t == arr[1] == arr[4] == arr[7]:
        return True
    if t == arr[2] == arr[5] == arr[8]:
        return True 
    
    return False

while True:
    a = input()
    if a == "end":
        break
    else:
        flag = True
        arr = list(map(str, a))
        xcnt = arr.count('X')
        ocnt = arr.count('O')
        
        if xcnt > ocnt+1:
            print("invalid")
            continue
        
        if ocnt == xcnt:
            if tickto(arr, 'O') and not tickto(arr, 'X'):
                print("valid")
                continue
            
        if ocnt > xcnt:
            print("invalid")
            continue
        
        if ocnt + 1 == xcnt:
            if tickto(arr, 'X') and not tickto(arr, 'O'):
                print("valid")
                continue
            
        if xcnt == 5 and ocnt == 4:
            if not tickto(arr, 'O'):
                print("valid")
                continue
            
        print("invalid")