#include <stdio.h>

int main() {
	int n,a,b,i;
	scanf("%d", &n);
	b = n;

	for (i=0; ;) {
		a = (b / 10) + (b % 10);
		b = (b % 10) * 10 + (a % 10);
		i++;
		if (b == n)
			break;
	}

	printf("%d", i);
}