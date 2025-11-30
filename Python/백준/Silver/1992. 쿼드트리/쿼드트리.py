N = int(input())
arr = [input() for i in range(N)]


def check(x,y,N):
    start = arr[x][y]
    for i in range(x,x+N):
        for j in range(y,y+N):
            if start != arr[i][j]:
                return 1
    return 0

def aaa(x,y,N):
    if N==1:
        return str(arr[x][y])

    if check(x,y,N)==0:
        return str(arr[x][y])

    p1 = aaa(x,y,N//2)
    p2 = aaa(x,y+(N//2),N//2)
    p3 = aaa(x+(N//2),y,N//2)
    p4 = aaa(x+(N//2),y+(N//2),N//2)
    return '('+p1+p2+p3+p4+')'


print(aaa(0,0,N))

