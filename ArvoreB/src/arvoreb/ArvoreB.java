/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb;

import java.util.ArrayList;

/**
 *
 * @author Paulo
 */
public class ArvoreB {

    private static final int T = 4;
    private Node mNoRaiz;
    private static final int NO_FILHO_ESQUERDA = 0;
    private static final int NO_FILHO_DIREITA = 1;

    class Node {

        public int mNumChaves = 0;
        public int[] mChaves = new int[2 * T - 1];
        public Object[] mObjects = new Object[2 * T - 1];
        public Node[] mNosFilhos = new Node[2 * T];
        public boolean mIsNoFolha;

        int buscaBinaria(int chave) {
            int indiceEsquerda = 0;
            int indiceDireita = mNumChaves - 1;
            while (indiceEsquerda <= indiceDireita) {
                final int indiceMeio = indiceEsquerda + ((indiceDireita - indiceEsquerda) / 2);
                if (mChaves[indiceMeio] < chave) {
                    indiceEsquerda = indiceMeio + 1;
                } else if (mChaves[indiceMeio] > chave) {
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
                for (i = indice; i < mNumChaves - 1; i++) {
                    mChaves[i] = mChaves[i + 1];
                    mObjects[i] = mObjects[i + 1];
                    if (!mIsNoFolha) {
                        if (i >= indice + filhoEsquerdaOuDireita) {
                            mNosFilhos[i] = mNosFilhos[i + 1];
                        }
                    }
                }
                mChaves[i] = 0;
                mObjects[i] = null;
                if (!mIsNoFolha) {
                    if (i >= indice + filhoEsquerdaOuDireita) {
                        mNosFilhos[i] = mNosFilhos[i + 1];
                    }
                    mNosFilhos[i + 1] = null;
                }
                mNumChaves--;
            }
        }

        //tradução canalha da função shiftRightByOne hahaha
        void deslocarUmParaDireita() {
            if (!mIsNoFolha) {
                mNosFilhos[mNumChaves + 1] = mNosFilhos[mNumChaves];
            }
            for (int i = mNumChaves - 1; i >= 0; i--) {
                mChaves[i + 1] = mChaves[i];
                mObjects[i + 1] = mObjects[i];
                if (!mIsNoFolha) {
                    mNosFilhos[i + 1] = mNosFilhos[i];
                }
            }
        }

        int indiceNoRaizSubArvore(int chave) {
            for (int i = 0; i < mNumChaves; i++) {
                if (chave < mChaves[i]) {
                    return i;
                }
            }
            return mNumChaves;
        }
    }

    public ArvoreB() {
        this.mNoRaiz = new Node();
        this.mNoRaiz.mIsNoFolha = true;
    }

    public void adicionar(int chave, Object object) {
        Node noRaiz = mNoRaiz;
        if (!atualizar(mNoRaiz, chave, object)) {
            if (noRaiz.mNumChaves == 2 * T - 1) {
                Node novoNoRaiz = new Node();
                mNoRaiz = novoNoRaiz;
                novoNoRaiz.mIsNoFolha = false;
                mNoRaiz.mNosFilhos[0] = noRaiz;
                dividirNoFilho(novoNoRaiz, 0, noRaiz); // Divide o noRaiz e move a chave do meio para o novoNoRaiz.
                inserirNumNoNaoCheio(novoNoRaiz, chave, object);
            } else {
                inserirNumNoNaoCheio(noRaiz, chave, object);
            }
        }
    }

