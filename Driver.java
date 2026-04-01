import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

class Pizza{
    protected static String [] vegPizzas = {"Margreta","Italian","Seven Cheesy"};
    protected static int[] vegPizzaPrice = {120,200,250};
    protected static String [] NonvegPizzas = {"Non-veg special","pepperoni","meat lover pizza"};
    protected static int[] nonvegPizzaPrice = {200,350,300};

    public static void PrintPizza(Boolean flag){
        System.out.println("-------------------------");
        if(flag==true){
            for(int i=0 ; i<vegPizzas.length ; i++){
                System.out.println("no." + (i+1) + " Pizza name : " + vegPizzas[i] + " ->  Price  -> " + vegPizzaPrice[i]);
            }
        }else{
            for(int i=0 ; i<NonvegPizzas.length ; i++){
                System.out.println("no." + (i+1) + " Pizza name : " + NonvegPizzas[i] + " ->  Price  -> " + nonvegPizzaPrice[i]);
            }
        }
        System.out.println("-------------------------");
    }
}

class Customer extends Pizza{
    private String name;
    int totalPizzas = 0;
    int totalPrice = 0;

    Customer(String n){
        name = n;
    }

    public void SelectPizza(Scanner sc){
        int counter = 0;
        String moreDecider = null;
        String flg = null;

        while(true){
            if(counter==0){
                System.out.print("Hey " + name + " What pizza do you want veg or non-veg :");
                flg = sc.nextLine();

                if(flg.equals("veg")){
                    System.out.println("Select the pizza number from below list");
                    Pizza.PrintPizza(true);
                    int no = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Select Quantity of " + Pizza.vegPizzas[no-1] + " :");
                    int Quantity = Integer.parseInt(sc.nextLine().trim());
                    totalPizzas += Quantity;
                    totalPrice += Pizza.vegPizzaPrice[no-1] * Quantity;
                    System.out.println(Quantity + " number of " + Pizza.vegPizzas[no-1] + " added to your order");

                }else if(flg.equals("non-veg")){
                    System.out.println("Select the pizza number from below list");
                    Pizza.PrintPizza(false);
                    int no = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Select Quantity of " + Pizza.NonvegPizzas[no-1] + " :");
                    int Quantity = Integer.parseInt(sc.nextLine().trim());
                    totalPizzas += Quantity;
                    totalPrice += Pizza.nonvegPizzaPrice[no-1] * Quantity;
                    System.out.println(Quantity + " number of " + Pizza.NonvegPizzas[no-1] + " added to your order");
                }
                counter += 1;

            }else{
                System.out.println("Do you want more pizzas ?");
                moreDecider = sc.nextLine();

                if(moreDecider.equals("yes")){
                    System.out.println("What type of pizza you want to add more veg or non veg ?");
                    flg = sc.nextLine();

                    if(flg.equals("veg")){
                        System.out.println("Select the pizza number from below list");
                        Pizza.PrintPizza(true);
                        int no = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Select Quantity of " + Pizza.vegPizzas[no-1] + " :");
                        int Quantity = Integer.parseInt(sc.nextLine().trim());
                        totalPizzas += Quantity;
                        totalPrice += Pizza.vegPizzaPrice[no-1] * Quantity;
                        System.out.println(Quantity + " number of " + Pizza.vegPizzas[no-1] + " added to your order");

                    }else if(flg.equals("non-veg")){
                        System.out.println("Select the pizza number from below list");
                        Pizza.PrintPizza(false);
                        int no = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Select Quantity of " + Pizza.NonvegPizzas[no-1] + " :");
                        int Quantity = Integer.parseInt(sc.nextLine().trim());
                        totalPizzas += Quantity;
                        totalPrice += Pizza.nonvegPizzaPrice[no-1] * Quantity;
                        System.out.println(Quantity + " number of " + Pizza.NonvegPizzas[no-1] + " added to your order");
                    }
                    counter += 1;

                }else if(moreDecider.equals("no")){
                    break;
                }
            }
        }
        generateBill();
    }

    void generateBill(){
        File f = new File("./customers.txt");
        if(!f.exists()){
            try{
                f.createNewFile();
            }catch(IOException e){
                System.out.println(e);
            }
        }
        String str = "Customer name : " + name + " total pizza bought : " + totalPizzas + " total price of that pizza " + totalPrice + "\n";
        try{
            FileWriter fw = new FileWriter("./customers.txt", true);
            fw.write(str);
            fw.close();
        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println(str);
    }
}

class Driver{
    public static void main(String args[]){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Enter your name first");
        String st1 = sc1.nextLine();
        Customer c1 = new Customer(st1);
        c1.SelectPizza(sc1);
    }
}
```

---

## Kya changes kiye:
```
✅ Ek hi Scanner (sc1) poore program me
✅ SelectPizza(Scanner sc) — parameter se Scanner lega
✅ nextInt() → nextLine() — pipe input ke liye better
✅ FileWriter ek baar me poora string likhta hai
