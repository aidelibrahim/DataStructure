package auction_2016;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Reports extends AuctionType {

    private String username;
    private int hold = 0;
    private int count = 0;
    //first declare for specific categories by using LinkedList method .*NL=NameList*
    LinkedList<String> usernameNL = new LinkedList<String>();
    LinkedList<String> passwordNL = new LinkedList<String>();
    LinkedList<String> ICNL = new LinkedList<String>();
    LinkedList<String> BankCardTypeNL = new LinkedList<String>();
    LinkedList<String> VisaNumberNL = new LinkedList<String>();
    LinkedList<String> PhoneNumberNL = new LinkedList<String>();
    LinkedList<String> emailNL = new LinkedList<String>();
    LinkedList<String> receiveNL = new LinkedList<String>();
    //first declare for specific categories by using LinkedList method . *AT=AuctionType*
    LinkedList<String> categoriesAT = new LinkedList<String>();
    LinkedList<String> itemAT = new LinkedList<String>();
    LinkedList<String> itemIDAT = new LinkedList<String>();
    LinkedList<String> seller_nameAT = new LinkedList<String>();
    LinkedList<String> identityAT = new LinkedList<String>();
    LinkedList<String> reg_timeAT = new LinkedList<String>();
    LinkedList<String> start_timeAT = new LinkedList<String>();
    LinkedList<String> end_timeAT = new LinkedList<String>();
    LinkedList<String> minimum_priceAT = new LinkedList<String>();
    LinkedList<String> placeAT = new LinkedList<String>();
    //declare the five Auction Type
    private final String english_auction = "English Auction";
    private final String japanese_auction = "Japanese Auction";
    private final String blind_auction = "Blind Auction";
    private final String vickrey_auction = "Vickrey Auction";
    private final String reserve_auction = "Reserve Auction";
    private String AuctionType;
    private String cat;
    //declare for specific categories by using arrayList method   .*US=Username*
    ArrayList<String> categoriesUS = new ArrayList<String>();
    ArrayList<String> itemUS = new ArrayList<String>();
    ArrayList<String> itemIDUS = new ArrayList<String>();
    ArrayList<String> identityUS = new ArrayList<String>();
    ArrayList<String> reg_timeUS = new ArrayList<String>();
    ArrayList<String> end_timeUS = new ArrayList<String>();
    ArrayList<String> minimumUS = new ArrayList<String>();
    ArrayList<String> aucTypeUS = new ArrayList<String>();
    ArrayList<String> statusUS = new ArrayList<String>();
    //declare new ArrayList for specific categories 
    ArrayList<String> categoriesWI = new ArrayList<String>();   //*WI=Winning Item*
    ArrayList<String> itemWI = new ArrayList<String>();
    ArrayList<String> itemIDWI = new ArrayList<String>();
    ArrayList<String> identityWI = new ArrayList<String>();
    ArrayList<String> reg_timeWI = new ArrayList<String>();
    ArrayList<String> end_timeWI = new ArrayList<String>();
    ArrayList<String> minimumWI = new ArrayList<String>();
    ArrayList<String> aucTypeWI = new ArrayList<String>();
    ArrayList<String> statusWI = new ArrayList<String>();
    ArrayList<Integer> frequencyWI = new ArrayList<Integer>();
    ArrayList<String> biddingStatusWI = new ArrayList<String>();
    //count for the bidding frequency
    int BiddingFrequency = 0;
    //store value from itemID_item_aucType
    LinkedList<String> name = new LinkedList<String>();
    LinkedList<String> date = new LinkedList<String>();
    LinkedList<String> Bid = new LinkedList<String>();

//constructor
    public Reports(String username) {
        super(username);
        this.username = username;
    }

    //transfer namelist.txt into respective categories
    //return the location of username in the namelist.txt
    public int transfer_nameList() {
        String[] getSpecificLine = new String[10];
        try {
            Scanner scann = new Scanner(new FileInputStream("NameList.txt"));
            int location = 0;
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
                usernameNL.addLast(getSpecificLine[0]);
                passwordNL.addLast(getSpecificLine[1]);
                ICNL.addLast(getSpecificLine[2]);
                BankCardTypeNL.addLast(getSpecificLine[3]);
                VisaNumberNL.addLast(getSpecificLine[4]);
                PhoneNumberNL.addLast(getSpecificLine[5]);
                emailNL.addLast(getSpecificLine[6]);
                receiveNL.addLast(getSpecificLine[7]);
            }
            scann.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //check the username lies in which line so that only the specific line is taken out by matching up with the username
        for (int i = 0; i < usernameNL.size(); i++) {
            if (username.equals(usernameNL.get(hold))) {
                break;
            }
            hold++;
        }
        return hold;
    }

    //display personal details by using recursion *PD=Personal details*
    public void displayPD(int hold) {
        System.out.println("========== PERSONAL DETAILS ==========");
        System.out.println("Name                    : " + usernameNL.get(hold));
        System.out.println("Password                : " + passwordNL.get(hold));
        System.out.println("Identity Card Number    : " + ICNL.get(hold));
        System.out.println("Type of Bank Card       : " + BankCardTypeNL.get(hold));
        System.out.println("Visa Number             : " + VisaNumberNL.get(hold));
        System.out.println("Phone Number            : " + PhoneNumberNL.get(hold));
        System.out.println("E-mail                  : " + emailNL.get(hold));
        System.out.println("Status of receiving latest update : " + receiveNL.get(hold));
    }

    //transfer Auction Type.txt into respective categories
    public void transferAucType() {
        String[] store = new String[10];
        try {
            Scanner scannn = new Scanner(new FileInputStream(AuctionType + ".txt"));
            while (scannn.hasNextLine()) {
                int count1 = 0;
                //Get a line in the database
                String check = scannn.nextLine();
                //Split the file
                for (String ret : check.split(",")) {
                    store[count1] = ret;
                    count1++;
                }
                //catagorized data // by using push(stack)
                if (AuctionType.equals(blind_auction) || AuctionType.equals(vickrey_auction)) {
                    categoriesAT.push(store[0]);
                    itemAT.push(store[1]);
                    itemIDAT.push(store[2]);
                    seller_nameAT.push(store[3]);
                    identityAT.push(store[4]);
                    reg_timeAT.push(store[5]);
                    start_timeAT.push(store[6]);
                    end_timeAT.push(store[7]);
                    minimum_priceAT.push(store[8]);
                    placeAT.push(store[9]);
                } else {
                    categoriesAT.push(store[0]);
                    itemAT.push(store[1]);
                    itemIDAT.push(store[2]);
                    seller_nameAT.push(store[3]);
                    identityAT.push(store[4]);
                    reg_timeAT.push(store[5]);
                    start_timeAT.push(store[6]);
                    end_timeAT.push(store[7]);
                    minimum_priceAT.push(store[8]);
                }
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // for start time ascending order in aucType.txt
    public void startTimeAscending() {
        categoriesAT.clear();
        itemAT.clear();
        itemIDAT.clear();
        seller_nameAT.clear();
        identityAT.clear();
        reg_timeAT.clear();
        start_timeAT.clear();
        end_timeAT.clear();
        minimum_priceAT.clear();
        placeAT.clear();
        transferAucType();
        for (int i = 0; i < start_timeAT.size(); i++) {
            for (int j = 0; j < start_timeAT.size() - 1; j++) {
                if (start_timeAT.get(j).compareTo(start_timeAT.get(j + 1)) > 0) {
                    String h = categoriesAT.get(j);
                    String ho = itemAT.get(j);
                    String hol = itemIDAT.get(j);
                    String hold = seller_nameAT.get(j);
                    String holdd = identityAT.get(j);
                    String holddd = reg_timeAT.get(j);
                    String holdddd = start_timeAT.get(j);
                    String holddddd = end_timeAT.get(j);
                    String holdddddd = minimum_priceAT.get(j);
                    categoriesAT.set(j, categoriesAT.get(j + 1));
                    itemAT.set(j, itemAT.get(j + 1));
                    itemIDAT.set(j, itemIDAT.get(j + 1));
                    seller_nameAT.set(j, seller_nameAT.get(j + 1));
                    identityAT.set(j, identityAT.get(j + 1));
                    reg_timeAT.set(j, reg_timeAT.get(j + 1));
                    start_timeAT.set(j, start_timeAT.get(j + 1));
                    end_timeAT.set(j, end_timeAT.get(j + 1));
                    minimum_priceAT.set(j, minimum_priceAT.get(j + 1));
                    categoriesAT.set(j + 1, h);
                    itemAT.set((j + 1), ho);
                    itemIDAT.set(j + 1, hol);
                    seller_nameAT.set(j + 1, hold);
                    identityAT.set(j + 1, holdd);
                    reg_timeAT.set(j + 1, holddd);
                    start_timeAT.set(j + 1, holdddd);
                    end_timeAT.set(j + 1, holddddd);
                    minimum_priceAT.set(j + 1, holdddddd);
                    if (AuctionType.equals(blind_auction) || AuctionType.equals(vickrey_auction)) {
                        String holds = placeAT.get(j);
                        placeAT.set(j, placeAT.get(j + 1));
                        placeAT.set((j + 1), holds);
                    }
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            if (AuctionType.equals(japanese_auction) || (AuctionType.equals(english_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + minimum_priceAT.get(j));
                }
            } else if (AuctionType.equals(blind_auction) || (AuctionType.equals(vickrey_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + placeAT.get(j));
                }
            } else {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j));

                }

            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    // for start time descending order in aucType.txt
    public void startTimeDescending() {
        startTimeAscending();
        LinkedList<String> temp2 = new LinkedList<String>();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream(AuctionType + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            for (int j = 0; j < categoriesAT.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    // for end time ascending order in aucType.txt
    public void endTimeAscending() {
        categoriesAT.clear();
        itemAT.clear();
        itemIDAT.clear();
        seller_nameAT.clear();
        identityAT.clear();
        reg_timeAT.clear();
        start_timeAT.clear();
        end_timeAT.clear();
        minimum_priceAT.clear();
        placeAT.clear();
        transferAucType();
        for (int i = 0; i < end_timeAT.size(); i++) {
            for (int j = 0; j < end_timeAT.size() - 1; j++) {
                if (end_timeAT.get(j).compareTo(end_timeAT.get(j + 1)) > 0) {
                    String h = categoriesAT.get(j);
                    String ho = itemAT.get(j);
                    String hol = itemIDAT.get(j);
                    String hold = seller_nameAT.get(j);
                    String holdd = identityAT.get(j);
                    String holddd = reg_timeAT.get(j);
                    String holdddd = start_timeAT.get(j);
                    String holddddd = end_timeAT.get(j);
                    String holdddddd = minimum_priceAT.get(j);
                    categoriesAT.set(j, categoriesAT.get(j + 1));
                    itemAT.set(j, itemAT.get(j + 1));
                    itemIDAT.set(j, itemIDAT.get(j + 1));
                    seller_nameAT.set(j, seller_nameAT.get(j + 1));
                    identityAT.set(j, identityAT.get(j + 1));
                    reg_timeAT.set(j, reg_timeAT.get(j + 1));
                    start_timeAT.set(j, start_timeAT.get(j + 1));
                    end_timeAT.set(j, end_timeAT.get(j + 1));
                    minimum_priceAT.set(j, minimum_priceAT.get(j + 1));
                    categoriesAT.set(j + 1, h);
                    itemAT.set((j + 1), ho);
                    itemIDAT.set(j + 1, hol);
                    seller_nameAT.set(j + 1, hold);
                    identityAT.set(j + 1, holdd);
                    reg_timeAT.set(j + 1, holddd);
                    start_timeAT.set(j + 1, holdddd);
                    end_timeAT.set(j + 1, holddddd);
                    minimum_priceAT.set(j + 1, holdddddd);
                    if (AuctionType.equals(blind_auction) || AuctionType.equals(vickrey_auction)) {
                        String holds = placeAT.get(j);
                        placeAT.set(j, placeAT.get(j + 1));
                        placeAT.set((j + 1), holds);
                    }
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            if (AuctionType.equals(japanese_auction) || (AuctionType.equals(english_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + minimum_priceAT.get(j));
                }
            } else if (AuctionType.equals(blind_auction) || (AuctionType.equals(vickrey_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + placeAT.get(j));
                }
            } else {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j));

                }

            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    // for end time descending order in aucType.txt
    public void endTimeDescending() {
        endTimeAscending();
        LinkedList<String> temp2 = new LinkedList<String>();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream(AuctionType + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            for (int j = 0; j < categoriesAT.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    // for item ID ascending order in aucType.txt
    public void itemIDAscending() {
        categoriesAT.clear();
        itemAT.clear();
        itemIDAT.clear();
        seller_nameAT.clear();
        identityAT.clear();
        reg_timeAT.clear();
        start_timeAT.clear();
        end_timeAT.clear();
        minimum_priceAT.clear();
        placeAT.clear();
        transferAucType();
        for (int i = 0; i < itemIDAT.size(); i++) {
            for (int j = 0; j < itemIDAT.size() - 1; j++) {
                if (itemIDAT.get(j).compareTo(itemIDAT.get(j + 1)) > 0) {
                    String h = categoriesAT.get(j);
                    String ho = itemAT.get(j);
                    String hol = itemIDAT.get(j);
                    String hold = seller_nameAT.get(j);
                    String holdd = identityAT.get(j);
                    String holddd = reg_timeAT.get(j);
                    String holdddd = start_timeAT.get(j);
                    String holddddd = end_timeAT.get(j);
                    String holdddddd = minimum_priceAT.get(j);
                    categoriesAT.set(j, categoriesAT.get(j + 1));
                    itemAT.set(j, itemAT.get(j + 1));
                    itemIDAT.set(j, itemIDAT.get(j + 1));
                    seller_nameAT.set(j, seller_nameAT.get(j + 1));
                    identityAT.set(j, identityAT.get(j + 1));
                    reg_timeAT.set(j, reg_timeAT.get(j + 1));
                    start_timeAT.set(j, start_timeAT.get(j + 1));
                    end_timeAT.set(j, end_timeAT.get(j + 1));
                    minimum_priceAT.set(j, minimum_priceAT.get(j + 1));
                    categoriesAT.set(j + 1, h);
                    itemAT.set((j + 1), ho);
                    itemIDAT.set(j + 1, hol);
                    seller_nameAT.set(j + 1, hold);
                    identityAT.set(j + 1, holdd);
                    reg_timeAT.set(j + 1, holddd);
                    start_timeAT.set(j + 1, holdddd);
                    end_timeAT.set(j + 1, holddddd);
                    minimum_priceAT.set(j + 1, holdddddd);
                    if (AuctionType.equals(blind_auction) || AuctionType.equals(vickrey_auction)) {
                        String holds = placeAT.get(j);
                        placeAT.set(j, placeAT.get(j + 1));
                        placeAT.set((j + 1), holds);
                    }
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            if (AuctionType.equals(japanese_auction) || (AuctionType.equals(english_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + minimum_priceAT.get(j));
                }
            } else if (AuctionType.equals(blind_auction) || (AuctionType.equals(vickrey_auction))) {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j) + "," + placeAT.get(j));
                }
            } else {
                for (int j = 0; j < start_timeAT.size(); j++) {
                    write.println(categoriesAT.get(j) + "," + itemAT.get(j) + "," + itemIDAT.get(j) + "," + seller_nameAT.get(j) + "," + identityAT.get(j) + "," + reg_timeAT.get(j) + "," + start_timeAT.get(j) + "," + end_timeAT.get(j));
                }
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    // for item ID descending order in aucType.txt
    public void itemIDDescending() {
        itemIDAscending();
        LinkedList<String> temp2 = new LinkedList<String>();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream(AuctionType + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(AuctionType + ".txt"));
            for (int j = 0; j < categoriesAT.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //display the records 
    public void displayAucType() {
        int i = 0;
        try {
            Scanner inputstream = new Scanner(new FileInputStream(AuctionType + ".txt"));
            i = 1;
            String a;
            if (AuctionType.equals(japanese_auction) || (AuctionType.equals(english_auction))) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\tCategories\t\t\tItem\t\t\t   Item ID\t\t\t\tSeller's Name\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]\t\tMinumum Price[RM]");
                System.out.println("...................................................................................................................................................................................................................................................................................................");

            } else if (AuctionType.equals(blind_auction) || (AuctionType.equals(vickrey_auction))) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\tCategories\t\t\tItem\t\t\t  Item ID\t\t\tSeller's Name\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]\t\tVenue");
                System.out.println("...................................................................................................................................................................................................................................................................................................");

            } else {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\tCategories\t\t\tItem\t\t\t  Item ID\t\t\tSeller's Name\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]");
                System.out.println("...................................................................................................................................................................................................................................................................................................");

            }
            while (inputstream.hasNextLine()) {
                //get a line in the database
                String check = inputstream.nextLine();
                System.out.print(i + ".\t");
                //split the file
                for (String ret : check.split(",")) {
                    String hold = ret;
                    if (hold.length() < 8) {
                        System.out.print(hold + "\t\t\t\t");
                    } else if (hold.length() < 16) {
                        System.out.print(hold + "\t\t\t");
                    } else if (hold.length() < 24) {
                        System.out.print(hold + "\t\t");
                    } else if (hold.length() < 32) {
                        System.out.print(hold + "\t");
                    }
                }
                System.out.println(" ");
                i++;
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //transfer username.txt in username.txt
    public void transferUsername() {
        categoriesUS.clear();
        itemUS.clear();
        itemIDUS.clear();
        identityUS.clear();
        reg_timeUS.clear();
        end_timeUS.clear();
        minimumUS.clear();
        aucTypeUS.clear();
        statusUS.clear();
        String[] store = new String[10];
        try {
            Scanner scannn = new Scanner(new FileInputStream("Username/"+username + ".txt"));
            while (scannn.hasNextLine()) {
                int count1 = 0;
                //Get a line in the database
                String check = scannn.nextLine();
                //Split the file
                for (String ret : check.split(",")) {
                    store[count1] = ret;
                    count1++;
                }
                categoriesUS.add(store[0]);
                itemUS.add(store[1]);
                itemIDUS.add(store[2]);
                identityUS.add(store[3]);
                reg_timeUS.add(store[4]);
                end_timeUS.add(store[5]);
                minimumUS.add(store[6]);
                aucTypeUS.add(store[7]);
                statusUS.add(store[8]);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //ascending order for end time username.txt
    public void ascEnd_time() {
        categoriesUS.clear();
        itemUS.clear();
        itemIDUS.clear();
        identityUS.clear();
        reg_timeUS.clear();
        end_timeUS.clear();
        minimumUS.clear();
        aucTypeUS.clear();
        statusUS.clear();
        transferUsername();
        for (int i = 0; i < end_timeUS.size(); i++) {
            for (int j = 0; j < end_timeUS.size() - 1; j++) {
                if (end_timeUS.get(j).compareTo(end_timeUS.get(j + 1)) > 0) {
                    String h1 = categoriesUS.get(j);
                    String h2 = itemUS.get(j);
                    String h3 = itemIDUS.get(j);
                    String h4 = identityUS.get(j);
                    String h5 = reg_timeUS.get(j);
                    String h6 = end_timeUS.get(j);
                    String h7 = minimumUS.get(j);
                    String h8 = aucTypeUS.get(j);
                    String h9 = statusUS.get(j);
                    categoriesUS.set(j, categoriesUS.get(j + 1));
                    itemUS.set(j, itemUS.get(j + 1));
                    itemIDUS.set(j, itemIDUS.get(j + 1));
                    identityUS.set(j, identityUS.get(j + 1));
                    reg_timeUS.set(j, reg_timeUS.get(j + 1));
                    end_timeUS.set(j, end_timeUS.get(j + 1));
                    minimumUS.set(j, minimumUS.get(j + 1));
                    aucTypeUS.set(j, aucTypeUS.get(j + 1));
                    statusUS.set(j, statusUS.get(j + 1));
                    categoriesUS.set(j + 1, h1);
                    itemUS.set((j + 1), h2);
                    itemIDUS.set(j + 1, h3);
                    identityUS.set(j + 1, h4);
                    reg_timeUS.set(j + 1, h5);
                    end_timeUS.set(j + 1, h6);
                    minimumUS.set(j + 1, h7);
                    aucTypeUS.set(j + 1, h8);
                    statusUS.set(j + 1, h9);
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(categoriesUS.get(j) + "," + itemUS.get(j) + "," + itemIDUS.get(j) + "," + identityUS.get(j) + "," + reg_timeUS.get(j) + "," + end_timeUS.get(j) + "," + minimumUS.get(j) + "," + aucTypeUS.get(j) + "," + statusUS.get(j));
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //dec order for end time username.txt
    public void decEnd_time() {
        ascEnd_time();
        //reverse the content of the file by using pop method
        LinkedList<String> temp = new LinkedList<String>();
        try {
            Scanner scannn = new Scanner(new FileInputStream("Username/"+username + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                System.out.println(check);
                temp.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(temp.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //asc for item id username.txt
    public void ascItem_ID() {
        categoriesUS.clear();
        itemUS.clear();
        itemIDUS.clear();
        identityUS.clear();
        reg_timeUS.clear();
        end_timeUS.clear();
        minimumUS.clear();
        aucTypeUS.clear();
        statusUS.clear();
        transferUsername();
        for (int i = 0; i < itemIDUS.size(); i++) {
            for (int j = 0; j < itemIDUS.size() - 1; j++) {
                if (itemIDUS.get(j).compareTo(itemIDUS.get(j + 1)) > 0) {
                    String h1 = categoriesUS.get(j);
                    String h2 = itemUS.get(j);
                    String h3 = itemIDUS.get(j);
                    String h4 = identityUS.get(j);
                    String h5 = reg_timeUS.get(j);
                    String h6 = end_timeUS.get(j);
                    String h7 = minimumUS.get(j);
                    String h8 = aucTypeUS.get(j);
                    String h9 = statusUS.get(j);
                    categoriesUS.set(j, categoriesUS.get(j + 1));
                    itemUS.set(j, itemUS.get(j + 1));
                    itemIDUS.set(j, itemIDUS.get(j + 1));
                    identityUS.set(j, identityUS.get(j + 1));
                    reg_timeUS.set(j, reg_timeUS.get(j + 1));
                    end_timeUS.set(j, end_timeUS.get(j + 1));
                    minimumUS.set(j, minimumUS.get(j + 1));
                    aucTypeUS.set(j, aucTypeUS.get(j + 1));
                    statusUS.set(j, statusUS.get(j + 1));
                    categoriesUS.set(j + 1, h1);
                    itemUS.set((j + 1), h2);
                    itemIDUS.set(j + 1, h3);
                    identityUS.set(j + 1, h4);
                    reg_timeUS.set(j + 1, h5);
                    end_timeUS.set(j + 1, h6);
                    minimumUS.set(j + 1, h7);
                    aucTypeUS.set(j + 1, h8);
                    statusUS.set(j + 1, h9);
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(categoriesUS.get(j) + "," + itemUS.get(j) + "," + itemIDUS.get(j) + "," + identityUS.get(j) + "," + reg_timeUS.get(j) + "," + end_timeUS.get(j) + "," + minimumUS.get(j) + "," + aucTypeUS.get(j) + "," + statusUS.get(j));
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //dec for item id username.txt
    public void decItem_ID() {
        ascItem_ID();
        LinkedList<String> temp2 = new LinkedList<String>();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream("Username/"+username + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //asc for auction type username.txt
    public void asc_auctype() {
        categoriesUS.clear();
        itemUS.clear();
        itemIDUS.clear();
        identityUS.clear();
        reg_timeUS.clear();
        end_timeUS.clear();
        minimumUS.clear();
        aucTypeUS.clear();
        statusUS.clear();
        transferUsername();
        for (int i = 0; i < aucTypeUS.size(); i++) {
            for (int j = 0; j < aucTypeUS.size() - 1; j++) {
                if (aucTypeUS.get(j).compareTo(aucTypeUS.get(j + 1)) > 0) {
                    String h1 = categoriesUS.get(j);
                    String h2 = itemUS.get(j);
                    String h3 = itemIDUS.get(j);
                    String h4 = identityUS.get(j);
                    String h5 = reg_timeUS.get(j);
                    String h6 = end_timeUS.get(j);
                    String h7 = minimumUS.get(j);
                    String h8 = aucTypeUS.get(j);
                    String h9 = statusUS.get(j);
                    categoriesUS.set(j, categoriesUS.get(j + 1));
                    itemUS.set(j, itemUS.get(j + 1));
                    itemIDUS.set(j, itemIDUS.get(j + 1));
                    identityUS.set(j, identityUS.get(j + 1));
                    reg_timeUS.set(j, reg_timeUS.get(j + 1));
                    end_timeUS.set(j, end_timeUS.get(j + 1));
                    minimumUS.set(j, minimumUS.get(j + 1));
                    aucTypeUS.set(j, aucTypeUS.get(j + 1));
                    statusUS.set(j, statusUS.get(j + 1));
                    categoriesUS.set(j + 1, h1);
                    itemUS.set((j + 1), h2);
                    itemIDUS.set(j + 1, h3);
                    identityUS.set(j + 1, h4);
                    reg_timeUS.set(j + 1, h5);
                    end_timeUS.set(j + 1, h6);
                    minimumUS.set(j + 1, h7);
                    aucTypeUS.set(j + 1, h8);
                    statusUS.set(j + 1, h9);
                }
            }
        }
        //write into the auction type.file
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(categoriesUS.get(j) + "," + itemUS.get(j) + "," + itemIDUS.get(j) + "," + identityUS.get(j) + "," + reg_timeUS.get(j) + "," + end_timeUS.get(j) + "," + minimumUS.get(j) + "," + aucTypeUS.get(j) + "," + statusUS.get(j));
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //dec for auction type username.txt
    public void dec_auctype() {
        asc_auctype();
        LinkedList<String> temp2 = new LinkedList<String>();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream("Username/"+username + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+username + ".txt"));
            for (int j = 0; j < categoriesUS.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //display all selling item in username.txt
    public void selling() {
        int k = 1;
        ArrayList<String> hold = new ArrayList<String>();
        transferUsername();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tCategories\t\t\tItem\t\t\t\tItem ID\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tEnd Time[D/M/Y H:M:S]\t\tPrice[RM]\t\t\tAuction Type\t\t\tStatus");
        System.out.println("...................................................................................................................................................................................................................................................................................................");

        for (int i = 0; i < identityUS.size(); i++) {
            if (identityUS.get(i).equalsIgnoreCase("seller")) {
                hold.add(categoriesUS.get(i) + "," + itemUS.get(i) + "," + itemIDUS.get(i) + "," + identityUS.get(i) + "," + reg_timeUS.get(i) + "," + end_timeUS.get(i) + "," + minimumUS.get(i) + "," + aucTypeUS.get(i) + "," + statusUS.get(i));
            }
        }
        for (int j = 0; j < hold.size(); j++) {
            System.out.print(k + ".\t");
            for (String ret : hold.get(j).split(",")) {
                String holdd = ret;
                if (holdd.length() < 8) {
                    System.out.print(holdd + "\t\t\t\t");
                } else if (holdd.length() < 16) {
                    System.out.print(holdd + "\t\t\t");
                } else if (holdd.length() < 24) {
                    System.out.print(holdd + "\t\t");
                } else if (holdd.length() < 32) {
                    System.out.print(holdd + "\t");
                }
            }
            System.out.println(" ");
            k++;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    //display all sold item in username.txt
    public void soldItem() {
        int k = 1;
        ArrayList<String> hold = new ArrayList<String>();
        transferUsername();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tCategories\t\t\tItem\t\t\t\tItem ID\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tEnd Time[D/M/Y H:M:S]\t\tPrice[RM]\t\t\tAuction Type\t\t\tStatus");
        System.out.println("...................................................................................................................................................................................................................................................................................................");

        for (int i = 0; i < identityUS.size(); i++) {
            if (identityUS.get(i).equalsIgnoreCase("seller") && !statusUS.get(i).equals("-")) {
                hold.add(categoriesUS.get(i) + "," + itemUS.get(i) + "," + itemIDUS.get(i) + "," + identityUS.get(i) + "," + reg_timeUS.get(i) + "," + end_timeUS.get(i) + "," + minimumUS.get(i) + "," + aucTypeUS.get(i) + "," + statusUS.get(i));
            }
        }
        for (int j = 0; j < hold.size(); j++) {
            System.out.print(k + ".\t");
            for (String ret : hold.get(j).split(",")) {
                String holdd = ret;
                if (holdd.length() < 8) {
                    System.out.print(holdd + "\t\t\t\t");
                } else if (holdd.length() < 16) {
                    System.out.print(holdd + "\t\t\t");
                } else if (holdd.length() < 24) {
                    System.out.print(holdd + "\t\t");
                } else if (holdd.length() < 32) {
                    System.out.print(holdd + "\t");
                }
            }
            System.out.println(" ");
            k++;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    //display unsold item
    public void unsoldItem() {
        int k = 1;
        ArrayList<String> hold = new ArrayList<String>();
        transferUsername();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tCategories\t\t\tItem\t\t\t\tItem ID\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tEnd Time[D/M/Y H:M:S]\t\tPrice[RM]\t\t\tAuction Type\t\t\tStatus");
        System.out.println("...................................................................................................................................................................................................................................................................................................");

        for (int i = 0; i < identityUS.size(); i++) {
            if (identityUS.get(i).equalsIgnoreCase("seller") && statusUS.get(i).equals("-")) {
                hold.add(categoriesUS.get(i) + "," + itemUS.get(i) + "," + itemIDUS.get(i) + "," + identityUS.get(i) + "," + reg_timeUS.get(i) + "," + end_timeUS.get(i) + "," + minimumUS.get(i) + "," + aucTypeUS.get(i) + "," + statusUS.get(i));
            }
        }
        for (int j = 0; j < hold.size(); j++) {
            System.out.print(k + ".\t");
            for (String ret : hold.get(j).split(",")) {
                String holdd = ret;
                if (holdd.length() < 8) {
                    System.out.print(holdd + "\t\t\t\t");
                } else if (holdd.length() < 16) {
                    System.out.print(holdd + "\t\t\t");
                } else if (holdd.length() < 24) {
                    System.out.print(holdd + "\t\t");
                } else if (holdd.length() < 32) {
                    System.out.print(holdd + "\t");
                }
            }
            System.out.println(" ");
            k++;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    //display the records for username username.txt
    public void displayUsername() {
        int i = 0;
        try {
            Scanner inputstream = new Scanner(new FileInputStream("Username/"+username + ".txt"));
            i = 1;
            String a;
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\tCategories\t\t\tItem\t\t\t\tItem ID\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tEnd Time[D/M/Y H:M:S]\t\tPrice[RM]\t\t\tAuction Type\t\t\tStatus");
            System.out.println("...................................................................................................................................................................................................................................................................................................");
            while (inputstream.hasNextLine()) {
                //get a line in the database
                String check = inputstream.nextLine();
                System.out.print(i + ".\t");
                //split the file
                for (String ret : check.split(",")) {
                    String hold = ret;
                    if (hold.length() < 8) {
                        System.out.print(hold + "\t\t\t\t");
                    } else if (hold.length() < 16) {
                        System.out.print(hold + "\t\t\t");
                    } else if (hold.length() < 24) {
                        System.out.print(hold + "\t\t");
                    } else if (hold.length() < 32) {
                        System.out.print(hold + "\t");
                    }
                }
                System.out.println(" ");
                i++;
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    //show bidder winning's information
    public void winningItem() {
        categoriesWI.clear();
        itemWI.clear();
        itemIDWI.clear();
        identityWI.clear();
        reg_timeWI.clear();
        end_timeWI.clear();
        minimumWI.clear();
        aucTypeWI.clear();
        statusWI.clear();
        frequencyWI.clear();
        biddingStatusWI.clear();
        int k = 1;
        ArrayList<String> hold = new ArrayList<String>();
        transferUsername();
        for (int i = 0; i < statusUS.size(); i++) {
            if (statusUS.get(i).equalsIgnoreCase("WINNING ITEM")) {
                categoriesWI.add(categoriesUS.get(i));
                itemWI.add(itemUS.get(i));
                itemIDWI.add(itemIDUS.get(i));
                identityWI.add(identityUS.get(i));
                reg_timeWI.add(reg_timeUS.get(i));
                end_timeWI.add(end_timeUS.get(i));
                minimumWI.add(minimumUS.get(i));
                aucTypeWI.add(aucTypeUS.get(i));
                statusWI.add(statusUS.get(i));
                int getFrequency = biddingStatus(username, itemIDUS.get(i), itemUS.get(i), aucTypeUS.get(i));
                frequencyWI.add(getFrequency);
                String getStatus = BiddingStatus(getFrequency);
                biddingStatusWI.add(getStatus);
            }
        }
        //display it 
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tCategories\t\t\tItem\t\t\t\tItem ID\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tEnd Time[D/M/Y H:M:S]\t\tPrice[RM]\t\t\tAuction Type\t\t\tStatus\t\t\tBidding Frequency\t\t\tBidding Status");
        System.out.println("..................................................................................................................................................................................................................................................................................................................................................................");

        for (int i = 0; i < statusWI.size(); i++) {
            hold.add(categoriesWI.get(i) + "," + itemWI.get(i) + "," + itemIDWI.get(i) + "," + identityWI.get(i) + "," + reg_timeWI.get(i) + "," + end_timeWI.get(i) + "," + minimumWI.get(i) + "," + aucTypeWI.get(i) + "," + statusWI.get(i) + "," + frequencyWI.get(i) + "," + biddingStatusWI.get(i));
        }
        for (int j = 0; j < hold.size(); j++) {
            System.out.print(k + ".\t");
            for (String ret : hold.get(j).split(",")) {
                String holdd = ret;
                if (holdd.length() < 8) {
                    System.out.print(holdd + "\t\t\t\t");
                } else if (holdd.length() < 16) {
                    System.out.print(holdd + "\t\t\t");
                } else if (holdd.length() < 24) {
                    System.out.print(holdd + "\t\t");
                } else if (holdd.length() < 32) {
                    System.out.print(holdd + "\t");
                }
            }
            System.out.println(" ");
            k++;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("============================");
        System.out.println("|| Total Winning Item : " + statusWI.size() + " ||");
        System.out.println("============================");

    }

    //do for bidding status and bidding frequency by checking the id_item.txt
    //count the bidding frequency of the user //Total minus one because there will be one extra username for registration purposes
    public int biddingStatus(String username, String item_ID, String item, String aucType) {
        String[] store = new String[4];
        BiddingFrequency = 0;
        name.clear();
        date.clear();
        Bid.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                name.add((String) store[0]);
                date.add((String) store[1]);
                Bid.add(store[2]);
            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        for (int i = 0; i < name.size(); i++) {
            if (username.equals(name.get(i))) {
                BiddingFrequency++;
            }
        }
        return (BiddingFrequency - 1);
    }

    //count for bidding status
    public String BiddingStatus(int frequency) {
        String status;
        if (frequency >= 1 && frequency <= 10) {
            status = "NewBie";
        } else if (frequency <= 20) {
            status = "Intermediate";
        } else {
            status = "Pro";
        }
        return status;
    }

    //sort bidding rate in ascending order
    public void asc_BiddingRate(String item_ID, String item, String aucType) {

        for (int j = 0; j < Bid.size(); j++) {
            for (int k = 0; k < Bid.size() - 1; k++) {
                if (Double.parseDouble(Bid.get(k))>(Double.parseDouble(Bid.get(k + 1))) ) {
                    String hold = Bid.get(k);
                    String holdd = name.get(k);
                    String holddd = date.get(k);
                    Bid.set(k, Bid.get(k + 1));
                    name.set(k, name.get(k + 1));
                    date.set(k, date.get(k + 1));
                    Bid.set((k + 1), hold);
                    name.set(k + 1, holdd);
                    date.set(k + 1, holddd);
                }
            }
            System.out.println(Bid.get(j));
        }
        // write in itemId_item_aucType.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            for (int i = 0; i < Bid.size(); i++) {
                write.println(name.get(i) + "," + date.get(i) + "," + Bid.get(i));
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //sort bidding rate in descending order
    public void dec_BiddingRate(String item_ID, String item, String aucType) {
        asc_BiddingRate(item_ID, item, aucType);
        //reverse details by using pop
        LinkedList<String> temp2 = new LinkedList<String>();
        temp2.clear();
        //temporary store the item
        try {
            Scanner scannn = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp2.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt by using pop method . hence the data are stored from bottom to upper
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            for (int j = 0; j < name.size(); j++) {
                write.println(temp2.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //sort name of bidder in ascending order
    public void asc_BidderName(String item_ID, String item, String aucType) {
        for (int j = 0; j < name.size(); j++) {
            for (int k = 0; k < name.size() - 1; k++) {
                if (name.get(k).compareTo(name.get(k + 1)) > 0) {
                    String hold = Bid.get(k);
                    String holdd = name.get(k);
                    String holddd = date.get(k);
                    Bid.set(k, Bid.get(k + 1));
                    name.set(k, name.get(k + 1));
                    date.set(k, date.get(k + 1));
                    Bid.set((k + 1), hold);
                    name.set(k + 1, holdd);
                    date.set(k + 1, holddd);
                }
            }
        }
        // write in itemId_item_aucType.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item + ".txt"));
            for (int i = 0; i < Bid.size(); i++) {
                write.println(name.get(i) + "," + date.get(i) + "," + Bid.get(i));
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //sort name of bidder in descending order
    public void dec_BidderName(String item_ID, String item, String aucType) {
        asc_BidderName(item_ID, item, aucType);
        //reverse the content of the file by using pop method
        LinkedList<String> temp = new LinkedList<String>();
        temp.clear();
        try {
            Scanner scannn = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item + ".txt"));
            while (scannn.hasNextLine()) {
                String check = scannn.nextLine();
                temp.addFirst(check);
            }
            scannn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //transfer back to username.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            for (int j = 0; j < Bid.size(); j++) {
                write.println(temp.pop());
            }
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //Display current bidder // use recursion method
    public void display(int count, int show) {
        if (count == name.size() - 1 && !Bid.get(count).equals("0")) {
            if (Bid.get(count).equals("0")) {
                show--;
            }
            if (name.get(count).length() < 8) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 16) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 24) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 32) {
                System.out.println((show) + ".\t" + name.get(count) + "\t" + date.get(count) + "\t\t" + Bid.get(count));
            }
        } else {
            if (Bid.get(count).equals("0")) {
                show--;
            }
            if (name.get(count).length() < 8 && !Bid.get(count).equals("0")) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 16 && !Bid.get(count).equals("0")) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 24 && !Bid.get(count).equals("0")) {
                System.out.println((show) + ".\t" + name.get(count) + "\t\t" + date.get(count) + "\t\t" + Bid.get(count));
            } else if (name.get(count).length() < 32 && !Bid.get(count).equals("0")) {
                System.out.println((show) + ".\t" + name.get(count) + "\t" + date.get(count) + "\t\t" + Bid.get(count));
            }
            display(count + 1, show + 1);
        }
    }

    //display for descending item
    public void display_descending(String item_ID, String item, String aucType) {
        int i = 0;
        String[] hold = new String[4];
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item + ".txt"));
            i = 1;
            String a;
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("\tName\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
            System.out.println(".............................................................................");
            while (inputstream.hasNextLine()) {
                //get a line in the database
                String check = inputstream.nextLine();
                int count = 0;
                //split the file
                for (String ret : check.split(",")) {
                    hold[count] = ret;
                    count++;
                }
                if (!hold[2].equals("0")) {
                    System.out.println(" ");
                    System.out.print(i + ".\t");
                    for (int j = 0; j < 3; j++) {
                        if (hold[j].length() < 8) {
                            System.out.print(hold[j] + "\t\t\t\t");
                        } else if (hold[j].length() < 16) {
                            System.out.print(hold[j] + "\t\t\t");
                        } else if (hold[j].length() < 24) {
                            System.out.print(hold[j] + "\t\t");
                        } else if (hold[j].length() < 32) {
                            System.out.print(hold[j] + "\t");
                        }
                    }
                    i++;
                }
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n---------------------------------------------------------------------------");
    }

    //main method for report
    public void report() {
        Scanner choose = new Scanner(System.in);
        System.out.print("Please choose a category to view\n[1]Personal details\n[2]Auction Type data\n[3]User's history data\nChoose:");
        String num = choose.nextLine();
      //  String holdd = num.charAt(0) + "";
        switch (num) {
            case "1":
                hold = transfer_nameList();
                displayPD(hold);
                break;
            case "2":
                System.out.print("Please choose an Auction Type to view\n[1]English Auction\n[2]Japanese Auction\n[3]Blind Auction\n[4]Vickrey Auction\n[5]Reserve Auction\nChoose:");
                String auctype = choose.nextLine();
             //   String temp = auctype.charAt(0) + "";
                switch (auctype) {
                    case "1":
                        AuctionType = english_auction;
                        break;
                    case "2":
                        AuctionType = japanese_auction;
                        break;
                    case "3":
                        AuctionType = blind_auction;
                        break;
                    case "4":
                        AuctionType = vickrey_auction;
                        break;
                    case "5":
                        AuctionType = reserve_auction;
                        break;
                    default:
                        System.out.println("======Invalid input======\n");
                        report();
                        break;
                }
                transferAucType();
                System.out.print("View by :\n[1]Start Time\n[2]End Time\n[3]Item ID\nChoose:");
                 {
                }
                String cat = choose.nextLine();
              //  String catt = cat.charAt(0) + "";

                switch (cat) {
                    case "1":

                        System.out.print("View by :\n[1]Ascending Order\n[2]Descending Order\nChoose:");
                        String terms = choose.nextLine();
                      //  String termss = terms.charAt(0) + "";
                        switch (terms) {
                            case "1":
                                startTimeAscending();
                                displayAucType();
                                break;
                            case "2":
                                startTimeDescending();
                                displayAucType();
                                break;
                            default:
                                System.out.println("======Invalid input======\n");
                                report();
                                break;
                        }
                        break;
                    case "2":
                        System.out.print("View by :\n[1]Ascending Order\n[2]Descending Order\nChoose:");
                        String term2 = choose.nextLine();
                 //       String term2s = term2.charAt(0) + "";
                        switch (term2) {
                            case "1":
                                endTimeAscending();
                                displayAucType();
                                break;
                            case "2":
                                endTimeDescending();
                                displayAucType();
                                break;
                            default:
                                System.out.println("======Invalid input======\n");
                                report();
                                break;
                        }
                        break;
                    case "3":
                        System.out.print("View by :\n[1]Ascending Order\n[2]Descending Order\nChoose:");
                        String term3 = choose.nextLine();
                      //  String term3s = term3.charAt(0) + "";
                        switch (term3) {
                            case "1":
                                itemIDAscending();
                                displayAucType();
                                break;
                            case "2":
                                itemIDDescending();
                                displayAucType();
                                break;
                            default:
                                System.out.println("======Invalid input======\n");
                                report();
                                break;
                        }
                        break;
                    default:
                        System.out.println("======Invalid input======\n");
                        report();
                        break;
                }
                break;
            case "3":
                System.out.print("View By:\n[1]All\n[2]Selling Item\n[3]Winning item\nChoose:");
                String catUS = choose.nextLine();
                transferUsername();
                switch (catUS) {
                    case "1":
                        System.out.print("Categorised by:\n[1]Ascending order for ending time\n[2]Descending order for ending time\n[3]Ascending order for Item Identification\n[4]Descending order for Item Identification\n[5]Ascending order for Auction Type\n[6]Descending order for Auction Type\nchoose:");
                        String det = choose.nextLine();
                        switch (det) {
                            case "1":
                                ascEnd_time();
                                displayUsername();
                                break;
                            case "2":
                                decEnd_time();
                                displayUsername();
                                break;
                            case "3":
                                ascItem_ID();
                                displayUsername();
                                break;
                            case "4":
                                decItem_ID();
                                displayUsername();
                                break;
                            case "5":
                                asc_auctype();
                                displayUsername();
                                break;
                            case "6":
                                dec_auctype();
                                displayUsername();
                                break;
                            default:
                                System.out.println("======Invalid input======\n");
                                report();
                                break;
                        }
                        break;
                    case "2":
                        System.out.print("View by:\n[1]All\n[2]Sold Item\n[3]Unsold Item\nChoose:");
                        String hold = choose.nextLine();
                        switch (hold) {
                            case "1":
                                selling();
                                break;
                            case "2":
                                soldItem();
                                break;
                            case "3":
                                unsoldItem();
                                break;
                            default:
                                System.out.println("======INVALID INPUT======\n");
                                report();
                                break;
                        }
                        break;
                    case "3":
                        System.out.print("<<<<<<<<<< Your Latest Winning Item >>>>>>>>>>");
                        winningItem();
                        System.out.print("Would you like to\n[1]Look winning item in details\n[2]Exit\nChoose:");
                        String get = choose.nextLine();
                    //    String gets = get.charAt(0) + "";
                        switch (get) {
                            case "1":
                                winningItem();
                                System.out.println("Please choose an item to view the bidding's history[Choose a number]");
                                String getNum = choose.nextLine();
                                int getNum2 = Integer.parseInt(getNum) - 1;
                                System.out.print("View By:\n[1]Bidding rate in ascending order\n[2]Bidding rate in descending order\n[3]Name of bidders in ascending order\n[4]Name of bidders in descending order\nChoose:");
                                String viewBy = choose.nextLine();
                         //       String viewBys = viewBy.charAt(0) + "";
                                switch (viewBy) {
                                    case "1":
                                        biddingStatus(username, itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        asc_BiddingRate(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        System.out.println("--------------------------------------------------------------------------------");
                                        System.out.println("\tName\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                                        System.out.println(".................................................................................");
                                        display(0, 1);
                                        System.out.println("--------------------------------------------------------------------------------");

                                        break;
                                    case "2":
                                        biddingStatus(username, itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        dec_BiddingRate(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        display_descending(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        break;
                                    case "3":
                                        biddingStatus(username, itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        asc_BidderName(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        System.out.println("---------------------------------------------------------------------------");
                                        System.out.println("  Name\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                                        System.out.println(".............................................................................");
                                        display(0, 1);
                                        System.out.println("---------------------------------------------------------------------------");

                                        break;
                                    case "4":
                                        biddingStatus(username, itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        dec_BidderName(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        display_descending(itemIDWI.get(getNum2), itemWI.get(getNum2), aucTypeWI.get(getNum2));
                                        break;
                                    default:
                                        System.out.println("======INVALID INPUT======n");
                                        report();
                                        break;
                                }
                                break;
                            case "2":
                                break;
                            default:
                                System.out.println("======INVALID INPUT======\n");
                                report();
                                break;
                        }
                        break;
                    default:
                        System.out.println("======INVALID INPUT======\n");
                        report();
                        break;
                }
                break;
            default:
                System.out.println("======INVALID INPUT======\n");
                report();
                break;
        }
    }

}
//reserve auction ITEM ID ?!!!
