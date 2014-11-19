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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArvoreB arvoreB = new ArvoreB();
        BTree bitri = new BTree();
        test1(arvoreB);
    }

    public static void test1(BTree bitri){
        bitri.add(1, "1");
        bitri.add(2, "2");
        bitri.add(3, "3");
        bitri.add(4, "4");
        bitri.add(5, "5");
        bitri.add(6, "6");
        bitri.add(7, "7");
        bitri.add(8, "8");
        bitri.add(9, "9");
        System.out.println(bitri.toString());
    }
    
    public static void test1(ArvoreB arvoreB) {
        arvoreB.adicionar(1, "1");
        arvoreB.adicionar(2, "2");
        arvoreB.adicionar(3, "3");
        arvoreB.adicionar(4, "4");
        arvoreB.adicionar(5, "5");
        arvoreB.adicionar(6, "6");
        arvoreB.adicionar(7, "7");
        arvoreB.adicionar(8, "8");
        arvoreB.adicionar(9, "9");
        System.out.println(arvoreB.toString());
    }
}
