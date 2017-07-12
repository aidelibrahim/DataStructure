package auction_2016;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/*
1.This is a module that handles the signing up of new accounts where each username that exists has no repition as the username is the most important key used to bind all the user data.
2.It will prompt user to enter their desired username where it will be checked to make sure username is not used in prior account sign ups.
3.Password confirmation will be performed to ensure new users are entering it correctly . When both conditions are true = account will be created .

constructor is <class>SignUpModule</class> are:
<constructor>SignUpModule</constructor> ----    empty constructor used to create an object
Among the modules that exists in <class>SignUpModule</class> are:
    <method>databaseCheck()</method>            void    ----    check whether userdatabase.txt exists or not else call <method>databaseCreate()</method> to create one
    <method>databaseCreate()</method>           void    ----    create userdatabase.txt
    <method>checkDatabaseLines()</method>       int     ----    returns the number of lines that exists in userdatabase.txt
    <method>getUsername()</method>              void    ----    prompt the user to enter their desired username
    <method>checkExistingUsername()</method>    void    ----    check whether the entered username is used before else prompt user to reenter another username
    <method>checkPasswordInput()</method>       void    ----    prompt user to enter their password and confirm it before their account is created
    <method>checkSignUpStatus()</method>        boolean ----    returns whether the username and password entered are legit and unique
    <method>addUseAccount()</method>            void    ----    adds the new username and password to D:/NameList.txt
    <method>signup()</method>                   void    ----    collection of methods used to create a new user account 



 */
public class SignUpModule <E> {

