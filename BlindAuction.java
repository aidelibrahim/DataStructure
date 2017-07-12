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
import java.util.Scanner;

public class BlindAuction<E> extends AuctionType {

    public String bid;
    public String username;
    public String aucType;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<Double> Bid = new ArrayList<Double>();
    private String[] store = new String[9];
    int count = 0;
    boolean timeout = false;
    E item;
    E endTime;
    E venue;
    E minPrice;
    E item_ID;
    E categories;
    E seller_name;
    E start_time;

    public BlindAuction(String username, String aucType, E item, E endTime, E venue, E minPrice, E categories, E seller_name, E start_time, E item_ID) {
        super(username);
        this.username = username;
        this.item = item;
        this.aucType = aucType;
        this.endTime = endTime;
        this.venue = venue;
        this.minPrice = minPrice;
        this.categories = categories;
        this.seller_name = seller_name;
        this.start_time = start_time;
        this.item_ID = item_ID;
    }

    //check whether the item_AuctionType file exists else create one 
    public void databaseCheck() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            inputstream.close();
        } catch (FileNotFoundException a) {
            System.out.println(a.getMessage());
            databaseCreate();
        }
    }

    //create the database
    public void databaseCreate() {
        try {
            PrintWriter createDatabase = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item  + ".txt"));
            createDatabase.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //transfer every data in item_BlindAuction.txt into ArrayList
    public void transfer() {
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

    //get current Date and Time
    public String getDate() {
        //call out date
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = formatter.format(date);
        return s;
    }

    //key in the bid from bidders in item_BlindAuction.txt
    public void keyInBidValue() {
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(aucType+"/"+item_ID + "_" + item + ".txt", true));
            write.println(username + "," + getDate() + "," + bid);
            write.close();
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
    }

    //prompt user to enter new Bid(){
    public void newBid() {
        double check = 0;

        Scanner read = new Scanner(System.in);
        System.out.print("Enter your bid in [RM]:");
        bid = read.nextLine();     //Initially you wrote integer . what if the user enter double value or other character ? The system will have error. Hence , we use string as input and check for them .      
        check = Double.parseDouble(bid);
        keyInBidValue();
        //check whether the specific item has reach the entime
        if (checkTimeOut() == false) {
            System.out.println("Your Bid has been entered Successfully!");
        } else {
            System.out.println("We're sorry " + username + " . This item has expired.");
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
            System.out.println("Number of bidder:" + (linenumber - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //will keep track by seller
    public void Sorting() {
        transfer();
        //use bubble sorting to sort the bid in ascending order
        for (int j = 0; j < name.size(); j++) {
            //System.out.println("j = " + j);
            for (int k = 0; k < name.size() - 1; k++) {
                if (Bid.get(k) != null) {
                    if (Bid.get(k).compareTo(Bid.get(k + 1)) > 0) {
                        Double hold = Bid.get(k);
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

        //display the winner
        int minValue = Integer.parseInt((String) minPrice);
        if(Bid.size()!=0){
        if (Bid.get(Bid.size() - 1) > minValue) {                           //if bid line number > minumum value set by seller
            System.out.println("Winner is:  " + name.get(name.size() - 1)); //should display the name ,followed by the winning price
            System.out.println("With the value of: " + Bid.get(Bid.size() - 1));
        }
      }
        //write in bidder.txt
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
                PrintWriter write = new PrintWriter(new FileOutputStream("Username"+name.get(name.size() - 1) + ".txt", true));
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
            Scanner inputStream = new Scanner(new FileInputStream("Username"+seller_name + ".txt"));
            while (inputStream.hasNextLine()) {
                String check = inputStream.nextLine();
                int counter1 = 0;
                for (String retrieve : check.split(",")) {
                    store[counter1] = retrieve;
                    counter1++;
                }
                cat.add((E) store[0]); // categories
                it.add((E) store[1]); //item
                itID.add((E) store[2]); //item ID
                id.add((E) store[3]);   //Identity
                st.add((E) store[4]);  // start time 
                et.add((E) store[5]); //end Time
                mp.add((E) store[6]);   //minimum price
                at.add((E) store[7]);  //auction time
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
            PrintWriter write = new PrintWriter(new FileOutputStream("Username"+seller_name + ".txt"));

            for (int i = 0; i < cat.size(); i++) {
                write.println(cat.get(i) + "," + it.get(i) + "," + itID.get(i) + "," + id.get(i) + "," + st.get(i) + "," + et.get(i) + "," + mp.get(i) + "," + at.get(i) + "," + uk.get(i));

            }
            write.close();

        } catch (IOException a) {
            System.out.println(a.getMessage());
        }

    }

    //compare endTime
    public boolean checkTimeOut() {
        if (getDate().compareTo((String) endTime) >= 0) {
            timeout = true;
        }
        return timeout;
    }

    //Display current bidder // use recursion method
    public void display(int count, int show) {
        if (count == name.size() - 1 && Bid.get(count) != 0) {
            if (Bid.get(count) == 0) {
                show--;
            }
            if (name.get(count).length() < 8) {
                System.out.println((show) + "." + name.get(count) + "\t\t\t\t" + date.get(count));
            } else if (name.get(count).length() < 16) {
                System.out.println((show) + "." + name.get(count) + "\t\t\t" + date.get(count));
            } else if (name.get(count).length() < 24) {
                System.out.println((show) + "." + name.get(count) + "\t\t" + date.get(count));
            } else if (name.get(count).length() < 32) {
                System.out.println((show) + "." + name.get(count) + "\t" + date.get(count));
            }
        } else {
            if (Bid.get(count) == 0) {
                show--;
            }
            if (name.get(count).length() < 8 && Bid.get(count) != 0) {
                System.out.println((show) + "." + name.get(count) + "\t\t\t\t" + date.get(count));
            } else if (name.get(count).length() < 16 && Bid.get(count) != 0) {
                System.out.println((show) + "." + name.get(count) + "\t\t\t" + date.get(count));
            } else if (name.get(count).length() < 24 && Bid.get(count) != 0) {
                System.out.println((show) + "." + name.get(count) + "\t\t" + date.get(count));
            } else if (name.get(count).length() < 32 && Bid.get(count) != 0) {
                System.out.println((show) + "." + name.get(count) + "\t" + date.get(count));
            }
            display(count + 1, show + 1);
        }
    }

    public void Exit() {
        transfer();
        System.out.println("======================================================================");
        System.out.println("  Name\t\t\t\tDate[D/M/Y H:M:S]");
        System.out.println("......................................................................");
        display(count, 1);
        System.out.println("======================================================================");
        CollectBid();
        System.out.println("Thank you for your bid. Good Luck!");
        System.out.println("The Winner will be annouced at:" + venue + " " + endTime);
    }

    public void BlindAuction() {
        checkTimeOut();
        if (timeout == false) {
            databaseCheck();
            transfer();
            newBid();
            Exit();
        }
        checkTimeOut();
        if (checkTimeOut() == true && name.size()==0) {
            System.out.println("We're sorry <"+username.toUpperCase()+"> . The item is expired!");
        }
        if (checkTimeOut() == true && name.size()!=0) {
            Sorting();
            saveSellerResult();
            System.out.println("We're sorry " + username + " .The item is expired .Please rechoose an available Item :)");
            AuctionType<String> at = new AuctionType<String>(username);
            at.AuctionType();
        }
    }
    // havent do the frequency bidder in the bidder.txt (report)
    // how to keep track on the end time, check sorting and winner (use thread)
}
