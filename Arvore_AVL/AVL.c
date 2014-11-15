#include "AVL.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


bool isBalanceada(Apontador A){
	if (abs(nNiveis(A->esq) - nNiveis(A->dir)) > 1)
		return false;
	else
		return true;
}

//verifica se uma árvore está balanceada a partir de um certo nó. Caso contrário, retorna um ponteiro para o primeiro
//nó desbalanceado vindo a partir do nó passado
Apontador verificaBalanceamento(int chave, Apontador A){
	Apontador T = NULL;
	
	if (Busca(chave,A) == NULL){
		printf("\nElemento passado nao encontrado!");
		return NULL;
	}
	
	if (A != NULL){
		if (chave < A->Dado)
			T = verificaBalanceamento(chave,A->esq);
		else if (chave > A->Dado)
			T = verificaBalanceamento(chave,A->dir);
	}
	else{
		printf("\nElemento passado nao existe!");
		return NULL;
	}
	
	if (T != NULL)
		return T;
	
	if (!isBalanceada(A))
		return A;
	
}

//insere um elemento na árvore.
void insereAVL(int chave, Apontador *A){
	int hdp,hep; //alturas direita e esquerda do nó "P"(primeiro nó desregulado caminhando-se a partir do inserido)
	int hdu,heu;//alturas do nó "U"(filho de "P" no caminho até o nó inserido).
	
	Insere(chave,*A);
	
	Apontador P = verificaBalanceamento(chave,*A);
	if (P != NULL){
		Apontador U;
		
		hep = nNiveis(P->esq);
		hdp = nNiveis(P->dir);
		
		//altura à direita de T é maior que à esqueda
		if (hdp > hep){
			U = P->dir;
			
			hdu = nNiveis(U->dir);
			heu = nNiveis(U->esq);
			
			if (hdu >= heu){
				//rotação simples à esquerda
				RE(A,P,U);
			}
			else{
				//Rotação dupla à esquerda
				RDE(A,P,U);
			}
		}
		else{ //altura à esquerda de T é maior que à direita
			U = P->esq;
			
			hdu = nNiveis(U->dir);
			heu = nNiveis(U->esq);
			
			if (heu >= hdu){
				//Rotação simples à direita
				RD(A,P,U);
			}
			else{
				//Rotação dupla à direita
				RDD(A,P,U);
			}
		}
	}
}

Apontador buscaApontador(Apontador A,Apontador filho){
	if (A == filho)
		return 0;
		
	if (A != NULL && filho != NULL){
		if (A->esq == filho || A->dir == filho)
			return A;
		else if (filho->Dado < A->Dado)
			return buscaApontador(A->esq,filho);
		else if (filho->Dado > A->Dado)
			return buscaApontador(A->dir,filho);
	}
	else
		return NULL;
}

void RE(Apontador *A,Apontador P, Apontador U){
	//"PaiP" é o pai do apontador P.
	Apontador PaiP = buscaApontador(*A,P),aux;
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
	Apontador PaiP = buscaApontador(*A,P),aux;
	if (PaiP == 0){//significa que o "P" da vez é a raíz, portanto esse nó não tem pai
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

void RDE(Apontador *A,Apontador P, Apontador U){
	Apontador filhoU;//filho de U partindo-se do nó inserido
	int hdu,heu,hdp,hep;
	
	hdu = nNiveis(U->dir);
	heu = nNiveis(U->esq);
	
	if (hdu > heu)
		filhoU = U->dir;
	else
		filhoU = U->esq;
	
	
	RD(A,U,filhoU);
	
	hdp = nNiveis(P->dir);
	hep = nNiveis(P->esq);
	
	if (hdp > hep)
		U = P->dir;
	else
		U = P->esq;
	
	
	RE(A,P,U);
}

void RDD(Apontador *A,Apontador P, Apontador U){
	Apontador filhoU;//filho de U partindo-se do nó inserido
	
	int hdu,heu,hdp,hep;
	
	hdu = nNiveis(U->dir);
	heu = nNiveis(U->esq);
	
	if (hdu > heu)
		filhoU = U->dir;
	else
		filhoU = U->esq;
		
	RE(A,U,filhoU);
	
	hdp = nNiveis(P->dir);
	hep = nNiveis(P->esq);
	
	if (hdp > hep)
		U = P->dir;
	else
		U = P->esq;
		
	RD(A,P,U);
}