    private String newUserName;
    private String newPassword;
    private String IC;
    private String BankCard;
    private String AccountNumber;
    private String E_mail;
    private String phoneNumber;
    private String receive_update;
    private String username;
    private boolean newUsernameAuthentication = true;
    private boolean newPasswordAuthentication = false;
    private boolean newAccountAuthentication = false;
    Scanner input = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);

      LoginModule login = new LoginModule();
    //empty constructor
   public SignUpModule  () {
        
    }

    //check whether the database file exists else create one 
    public void databaseCheck() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream("NameList.txt"));
            inputstream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
            databaseCreate();
        }
    }

    //create the database
    public void databaseCreate() {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream("NameList.txt"));
            createDatabase.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //check and return how many lines there is in the NameList.txt
    public int checkDatabaseLines() {
        int i = 0;
        try {
            Scanner inputstream = new Scanner(new FileInputStream("NameList.txt"));
            while (inputstream.hasNextLine()) {
                inputstream.nextLine();
                i++;
            }
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        return i;
    }

    //prompt user to input their username for the new account
    private void getUserName() {
        System.out.println("Please enter your name for your new Account: ");
        newUserName = input.nextLine();
    }

    //check existing username in the database to ensure there is no overlapping else enter a new name
    public void checkExistingUserName() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream("NameList.txt"));
            int i = 0;
            String[] usernameCheck = new String[checkDatabaseLines()];
            while (inputstream.hasNextLine()) {
                //get a line in the database
                String check = inputstream.nextLine();
                //split the file
                for (String ret : check.split(",")) {
                    usernameCheck[i] = ret;
                    if (newUserName.equals(usernameCheck[i])) {
                        System.out.println("Username has been used . Please try again with another username: ");
                        newUserName = input.nextLine();
                        checkExistingUserName();
                    }
                    break;
                }
            }
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //prompt user to enter and recomfirm their password before accoount is created
    public void checkPasswordInput() {
        while (!newPasswordAuthentication) {
            System.out.println("Please enter your password : ");
            String password1 = input.nextLine();
            System.out.println("Re-enter your password : ");
            String password2 = input.nextLine();
            if (password1.equals(password2)) {
                newPassword = password1;
                newPasswordAuthentication = true;
                break;
            }
            else{
                System.out.println("Password entered not match .Please try again !");
            }
        }
    }

    //return whether the new username and password are created succesfully
    public boolean checkSignUpStatus() {
        if (newUsernameAuthentication && newPasswordAuthentication) {
            newAccountAuthentication = true;
        }
        return newAccountAuthentication;
    }

    //add in new details
    public void addIC() {
        Scanner inputt=new Scanner(System.in);
        System.out.println("Please enter your IC number: ");
        IC = inputt.nextLine();
    }

    public void addBankCardType() {
        Scanner getLine = new Scanner(System.in);
        System.out.print("Please enter your Bank Card Type:\n[1]Visa Debit Card\n[2]Visa Credit Card\n[3]Master Card\n[4]Visa Electron\n[5]Others\nChoose: ");
        int bank = input.nextInt();
        switch (bank) {
            case 1:
                BankCard = "Visa Debit Card";
                break;
            case 2:
                BankCard = "Visa Credit Card";
                break;
            case 3:
                BankCard = "Master Card";
                break;
            case 4:
                BankCard = "Visa Electron Card";
                break;
            case 5:
                System.out.print("Please specify :\nEnter:");
                String input = getLine.nextLine();
                BankCard=input;
                break;
            default:
                addBankCardType();
                break;
        }
        System.out.print("Please enter your Visa number or further details: ");
        AccountNumber = input2.nextLine();
    }

    public void phoneNumber() {
        System.out.print("Please enter your Contact Number: ");
        phoneNumber = input2.nextLine();
    }

    public void E_Mail() {
        System.out.print("Please enter your e-mail: ");
        E_mail = input2.nextLine();
        System.out.print("Would you like you receive our latest update?\n[1]Yes\n[2]No\nChoose:");
        int choose = input.nextInt();
        switch (choose) {
            case 1:
                receive_update = "Yes";
                break;
            case 2:
                receive_update = "No";
                break;
            default:
                E_Mail();
                break;
        }
    }

    public void addUserAccount() {
        try {
            PrintWriter inputstream = new PrintWriter(new FileOutputStream("NameList.txt", true));
            inputstream.println(newUserName + "," + newPassword + "," + IC + "," + BankCard + "," + AccountNumber + "," + phoneNumber + "," + E_mail + "," + receive_update);
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //prompt the user to edit personal details (use arrayList)
    public void edit_personal_details() {
        //declare array for username , password , IC , Bank_Card_Type , visa_number , Phone_Number , e-mail   
        ArrayList<E> username1 = new ArrayList<>();
        ArrayList<E> password = new ArrayList<>();
        ArrayList<E> IC1 = new ArrayList<>();
        ArrayList<E> Bank_Card_Type = new ArrayList<>();
        ArrayList<E> visa_number = new ArrayList<>();
        ArrayList<E> Phone_Number = new ArrayList<>();
        ArrayList<E> email = new ArrayList<>();
        ArrayList<E> receive = new ArrayList<>();
        int holdd = 0;
        try {
            Scanner scann = new Scanner(new FileInputStream("NameList.txt"));
            int location = 0;
            String[] getSpecificLine = new String[10];
            while (scann.hasNextLine()) {
                int count1 = 0;
                //Get a line in the database
                String check = scann.nextLine();
                //Split the file
                for (String ret : check.split(",")) {
                    getSpecificLine[count1] = ret;
                    count1++;
                }
                //catagorized data
                username1.add((E) getSpecificLine[0]);
                password.add((E) getSpecificLine[1]);
                IC1.add((E) getSpecificLine[2]);
                Bank_Card_Type.add((E) getSpecificLine[3]);
                visa_number.add((E) getSpecificLine[4]);
                Phone_Number.add((E) getSpecificLine[5]);
                email.add((E) getSpecificLine[6]);
                receive.add((E) getSpecificLine[7]);
            }
            scann.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //check the username lies in which line so that only the specific line is taken out by matching up with the username
       for(int i=0; i<username1.size();i++){
       if(username.equals(username1.get(holdd))){
           break;
           }
           holdd++;
       }
       
        //let the user choose which to delete [int]   
        System.out.print("\nWhich information would you like to edit :\n[1]I/C\n[2]Bank Card Information \n[3]Contact Number\n[4]E-mail\nChoose: ");
        int edit = input.nextInt();
        switch (edit) {
            case 1:
                addIC();
                IC1.remove(holdd);
                IC1.add(holdd, (E) IC);
                break;
            case 2:
                addBankCardType();
                Bank_Card_Type.remove(holdd);
                Bank_Card_Type.add(holdd, (E) BankCard);
                visa_number.remove(holdd);
                visa_number.add(holdd, (E) AccountNumber);
                break;
            case 3:
                phoneNumber();
                Phone_Number.remove(holdd);
                Phone_Number.add(holdd, (E) phoneNumber);
                break;
            case 4:
                E_Mail();
                email.remove(holdd);
                email.add(holdd, (E) E_mail);
                receive.remove(holdd);
                receive.add(holdd, (E) receive_update);
                break;
            default:
                edit_personal_details();
                break;
        }
        //trasfer data into NameList.txt
        try {
            PrintWriter inputstream = new PrintWriter(new FileOutputStream("NameList.txt"));
            for(int i =0 ; i<username1.size() ; i++){
            inputstream.println(username1.get(i) + "," + password.get(i) + "," + IC1.get(i) + "," + Bank_Card_Type.get(i) + "," + visa_number.get(i) + "," + Phone_Number.get(i) + "," + email.get(i) + "," + receive.get(i));
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Your Information has been updated successfully!");
  }
//display user's details
    public void displayDetails(){
          String[] username1 = new String[checkDatabaseLines()];
            String[] password1 = new String[checkDatabaseLines()];
            String[] IC1 = new String[checkDatabaseLines()];
            String[] cardType1 = new String[checkDatabaseLines()];
            String[] cardNumber1 = new String[checkDatabaseLines()];
            String[] phoneNumber1 = new String[checkDatabaseLines()];
            String[] e_mail1 = new String[checkDatabaseLines()];
            String[] receive1 = new String[checkDatabaseLines()];
            String[] store = new String[8];
            int counter2=0;
         try {
            Scanner inputstream = new Scanner(new FileInputStream("NameList.txt"));
             while (inputstream.hasNextLine()) {
                 int counter1=0;
                //get a line in the database, 
                String check = inputstream.nextLine();
                //split the file and get the specific line that belongs to the respective user
                for (String ret : check.split(",")) {
                    username1[counter2] = ret;
                     if ((username1[counter2]).equals(username)) {
                        for (String rett : check.split(",")) {
                        store[counter1] = rett;
                        counter1++;}
                        username1[0]=store[0];
                        password1[0]=store[1];
                        IC1[0]=store[2];
                        cardType1[0]=store[3];
                        cardNumber1[0]=store[4];
                        phoneNumber1[0]=store[5];
                        e_mail1[0]=store[6];
                        receive1[0]=store[7];
                    }
                    counter2 ++;
                    break;
                }
             }
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
         //display in words
         System.out.println("============== Your personal details ==============");
         System.out.println("Name                                 :"+username1[0]);
         System.out.println("I/C                                  :"+IC1[0]);
         System.out.println("Card Type                            :"+cardType1[0]);
         System.out.println("Card Number                          :"+cardNumber1[0]);
         System.out.println("Phone Number                         :"+phoneNumber1[0]);
         System.out.println("E-Mail                               :"+e_mail1[0]);
         System.out.println("Status of receiving latest information:"+receive1[0]);
         System.out.println("===================================================");
    }
   public String Username(String username){
       this.username = username;
       return this.username;
   }
   //delete user from the Auction system
   public void deleteUser(){
        //declare array for username , password , IC , Bank_Card_Type , visa_number , Phone_Number , e-mail   
        ArrayList<E> username1 = new ArrayList<>();
        ArrayList<E> password = new ArrayList<>();
        ArrayList<E> IC1 = new ArrayList<>();
        ArrayList<E> Bank_Card_Type = new ArrayList<>();
        ArrayList<E> visa_number = new ArrayList<>();
        ArrayList<E> Phone_Number = new ArrayList<>();
        ArrayList<E> email = new ArrayList<>();
        ArrayList<E> receive = new ArrayList<>();
        int holdd = 0;
        //clear any previous data
        username1.clear();password.clear();IC1.clear();Bank_Card_Type.clear();visa_number.clear();Phone_Number.clear();email.clear();receive.clear();
        try {
            Scanner scann = new Scanner(new FileInputStream("NameList.txt"));
            int location = 0;
            String[] getSpecificLine = new String[10];
            while (scann.hasNextLine()) {
                int count1 = 0;
                //Get a line in the database
                String check = scann.nextLine();
                //Split the file
                for (String ret : check.split(",")) {
                    getSpecificLine[count1] = ret;
                    count1++;
                }
                //catagorized data
                username1.add((E) getSpecificLine[0]);
                password.add((E) getSpecificLine[1]);
                IC1.add((E) getSpecificLine[2]);
                Bank_Card_Type.add((E) getSpecificLine[3]);
                visa_number.add((E) getSpecificLine[4]);
                Phone_Number.add((E) getSpecificLine[5]);
                email.add((E) getSpecificLine[6]);
                receive.add((E) getSpecificLine[7]);
            }
            scann.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //match the username with the line in the arraylist 
        for(int i =0 ; i<username1.size() ; i++){
            if(username1.get(i).equals(username)){
                username1.remove(i); //remove the specific line from the data
                password.remove(i);
                IC1.remove(i);
                Bank_Card_Type.remove(i);
                visa_number.remove(i);
                Phone_Number.remove(i);
                email.remove(i);
                receive.remove(i);
                break;
            }
        }
        //transfer the new data into NameList.txt
              try {
            PrintWriter inputstream = new PrintWriter(new FileOutputStream("NameList.txt"));
            for(int i =0 ; i<username1.size() ; i++){
            inputstream.println(username1.get(i) + "," + password.get(i) + "," + IC1.get(i) + "," + Bank_Card_Type.get(i) + "," + visa_number.get(i) + "," + Phone_Number.get(i) + "," + email.get(i) + "," + receive.get(i));
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=== Your account has been removed successfully from <<MyChoice>> Auction System! ===");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Have a Nice Day ! :) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }
    //display Inspiration quote
     public String Inspiration_Quotes(){
         Random rand= new Random();
         LinkedList <String> IQ= new LinkedList<String>();
         try {
            Scanner scann = new Scanner(new FileInputStream("InspirationQuotes.txt"));
             int location = 0;
            String[] getSpecificLine = new String[10];
            while (scann.hasNextLine()) {
                //Get a line in the database
                String check = scann.nextLine();
                IQ.add(check);
     }
               scann.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            int getRandom=rand.nextInt(IQ.size()-1);
            //return a line of quote
            return IQ.get(getRandom);
     }
   
   //get current date
    public String getDate() {
        //call out date
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }
    
    //adds the new username and password into NameList.txt
    //collecting all the methods used to create new account
    public void signUp() {
        databaseCheck();
        System.out.println("********** Accout Sign Up **********");
        getUserName();
        checkExistingUserName();
        checkPasswordInput();
        addIC();
        addBankCardType();
        phoneNumber();
        E_Mail();
        if (checkSignUpStatus()) {
            addUserAccount();
            System.out.println("\n===== Your account has been created Successfully!! =====");
            System.out.println("   ===You may enjoy and Log into your account now! ===\n");
        }
    }
}
