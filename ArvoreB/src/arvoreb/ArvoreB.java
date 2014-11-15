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
        if (no.mIsNoFolha) { // 1. Se a chave está no no e se esse nó é um no folha, entao deletar a chave deste no.
            int i;
            if ((i = no.buscaBinaria(chave)) != -1) { // chave é a iésima chave do no e o no contem a chave.
                no.remove(i, NO_FILHO_ESQUERDA);
            }
        } else {
            int i;
            if ((i = no.buscaBinaria(chave)) != -1) { // 2. Se o no é um no interno e contem a chave... (chave é a iésima chave do no, e o no contem a chave)
                Node noFilhoEsquerda = no.mNosFilhos[i];
                Node noFilhoDireita = no.mNosFilhos[i + 1];
                if (noFilhoEsquerda.mNumChaves >= T) { // 2a. Se o no filho antecedente(filho esquerda) tem ao menos T chaves...
                    Node noAntecedente = noFilhoEsquerda;
                    Node noApagavel = noAntecedente; // Tenha certeza de nao apagar uma chave de um no com apenas T-1 elementos.
                    while (!noAntecedente.mIsNoFolha) {// Portanto só descer para o no anterior (noApagavel) do no antecedente e delete a chave usando 3.
                        noApagavel = noAntecedente;
                        noAntecedente = noAntecedente.mNosFilhos[no.mNumChaves - 1];
                    }
                    no.mChaves[i] = noAntecedente.mChaves[noAntecedente.mNumChaves - 1];
                    no.mObjects[i] = noAntecedente.mObjects[noAntecedente.mNumChaves - 1];
                    delete(noApagavel, no.mChaves[i]);
                } else if (noFilhoDireita.mNumChaves >= T) { // 2b. Se o no filho sucessor(filho direita) tem ao menos T chaves...
                    Node noSucessor = noFilhoDireita;
                    Node noApagavel = noSucessor; // Tenha certeza de nao apagar uma chave de um no com apenas T-1 elementos.
                    while (!noSucessor.mIsNoFolha) {// Portanto só descer para o no anterior (noApagavel) do no sucessor e delete a chave usando 3.
                        noApagavel = noSucessor;
                        noSucessor = noSucessor.mNosFilhos[0];
                    }
                    no.mChaves[i] = noSucessor.mChaves[0];
                    no.mObjects[i] = noSucessor.mObjects[0];
                    delete(noApagavel, no.mChaves[i]);
                } else {// 2c. Se ambos, antecessor e sucessor tem apenasT-1 chaves...
                    // Se ambos filhos tem nos para esquerda e direita do elemento deletado tem o numero minimo de elementos,
                    // ou seja, T - 1, eles podem ser unidos em um unico no com 2 * T - 2 elementos.
                    int indiceChaveDoMeio = unirNos(noFilhoEsquerda, noFilhoDireita);
                    moveChave(no, i, NO_FILHO_DIREITA, noFilhoEsquerda, indiceChaveDoMeio); // Delete os i's filhos de ponteiros da direita do no.(Delete i's right child pointer from node.)
                    delete(noFilhoEsquerda, chave);
                }
            } else { // 3. Se a chave nao é reenviada no nó(If the key is not resent in node), desca até a raiz da apropriada subarvore quedeve conter a chave...
                // O método é estruturado para garantir que sepre que delete é chamado recursivamente no nó "no", o número de chave no nó é ao menos no minimo T.
                // Note que essa condição requer mais uma chave alem do minimo pedido usualmente pelas condições da Arvores-B. Essa condição reforçada permite-nos
                // deletar a chave da arvore um uma passada descendente sem sermos que "voltar pra cima" (This strengthened condition allows us to delete a key from the tree in one downward pass
                // without having to "back up".)
                i = no.indiceNoRaizSubArvore(chave);
                Node noFilho = no.mNosFilhos[i]; // noFilho é o iésimo filho do no.
                //parei na linha 250
                // https://code.google.com/p/himmele/source/browse/trunk/Algorithms%20and%20Data%20Structures/BTree/src/BTree.java
            }
        }
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
