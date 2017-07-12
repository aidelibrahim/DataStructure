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
import java.util.Scanner;

public class AuctionType<E> {

    private String username;
    private String aucType; //5 types of auction type
    private final String english_auction = "English Auction";
    private final String japanese_auction = "Japanese Auction";
    private final String blind_auction = "Blind Auction";
    private final String vickrey_auction = "Vickrey Auction";
    private final String reserve_auction = "Reserve Auction";
    private String identity=""; //bidder OR seller
    private final String bidder = "bidder";
    private final String seller = "seller";
    private String others;
    private final String antique = "Antique";//antique
    private final String painting = "Painting";
    private final String art = "Art";
    private final String furniture = "Furniture";
    private final String jewelry = "Jewelry";
    private final String electronic = "Electronic";//electronic 
    private final String computer = "Computer";
    private final String phone = "Phone";
    private String Music_player = "Music Player";
    private final String Apple_product = "Apple Product";
    private final String GPS = "GPS";
    private final String Real_estate = "Real Estate";//Real estate 
    private final String garden = "Garden";
    private final String land = "Land";
    private final String houses = "Houses";
    private final String sea = "Sea";
    private final String mountain = "Mountain";
    private final String EntertainmentAndSports = "Entertainement and Sports"; //Entertainement and Sports
    private final String Sports_equipment = "Sports Equipment";
    private final String concert_ticket = "Concert Ticket";
    private final String classesVoucher = "Class Voucher";
    private final String boardAndCardGame = "Board and Card Game";
    private final String Furniture = "Furniture"; //Furniture
    private final String desk = "Desk";
    private final String cabinet = "Cabinet";
    private final String sofa = "Sofa";
    private final String fan = "Fan";
    private final String air_cond = "Air conditioner";
    private final String GoodsAndServices = "Goods And Services"; //Goods and services
    private final String InteriorDesignServices = "Interior Design Services";
    private final String ComputerClasses = "Computer Classes";
    private final String AccountingServices = "Accounting Services";
    private final String EstatePlanning = "Estate Planning";
    private final String LandscapingServices = "Landscapping Services";
    private final String TaxPreparation = "Tax Preparation";
    private final String fashion = "Fashion";//Fashion
    private final String womenSApparelAndAccessories = "Women's Apparel And Accessories";
    private final String menSApparelAndAccessorries = "Men's Apparel And Accessories";
    private final String Jewelry = "Jewelry";
    private final String watches = "Watches";
    private final String shoe = "Shoe";
    private String endDate;
    String minPrice;
    private String place;
    private String startDate;
    private int line;
    private int chosen;
    private boolean timeout = false;
    private boolean timestart = false;
    private final ArrayList<E> categories = new ArrayList<>();
    private final ArrayList<E> item = new ArrayList<>();
    private final String[] store = new String[15];
    private ArrayList<E> item1 = new ArrayList<>();
    private ArrayList<E> categories1 = new ArrayList<>();
    private ArrayList<E> itemID = new ArrayList<>();
    private ArrayList<E> seller_name = new ArrayList<>();
    private ArrayList<E> identityy = new ArrayList<>();
    private ArrayList<E> start_time = new ArrayList<>();
    private ArrayList<E> start_date = new ArrayList<>();
    private ArrayList<E> end_time = new ArrayList<>();
    private ArrayList<E> minimum_price = new ArrayList<>();
    private ArrayList<E> placee = new ArrayList<>();
    private ArrayList<E> startTime = new ArrayList<>();
    private LinkedList<E> checkName = new LinkedList<>();
    private LinkedList<E> checkk = new LinkedList<>();
    private int totalLine;
    String holdThisItem;
    Scanner scan = new Scanner(System.in);

    //constructor
    public AuctionType(String username) {
        this.username = username;
    }

    public String setDate() {
        //call out date
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    //prompt user to choose auction type
    public String chooseAucType() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nChoose an Auction Type:\n[1]English Auction\n[2]Japanese Auction\n[3]Blind Auction\n[4]Vickrey Auction\n[5]Reserve Auction\nChoose:");
        int type = scan.nextInt();
        switch (type) {
            case 1:
                aucType = english_auction;
                break;
            case 2:
                aucType = japanese_auction;
                break;
            case 3:
                aucType = blind_auction;
                break;
            case 4:
                aucType = vickrey_auction;
                break;
            case 5:
                aucType = reserve_auction;
                break;
            default:
                chooseAucType();
                break;
        }
        aucDetails();
        return aucType;
    }

