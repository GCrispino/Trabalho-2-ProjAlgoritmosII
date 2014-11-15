#include <stdio.h>
#include <stdlib.h>
#include "AVL.h"

int main(int argc, char **argv)
{
	Apontador A = CriaArvore();
	
	insereAVL(10,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(20,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(15,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	//insereAVL(20,A);
	//Insere(18,A);
	//insereAVL(25,A);
	
	
	
	/*insereAVL(10,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(20,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(15,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(5,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(1,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(12,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(25,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	insereAVL(30,A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	*/
	
	ImprimePreOrdem(A);
	//printf("\n\n%d",C->Dado);
	
	
	/*printf("\n");
	T = verificaBalanceamento(11,A);
	
	if (T == NULL)
		printf("\nNULL");
	else
		printf("\n%d",T->Dado);
	//printf("%d",isBalanceada(A));*/
	
	return 0;
}
