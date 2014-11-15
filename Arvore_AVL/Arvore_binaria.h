
typedef struct arvore *Apontador;

typedef struct arvore{
	Apontador esq,dir;
	int Dado;
}Arvore;

Apontador CriaArvore();
void Insere(int d,Apontador A);
void ImprimePreOrdem(Apontador A);
void ImprimeEmOrdem(Apontador A);
void ImprimePosOrdem(Apontador A);
Apontador Busca(int d,Apontador A);
Apontador Remove(int d, Apontador A);
Apontador AchaMenor(Apontador A);
int nNiveis(Apontador A);

