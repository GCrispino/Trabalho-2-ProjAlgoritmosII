#include "Arvore_Binaria.h"
#include <stdio.h>
#include <stdlib.h>

Apontador CriaArvore(){
	Apontador A = (Apontador)malloc(sizeof(Arvore));
	A->esq = NULL;
	A->dir = NULL;
	A->Dado = -1;
	return A;
}

void Insere(int d, Apontador A){
	if (A->Dado == -1)
		A->Dado = d;
	else if (d < A->Dado)
			if (A->esq)
				Insere(d,A->esq);
			else{
				Apontador novo = CriaArvore();
				novo->Dado = d;
				A->esq = novo;
			}
		else if(d > A->Dado)
				if (A->dir)
					Insere(d,A->dir);
				else{
					Apontador novo = CriaArvore();
					novo->Dado = d;
					A->dir = novo;
				}
			else
				printf("\nElemento %d ja presente na arvore",d);
}

void ImprimePreOrdem(Apontador A){
	if (A != NULL){
		printf("%d ",A->Dado);
		ImprimePreOrdem(A->esq);
		ImprimePreOrdem(A->dir);
	}
}

void ImprimeEmOrdem(Apontador A){
	if (A != NULL){
		ImprimeEmOrdem(A->esq);
		printf("%d ",A->Dado);
		ImprimeEmOrdem(A->dir);
	}
}

void ImprimePosOrdem(Apontador A){
	if (A != NULL){
		ImprimePosOrdem(A->esq);
		ImprimePosOrdem(A->dir);
		printf("%d ",A->Dado);
	}
}

void show(Apontador x, int b) { 
     int i;
     
    if (x == NULL) {
        for (i = 0; i < b; i++) printf("   ");
        printf("-||\n");
        return;
    }
    show(x->dir, b+1);    
    printnode(x, b);
    show(x->esq, b+1);    
}

void printnode(Apontador x, int esp) {
    int i;     
    for (i = 0; i < esp; i++) printf("   ");
    //cout<<x->Dado<<"("<<x->cor<<")"<<endl;
    printf("%d\n",x->Dado);
}

Apontador Busca(int d,Apontador A){
	if (A != NULL){
		if (d < A->Dado)
			return Busca(d,A->esq);
		else if (d > A->Dado)
				return Busca(d,A->dir);
		 	 else if(d == A->Dado)
		 			 return A;
	}
	else
		return NULL;	
}

Apontador Remove(int d, Apontador A){
	Apontador aux;
	
	if (A == NULL)
		printf("\nElemento nao encontrado!");
	else if (d < A->Dado)
			A->esq = Remove(d,A->esq);
		 else if(d > A->Dado)
				A->dir = Remove(d,A->dir);
			  else if(A->dir == NULL && A->esq == NULL){
			  		free(A);
			  		return NULL;
			  }
				   else if(A->dir == NULL){
						aux = A->esq;
						free(A);
						return aux;
				   }
				   		else if(A->esq == NULL){
				   			aux = A->dir;
				   			free(A);
				   			return aux;
				   		}
				   			else {
				   				Apontador aux2 = AchaMenor(A->dir);
				   				A->Dado = aux2->Dado;
				   				A->dir = Remove(aux2->Dado,A->dir);
				   				return A;
				   			}
	return A;
}

Apontador AchaMenor(Apontador A){
	if(A->esq == NULL)
		return A;
	else
		return AchaMenor(A->esq);
}

int nNiveis(Apontador A){
	int u,v;
	if (A == NULL)
		return -1;
	
	u = nNiveis(A->dir);
	v = nNiveis(A->esq);
	
	if (u > v)
		return u + 1;
	else
		return v + 1;
}

int achaUltimo(Apontador A,int direcao){
	if (direcao == 0)
		while(A->esq != NULL)
			A = A->esq;
	else if (direcao == 1)
		while(A->dir != NULL)
			A = A->dir;
	else
		return -1;
	
	return A->Dado;
}
