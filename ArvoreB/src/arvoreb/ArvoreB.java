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
                if (noFilho.mNumChaves == T - 1) {
                    Node irmaoFilhoDireita = (i - 1 >= 0) ? no.mNosFilhos[i - 1] : null;
                    Node irmaoFilhoEsquerda = (i + 1 <= no.mNumChaves) ? no.mNosFilhos[i + 1] : null;
                    if (irmaoFilhoEsquerda != null && irmaoFilhoEsquerda.mNumChaves >= T) { // 3a. O irmao da esquerda tem >= T chaves...
                        // Move a key from the subtree's root node down into childNode along with the appropriate child pointer.
                        // Therefore, first shift all elements and children of childNode right by 1.                        
                        //
                        // Move a chave da subarvore do no raiz, para baixo, no noFilho no apropriado ponteiro filho.
                        // Portanto, primeiro desloque todos os elementos e filhos do noFilho em 1, para a direita.
                        noFilho.deslocarUmParaDireita();
                        noFilho.mChaves[0] = no.mChaves[i - 1]; // i-1 é o indice da chave que é menor que a chave do noFilho.
                        noFilho.mObjects[0] = no.mObjects[i - 1];
                        if (!noFilho.mIsNoFolha) {
                            noFilho.mNosFilhos[0] = irmaoFilhoEsquerda.mNosFilhos[irmaoFilhoEsquerda.mNumChaves];
                        }
                        noFilho.mNumChaves++;

                        // Move uma chave do irmao da esquerda para o no raiz da subarvore.
                        no.mChaves[i - 1] = irmaoFilhoEsquerda.mChaves[irmaoFilhoEsquerda.mNumChaves - 1];
                        no.mObjects[i - 1] = irmaoFilhoEsquerda.mObjects[irmaoFilhoEsquerda.mNumChaves - 1];

                        // Remove a chave do irmao da esquerda juntamente com o no filho da direita.
                        irmaoFilhoEsquerda.remove(irmaoFilhoEsquerda.mNumChaves - 1, NO_FILHO_DIREITA);
                    } else if (irmaoFilhoDireita != null && irmaoFilhoDireita.mNumChaves >= T) { // 3b. O irmao da direita tem >= T chaves...
                        // Move a chave da subarvore do no raiz, para baixo, no noFilho no apropriado ponteiro filho.
                        noFilho.mChaves[noFilho.mNumChaves] = no.mChaves[i]; // i é o indice da chave no no que é maior que a maior chave do noFilho
                        noFilho.mObjects[noFilho.mNumChaves] = no.mObjects[i];
                        if (!noFilho.mIsNoFolha) {
                            noFilho.mNosFilhos[noFilho.mNumChaves + 1] = irmaoFilhoDireita.mNosFilhos[0];
                        }
                        noFilho.mNumChaves++;

                        // Move uma chave do irmao da direita para o no raiz da subarvore.
                        no.mChaves[i] = irmaoFilhoDireita.mChaves[0];
                        no.mObjects[i] = irmaoFilhoDireita.mObjects[0];

                        // Remove a chave do irmao da esquerda juntamente com o no filho da esquerda.
                        irmaoFilhoDireita.remove(0, NO_FILHO_ESQUERDA);
                    } else { // 3c. Ambos irmaos do noFilho tem somente T - 1 chaves cada...
                        if (irmaoFilhoEsquerda != null) {
                            int indiceChaveMeio = unirNos(noFilho, irmaoFilhoEsquerda);
                            moveChave(no, i - 1, NO_FILHO_ESQUERDA, noFilho, indiceChaveMeio); // i-1 é o indice da chave do meio no no quando une com o irmao da esquerda.
                        } else if (irmaoFilhoDireita != null) {
                            int indiceChaveMeio = unirNos(noFilho, irmaoFilhoDireita);
                            moveChave(no, i - 1, NO_FILHO_ESQUERDA, noFilho, indiceChaveMeio); // i-1 é o indice da chave do meio no no quando une com o irmao da esquerda.
                        }
                    }
                }
                delete(noFilho, chave);
            }
        }
    }

    // Merge two nodes and keep the median key (element) empty.
    // Une dois nós e retorna a chave (elemento) do meio vazio;
    int unirNos(Node dstNode, Node srcNode) {
        int indiceChaveMeio;
        if (srcNode.mChaves[0] < dstNode.mChaves[dstNode.mNumChaves - 1]) {
            int i;
            // Shift all elements of dstNode right by srcNode.mNumKeys + 1 to make place for the srcNode and the median key.
            // Desloca todos os elementos de dstNode para a direira em srcNode.mNumChaves + 1 para dar espaco para a chave do meio e srcNode
            if (!dstNode.mIsNoFolha) {
                dstNode.mNosFilhos[srcNode.mNumChaves + dstNode.mNumChaves + 1] = dstNode.mNosFilhos[dstNode.mNumChaves];
            }
            for (i = dstNode.mNumChaves; i > 0; i--) {
                dstNode.mChaves[srcNode.mNumChaves + 1] = dstNode.mChaves[i - 1];
                dstNode.mObjects[srcNode.mNumChaves + 1] = dstNode.mObjects[i - 1];
                if (!dstNode.mIsNoFolha) {
                    dstNode.mNosFilhos[srcNode.mNumChaves + 1] = dstNode.mNosFilhos[i - 1];
                }
            }

            // Limpa a chave (elemento) do meio
            indiceChaveMeio = srcNode.mNumChaves;
            dstNode.mChaves[indiceChaveMeio] = 0;
            dstNode.mObjects[indiceChaveMeio] = null;

            // Copia os elemento de srcNode para dstNode.
            for (i = 0; i < srcNode.mNumChaves; i++) {
                dstNode.mChaves[i] = srcNode.mChaves[i];
                dstNode.mObjects[i] = srcNode.mObjects[i];
                if (!srcNode.mIsNoFolha) {
                    dstNode.mNosFilhos[i] = srcNode.mNosFilhos[i];
                }
            }
            if (!srcNode.mIsNoFolha) {
                dstNode.mNosFilhos[i] = srcNode.mNosFilhos[i];
            }
        } else {
            // Limpa a chave (elemento) do meio
            indiceChaveMeio = dstNode.mNumChaves;
            dstNode.mChaves[indiceChaveMeio] = 0;
            dstNode.mObjects[indiceChaveMeio] = null;

            // Copia os elemento de srcNode para dstNode.
            int offset = indiceChaveMeio + 1;
            int i;
            for (i = 0; i < srcNode.mNumChaves; i++) {
                dstNode.mChaves[offset + i] = srcNode.mChaves[i];
                dstNode.mObjects[offset + i] = srcNode.mObjects[i];
                if (!srcNode.mIsNoFolha) {
                    dstNode.mNosFilhos[offset + 1] = srcNode.mNosFilhos[i];
                }
            }
            if (!srcNode.mIsNoFolha) {
                dstNode.mNosFilhos[offset + 1] = srcNode.mNosFilhos[i];
            }
        }
        dstNode.mNumChaves += srcNode.mNumChaves;
        return indiceChaveMeio;
    }

    // Move the key from srcNode at index into dstNode at medianKeyIndex. Note that the element at index is already empty.
    // Move a chave do srcNode no indice para o dstNode no indiceChaveMeio. Note que o elemento no indice já esta vazio.
    void moveChave(Node srcNode, int srcIndiceChave, int indiceFilho, Node dstNode, int indiceChaveMeio) {
        dstNode.mChaves[indiceChaveMeio] = srcNode.mChaves[srcIndiceChave];
        dstNode.mObjects[indiceChaveMeio] = srcNode.mObjects[srcIndiceChave];
        dstNode.mNumChaves++;

        srcNode.remove(srcIndiceChave, indiceFilho);

        if (srcNode == mNoRaiz && srcNode.mNumChaves == 0) {
            mNoRaiz = dstNode;
        }
    }

    public Object search(int chave) {
        return search(mNoRaiz, chave);
    }

    // Metodo de busca recursivo.
    public Object search(Node no, int chave) {
        int i = 0;
        while (i < no.mNumChaves && chave > no.mChaves[i]) {
            i++;
        }
        if (i < no.mNumChaves && chave == no.mChaves[i]) {
            return no.mObjects[i];
        }
        if (no.mIsNoFolha) {
            return null;
        } else {
            return search(no.mNosFilhos[i], chave);
        }
    }

    public Object search2(int chave) {
        return search2(mNoRaiz, chave);
    }

    // Metodo de busca iterativo.
    public Object search2(Node no, int chave) {
        while (no != null) {
            int i = 0;
            while (i < no.mNumChaves && chave > no.mChaves[i]) {
                i++;
            }
            if (i < no.mNumChaves && chave == no.mChaves[i]) {
                return no.mObjects[i];
            }
            if (no.mIsNoFolha) {
                return null;
            } else {
                no = no.mNosFilhos[i];
            }
        }
        return null;
    }

    private boolean atualizar(Node no, int chave, Object object) {
        while (no != null) {
            int i = 0;
            while (i < no.mNumChaves && chave > no.mChaves[i]) {
                i++;
            }
            if (i < no.mNumChaves && chave == no.mChaves[i]) {
                no.mObjects[i] = object;
                return true;
            }
            if (no.mIsNoFolha) {
                return false;
            } else {
                no = no.mNosFilhos[i];
            }
        }
        return false;
    }

    // Caminhamento em ordem pela arvore.
    String printArvoreB(Node no) {
        String string = "";
        if (no != null) {
            if (no.mIsNoFolha) {
                for (int i = 0; i < no.mNumChaves; i++) {
                    string += no.mObjects[i] + ", ";
                }
            } else {
                int i;
                for (i = 0; i < no.mNumChaves; i++) {
                    string += printArvoreB(no.mNosFilhos[i]);
                    string += no.mObjects[i] + ", ";
                }
                string += printArvoreB(no.mNosFilhos[i]);
            }
        }
        return string;
    }

    @Override
    public String toString() {
        return printArvoreB(mNoRaiz);
    }

    void validar() throws Exception{
        ArrayList<Integer> array = getChaves(mNoRaiz);
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i) >= array.get(i + 1)) {
                throw new Exception("Arvore-B invalida: " + array.get(i) + " maior que " + array.get(i + 1));
            }
        }
    }

    // Caminhamento em ordem pela arvore.
    ArrayList<Integer> getChaves(Node no) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        if(no!=null){
            if(no.mIsNoFolha){
                for (int i = 0; i < no.mNumChaves; i++) {
                    array.add(no.mChaves[i]);
                }
            }else{
                int i;
                for (i = 0; i < no.mNumChaves; i++) {
                    array.addAll(getChaves(no.mNosFilhos[i]));
                    array.add(no.mChaves[i]);
                }
                array.addAll(getChaves(no.mNosFilhos[i]));
            }
        }
        return array;
    }
}