    public void aucDetails() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n-----RULES AND REGULATIONS-----");
        if (aucType.equalsIgnoreCase(english_auction)) {
            System.out.println("$-Participants bid openly against one another,with each subsequent bid required to be higher than the previous bid.\n$-The first bid must exceed the minumum value set by the auctioneer.\n$-Auction ends when no participant is willing to bid further.");
        }
        if (aucType.equalsIgnoreCase(japanese_auction)) {
            System.out.println("$-When bidding starts no new bidders can join.\n$-each bidder must continue to bid each round or drop out.");
        }
        if (aucType.equalsIgnoreCase(blind_auction)) {
            System.out.println("$-no bidder knows the bid of any other participant.\n$-Bidders can only submit one bid each.\n$-submitted bids are opened at a place and time that were predetermined earlier.\n$-. The highest bidder pays the price they submitted.");
        }
        if (aucType.equalsIgnoreCase(vickrey_auction)) {
            System.out.println("$-no bidder knows the bid of any other participant.\n$-Bidders can only submit one bid each.\n$-submitted bids are opened at a place and time that were predetermined earlier.\n$-Winning bidder pays the second-highest bid rather than his or her own.");
        }
        if (aucType.equalsIgnoreCase(reserve_auction)) {
            System.out.println("$-Seller reserves the right to accept or reject the highest bid if the final bid does not satisfy the seller.\n$-The auctioneer may accept the bid if ±5% of the minimum price is offered by the bidder.");
        }
        System.out.print("Do you wish to continue ?\n[1]Yes\n[2]Choose again\nChoose:");
        int hold = scan.nextInt();
        switch (hold) {
            case 1:
                break;
            case 2:
                chooseAucType();
                break;
            default:
                aucDetails();
                break;
        }
    }

    public String identity() {
        identity="";
        Scanner scan = new Scanner(System.in);
        System.out.print("\nDo you wish to continue as \n[1]Bidder\n[2]Seller\nChoose:");
        int choose = scan.nextInt();
        switch (choose) {
            case 1:
                identity = bidder;
                break;
            case 2:
                identity = seller;
                break;
            default:
                identity();
                break;
        }
        return identity;
    }

    //Check whether the auction text file exists else , create one .
    public void auctionTypeFileCheck() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType + ".txt"));
            inputstream.close();
        } catch (FileNotFoundException a) {
            auctionTypeFileCreate();
        }
    }

    //Create the auction text file
    public void auctionTypeFileCreate() {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType + ".txt"));
            createDatabase.close();
        } catch (IOException b) {
            System.out.println(b.getMessage());
        }
    }

    //check and return how many lines are there in the aucType.txt
    public int checkDatabaseLines() {
        totalLine = 1;
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType + ".txt"));
            while (inputstream.hasNextLine()) {
                inputstream.nextLine();
                totalLine++;
            }
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        return totalLine;
    }

    //display the content in the auction type file
    //categories,item,seller's name , identity,start time , end time,minumum price
    public void displayAuctionTypeFile() {
        int i = 0;
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType + ".txt"));
            i = 1;
            String a;
            if (aucType.equals(japanese_auction) || (aucType.equals(english_auction))) {
              System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
              System.out.println("\tCategories\t\t\tItem\t\t\t  Item ID\t\t\tSeller's Name\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]\t\tMinumum Price[RM]");
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
        line = i;
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
           
    }

    //display for Blind Auction ,reserve auction and Vickrey Aution
    public void hideBidDisplay() {
        categorizedItem();
        String[] holdd = new String[categories1.size()];
        int j = 1;
        //store everthing in a array
        if (aucType.equals(blind_auction) || aucType.equals(vickrey_auction)) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\tCategories\t\t\tItem\t\t\t  Item ID\t\t\tSeller's Name\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]\t\tVenue");
            System.out.println("...................................................................................................................................................................................................................................................................................................");
            for (int i = 0; i < categories1.size(); i++) {
                holdd[i] = categories1.get(i) + "," + item1.get(i) + "," + itemID.get(i) + "," + seller_name.get(i) + "," + identityy.get(i) + "," + start_time.get(i) + "," + start_date.get(i) + "," + end_time.get(i) + "," + placee.get(i);
            }
        }
        if (aucType.equals(reserve_auction)) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\tCategories\t\t\tItem\t\t\t  Item ID\t\t\tSeller's Name\t\t\t\tIdentity\t\t\tRegister Time[D/M/Y H:M:S]\tStart Time[D/M/Y H:M:S]\t\tEnd Time[D/M/Y H:M:S]");
            System.out.println("...................................................................................................................................................................................................................................................................................................");
            for (int i = 0; i < categories1.size(); i++) {
                holdd[i] = categories1.get(i) + "," + item1.get(i) + "," + itemID.get(i) + "," + seller_name.get(i) + "," + identityy.get(i) + "," + start_time.get(i) + "," + start_date.get(i) + "," + end_time.get(i);

            }
        }
        for (int count = 0; count < categories1.size(); count++) {
            //get a line in the database
            String check = holdd[count];
            System.out.print(j + ".\t");
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
            j++;

        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //for seller
    public void sell() {
        item.clear();
        categories.clear();
        Scanner scan = new Scanner(System.in);
        Scanner linee = new Scanner(System.in);
        System.out.print("\nPlease select a category of your item:\n[1]Antique\n[2]Electronic gadget\n[3]Real Estate\n[4]Entertainment and Sports\n[5]Furniture\n[6]Goods and Services\n[7]Fashion\n[8]Others\nChoose: ");
        String hold = linee.nextLine();
        {
        }
        switch (hold) {
            case "1":
                categories.add((E) antique);
                System.out.print("Select a selling item:\n[1]Painting\n[2]Art\n[3]Furniture\n[4]Jewelry\n[5]Others\nChoose: ");
                String temp = linee.nextLine();
                 {
                }
                switch (temp) {
                    case "1":
                        item.add((E) painting);
                        break;
                    case "2":
                        item.add((E) art);
                        break;
                    case "3":
                        item.add((E) furniture);
                        break;
                    case "4":
                        item.add((E) jewelry);
                        break;
                    case "5":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "2":
                categories.add((E) electronic);
                System.out.print("Select a selling item:\n[1]Computer\n[2]Phone\n[3]Music Player\n[4]Apple Product\n[5]GPS\n[6]Others\nChoose:");
                temp = linee.nextLine();
                 {
                }
                switch (temp) {
                    case "1":
                        item.add((E) computer);
                        break;
                    case "2":
                        item.add((E) phone);
                        break;
                    case "3":
                        item.add((E) Music_player);
                        break;
                    case "4":
                        item.add((E) Apple_product);
                        break;
                    case "5":
                        item.add((E) GPS);
                        break;
                    case "6":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "3":
                categories.add((E) Real_estate);
                System.out.print("Select a selling item\n[1]Garden\n[2]Land\n[3]Houses\n[4]Sea\n[5]Mountain\n[6]Others:");
                temp = linee.nextLine();
                 {
                }
                switch (temp) {
                    case "1":
                        item.add((E) garden);
                        break;
                    case "2":
                        item.add((E) land);
                        break;
                    case "3":
                        item.add((E) houses);
                        break;
                    case "4":
                        item.add((E) sea);
                        break;
                    case "5":
                        item.add((E) mountain);
                        break;
                    case "6":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;

                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "4":
                categories.add((E) EntertainmentAndSports);
                System.out.print("Select a selling item:\n[1]Sports Equipment\n[2]Concert Ticket\n[3]Class Voucher\n[4]Board and Card Game\n[5]others\nChoose:");
                temp = linee.nextLine();
                switch (temp) {
                    case "1":
                        item.add((E) Sports_equipment);
                        break;
                    case "2":
                        item.add((E) concert_ticket);
                        break;
                    case "3":
                        item.add((E) classesVoucher);
                        break;
                    case "4":
                        item.add((E) boardAndCardGame);
                        break;
                    case "5":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "5":
                categories.add((E) Furniture);
                System.out.print("Select a selling item:\n[1]Desk\n[2]Cabinet\n[3]Sofa\n[4]Fan\n[5]Air-conditional\n[6]Others\nChoose:");
                temp = linee.nextLine();
                 {
                }
                switch (temp) {
                    case "1":
                        item.add((E) desk);
                        break;
                    case "2":
                        item.add((E) cabinet);
                        break;
                    case "3":
                        item.add((E) sofa);
                        break;
                    case "4":
                        item.add((E) fan);
                        break;
                    case "5":
                        item.add((E) air_cond);
                        break;
                    case "6":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "6":
                categories.add((E) GoodsAndServices);
                System.out.print("Select an item:\n[1]Goods And Services\n[2]Interior Design Services\n[3]Computer Classes\n[4]Accounting Services\n[5]Estate Planning\n[6]Others\nChoose:");
                temp = linee.nextLine();
                switch (temp) {
                    case "1":
                        item.add((E) GoodsAndServices);
                        break;
                    case "2":
                        item.add((E) InteriorDesignServices);
                        break;
                    case "3":
                        item.add((E) ComputerClasses);
                        break;
                    case "4":
                        item.add((E) AccountingServices);
                        break;
                    case "5":
                        item.add((E) EstatePlanning);
                        break;
                    case "6":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "7":
                categories.add((E) fashion);
                System.out.print("Select a selling item:\n[1]Women's Apparel and Accessories\n[2]Men's Apparel and Accessories\n[3]Jewelry\n[4]Watches\n[5]Shoe\n[6]Others:");
                temp = linee.nextLine();
                switch (temp) {
                    case "1":
                        item.add((E) womenSApparelAndAccessories);
                        break;
                    case "2":
                        item.add((E) menSApparelAndAccessorries);
                        break;
                    case "3":
                        item.add((E) jewelry);
                        break;
                    case "4":
                        item.add((E) watches);
                        break;
                    case "5":
                        item.add((E) shoe);
                        break;
                    case "6":
                        System.out.print("Please specify your item:");
                        String others = linee.nextLine();
                        item.add((E) others);
                        break;
                    default:
                        System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                        sell();
                        break;
                }
                break;
            case "8":
                System.out.print("Please specify a categories : ");
                String hold1 = linee.nextLine();
                categories.add((E) hold1);
                System.out.print("Please specify your item : ");
                others = linee.nextLine();
                item.add((E) others);
                break;

            default:
                System.out.println("===INVALID INPUT===");
                sell();
                break;
        }
    }

    //prompt selleing to key in the further details about the bidding item
    public void moreDetails() {
        Scanner line = new Scanner(System.in);
        String choose;
        System.out.println("Please enter a Starting Date [Day/Month/Year Hour:Minute:Second]");
        System.out.println("Today's date : " + setDate());
        System.out.print("Enter a date: ");
        String getStartDate = line.nextLine();
        if (getStartDate.compareTo("00/00/0000 00:00:00") > 0) {
            startDate = getStartDate;
        } else {
            System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
            moreDetails();
        }
        System.out.println("\nPlease enter a Ending Date[Day/Month/Year Hour:Minute:Second]:");
        System.out.println("Today's date : " + setDate());
        System.out.print("Enter a date: ");
        String getEndDate = line.nextLine();
        if (getEndDate.compareTo("00/00/0000 00:00:00") > 0) {
            endDate = getEndDate;
        } else {
            System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
            moreDetails();
        }
        System.out.print("\nWould you like to set an minimum price?\n[1]Yes\n[2]No\nChoose:");
        choose = line.nextLine();
        if (choose.equals("1")) {
            System.out.print("Enter a minimum price[RM]: ");
            minPrice = line.nextLine();
        } else if (choose.equals("2")) {
            minPrice = "-";
        } else {
            System.out.println("===INVALID INPUT,PLEASE TRY AGAIN!===\n");
            moreDetails();
        }
        if (aucType.equals(blind_auction) || aucType.equals(vickrey_auction)) {
            System.out.print("\nWould you like to set a venue to announce the winner?\n[1]Yes\n[2]No\nChoose:");
            choose = line.nextLine();
            if (choose.equals("1")) {
                System.out.print("Enter a place: ");
                place = line.nextLine();
            } else if (choose.equals("2")) {
                place = "-";
            } else {
                System.out.println("===INVALID INPUT.PLEASE TRY AGAIN!===\n");
                moreDetails();
            }
        }

    }

    //after the seller has record everything
    //transfer data into a text file
    public void recordFileTransfer(int num) {

        try {
            PrintWriter inputStream = new PrintWriter(new FileOutputStream(aucType + ".txt", true));

            if (aucType.equals(english_auction) || aucType.equals(reserve_auction)) {
            //    System.out.println(minPrice+"wdfewfwefweff");
                inputStream.println(categories.get(0) + "," + item.get(0) + "," + ("I" + num) + "," + username + "," + identity + "," + setDate() + "," + startDate + "," + endDate + "," + minPrice);
            } else if (aucType.equals(blind_auction) || aucType.equals(vickrey_auction)) {
                inputStream.println(categories.get(0) + "," + item.get(0) + "," + ("I" + num) + "," + username + "," + identity + "," + setDate() + "," + startDate + "," + endDate + "," + minPrice + "," + place);
            } else if (aucType.equals(japanese_auction)) {
                inputStream.println(categories.get(0) + "," + item.get(0) + "," + ("I" + num) + "," + username + "," + identity + "," + setDate() + "," + startDate + "," + endDate + "," + minPrice);
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Record has been saved !");
        if ((aucType.equals(english_auction)) || (aucType.equals(japanese_auction))) {
            displayAuctionTypeFile();
        } else {
            hideBidDisplay();
        }
    }

    //create username.txt 
    //to store the history for SELLER
    public void usernameFileCreate(int num) {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream("Username/"+username + ".txt", true));
            createDatabase.println(categories.get(0) + "," + item.get(0) + "," + ("I" + num) + "," + identity + "," + setDate() + "," + endDate + "," + minPrice + "," + aucType + "," + "-");
            createDatabase.close();
        } catch (IOException b) {
            System.out.println(b.getMessage());
        }
        try {
            PrintWriter inputStream = new PrintWriter(new FileOutputStream(aucType+"/"+("I" + num) + "_" + item.get(0) + ".txt"));
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //transfer everything in the textfile into arrayList and categorized them . 
    //so when the bidder choose which item to bit , programme can take out the specific line in the textfile and transfer it to item_auctype.txt
    public void categorizedItem() {
        categories1.clear();
        item1.clear();
        itemID.clear();
        seller_name.clear();
        identityy.clear();
        start_time.clear();
        start_date.clear();
        end_time.clear();
        minimum_price.clear();
        placee.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                if (aucType.equals(blind_auction) || aucType.equals(vickrey_auction)) {
                    categories1.add((E) store[0]);
                    item1.add((E) store[1]);
                    itemID.add((E) store[2]);
                    seller_name.add((E) store[3]);
                    identityy.add((E) store[4]);
                    start_time.add((E) store[5]);
                    start_date.add((E) store[6]);
                    end_time.add((E) store[7]);
                    minimum_price.add((E) store[8]);
                    placee.add((E) store[9]);
                } else if (aucType.equals(japanese_auction)) {
                    categories1.add((E) store[0]);
                    item1.add((E) store[1]);
                    itemID.add((E) store[2]);
                    seller_name.add((E) store[3]);
                    identityy.add((E) store[4]);
                    start_time.add((E) store[5]);
                    start_date.add((E) store[6]);
                    end_time.add((E) store[7]);
                    minimum_price.add((E) store[8]);
                } else if (aucType.equals(reserve_auction) || aucType.equals(english_auction)) {
                    categories1.add((E) store[0]);
                    item1.add((E) store[1]);
                    itemID.add((E) store[2]);
                    seller_name.add((E) store[3]);
                    identityy.add((E) store[4]);
                    start_time.add((E) store[5]);
                    start_date.add((E) store[6]);
                    end_time.add((E) store[7]);
                    minimum_price.add((E) store[8]);
                }
            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
    }

    //check whether the aucType.txt is empty
    public void checkEmpty() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                checkk.add((E) store[0]);
            }
            inputStream.close();
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
    }

    //check the existance of a specific name
    public void checkName() {
        checkName.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType+"/"+itemID.get(chosen) + "_" + item1.get(chosen) + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                checkName.add((E) store[0]);
            }
            inputStream.close();
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
    }

    //for bidder
    public void bidder() {
        Scanner scan = new Scanner(System.in);
        Scanner getint = new Scanner(System.in);
        System.out.println("Current Date: " + setDate());
        if (aucType.equals(english_auction) || aucType.equals(japanese_auction)) {
            displayAuctionTypeFile();
        }//display current data in the file
        else if (aucType.equals(blind_auction) || aucType.equals(vickrey_auction) || aucType.equals(reserve_auction)) {
            hideBidDisplay();
        } //these 3 auction don't display minimum price
        System.out.print("Which item would you like to bit? [Choose number]\nChoose:");
        String temp = scan.nextLine();
        chosen = Integer.parseInt(temp) - 1;
          checkEmpty();
        if (checkk.isEmpty() == true) {
            System.out.println("We're Sorry " + username + ". No selling item is available. Please rechoose the auction type to continue.");
            AuctionType();
        }
       else if(chosen>start_date.size()-1){
            System.out.println("===INVALID INPUT .PLEASE TRY AGAIN!===");
            bidder();
        }
        checkName();
        if (aucType.equals(english_auction) || aucType.equals(blind_auction) || aucType.equals(vickrey_auction) || aucType.equals(reserve_auction)) {
            if (checkTimeStart() == false) {
                System.out.println("The auction for the item will start at " + start_date.get(chosen) + " .Please come again !");
               AuctionType() ;
            }
        }
        checkName();
        if (checkTimeOut() == false && checkTimeStart() == true) {
            //check whether the bidder have registered . If yes , do not write anything into bidder.txt
            if (checkName.contains(username) == false) {
                try {
                    PrintWriter inputStream = new PrintWriter(new FileOutputStream(aucType+"/"+itemID.get(chosen) + "_" + item1.get(chosen) + ".txt", true));
                    inputStream.println(username + "," + setDate() + "," + "0");

                    inputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        //   holdThisItem = (String) item1.get(chosen); //get this specific item
//        System.out.println("Record has been saved !");
    }

    public boolean checkTimeStart() {
        if (setDate().compareTo((String) start_date.get(chosen)) >= 0) {
            timestart = true;
        }
        return timestart;
    }

    public boolean checkTimeOut() {
        if (setDate().compareTo((String) end_time.get(chosen)) >= 0) {
            timeout = true;
        }
        return timeout;
    }

    public void AuctionType() {
        chooseAucType();
        auctionTypeFileCheck();
        identity = identity();
        int num = checkDatabaseLines();
        // displayAuctionTypeFile();
        if (identity.equalsIgnoreCase(seller)) {
            sell();
            moreDetails();
            recordFileTransfer(num);
            usernameFileCreate(num);
        } else if (identity.equalsIgnoreCase(bidder)) {
            categorizedItem();
            bidder();
            checkTimeOut();
            if (aucType.equals(english_auction)) {
                EnglishAuction<String> ea = new EnglishAuction<String>(username, aucType, (String) item1.get(chosen), (String) end_time.get(chosen), (String) categories1.get(chosen), (String) seller_name.get(chosen), (String) start_time.get(chosen), (String) itemID.get(chosen), (String) start_date.get(chosen), (String) minimum_price.get(chosen));
                ea.EnglishAuction();
            } else if (aucType.endsWith(japanese_auction)) {
                JapaneseAuction<String> ja = new JapaneseAuction<String>(username, aucType, (String) item1.get(chosen), (String) end_time.get(chosen), (String) categories1.get(chosen), (String) seller_name.get(chosen), (String) start_time.get(chosen), (String) itemID.get(chosen), (String) start_date.get(chosen), (String) minimum_price.get(chosen));
                ja.JapananesAuction();
            } else if (aucType.equals(blind_auction)) {
                BlindAuction<String> ba = new BlindAuction<String>(username, aucType, (String) item1.get(chosen), (String) end_time.get(chosen), (String) placee.get(chosen), (String) minimum_price.get(chosen), (String) categories1.get(chosen), (String) seller_name.get(chosen), (String) start_time.get(chosen), (String) itemID.get(chosen));
                checkName();
                int found = 0;
                for (int i = 0; i < checkName.size(); i++) {
                    if (checkName.get(i).equals(username)) {
                        found++;
                    }
                }
                if (found < 2 || timeout == true) {
                    ba.BlindAuction();
                } else {
                    System.out.println("We're sorry " + username + "! Each bidder can only submit one bid each.");
                    AuctionType();
                }
            } else if (aucType.equals(vickrey_auction)) {
                checkName();
                int found = 0;
                for (int i = 0; i < checkName.size(); i++) {
                    if (checkName.get(i).equals(username)) {
                        found++;
                    }
                }
                if (found < 2 || timeout == true) {
                    VickreyAuction<String> va = new VickreyAuction<String>(username, aucType, (String) item1.get(chosen), (String) end_time.get(chosen), (String) placee.get(chosen), (String) minimum_price.get(chosen), (String) categories1.get(chosen), (String) seller_name.get(chosen), (String) start_time.get(chosen), (String) itemID.get(chosen));
                    va.VickreyAuction();
                } else {
                    System.out.println("We're sorry " + username + "! Each bidders can only submit one bid each.");
                    AuctionType();
                }
            } else if (aucType.equals(reserve_auction)) {
                ReserveAuction<String> ra = new ReserveAuction<String>(username, aucType, (String) item1.get(chosen), (String) minimum_price.get(chosen), (String) end_time.get(chosen), (String) categories1.get(chosen), (String) seller_name.get(chosen), (String) start_time.get(chosen), (String) itemID.get(chosen));
                ra.reserveAction();
            }

        }
    }

    //call report
    public void report() {
        Reports re = new Reports(username);
        re.report();
    }

}
