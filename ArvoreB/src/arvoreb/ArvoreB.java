/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb;

/**
 *
 * @author Paulo
 */
public class ArvoreB {

    private static final int T = 4;
    private Node noRaiz;
    private static final int NO_FILHO_ESQUERDA = 0;
    private static final int NO_FILHO_DIREITA = 1;

    class Node {

        public int numChaves = 0;
        public int[] chaves = new int[2 * T - 1];
        public Object[] mObjects = new Object[2 * T - 1];
        public Node[] nosFilhos = new Node[2 * T];
        public boolean isNoFolha;

        int buscaBinaria(int chave) {
            int indiceEsquerda = 0;
            int indiceDireita = numChaves - 1;
            while (indiceEsquerda <= indiceDireita) {
                final int indiceMeio = indiceEsquerda + ((indiceDireita - indiceEsquerda) / 2);
                if (chaves[indiceMeio] < chave) {
                    indiceEsquerda = indiceMeio + 1;
                } else if (chaves[indiceMeio] > chave) {
                    indiceDireita = indiceMeio - 1;
                } else {
                    return indiceMeio;
                }
            }
            return -1;
        }

        boolean contem(int chave) {
            return buscaBinaria(chave) != -1;
        }

        // Remove an element from a node and also the left (0) or right (+1) child.
        void remove(int indice, int filhoEsquerdaOuDireita) {
            if (indice >= 0) {
                int i;
                for (i = indice; i < numChaves - 1; i++) {
                    chaves[i] = chaves[i + 1];
                    mObjects[i] = mObjects[i + 1];
                    if (!isNoFolha) {
                        if (i >= indice + filhoEsquerdaOuDireita) {
                            nosFilhos[i] = nosFilhos[i + 1];
                        }
                    }
                }
                chaves[i] = 0;
                mObjects[i] = null;
                if (!isNoFolha) {
                    if (i >= indice + filhoEsquerdaOuDireita) {
                        nosFilhos[i] = nosFilhos[i + 1];
                    }
                    nosFilhos[i + 1] = null;
                }
                numChaves--;
            }
        }
        
        //tradução canalha da função shiftRightByOne hahaha
        void deslocarUmParaDireita(){
            
        }
    }
}
