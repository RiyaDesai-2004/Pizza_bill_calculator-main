/*

    Project name : Pizza bill calculator
     
        : developed in java version : 18.0.2
        : can be runned by any compatible OS 
        : compatible java version should be installed and setted to path
        : to compile : javac Driver.java
        : runned by : java Driver 
*/
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

class Pizza{

    // static list of veg pizza that customer can buy
    protected static String [] vegPizzas = {"Margreta","Italian","Seven Cheesy"};
    // static list of veg pizza price  
    protected static int[] vegPizzaPrice = {120,200,250};

    // static list of non-veg pizza that customer can buy
    protected static String [] NonvegPizzas = {"Non-veg special","pepperoni","meat lover pizza"};
    // static list of non veg pizza price  
    protected static int[] nonvegPizzaPrice = {200,350,300};


    // a method that prints veg pizza if flag passed is true otherwise prints non veg if fflag passed is false
    public static void PrintPizza(Boolean flag){
        System.out.println("-------------------------");
        if(flag==true){
            for(int i=0 ; i<vegPizzas.length ; i++){
                System.out.println("no." +  (i+1)  +  " Pizza name : " + vegPizzas[i] + " ->  Price  -> " + vegPizzaPrice[i]);
            }
        }else{
            for(int i=0 ; i<NonvegPizzas.length ; i++){
                System.out.println("no." +  (i+1)  + " Pizza name : " + NonvegPizzas[i] + " ->  Price  -> " + nonvegPizzaPrice[i]);
            }
        }
        System.out.println("-------------------------");
    }
}

class Customer extends Pizza{
    // name of customer
    private String name;
    // storing the total number of pizzas 
    int totalPizzas =0 ;
    // total price of pizzas 
    int totalPrice = 0;

    // custructor that takes n (name) as argument and set instance string referce to passed string object
    Customer(String n){
        name = n;
    }
    public void SelectPizza(){
        // counter flag for deciding the visit is first or not
        int counter = 0 ;
        Scanner sc = new Scanner(System.in);
        // decider that decides whether user wants another pizza or not
        String moreDecider = null;
        // flag is used for deciding the pizza between veg and non veg
        String flg=null;
        while(true){
            if(counter==0){
                // selecting and setting the flag for veg and non veg pizza
                System.out.print("Hey " + name  +  " What pizza do you want veg or non-veg :");
                flg  = sc.nextLine();
                
                // if the pizza is veg 
                if(flg.equals("veg")){

                    System.out.println("Select the pizza number from below list");
                    // passes true to print pizza to list all veg pizzas
                    Pizza.PrintPizza(true);
                    // select the number of pizza from list
                    int no = sc.nextInt();
                    // print the name of pizza in given number
                    System.out.print("Select Quantity of " + Pizza.vegPizzas[no-1] + " :"); //index match karva mate no-1
                    // getting the quantity of selected pizza
                    int Quantity = sc.nextInt();
                    // add quantity to total pizzas instance variable
                    totalPizzas += Quantity;
                    // add price of pizza to total price 'quantity times'
                    for(int i=0 ; i< Quantity ; i++){
                        totalPrice+=Pizza.vegPizzaPrice[no-1];
                    }
                    System.out.println( Quantity +  " number of " + Pizza.vegPizzas[no-1] + " added to your order");

                }else if(flg.equals("non-veg")){

                    System.out.println("Select the pizza number from below list");
                    Pizza.PrintPizza(false);
                    int no = sc.nextInt();
                    System.out.print("Select Quantity of " + Pizza.NonvegPizzas[no-1] + " :");
                    int Quantity = sc.nextInt();
                    totalPizzas += Quantity;
                    for(int i=0 ; i< Quantity ; i++){
                            totalPrice+=Pizza.nonvegPizzaPrice[no-1];
                        }
                    System.out.println( Quantity +  " number of " + Pizza.NonvegPizzas[no-1] + " added to your order");
                }
                counter+=1;

            }else{
                // if user wants more pizzas 
                System.out.println("Do you want more pizzas ?");
                moreDecider = sc.next();

                // if moredecider = yes 
                if(moreDecider.equals("yes")){
                    // get the type of pizza 
                    System.out.println("What type of pizza you want to add more veg or non veg \nEnter veg for veg and non-veg for non veg ?");
                    flg  = sc.next();

                    if(flg.equals("veg")){

                        System.out.println("Select the pizza number from below list");
                        Pizza.PrintPizza(true);
                        int no = sc.nextInt();
                        System.out.print("Select Quantity of " + Pizza.vegPizzas[no-1] + " :");
                        int Quantity = sc.nextInt();
                        totalPizzas += Quantity;
                        for(int i=0 ; i< Quantity ; i++){
                            totalPrice+=Pizza.vegPizzaPrice[no-1];
                        }
                        System.out.println( Quantity +  " number of " + Pizza.vegPizzas[no-1] + " added to your order");

                    }else if(flg.equals("non-veg")){

                        System.out.println("Select the pizza number from below list");
                        Pizza.PrintPizza(false);
                        int no = sc.nextInt();
                        System.out.print("Select Quantity of " + Pizza.NonvegPizzas[no-1] + " :");
                        int Quantity = sc.nextInt();
                        totalPizzas += Quantity;
                        for(int i=0 ; i< Quantity ; i++){
                            totalPrice+=Pizza.nonvegPizzaPrice[no-1];
                        }
                        System.out.println( Quantity +  " number of " + Pizza.NonvegPizzas[no-1] + " added to your order");
                        
                    }
                    counter+=1;

                }else if(moreDecider.equals("no")){
                    // exit loop if user dont want extra pizza
                    break;
                }
            }
        }
        generateBill();
        return ;
    }

    void generateBill(){
        // create new file object
        File f = new File("./customers.txt");  //filepath , file no obj banyo
        // if file doesn't exist create new file
        if(!f.exists()){
            try{
                f.createNewFile();
            }catch(IOException e){
                System.out.println(e);
            }
        }
        // after creating the file or checking if it exist append the string to file
        String str = "Customer name : " + name + " total pizza bought : " + totalPizzas + " total price of that pizza " + totalPrice + "\n";
        for(int i=0 ; i<str.length() ; i++){
            try{
                FileWriter fw = new FileWriter("./customers.txt",true);
                fw.write(str.charAt(i));
                fw.close();
            }catch(IOException e){
                System.out.println(e);
            }
        }
        System.out.println(str); 
        return ;
    }
}

class Driver{
    public static void main(String args[]){
        Scanner sc1 = new Scanner(System.in);
        String st1; // customer name
        // getting the name of customer
        System.out.println("Enter your name first");
        st1 = sc1.nextLine();
        // passing the entered name to constructor of Customer class
        Customer c1 = new Customer(st1);
        // calling method to select and order pizza
        c1.SelectPizza();
    }
}