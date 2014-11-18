#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <conio.h>
#include "AVL.h"

void menu();
void menuImprimir();

int main(int argc, char **argv)
{
	Apontador A = CriaArvore();
	int n,opcao;
	
	/*insereAVL(15,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(5,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(1,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(5,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(18,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(13,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(10,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(12,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(23,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	insereAVL(21,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	//insereAVL(20,A);
	//Insere(18,A);
	//insereAVL(25,A);
	
	removeAVL(10,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	removeAVL(20,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	removeAVL(15,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	removeAVL(25,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();
	
	removeAVL(30,&A);
	printf("\n");
	ImprimePreOrdem(A);
	getch();*/
	
	do{
		system("cls");
		menu();
		scanf("%d",&opcao);
		
		switch(opcao){
			case 1:
				printf("\nDigite o elemento a ser inserido: ");
				scanf("%d",&n);
				insereAVL(n,&A);
				break;
			case 2:
				printf("\nDigite o elemento a ser removido: ");
				scanf("%d",&n);
				removeAVL(n,&A);
				break;
			case 3:
				menuImprimir();
				scanf("%d",&opcao);
					switch(opcao){
						case 1:
							printf("\nArvore: \n");
							ImprimeEmOrdem(A);
							break;
						case 2:
							printf("\nArvore: \n");
							ImprimePreOrdem(A);
							break;
						case 3:
							printf("\nArvore: \n");
							ImprimePosOrdem(A);
							break;
						default:
							printf("\nOpcao invalida!");
					}
					getch();
				break;
			case 4:
				printf("\nPrograma encerrado!");
				getch();
			default:
				printf("\nOpcao invalida!");
				getch();
		}
	}while(opcao != 4);
	
	
	
	
	free(A);
	
	return 0;
}

void menu(){
	printf("\nEscolha uma opcao: ");
	printf("\n1. Inserir um elemento na arvore: ");
	printf("\n2. Remover um elemento da árvore: ");
	printf("\n3. Imprimir a arvore: ");
	printf("\n4. Sair do programa: ");
}

void menuImprimir(){
	printf("\n1. Imprimir em ordem. ");
	printf("\n2. Imprimir pre ordem.");
	printf("\n3. Imprimir pos ordem.");
}