    //
    // Split the node, node, of a B-Tree into two nodes that both contain T-1 elements and move node's median key up to the parentNode.
    // This method will only be called if node is full; node is the i-th child of parentNode.
    //
    // Divide o no de uma arvoreB em dois nos sendo que ambos contem T-1 elementos e move a chave do no do meio para o noPai.
    // Esse metodo só vai ser chamado se o no estiver cheio; no é o iésimo filho de noPai.
    void dividirNoFilho(Node noPai, int i, Node no) {
        Node novoNo = new Node();
        novoNo.mIsNoFolha = no.mIsNoFolha;
        novoNo.mNumChaves = T - 1;
        for (int j = 0; j < T - 1; j++) { // Copia os ultimos T-1 elementos de no para o novoNo.
            novoNo.mChaves[j] = no.mChaves[j + T];
            novoNo.mObjects[j] = no.mObjects[j + T];
        }
        if (!novoNo.mIsNoFolha) {
            for (int j = 0; j < T; j++) { // Copia os ultimos T ponteiros de no para novoNo
                novoNo.mNosFilhos[j] = no.mNosFilhos[j + T];
            }
            for (int j = T; j < no.mNumChaves; j++) {
                no.mNosFilhos[j] = null;
            }
        }
        for (int j = T; j < no.mNumChaves; j++) {
            no.mChaves[j] = 0;
            no.mObjects[j] = null;
        }
        no.mNumChaves = T - 1;

        // Insere um (filho) ponteiro para o no novoNo no noPai, movendo outras chaves e ponteiros case seja necessario.
        for (int j = noPai.mNumChaves; j >= i + 1; j--) {
            noPai.mNosFilhos[j + 1] = noPai.mNosFilhos[j];
        }
        noPai.mNosFilhos[i + 1] = novoNo;
        for (int j = noPai.mNumChaves; j >= i + 1; j--) {
            noPai.mChaves[j + 1] = noPai.mChaves[j];
            noPai.mObjects[j + 1] = noPai.mObjects[j];
        }
        noPai.mChaves[i] = no.mChaves[T - 1];
        noPai.mObjects[i] = no.mObjects[T - 1];
        no.mChaves[T - 1] = 0;
        no.mObjects[T - 1] = null;
        noPai.mNumChaves++;
    }

    //
    // Insert an element into a B-Tree. (The element will ultimately be inserted into a leaf node). 
    //
    // Insere um elemento em uma arvoreB. (O elemento vai, em ultima analize, ser inserido em num no folha).
    void inserirNumNoNaoCheio(Node no, int chave, Object object) {
        int i = no.mNumChaves - 1;
        if (no.mIsNoFolha) {
            // Sendo o no um no nao cheio, inserir o novo elemento na sua posicao adequada no no.
            while (i >= 0 && chave < no.mChaves[i]) {
                no.mChaves[i + 1] = no.mChaves[i];
                no.mObjects[i + 1] = no.mObjects[i];
                i--;
            }
            i++;
            no.mChaves[i] = chave;
            no.mObjects[i] = object;
            no.mNumChaves++;
        } else {
            // Procura a partir da ultima chave o no ate que seka achado o ponteiro
            // que é o no raiz da subArvore onde o novo elemento deve ser posicionado.
            while (i >= 0 && chave < no.mChaves[i]) {
                i--;
            }
            i++;
            if (no.mNosFilhos[i].mNumChaves == (2 * T - 1)) {
                dividirNoFilho(no, i, no.mNosFilhos[i]);
                if (chave > no.mChaves[i]) {
                    i++;
                }
            }
            inserirNumNoNaoCheio(no.mNosFilhos[i], chave, object);
        }
    }

    public void delete(int chave) {
        delete(mNoRaiz, chave);
    }

    public void delete(Node no, int chave) {

    }

    // Merge two nodes and keep the median key (element) empty.
    int unirNos(Node dstNode, Node srcNode) {
        return 0;
    }

    // Move the key from srcNode at index into dstNode at medianKeyIndex. Note that the element at index is already empty.
    void moveChave(Node srcNode, int srcKeyIndex, int childIndex, Node dstNode, int medianKeyIndex) {

    }

    public Object search(int chave) {
        return search(mNoRaiz, chave);
    }

    // Recursive search method.
    public Object search(Node no, int chave) {
        return null;
    }

    public Object search2(int chave) {
        return search2(mNoRaiz, chave);
    }

    // Iterative search method.
    public Object search2(Node no, int chave) {
        return null;
    }

    private boolean atualizar(Node no, int chave, Object object) {
        return false;
    }

    // Inorder walk over the tree.
    String printArvoreB(Node no) {
        return "";
    }

    @Override
    public String toString() {
        return printArvoreB(mNoRaiz);
    }

    void validar() throws Exception {

    }

    // Inorder walk over the tree.
    ArrayList<Integer> getChaves(Node no) {
        return null;
    }
}
