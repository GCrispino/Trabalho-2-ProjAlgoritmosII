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

void Insere(int d, Apontador A, Apontador *raiz) {

    if (A->Dado == -1) {
        A->Dado = d;
        checaRB_caso1(A, raiz);
    } else if (d < A->Dado) {
        if (A->esq) { //Checa se existe filho esquerdo
            Insere(d, A->esq, raiz);
        } else {
            Apontador novo = CriaArvore();
            novo->Dado = d;
            novo->pai = A;
            A->esq = novo;
            checaRB_caso1(novo, raiz);
        }
    } else if (d > A->Dado) {
        if (A->dir) { //checa se existe filho a direita
            Insere(d, A->dir, raiz);
        } else {
            Apontador novo = CriaArvore();
            novo->Dado = d;
            novo->pai = A;
            A->dir = novo;
            checaRB_caso1(novo, raiz);
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

void checaRB_caso1(Apontador A, Apontador *raiz) {
    if (A->pai == NULL) {
        A->cor = 'P';
    } else
        checaRB_caso2(A, raiz);
}

void checaRB_caso2(Apontador A, Apontador *raiz) {
    if (A->pai->cor == 'P')
        return; /* Árvore ainda é válida */
    else
        checaRB_caso3(A, raiz);
}

void checaRB_caso3(Apontador A, Apontador *raiz) {
    Apontador tio = AchaTio(A);
    Apontador avo;
    if ((tio != NULL) && (tio->cor == 'V')) {
        A->pai->cor = 'P';
        tio->cor = 'P';
        avo = AchaAvo(A);
        avo->cor = 'V';
        checaRB_caso1(avo, raiz);
    } else {
        checaRB_caso4(A, raiz);
    }
}

void checaRB_caso4(Apontador A, Apontador *raiz) {
    cout << "Caso 4.\n";
    Apontador avo = AchaAvo(A);
    if ((A == A->pai->dir) && (A->pai == avo->esq)) {
        RE(raiz, A->pai, A->pai->dir);
        A = A->esq;
    } else if ((A == A->pai->esq) && (A->pai == avo->dir)) {
        RD(raiz, A->pai, A->pai->esq);
        A = A->dir;
    }
    
    
    checaRB_caso5(A, raiz);
    
}

void checaRB_caso5(Apontador A, Apontador *raiz) {
    Apontador avo = AchaAvo(A);
    A->pai->cor = 'P';
    avo->cor = 'V';
    if ((A == A->pai->esq) && (A->pai == avo->esq)) {
        RD(raiz, avo, avo->esq);
    } else {
        /*
         * Aqui, (A == A->pai->dir) && (A->pai == avo->dir).
         */
        RE(raiz, avo, avo->dir);
    }
}
void show(Apontador x, int b) { 
    if (x == NULL) {
        for (int i = 0; i < b; i++) cout<<"   ";
        cout<<"-||"<<endl;
        return;
    }
    show(x->dir, b+1);    
    printnode(x, b);
    show(x->esq, b+1);    
}
void printnode(Apontador x, int esp) {
    int i;     
    for (i = 0; i < esp; i++) cout<<"   ";
    cout<<x->Dado<<"("<<x->cor<<")"<<endl;
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
    else if (d < A->Dado){
        cout<<A->Dado<<endl;
        A->esq = Remove(d, A->esq);
    }else if (d > A->Dado){
        cout<<A->Dado<<endl;
        A->dir = Remove(d, A->dir);
    }else if (A->dir == NULL && A->esq == NULL) {
        cout<<A->Dado<<endl;
        free(A);
        cout<<"Elemento removido com sucesso.\n";
        return NULL;
    } else if (A->dir == NULL) {
        cout<<A->Dado<<endl;
        aux = A->esq;
        free(A);
        cout<<"Elemento removido com sucesso.\n";        
        return aux;
    } else if (A->esq == NULL) {
        cout<<A->Dado<<endl;
        aux = A->dir;
        free(A);
        cout<<"Elemento removido com sucesso.\n";
        return aux;
    } else {
        cout<<"D6\n";
        cout<<A->Dado<<endl;        
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

void RE(Apontador *A, Apontador P, Apontador U) {
    //"PaiP" é o pai do apontador P.
    
    Apontador PaiP = P->pai;
    Apontador aux;
    if (PaiP == NULL) {//significa que o "P" da vez é a raíz, portanto esse nó não tem pai
        P = *A;
        *A = U;
        U->pai = NULL;
        P->pai = *A;
        aux = U->esq;
        U->esq = P;
        P->dir = aux;

    } else {
        if (PaiP->dir == P) {
            //O nó que aponta para P aponta agora para U(filho de P)
            PaiP->dir = U;
            U->pai = PaiP;
            P->pai = U;
            //A variável auxiliar guarda o endereço do elemento à esquerda de U
            aux = U->esq;
            //O apontador à esquerda de U passa a apontar para P
            U->esq = P;
            //O apontador à direita de P passa a apontar para o endereço salvo na variável auxiliar
            P->dir = aux;
        } else {
            PaiP->esq = U;
            U->pai = PaiP;
            P->pai = U;
            aux = U->esq;
            U->esq = P;
            P->dir = aux;
            
        }
    }
}

void RD(Apontador *A, Apontador P, Apontador U) {
    //"PaiP" é o pai do apontador P.
    Apontador PaiP = P->pai;
//    cout<<"PaiP:"<<PaiP->Dado<<endl;
    Apontador aux;
    if (PaiP == NULL) {//significa que o "P" da vez é a raíz, portanto esse nó não tem pai
        //        PaiP = &A; //é atribuído ao apontador "PaiP" o apontador da raíz, para que a função prossiga normalmente.
        *A = U;
        aux = U->dir;
        U->dir = P;
        P->esq = aux;        
    } else {
        if (PaiP->dir == P) {
            PaiP->dir = U;
            U->pai = PaiP;
            P->pai = U;
            aux = U->dir;
            U->dir = P;
            P->esq = aux;       
            
        } else {
            PaiP->esq = U;
            aux = U->dir;
            U->pai = PaiP;
            P->pai = U;
            U->dir = P;
            P->esq = aux;
        }
    }
}





