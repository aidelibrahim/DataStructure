package auction_2016;

import java.util.Locale;
import java.util.Scanner;

public class Auction_2016 {

    static int i = 0;
    static boolean key = false;
    static boolean editPersonalDetails =false;
   
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        //guide user to Log in into their account or sign up as a new user
        LoginModule login = new LoginModule();
        SignUpModule<String> signup = new SignUpModule<>();
        boolean runProgram = true;
        BackToAuction:
        while (runProgram) {
            while (true) {
                test:
                System.out.println(" =============== WELCOME TO <MyChoice> Auction System ! ===============");
                System.out.println(" -------------------------> Quote Of The Day <-------------------------");
                System.out.println(" '"+signup.Inspiration_Quotes()+"' ");
                System.out.print("\nWould you like to Log In or Create a new account?\n[1]Log In\n[2]Sign up\n[3]Exit\nChoose:");
                String in = scan.nextLine();
                if (in.length() == 1) {
                    i = Integer.parseInt(in);
                    if (i < 4 && i > 0) {
                        break;
                    }
                }
            }
            switch (i) {
                case 1:
                    System.out.println("\n----------LOGIN----------");
                    while (!login.loginModule()) {
                    }
                    signup.Username(login.getUsername());
                    break;
                case 2:
                    System.out.println("\n----------Sign Up----------");
                    signup.signUp();
                     {
                    }
                    System.out.println("\n----------LOGIN----------");
                    while (!login.loginModule()) {
                    }
                     signup.Username(login.getUsername());
                    break ;
                case 3:
                    if(login.getUsername()!=null){
                   System.out.println("\n***********************************************************************");
                    System.out.println("Thank You << " + login.getUsername().toUpperCase(Locale.ITALY) + " >> for using Smart Auction System !! ");
                    }
                    else{
                    System.out.println("Thank You for using Smart Auction System !! ");
                     }
                    System.out.println("End : " + signup.getDate() );
                    System.out.println("Please Come Again! : )  ");
                    runProgram = false;
                    System.exit(0);
                    break;
            }
            
            AuctionType auc = new AuctionType(login.getUsername());
            int in;
            test:  //display personal details 
            do{
            System.out.print("\nWould you like to edit your personal details ?\n[1]Yes\n[2]No,Go to Main Menu\n[3]Remove myself from the auction system\nChoose:");
            String inn = scan.nextLine();
           
            switch (inn) {
                case "1":
                    signup.displayDetails();
                    signup.edit_personal_details();
                    signup.displayDetails();
                    editPersonalDetails=true;
                case "2":
                    do {
                        System.out.println("\n========== MENU OF <MyChoice> AUCTION FINANCIAL SYSTEM ==========");
                        System.out.print("Would you like to start the auction or view Report?\n[1]Start Auction\n[2]View Report\n[3]Exit\nChoose:");
                        String get = scan.nextLine();
                      //  String hold = get.charAt(0) + "";
                        if (get.equals("1")) {
                            key = true;
                            auc.AuctionType();
                        } else if (get.equals("2")) {
                            key = true;
                            auc.report();
                        } else if (get.equals("3")) {
                            break;
                        } else {
                            key = false;
                        }
                    } while (key = true);
                    editPersonalDetails=true;
                    break;
                case "3":
                    signup.deleteUser();
                    break BackToAuction;
                default:
                    System.out.println("=== INVALID INPUT ===");
                    break test;
            }

        }while(editPersonalDetails!=true);
    }
}
}

//menu
//edit details 
//choose auction
//reports
