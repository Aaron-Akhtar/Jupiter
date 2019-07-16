package org.thesecretintelligence.jupiter;

import org.thesecretintelligence.jupiter.input.Input;
import org.thesecretintelligence.jupiter.utils.Title;

import java.util.Scanner;

/**
 * Coded by Aaron Akhtar (Shprqness) - The Secret Intelligence
 * @author Aaron Akhtar
 * */

public class Jupiter {

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args){
        clearScreen();
        Title.print();
        System.out.println("  ");
        Scanner scanner = new Scanner(System.in);
        Input.get(scanner);

    }

}
