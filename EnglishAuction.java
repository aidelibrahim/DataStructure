
package auction_2016;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class EnglishAuction<E> extends AuctionType {

    public double price;
    public String username;
    public E item;
    public E endTime;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<Double> Bid = new ArrayList<>();
    LinkedList<String> checkName = new LinkedList<>();
    LinkedList<String> checkDate = new LinkedList<>();
    LinkedList<String> checkBit = new LinkedList<>();
    int getNum = 0;
    private String[] store = new String[9];
    double check = 0;
    String minPrice;
    double max;
    int count = 0;
    int choose;
    boolean timeout = false;
    String aucType;
    E categories;
    E seller_name;
    E start_time;
    E item_ID;
    E start_date;

    public EnglishAuction(String username, String aucType, E item, E endTime, E categories, E seller_name, E start_time, E item_ID, E start_date, String minPrice) {
        super(username);
        this.username = username;
        this.aucType = aucType;
        this.item = item;
        this.endTime = endTime;
        this.categories = categories;
        this.seller_name = seller_name;
        this.start_time = start_time;
        this.item_ID = item_ID;
        this.start_date = start_date;
        this.minPrice = minPrice;
        max = Double.parseDouble(minPrice);
    }

    public void databaseCheck() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            inputstream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
            databaseCreate();
        }
    }

    public void databaseCreate() {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            createDatabase.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //transfer and catagorize the file
    public void FileTransfer() {
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
                name.add((String) (E) store[0]);
                date.add((String) (E) store[1]);
                Double hold = Double.parseDouble(store[2]);
                Bid.add(hold);
            }
            inputStream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
        }
    }

    //get the highest bit for comparing
    public double getCurrentHighestPrice() {

        for (int i = 0; i < Bid.size(); i++) {
            if (Bid.get(i) > max) {
                max = Bid.get(i);
            }
        }
        if (price > max) {
            return price;
        } else {
            return max;
        }
    }

    public boolean checkValueEntered() {

        System.out.println("\nPlease re-enter a higher value !");
        return false;

    }

    public void EnterNewValue() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter your bid in [RM]: ");
        price = read.nextDouble();

        if (price > max) {
            System.out.println("Your Bid has been entered Successfully!");
            keyInBidValue();
        } else {
            checkValueEntered();
            EnterNewValue();
        }

        if (price > max) {
            max = price;
        }
    }

    public void chooseAction() {
        Scanner choose = new Scanner(System.in);
        System.out.println("\nYour Bid must be higher than RM" + getCurrentHighestPrice());
        System.out.println("Do you wish to continue or surrender?");
        System.out.println("[1]Continue\n[2]Surrender\nChoose:");
        String in = choose.nextLine();
 
        switch (in) {
            case "1":
                EnterNewValue();
                FileTransfer();
                System.out.println("======================================================================");
                System.out.println("Name\t\t\t\tDate[D/M/Y H:M:S]\t\tBid[RM]");
                System.out.println("......................................................................");
                display(count, 1);
                System.out.println("======================================================================");
                break;
            case "2":
                System.out.println("Thank You for your time.You have quit the auction");
                System.out.print("----------- You have quit the auction at :" + getDate() + " ----------\n");
                CheckName();
                for (int i = 0; i < checkName.size(); i++) {
                    if (username.equals(checkName.get(i))) {
                        checkName.remove(i);
                        checkDate.remove(i);
                        checkBit.remove(i);
                    }
                }
                //write into the text file
                try {
                    PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
                    for (int i = 0; i < checkName.size(); i++) {
                        write.println(checkName.get(i) + "," + checkDate.get(i) + "," + checkBit.get(i));
                    }
                    write.close();
                } catch (IOException a) {
                    System.out.println(a.getMessage());
                }
                break;
            default:
                chooseAction();
                break;
        }
    }

    public void keyInBidValue() {
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item + ".txt", true));
            write.println(username + "," + getDate() + "," + price);
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    public void getTime() {

        if (checkTimeOut() == false) {
            System.out.println("Welcome To --> " + item + " , " + aucType + " !");
        } 
    }

    public boolean checkTimeOut() {
        if (getDate().compareTo((String) endTime) >= 0) {
            timeout = true;
        }
        return timeout;
    }

    public void checkWinner() {
        if(name.size()!=0){
        System.out.println("The winner is " + name.get(name.size() - 1));
        System.out.println("Winner's price: RM" + Bid.get(Bid.size() - 1));
    }
    }
    public String getDate() {
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    //supervise the number of bidder
    public void CheckName() {
        checkName.clear();
        checkDate.clear();
        checkBit.clear();
        try {
            Scanner inputStream = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
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
        for (int i = 0; i < Bid.size(); i++) {
            if (Bid.get(i) == 0) {
                getNum--;
            }
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
            Scanner inputStream = new Scanner(new FileInputStream("Username/"+name.get(name.size() - 1) + ".txt"));
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
                PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+name.get(name.size() - 1) + ".txt", true));
                write.println(categories + "," + item + "," + item_ID + "," + "Bidder" + "," + start_time + "," + getDate() + "," + Bid.get(Bid.size() - 1) + "," + aucType + "," + "Winning Item");
                write.close();
            } catch (IOException a) {
                System.out.println(a.getMessage());
            }
        }
    }

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
            Scanner inputStream = new Scanner(new FileInputStream("Username/"+seller_name + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                cat.add((E) store[0]); //categories
                it.add((E) store[1]);  //item
                itID.add((E) store[2]); //itme ID
                id.add((E) store[3]);  //identity
                st.add((E) store[4]);   //start time
                et.add((E) store[5]);  //end time
                mp.add((E) store[6]);  //minumum price
                at.add((E) store[7]);   //auction type
                uk.add((E) store[8]);  //unknown

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
                uk.add(i, (E) Bid.get(Bid.size() - 1));
                break;
            }
        }
        //write into seller.txt
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream("Username/"+seller_name + ".txt"));
            for (int i = 0; i < cat.size(); i++) {
                write.println(cat.get(i) + "," + it.get(i) + "," + itID.get(i) + "," + id.get(i) + "," + st.get(i) + "," + et.get(i) + "," + mp.get(i) + "," + at.get(i) + "," + uk.get(i));

            }
            write.close();

        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    public void display(int count, int show) {
        if (name.get(count) != null) {
            if (count == name.size() - 1 && Bid.get(count) != 0) {
                if (Bid.get(count) == 0) {
                    show--;
                }
                if (name.get(count).length() < 8) {
                    System.out.println((show) + "." + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 16) {
                    System.out.println((show) + "." + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 24) {
                    System.out.println((show) + "." + name.get(count) + "\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 32) {
                    System.out.println((show) + "." + name.get(count) + "\t" + date.get(count) + "\t\t" + Bid.get(count));
                }
            } else {
                if (Bid.get(count) == 0) {
                    show--;
                }
                if (name.get(count).length() < 8 && Bid.get(count) != 0) {
                    System.out.println((show) + "." + name.get(count) + "\t\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 16 && Bid.get(count) != 0) {
                    System.out.println((show) + "." + name.get(count) + "\t\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 24 && Bid.get(count) != 0) {
                    System.out.println((show) + "." + name.get(count) + "\t\t" + date.get(count) + "\t\t" + Bid.get(count));
                } else if (name.get(count).length() < 32 && Bid.get(count) != 0) {
                    System.out.println((show) + "." + name.get(count) + "\t" + date.get(count) + "\t\t" + Bid.get(count));
                }
                display(count + 1, show + 1);
            }
        }
    }

    public void CollectBid() {
        int linenumber = 0;
        try {

            File file = new File(aucType+"/"+item_ID + "_" + item + ".txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);

                while (lnr.readLine() != null) {
                    linenumber++;
                }
            }
            System.out.println("Number of bidder:" + linenumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EnglishAuction() {
        getTime();
        if (timeout == false) {
            databaseCheck();
            FileTransfer();
            chooseAction();
            CheckName();
            if (checkName.size() == 0) {
                checkWinner();
                saveBidderResult();
                saveSellerResult();
            }
        } else if(checkTimeOut() == true && name.size()!=0){
            System.out.println("We're Sorry " + username.toUpperCase() + "! The item you choosed is already Expired.");
            FileTransfer();
            checkWinner();
            saveBidderResult();
            saveSellerResult();
            System.out.println("Please rechoose an available item !");
            AuctionType<String> at = new AuctionType<String>(username);
            at.AuctionType();
        }
         else if(checkTimeOut() == true && name.size()==0){
              System.out.println("We're Sorry " + username.toUpperCase() + "! The item you choosed is already Expired.");
             System.out.println("Please rechoose an available item !");
            AuctionType<String> at = new AuctionType<String>(username);
            at.AuctionType();
         }
    }
}
