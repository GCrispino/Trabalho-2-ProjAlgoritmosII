/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        test1(arvoreB);
//        try {
//            test2(arvoreB);
//            System.out.println("ok");
//        } catch (Exception ex) {
//            System.out.println("falhou");
//        }

    }

    public static void test2(ArvoreB arvoreB){
        
    }
//    public static void test2(ArvoreB bTree) throws Exception {
//        int primeNumbers[] = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
//            73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179};
//
//        for (int i = 0; i < primeNumbers.length; i++) {
//            bTree.adicionar(primeNumbers[i], String.valueOf(primeNumbers[i]));
//        }
//        System.out.println(bTree.toString());
//        for (int i = 0; i < primeNumbers.length; i++) {
//            String value = String.valueOf(primeNumbers[i]);
//            Object searchResult = (Object) bTree.search(primeNumbers[i]);
//            if (!value.equals(searchResult)) {
//                System.out.println("Oops: Key " + primeNumbers[i] + " retrieved object " + searchResult);
//            }
//        }
//
//        bTree.delete(17);
//        bTree.validar();
//        bTree.delete(42);
//        bTree.validar();
//        bTree.delete(131);
//        bTree.validar();
//        bTree.delete(5);
//        bTree.validar();
//
//        for (int i = 77; i >= 0; i--) {
//            bTree.delete(i);
//            bTree.validar();
//        }
//
//        for (int i = 0; i < primeNumbers.length; i++) {
//            bTree.adicionar(primeNumbers[i], String.valueOf(primeNumbers[i]));
//        }
//        for (int i = 0; i < primeNumbers.length; i++) {
//            String value = String.valueOf(primeNumbers[i]);
//            Object searchResult = (Object) bTree.search(primeNumbers[i]);
//            if (!value.equals(searchResult)) {
//                System.out.println("Oops: Key " + primeNumbers[i] + " retrieved object " + searchResult);
//            }
//        }
//
//        for (int i = primeNumbers.length - 1; i >= 0; i--) {
//            bTree.delete(primeNumbers[i]);
//            bTree.validar();
//        }
//    }

    public static void test1(ArvoreB arvoreB) {
        arvoreB.adicionar(20, "20");
        arvoreB.adicionar(40,"40");
        arvoreB.adicionar(10,"10");
        arvoreB.adicionar(30,"30");
        arvoreB.adicionar(15,"15");
        arvoreB.adicionar(35, "35");
        arvoreB.adicionar(7,"7");
        arvoreB.adicionar(26,"26");
        arvoreB.adicionar(22,"22");
        arvoreB.adicionar(18,"18");
        System.out.println(arvoreB.toString());
    }
}
