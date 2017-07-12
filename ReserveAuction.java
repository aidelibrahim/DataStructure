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

public class ReserveAuction<E> extends AuctionType {
    //declare variable

    public String username;
    private String minPrice; // to get the minimum price set the seller
    private String aucType;
    private String endTime;
    private String bid;
    String start_time;
    private double max = 0;
    private double compare = 0; //compare the price and find out the maximum value
    public double leastPrice;
    public double mostPrice;
    private boolean higherThan = false;
    private boolean timeout = false;
    private boolean accept = false;
    private boolean withinRange = false;
    private boolean secondChance = false;
    private String[] store = new String[9];
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<Double> bidPrice = new ArrayList<Double>();
    private ArrayList<E> item1 = new ArrayList<>();
    private ArrayList<E> categories1 = new ArrayList<>();
    private ArrayList<E> itemID = new ArrayList<>();
    private ArrayList<E> sellerName = new ArrayList<>();
    private ArrayList<E> identityy = new ArrayList<>();
    private ArrayList<E> startTime = new ArrayList<>();
    private ArrayList<E> start_date = new ArrayList<>();
    private ArrayList<E> end_time = new ArrayList<>();
    private ArrayList<E> minimum_price = new ArrayList<>();
    LinkedList<String> checkName = new LinkedList<>();
    LinkedList<String> checkDate = new LinkedList<>();
    LinkedList<String> checkBit = new LinkedList<>();
    E item;
    E categories;
    E seller_name;
    E item_ID;

    public ReserveAuction(String username, String aucType, E item, String minPrice, String endTime, E categories, E seller_name, String start_time, E item_ID) {
        super(username);
        this.username = username;
        this.aucType = aucType;
        this.item = item;
        this.minPrice = minPrice;
        this.endTime = endTime;
        this.categories = categories;
        this.seller_name = seller_name;
        this.start_time = start_time;
        this.item_ID = item_ID;
    }

