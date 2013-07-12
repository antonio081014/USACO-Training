/*
ID: antonio13
TASK: ratios
LANG: C++
*/
#include <cstdio>
#include <cstring>

int main() {
	freopen("ratios.in", "r", stdin), freopen("ratios.out", "w", stdout);
	int r[4][3], ans[4], best = 400, bi = -1, bj = -1, bk= -1, br = -1, ratio;
	for (int i = 0; i < 4; i++)
		scanf("%d %d %d", r[i], r[i] + 1, r[i] + 2);

	for (int i = 0; i < 101; i++)
		for (int j = 0; j < 101; j++)
			for (int k = 0, good = 1; k < 101; good = 1, k++) {
				if (i + j + k < 1) continue;
				for (int m = 0; m < 3; ratio=r[0][m]?ans[m]/r[0][m]:ratio, m++)
					ans[m] = (r[1][m]*i+r[2][m]*j+r[3][m]*k);
				for (int m=0;m<3;m++) good&=(ratio*r[0][m]==ans[m]);
				if (good && i + j + k < best)
					best = i + j + k, bi = i, bj = j, bk = k, br = ratio;
			}

	if (bi == -1) puts("NONE");
	else printf("%d %d %d %d\n", bi, bj, bk, br);
}
