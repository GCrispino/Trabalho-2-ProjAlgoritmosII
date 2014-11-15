#include "Arvore_binaria.h"
#include <stdbool.h>

//verifica se uma determinada árvore está balanceada.
bool isBalanceada(Apontador A);

//verifica se uma árvore está balanceada a partir de um certo nó. Caso contrário, retorna um ponteiro para o primeiro
//nó desbalanceado vindo a partir do nó passado
Apontador verificaBalanceamento(int chave, Apontador A);

//busca pelo pai do Apontador "filho" passado como parâmetro.
Apontador buscaApontador(Apontador A,Apontador filho);

void insereAVL(int chave, Apontador *A);
void removeAVL(int chave, Apontador *A);

//Rotação simples à esquerda
void RE(Apontador *A,Apontador P, Apontador U);
//Rotação simples à direita
void RD(Apontador *A,Apontador P, Apontador U);

//Rotação dupla à esquerda
void RDE(Apontador *A,Apontador P, Apontador U);
//Rotação dupla à direita
void RDD(Apontador *A,Apontador P, Apontador U);