    //check whether the item_AuctionType file exists else create one 
    public void databaseCheck() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType + "/" + item_ID + "_" + item + ".txt"));
            inputstream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
            databaseCreate();
        }
    }

    //create the database
    public void databaseCreate() {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType + "/" + item_ID + "_" + item + ".txt"));
            createDatabase.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //get current Date and Time
    public String getDate() {
        //call out date
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    //transfer every data in item_reserveAuction.txt into ArrayList
    public void transfer() {
        name.clear();
        date.clear();
        bidPrice.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + "/" + item_ID + "_" + item + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                name.add((String) (E) store[0]);
                date.add((String) (E) store[1]);
                Double hold = Double.parseDouble(store[2]);
                bidPrice.add(hold);
            }

            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
    }

    //supervise the number of bidder
    public void checkName() {
        checkName.clear();
        checkDate.clear();
        checkBit.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + "/" + item_ID + "_" + item + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                checkName.add((String) (E) store[0]);
                checkDate.add((String) (E) store[1]);
                checkBit.add((String) (E) store[2]);
            }
            inputStream.close();
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }
        int getNum = checkName.size();

    }

    //prompt user to enter new Bid(){
    public void newBid() {

        boolean won = false;
        double check = 0;

        Scanner read = new Scanner(System.in);
        System.out.println("\nYour Bid must be higher than RM" + getCurrentHighestPrice());
        System.out.println("Do you wish to continue or surrender?");
        System.out.println("[1]Continue\n[2]Surrender\nChoose:");
        String in = read.nextLine();
        switch (in) {
            case "1":
                System.out.print("Enter your bid: RM");
                bid = read.nextLine();     //Initially you wrote integer . what if the user enter double value or other character ? The system will have error. Hence , we use string as input and check for them .      
                check = Double.parseDouble(bid);
                compareBidPrice(check);
                check_range(bid);
                if (higherThan = false) {
                    System.out.println("Sorry , Please enter a higher value!");
                    newBid();
                } else if (check > mostPrice) {
                    keyInBidValue();
                    transfer();
                    System.out.println(" =====Congratulations! You have won the item!===== ");
                    System.out.println("======================================================================");
                    System.out.println("Name\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                    System.out.println("......................................................................");
                    display(0, 1);
                    System.out.println("======================================================================");
                    changeExpiredDate();
                    announce_winner();
                    winner_profile();
                    saveSellerResult();
                    saveBidderResult();
                    //won
                    break;
                } else if (check_range(bid) == false) {
                    System.out.println("---Sorry , reserve not met ! ---");
                    keyInBidValue();
                    transfer();
                    System.out.println("=====================================================================================");
                    System.out.println("\tName\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                    System.out.println(".....................................................................................");
                    display(0, 1);
                    System.out.println("=====================================================================================");

                    break;
                } else if (check_range(bid) == true) {
                    checkChance();
                    if (secondChance == true) {
                       System.out.println(" \n=====Congratulations! You have won the item!===== ");
//                         won = true;
                        changeExpiredDate();
                        announce_winner();
                        winner_profile();
                        saveSellerResult();
                        saveBidderResult();

                    } else {
                        keyInBidValue();
                        transfer();
                        System.out.println("======================================================================");
                        System.out.println("Name\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                        System.out.println("......................................................................");
                        display(0, 1);
                        System.out.println("======================================================================");
                        won = false;
                        break;
                    }
                }

                break;
            case "2":
                System.out.println("Thank You for your time.You have quit the auction");
                System.out.print("----------- You have quit the auction at :" + getDate() + " ----------\n");
                checkName();
                for (int i = 0; i < checkName.size(); i++) {
                    if (username.equals(checkName.get(i))) {
                        checkName.remove(i);
                        checkDate.remove(i);
                        checkBit.remove(i);
                    }

                }
                //write into the text file
                try {
                    PrintWriter write = new PrintWriter(new FileOutputStream(aucType + "/" + item_ID + "_" + item + ".txt"));
                    for (int i = 0; i < checkName.size(); i++) {
                        write.println(checkName.get(i) + "," + checkDate.get(i) + "," + checkBit.get(i));
                    }
                    write.close();
                } catch (IOException a) {
                    System.out.println(a.getMessage());
                }
                break;
            default:
                newBid();
                break;
        }
//            if (won = true) {
//                System.out.println("here");
//            changeExpiredDate();
//            announce_winner();
//            winner_profile();
//            saveSellerResult();
//            saveBidderResult();
//            }
    }

    //get the highest bit for comparing
    public double getCurrentHighestPrice() {

        for (int i = 0; i < bidPrice.size(); i++) {
            if (bidPrice.get(i) > max) {
                max = bidPrice.get(i);
            }
        }
        return max;
    }

    //check bidPrice
    public boolean compareBidPrice(double price) {
        transfer();
        for (int i = 0; i < bidPrice.size(); i++) {
            if (bidPrice.get(i) > compare) {
                compare = bidPrice.get(i);
            }
        }
        //compare the value entered by user with the previous higher value
        if (price > compare) {
            higherThan = true;
        } else {
            higherThan = false;
        }
        return higherThan;
    }

    //key in the bid from bidders in item_reserve.txt
    public void keyInBidValue() {
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType + "/" + item_ID + "_" + item + ".txt", true));
            write.println(username + "," + getDate() + "," + bid);
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //check for second value
    public boolean checkChance() {
        Scanner read = new Scanner(System.in);
        System.out.println("Your bid is ±5% within the reserve value. Hence, you receive the predilection to enter a second value[RM] ");
        System.out.print("Enter : RM");
        bid = read.nextLine();     //Initially you wrote integer . what if the user enter double value or other character ? The system will have error. Hence , we use string as input and check for them .      
        Double sec_bid = Double.parseDouble(bid);
//                compareBidPrice(sec_bid);
        if (minPrice.compareTo(bid) > 0) {
            secondChance = false;
            System.out.println("Sorry,Reserve not met!");
        } else {
            secondChance = true;
            keyInBidValue();
        }
        return secondChance;
    }

    //compare endTime
    public boolean checkTimeOut() {
        if (getDate().compareTo(endTime) >= 0) {
            timeout = true;
        }
        return timeout;
    }

    //push the highest bidPrice
    public void push(double a) {
        bidPrice.add(a);
    }

    //pop the highest bidPrice
    public double pop() {
        return bidPrice.remove(bidPrice.size() - 1);
    }

    //peep the highest bid date
    public String peep_date() {
        String datee;
        if (date.size() != 0) {
            datee = date.get(date.size() - 1);
        } else {
            datee = date.get(0);
        }
        return datee;
    }

    //peep the highest bidPrice
    public Double peep_price() {
        transfer();
        Double bidd;
        if (bidPrice.size() != 0) {
            bidd = bidPrice.get(bidPrice.size() - 1);
        } else {
            bidd = bidPrice.get(0);
        }
        return bidd;
    }

    //peep the highest bidder's name
    public String peep_name() {
        String namee;
        if (name.size() != 1) {
            namee = name.get(name.size() - 1);
        } else {
            namee = name.get(0);
        }
        return namee;
    }

    //Display current bidder // use recursion method
    public void display(int count, int show) {
        if (name.get(count) != null) {
            if (count == name.size() - 1 && bidPrice.get(count) != 0) {
                if (bidPrice.get(count) == 0) {
                    show--;
                }
                if (name.get(count).length() < 8) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 16) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 24) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 32) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                }
            } else {
                if (bidPrice.get(count) == 0) {
                    show--;
                }
                if (name.get(count).length() < 8 && bidPrice.get(count) != 0) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 16 && bidPrice.get(count) != 0) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 24 && bidPrice.get(count) != 0) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                } else if (name.get(count).length() < 32 && bidPrice.get(count) != 0) {
                    System.out.println((show) + ".\t" + name.get(count) + "\t" + date.get(count) + "\t\t" + bidPrice.get(count));
                }
                display(count + 1, show + 1);
            }
        }
    }

    public boolean check_range(String value) {
        double currentValue = Double.parseDouble(value);
        double currentHighestValue = peep_price();
        double minP = Double.parseDouble(minPrice);
        leastPrice = minP * 0.95;
        mostPrice = minP * 1.05;
        if (leastPrice <= currentValue && mostPrice >= currentValue) {
            withinRange = true;
        } else {
        }
        return withinRange;
    }

    //for seller to accept or reject the highest bid
    public boolean acceptWinner() {
        Scanner inp = new Scanner(System.in);
        Scanner inpt = new Scanner(System.in);
        double hold = Double.parseDouble(minPrice);
        double currentHighestValue = peep_price();

        System.out.println("Your Reserved price is: RM" + hold);
        System.out.print("Would you like to update the reserved price?\n[1]Yes\n[2]No\nChoose:");
        int chg = inp.nextInt();
        switch (chg) {
            case 1:
                System.out.print("Enter a new reserve price: RM");
                hold = inpt.nextDouble();
                saveNewReservePrice(hold);
                System.out.println("------RESERVE PRICE UPDATED!-----");
                break;
            case 2:
                System.out.println("===RECORD SAVED . NO ACTION IS TAKEN===");
                break;
            default:
                System.out.println("===INVALID INPUT.TRY AGAIN!===");
                acceptWinner();
                break;
        }
        leastPrice = hold * 0.95;
        mostPrice = hold * 1.05;
        if (leastPrice <= currentHighestValue && mostPrice >= currentHighestValue) {
            System.out.print("Current Highest bid is ±5% of the minimum Price [RM" + currentHighestValue + "] . Would you like to accept the bid?/n[1]Yes/n[2]No/nChoose:");
            int temp = inp.nextInt();
            switch (temp) {
                case 1:
                    accept = true;
                    break;
                case 2:
                    accept = false;
                    break;
                default:
                    acceptWinner();
                    break;
            }
        }
        return accept;
    }

    //announce the winner
    public void announce_winner() {
        System.out.println("Date:" + getDate());
        System.out.println("The winner is   :" + peep_name());
        System.out.println("Date of bidding :" + peep_date());
        System.out.println("Value of bid:  RM" + peep_price());
    }

    //write into the bidder's textFile
    public void winner_profile() {
        //do this extra step from rewriting twice for the same winning item
        ArrayList<E> catb = new ArrayList<>();
        ArrayList<E> itb = new ArrayList<>();
        ArrayList<E> itIDb = new ArrayList<>();
        ArrayList<E> idb = new ArrayList<>();
        ArrayList<E> stb = new ArrayList<>();
        ArrayList<E> etb = new ArrayList<>();
        ArrayList<E> mpb = new ArrayList<>();
        ArrayList<E> atb = new ArrayList<>();
        ArrayList<E> ukb = new ArrayList<>();
        try {
            Scanner inputStream = new Scanner(new FileInputStream("Username/" + name.get(name.size() - 1) + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                catb.add((E) store[0]); // categories  //b for bidder
                itb.add((E) store[1]); //item
                itIDb.add((E) store[2]); //item ID
                idb.add((E) store[3]);   //Identity
                stb.add((E) store[4]);  // start time 
                etb.add((E) store[5]); //end Time
                mpb.add((E) store[6]);   //minimum price
                atb.add((E) store[7]);  //auction time
                ukb.add((E) store[8]);  //unknown

            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        if (itIDb.contains(item_ID) == false && atb.contains(aucType) == false) {
            try {
                PrintWriter write = new PrintWriter(new FileOutputStream("Username/" + name.get(name.size() - 1) + ".txt", true));
                write.println(categories + "," + item + "," + item_ID + "," + "Bidder" + "," + start_time + "," + getDate() + "," + bidPrice.get(bidPrice.size() - 1) + "," + aucType + "," + "Winning Item");
                write.close();
            } catch (IOException a) {
                System.out.println(a.getMessage());
            }
        }
    }

    //
    //write in seller.txt //match with item and start time , replace "-" with winning bid
    public void saveSellerResult() {
        ArrayList<E> cat = new ArrayList<>();
        ArrayList<E> it = new ArrayList<>();
        ArrayList<E> itID = new ArrayList<>();
        ArrayList<E> id = new ArrayList<>();
        ArrayList<E> st = new ArrayList<>();
        ArrayList<E> et = new ArrayList<>();
        ArrayList<E> mp = new ArrayList<>();
        ArrayList<E> at = new ArrayList<>();
        ArrayList<E> uk = new ArrayList<>();
        //transfer everything in seller_name.txt to categories
        try {
            Scanner inputStream = new Scanner(new FileInputStream("Username/" + seller_name + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                cat.add((E) store[0]);
                it.add((E) store[1]);
                itID.add((E) store[2]);
                id.add((E) store[3]);
                st.add((E) store[4]);
                et.add((E) store[5]);
                mp.add((E) store[6]);
                at.add((E) store[7]);
                uk.add((E) store[8]);

            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        //get the specific line by matching the item and start time
        int hold = 0;
        for (int i = 0; i < cat.size(); i++) {
            if (item.equals(it.get(i)) && start_time.equals(st.get(i))) {
                uk.remove(i);
                uk.add(i, (E) bidPrice.get(bidPrice.size() - 1));
                break;
            }
        }
        //write into seller.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/" + seller_name + ".txt"));

            for (int i = 0; i < cat.size(); i++) {
                write.println(cat.get(i) + "," + it.get(i) + "," + itID.get(i) + "," + id.get(i) + "," + st.get(i) + "," + et.get(i) + "," + mp.get(i) + "," + at.get(i) + "," + uk.get(i));

            }
            write.close();

        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //write in bidder.txt
    public void saveBidderResult() {
        //do this extra step from rewriting twice for the same winning item
        ArrayList<E> catb = new ArrayList<>();
        ArrayList<E> itb = new ArrayList<>();
        ArrayList<E> itIDb = new ArrayList<>();
        ArrayList<E> idb = new ArrayList<>();
        ArrayList<E> stb = new ArrayList<>();
        ArrayList<E> etb = new ArrayList<>();
        ArrayList<E> mpb = new ArrayList<>();
        ArrayList<E> atb = new ArrayList<>();
        ArrayList<E> ukb = new ArrayList<>();
        try {
            Scanner inputStream = new Scanner(new FileInputStream("Username/" + name.get(name.size() - 1) + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                catb.add((E) store[0]); // categories  //b for bidder
                itb.add((E) store[1]); //item
                itIDb.add((E) store[2]); //item ID
                idb.add((E) store[3]);   //Identity
                stb.add((E) store[4]);  // start time 
                etb.add((E) store[5]); //end Time
                mpb.add((E) store[6]);   //minimum price
                atb.add((E) store[7]);  //auction time
                ukb.add((E) store[8]);  //unknown

            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        if (stb.contains(start_time) == false) {
            try {
                PrintWriter write = new PrintWriter(new FileOutputStream("Username/" + name.get(name.size() - 1) + ".txt", true));
                write.println(categories + "," + item + "," + item_ID + "," + "Bidder" + "," + start_time + "," + getDate() + "," + bidPrice.get(bidPrice.size() - 1) + "," + aucType + "," + "Winning Item");
                write.close();
            } catch (IOException a) {
                System.out.println(a.getMessage());
            }
        }
    }

    //change expired date . as the item has sold .no further bidding require
    public void changeExpiredDate() {
        categories1.clear();
        item1.clear();
        itemID.clear();
        sellerName.clear();
        identityy.clear();
        startTime.clear();
        start_date.clear();
        end_time.clear();
        minimum_price.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                categories1.add((E) store[0]);
                item1.add((E) store[1]);
                itemID.add((E) store[2]);
                sellerName.add((E) store[3]);
                identityy.add((E) store[4]);
                startTime.add((E) store[5]);
                start_date.add((E) store[6]);
                end_time.add((E) store[7]);
                minimum_price.add((E) store[8]);

            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        for (int i = 0; i < item1.size(); i++) {
            if (itemID.get(i).equals(item_ID) && startTime.get(i).equals(start_time)) {
                end_time.remove(i);
                end_time.add(i, (E) setDate());
            }
        }
        //write into the file
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType + ".txt"));
            for (int i = 0; i < itemID.size(); i++) {
                createDatabase.println(categories1.get(i) + "," + item1.get(i) + "," + itemID.get(i) + "," + sellerName.get(i) + "," + identityy.get(i) + "," + startTime.get(i) + "," + start_date.get(i) + "," + end_time.get(i) + "," + minimum_price.get(i));
            }
            createDatabase.close();
        } catch (IOException b) {
            System.out.println(b.getMessage());
        }
    }

    //save new reserve price
    public void saveNewReservePrice(Double hold) {
        categories1.clear();
        item1.clear();
        itemID.clear();
        sellerName.clear();
        identityy.clear();
        startTime.clear();
        start_date.clear();
        end_time.clear();
        minimum_price.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                categories1.add((E) store[0]);
                item1.add((E) store[1]);
                itemID.add((E) store[2]);
                sellerName.add((E) store[3]);
                identityy.add((E) store[4]);
                startTime.add((E) store[5]);
                start_date.add((E) store[6]);
                end_time.add((E) store[7]);
                minimum_price.add((E) store[8]);

            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
        for (int i = 0; i < item1.size(); i++) {
            if (itemID.get(i).equals(item_ID) && startTime.get(i).equals(start_time)) {
                minimum_price.remove(i);
                minimum_price.add(i, (E) hold);
            }
        }
        //write into the file
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType + ".txt"));
            for (int i = 0; i < itemID.size(); i++) {
                createDatabase.println(categories1.get(i) + "," + item1.get(i) + "," + itemID.get(i) + "," + sellerName.get(i) + "," + identityy.get(i) + "," + startTime.get(i) + "," + start_date.get(i) + "," + end_time.get(i) + "," + minimum_price.get(i));
            }
            createDatabase.close();
        } catch (IOException b) {
            System.out.println(b.getMessage());
        }
    }

    //conclude the reserveAuction
    public void reserveAction() {
        checkTimeOut();
        if (timeout == false) {
            databaseCheck();
            getDate();
            transfer();
            if (username.equals(seller_name)) {
                acceptWinner();
                if (accept == true) {
                    announce_winner();
                    winner_profile();
                    saveSellerResult();
                    saveBidderResult();
                }
            }
            if (!username.equals(seller_name)) {
                newBid();
                transfer();
                checkTimeOut();
            }
        } else if (checkTimeOut() == true && name.size() != 0) {
            announce_winner();
            System.out.println("We're Sorry " + username.toUpperCase() + "! The item you chose is already Expired.");
            System.out.println("Please re-choose an available Item :)");
            AuctionType<String> at = new AuctionType<String>(username);
            at.AuctionType();
        } else if (checkTimeOut() == true && name.size() == 0) {
            System.out.println("We're Sorry " + username.toUpperCase() + "! The item you chose is already Expired.");
            System.out.println("Please re-choose an available Item :)");
            AuctionType<String> at = new AuctionType<String>(username);
            at.AuctionType();
        }
    }

}
