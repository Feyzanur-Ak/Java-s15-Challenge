package com.library.Person;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Person {

    Set<String> names=new HashSet<>(); // kayıt kısmının ekleneceği isimler

   public void start() {
       Scanner question=new Scanner(System.in);
       System.out.println("İsminizi giriniz: ");

       String answer = question.nextLine();

       if(names.contains(answer)) {
           System.out.println("Hoş geldiniz, " + answer);
       } else {
           whoyouare(answer);
       }
   }

   public  void whoyouare(String name) {
       System.out.println("Merhaba, " + name + "! Seni tanımıyorum, ismini listeye ekliyorum.");
       names.add(name);
   }

}
