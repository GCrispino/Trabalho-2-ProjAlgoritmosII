/* 
 * File:   main.cpp
 * Author: Kae
 *
 * Created on 14 de Novembro de 2014, 12:39
 */

#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <string>
#include <iostream>
#include "RB.h"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    Apontador arvore;
    arvore = CriaArvore();
    int res, d;
    int vet[] = {11, 2, 14, 1, 7, 13, 15, 5, 8};
    for (int i = 0; i < 9; i++) {
        //        int vet[] = {2,1,4,3,5,6};
        //        for (int i = 0; i < 6; i++) {
        Insere(vet[i], arvore, &arvore);
    }

    while (1) {
        system("cls");
        cout << "1-Inserir no' na arvore\n";
        cout << "2-Imprimir Arvore.\n";
        cout << "3-Busca Elemento.\n";
        cout << "4-Remove Elemento.\n";
        cout << "\n0-Sair\n";

        cout << "->";
        cin>>res;
        switch (res) {
            case 0:
                exit(0);
            case 1:
                system("cls");
                cout << "Digite o valor para inserir:";
                cin>>d;
                Insere(d, arvore, &arvore);
                system("pause");
                break;
            case 2:
                system("cls");
                show(arvore, 0);
                system("pause");
                break;
            case 3:
                system("cls");
                cout << "Digite o valor para buscar:";
                cin>>d;
                Apontador ret ;
                ret = Busca(d,arvore);
                if(ret!=NULL){
                    cout<<"O elemento "<<ret->Dado<<" esta localizado na arvore.\n";
                }else{
                    cout<<"O elemento nao esta localizado na arvore.\n";
                }
                system("pause");
                break;
            case 4:
                system("cls");
                cout << "Digite o valor para remover:";
                cin>>d;
                Remove(d, arvore);
                system("pause");
                break;
            default:
                cout << "Valor invalido\n";
                system("pause");

        }

    };
    return 0;
}

