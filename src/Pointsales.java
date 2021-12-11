import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
//////////////////////////////////////////////
////// rolan, lance frazer O.
////// abstract and extend
/////////////////////////////////////////////

abstract class dataItem {
    String[] itemName = { "soap", "bread", "flour", "egg", "chicken", "ketchup", "vitamin", "oil", "vinegar",
            "onions" };
    double[] itemPrice = { 6.0, 5.0, 4.0, 1.0, 10.0, 12.0, 15.0, 8.0, 8.0, 8.0 };
}

class getdata extends dataItem {

}

class reciept extends dataItem {

    private List<String> names = new ArrayList<String>();
    private List<Double> prices = new ArrayList<Double>();
    private List<Integer> quntity = new ArrayList<Integer>();
    String[] namesArr = new String[this.names.size()];
    Double[] priceArr = new Double[this.prices.size()];
    Integer[] quantityArr = new Integer[this.quntity.size()];

    double totalprice = 0.00;

    void setdata(String name, int numItems) {
        for (int i = 0; i < itemName.length; i++) {
            if (name.equals(this.itemName[i])) {
                this.names.add(this.itemName[i]);
                this.prices.add(this.itemPrice[i]);
                this.quntity.add(numItems);
            }
        }
    }

    void printlayout() {

        namesArr = names.toArray(namesArr);
        quantityArr = quntity.toArray(quantityArr);
        priceArr = prices.toArray(priceArr);

        System.out.printf("%-20s %20s %20s %20s %20s", "Item Name", "Quantity", "price per item", "price",
                "price with VAT");
        for (int i = 0; i < namesArr.length; i++) {
            System.out.printf("\n%-20s %20d %20.2f %20.2f %20.2f", namesArr[i], quantityArr[i], priceArr[i],
                    (quantityArr[i] * priceArr[i]),
                    ((quantityArr[i] * priceArr[i]) + ((quantityArr[i] * priceArr[i]) * 0.12)));
        }
        for (int i = 0; i < priceArr.length; i++) {
            totalprice = totalprice + (priceArr[i] * quantityArr[i]);
        }
        totalprice = totalprice + (totalprice * 0.12);
        System.out.printf("\n\n%95s %.2f", "total price: ", totalprice);

    }

    void payment(double bill){
        if(bill > this.totalprice){
            System.out.println("you had a change " + (bill-this.totalprice));
        }else if(bill < this.totalprice){
            System.out.println("you dont had enough money");
        }else if(bill == this.totalprice){
            System.out.println("you dont had a change");
        }

    }

    

}

public class Pointsales {
    public static void main(String[] args) throws Exception {

        getdata data = new getdata();
        reciept reciepting = new reciept();

        System.out.println("hello, welcome to ur shop");
        System.out.println("which items would you like to buy?");
        System.out.printf("%s\t\t%20s\n", "name", "price");
        System.out.printf("%s\t\t%20s\n", "----", "-----");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\t\t%20.2f\n", data.itemName[i], data.itemPrice[i]);
        }

        Scanner input = new Scanner(System.in);
        Scanner answer = new Scanner(System.in);
        while (true) {
            System.out.println("name of the item?");
            String boughtItem = input.next();

            while (true) {
                String tag = "";
                for (int i = 0; i < data.itemName.length; i++) {
                    if (boughtItem.equals(data.itemName[i].toString())) {
                        tag = data.itemName[i].toString();
                        break;
                    }
                }
                if (tag.equals(boughtItem)) {
                    break;
                } else {
                    System.out.println("invalid name from shoplist,reenter the correct item.\n>");
                    boughtItem = input.next();
                }
            }
            System.out.println("how many " + boughtItem + "?");
            int quanti = input.nextInt();
            input.nextLine();
            reciepting.setdata(boughtItem, quanti);

            System.out.println("view my order? y/n");
            String loop = answer.next().toLowerCase();
            if (loop.equals("y")) {
                reciepting.printlayout();
                System.out.println("\nwant to purchase your order?");
                loop = answer.next().toLowerCase();
                if (loop.equals("y")) {
                    answer.close();
                    break;
                }
            }
        }
        System.out.print("how much u would like to pay?");
        input.next();
        double pay = input.nextDouble();
        reciepting.payment(pay);

        input.close();
       
    }
}
