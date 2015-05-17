/* 
 * File:   AVL.h
 * Author: Kae
 *
 * Created on 14 de Novembro de 2014, 12:44
 */

#ifndef AVL_H
#define	AVL_H

typedef struct arvore *Apontador;

typedef struct arvore{
	Apontador dir,esq,pai;
        char cor;
	int Dado;
}Arvore;

Apontador CriaArvore();
void Insere(int d,Apontador A, Apontador *raiz);
void checaRB_caso1(Apontador A, Apontador *raiz);
void checaRB_caso2(Apontador A, Apontador *raiz);
void checaRB_caso3(Apontador A, Apontador *raiz);
void checaRB_caso4(Apontador A, Apontador *raiz);
void checaRB_caso5(Apontador A, Apontador *raiz);
void RD(Apontador *A,Apontador P, Apontador U);
void RE(Apontador *A,Apontador P, Apontador U);
Apontador Remove(int d, Apontador A);
Apontador Busca(int d,Apontador A);
Apontador AchaMenor(Apontador A) ;
void show(Apontador x, int b);
void printnode(Apontador x, int esp);




#endif	/* AVL_H */

