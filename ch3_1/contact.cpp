/*
 ID: antonio13
 PROG: contact
 LANG: C++
 */
#include <iostream>
#include <cstring>
#include <cstdio>
#include <algorithm>
using namespace std;

struct Pattern { int pattern, count; };
bool operator <(const Pattern& a, const Pattern& b) {
	return a.count == b.count ? a.pattern < b.pattern : a.count > b.count;
}
void print(int num) {
	if (num <= 1) return;
	print(num / 2), putchar('0' + (num % 2));
}
int main() {
	freopen("contact.in", "r", stdin), freopen("contact.out", "w", stdout);
	char x[200001];
	int count[20000] = {0}, mask, a, b, n, A_min, B_max, L=1, i, j, top = 0, inLine = 0, N = 0;
	Pattern patterns[20001];
	cin >> a >> b >> n;
	A_min = 1 << (a), B_max = (1 << (b + 1)) - 1;
	while (cin >> x[L])
		L++;

	for (i = 0; i < L; i++)
		for (j = 1, mask = 1L; j <= b && i + j < L; j++)
			mask = (mask << 1) + (x[i + j] - '0'), count[mask] += j >= a;

	for (int i = A_min; i <= B_max; i++)
		if (count[i])
			patterns[top++] = Pattern { i, count[i] };

	sort(patterns, patterns + top);

	printf("%d\n", patterns[0].count), print(patterns[0].pattern);
	for (int i = 1; i < top; i++) {
		if (patterns[i].count != patterns[i - 1].count && !(inLine = 0) && ++N) {
			if (N >= n) break;
			printf("\n%d\n", patterns[i].count);
		} else
			putchar(inLine == 5 ? '\n' : ' '), inLine = (inLine + 1) % 6;
		print(patterns[i].pattern);
	}
	puts("");
}
