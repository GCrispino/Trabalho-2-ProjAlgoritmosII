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
	int n,opcao,opcao2;
	
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
				scanf("%d",&opcao2);
				printf("\nArvore: \n");
					switch(opcao2){
						case 1:
							ImprimeEmOrdem(A);
							break;
						case 2:
							ImprimePreOrdem(A);
							break;
						case 3:
							ImprimePosOrdem(A);
							break;
						case 4:
                             show(A,0);
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
	printf("\n2. Remover um elemento da arvore: ");
	printf("\n3. Imprimir a arvore: ");
	printf("\n4. Sair do programa: ");
}

void menuImprimir(){
	printf("\n1. Imprimir em ordem. ");
	printf("\n2. Imprimir pre ordem.");
	printf("\n3. Imprimir pos ordem.");
	printf("\n4. Imprimir de forma organizada.");
}
