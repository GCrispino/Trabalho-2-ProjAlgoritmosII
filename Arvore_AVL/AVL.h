#include "Arvore_binaria.h"
#include <stdbool.h>

//verifica se uma determinada árvore está balanceada.
bool isBalanceada(Apontador A);

Apontador verificaBalanceamento(int chave, Apontador A);

//busca pelo pai do Apontador "filho" passado como parâmetro.
Apontador buscaApontador(Apontador A,Apontador filho);

//Rotação simples à esquerda
void RE(Apontador A,Apontador P, Apontador U);
//Rotação simples à direita
void RD(Apontador A,Apontador P, Apontador U);

//Rotação dupla à esquerda
void RDE(Apontador A,Apontador P, Apontador U);
//Rotação dupla à direita
void RDD(Apontador A,Apontador P, Apontador U);