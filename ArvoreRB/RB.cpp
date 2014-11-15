#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include "RB.h"

using namespace std;

Apontador CriaArvore() {
    Apontador A = (Apontador) malloc(sizeof (Arvore));
    A->esq->cor = 'P';
    A->dir->cor = 'P';

    A->pai = NULL;
    A->esq = NULL;
    A->dir = NULL;
    A->Dado = -1;
    A->cor = 'V';

    return A;
}

void Insere(int d, Apontador A) {

    if (A->Dado == -1) {
        A->Dado = d;
        checaRB_caso1(A);
    } else if (d < A->Dado) {
        if (A->esq) { //Checa se existe filho esquerdo
            Insere(d, A->esq);
        } else {
            Apontador novo = CriaArvore();
            novo->Dado = d;
            novo->pai = A;
            A->esq = novo;
            checaRB_caso1(novo);
        }
    } else if (d > A->Dado) {
        if (A->dir) { //checa se existe filho a direita
            Insere(d, A->dir);
        } else {
            Apontador novo = CriaArvore();
            novo->Dado = d;
            novo->pai = A;
            A->dir = novo;
            checaRB_caso1(novo);
        }
    } else {
        printf("\nElemento %d ja presente na arvore", d);
    }
}

Apontador AchaAvo(Apontador A) {
    if ((A != NULL) && (A->pai != NULL))
        return A->pai->pai;
    else
        return NULL;
}

Apontador AchaTio(Apontador A) {
    Apontador avo = AchaAvo(A);
    if (avo == NULL)
        return NULL; // Não ter avô significa não ter tio
    if (A->pai == avo->esq)
        return avo->dir;
    else
        return avo->esq;
}

void checaRB_caso1(Apontador A) {
    if (A->pai == NULL) {
        A->cor = 'P';
    } else
        checaRB_caso2(A);
}

void checaRB_caso2(Apontador A) {
    if (A->pai->cor == 'P')
        return; /* Árvore ainda é válida */
    else
        checaRB_caso3(A);
}

void checaRB_caso3(Apontador A) {
    Apontador tio = AchaTio(A);
    Apontador avo;
    if ((tio != NULL) && (tio->cor == 'V')) {
        A->pai->cor = 'P';
        tio->cor = 'P';
        avo = AchaAvo(A);
        avo->cor = 'V';
        checaRB_caso1(avo);
    } else {
        checaRB_caso4(A);
    }
}

void checaRB_caso4(Apontador A) {
    Apontador avo = AchaAvo(A);

    if ((A == A->pai->dir) && (A->pai == avo->esq)) {
        		RE(A->pai);
        A = A->esq;
    } else if ((A == A->pai->esq) && (A->pai == avo->dir)) {
        		RD(A->pai);
        A = A->dir;
    }
    checaRB_caso5(A);
}

void checaRB_caso5(Apontador A) {
    Apontador avo = AchaAvo(A);

    A->pai->cor = 'P';
    avo->cor = 'V';
    if ((A == A->pai->esq) && (A->pai == avo->esq)) {
        		RD(avo);
    } else {
        /*
         * Aqui, (n == n->pai->direita) && (n->pai == g->direita).
         */
        		RE(avo);
    }
}

void ImprimePreOrdem(Apontador A) {
    if (A != NULL) {
        cout << A->Dado << "-";
        cout << A->cor;
        ImprimePreOrdem(A->esq);
        ImprimePreOrdem(A->dir);
    }

}

void ImprimeEmOrdem(Apontador A) {
    if (A != NULL) {
        ImprimeEmOrdem(A->esq);
        printf("%d ", A->Dado);
        ImprimeEmOrdem(A->dir);
    }
}

void ImprimePosOrdem(Apontador A) {
    if (A != NULL) {
        ImprimePosOrdem(A->esq);
        ImprimePosOrdem(A->dir);
        printf("%d ", A->Dado);
    }
}

Apontador Busca(int d, Apontador A) {
    if (A != NULL) {
        if (d < A->Dado)
            return Busca(d, A->esq);
        else if (d > A->Dado)
            return Busca(d, A->dir);
        else if (d == A->Dado)
            return A;
    } else
        return NULL;
}

Apontador Remove(int d, Apontador A) {
    Apontador aux;

    if (A == NULL)
        printf("\nElemento nao encontrado!");
    else if (d < A->Dado)
        A->esq = Remove(d, A->esq);
    else if (d > A->Dado)
        A->dir = Remove(d, A->dir);
    else if (A->dir == NULL && A->esq == NULL) {
        free(A);
        return NULL;
    } else if (A->dir == NULL) {
        aux = A->esq;
        free(A);
        return aux;
    } else if (A->esq == NULL) {
        aux = A->dir;
        free(A);
        return aux;
    } else {
        Apontador aux2 = AchaMenor(A->dir);
        A->Dado = aux2->Dado;
        A->dir = Remove(aux2->Dado, A->dir);
        return A;
    }
    return A;
}

Apontador AchaMenor(Apontador A) {
    if (A->esq == NULL)
        return A;
    else
        return AchaMenor(A->esq);
}
void RE(Apontador *A,Apontador P, Apontador U){
	//"PaiP" é o pai do apontador P.
	Apontador PaiP = P->pai;
        Apontador aux;
	if (PaiP == 0){//significa que o "P" da vez é a raíz, portanto esse nó não tem pai
		P = *A;
		*A = U;
		
		aux = U->esq;
		U->esq = P;
		
		
		P->dir = aux;
			
	}
	else{
		if (PaiP->dir == P){
			//O nó que aponta para P aponta agora para U(filho de P)
			PaiP->dir = U;
			
			//A variável auxiliar guarda o endereço do elemento à esquerda de U
			aux = U->esq;
			//O apontador à esquerda de U passa a apontar para P
			U->esq = P;
			//O apontador à direita de P passa a apontar para o endereço salvo na variável auxiliar
			P->dir = aux;
		}
		else{
			PaiP->esq = U;
				
			aux = U->esq;
						
			U->esq = P;
			P->dir = aux;
		}
	}
}

void RD(Apontador *A,Apontador P, Apontador U){
	//"PaiP" é o pai do apontador P.
	Apontador PaiP = P->pai;
        Apontador aux;
	if (PaiP == NULL){//significa que o "P" da vez é a raíz, portanto esse nó não tem pai
		//PaiP = A; //é atribuído ao apontador "PaiP" o apontador da raíz, para que a função prossiga normalmente.
		
		aux = *A;
		*A = U;
		
		if (U->dir)
			aux = U->dir;
		else
			aux = NULL;
			
		U->dir = aux;
	}
	else{
		if (PaiP->dir == P){
			PaiP->dir = U;
					
			if (U->dir)
				aux = U->dir;
			else
				aux = NULL;
			
			U->dir = P;
			P->esq = NULL;
		}
		else{
			PaiP->esq = U;
						
			if (U->dir)
				aux = U->dir;
			else
				aux = NULL;
							
			U->dir = P;
			P->esq = aux;
		}
	}
}





