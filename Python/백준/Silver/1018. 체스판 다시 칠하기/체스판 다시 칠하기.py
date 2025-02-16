N, M = map(int, input().split())
checker = [[] for _ in range(N)]
result = []
for i in range(N):
    checker[i] = input()

for i in range(N-7):
    for j in range(M-7):
        w = 0
        b = 0
        for k in range(i,i+8):
            for l in range(j,j+8):
                if (k+l)%2 == 0:
                    if checker[k][l] != 'W':
                        w += 1
                    else:
                        b += 1
                else:
                    if checker[k][l] != 'B':
                        w += 1
                    else:
                        b += 1
        result.append(w)
        result.append(b)

print(min(result))
