/*
 ID: antonio13
 LANG: C
 PROG: fracdec
 */

/**
 * @author antonio081014
 * @time: 2012-5-11, 15:0:49
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define MAXDIGIT 100100

char dec[MAXDIGIT];
int lastrem[MAXDIGIT];
char buf[MAXDIGIT];

void
main(void)
{
    FILE *fin, *fout;
    int n, d, k, i, rem, len;

    fin = fopen("fracdec.in", "r");
    fout = fopen("fracdec.out", "w");
    assert(fin != NULL && fout != NULL);

    fscanf(fin, "%d %d", &n, &d);
    sprintf(buf, "%d.", n/d);

 /* long division keeping track of if we've seem a remainder before */
    for(i=0; i<MAXDIGIT; i++)
 lastrem[i] = -1;

    rem = n % d;
    for(i=0;; i++) {
 if(rem == 0) {
     if(i == 0)
  sprintf(buf+strlen(buf), "0");
     else
  sprintf(buf+strlen(buf), "%s", dec);
     break;
 }
 if(lastrem[rem] != -1) {
     k = lastrem[rem];
     sprintf(buf+strlen(buf), "%.*s(%s)", k, dec, dec+k);
     break;
 }

 lastrem[rem] = i;
 n = rem * 10;
 dec[i] = n/d + '0';
 rem = n%d;
    }

    /* print buf 76 chars per line */
    len = strlen(buf);
    for(i=0; i<len; i+=76) {
     fprintf(fout, "%.76s\n", buf+i);
    }
    exit(0);
}
