n = int(input())
rgb = [list(map(int, input().split())) for _ in range(n)]
ans = [[3003]*3 for _ in range(n)]
ans[0] = rgb[0]
for i in range(1,n):
    ans[i][0] = min(ans[i-1][1], ans[i-1][2]) + rgb[i][0]
    ans[i][1] = min(ans[i - 1][0], ans[i - 1][2]) + rgb[i][1]
    ans[i][2] = min(ans[i - 1][0], ans[i - 1][1]) + rgb[i][2]
print(min(ans[n-1]))