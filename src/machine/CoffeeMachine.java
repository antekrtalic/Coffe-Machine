package machine;
import java.util.HashMap;
import java.util.Scanner;


public class CoffeeMachine {

    private static Scanner scanner = new Scanner(System.in);
    private static int avlMoney = 550;
    private static int avlWater = 400;
    private static int avlMilk = 540;
    private static int avlCoffeeBeans = 120;
    private static int avlCups = 9;
    private static final HashMap<String, Integer> actMap = new HashMap<>();

    private static final int[][] coffeeDetails = {{250, 0, 16, 4}, // expresso
                                                    {350, 75, 20, 7}, // latte
                                                    {200, 100, 12, 6}}; // cappucino
   
    
    public static void main(String[] args) {

        actMap.put("buy", 0);
        actMap.put("fill", 1);
        actMap.put("take", 2);
        actMap.put("remaining", 3);
        actMap.put("exit", 4);

        boolean toContinue = true;

        do {
            String action = doAction();
            switch (actMap.get(action)) {
                case 0:
                    buy();
                    break;
                case 1:
                    fill();
                    break;
                case 2:
                    take();
                    break;
                case 3:
                    coffeeMachineQuantityDetails();
                    break;
                case 4:
                    toContinue = false;
                    break;
            }
        }while (toContinue);

    }
    
    public static String doAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit)");
        return scanner.next().toLowerCase();
    }

    public static void take() {
        System.out.println("I gave you $" + avlMoney);
        avlMoney = 0;
    }

    public static void fill() {
        System.out.println("Write how many ml of water you want to add:");
        int mlOfWater = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int mlOfMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeBeans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int disposableCups = scanner.nextInt();

        avlWater += mlOfWater;
        avlMilk += mlOfMilk;
        avlCoffeeBeans += coffeeBeans;
        avlCups += disposableCups;
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String pickCoffe = scanner.next();
        if (pickCoffe.equals("back")) return;
        int coffeeType = Integer.parseInt(pickCoffe);
        if (enoughResource(coffeeType)) {
            avlWater -= coffeeDetails[coffeeType - 1][0];
            avlMilk -= coffeeDetails[coffeeType - 1][1];
            avlCoffeeBeans -= coffeeDetails[coffeeType - 1][2];
            avlCups--;
            avlMoney += coffeeDetails[coffeeType - 1][3];
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    public static boolean enoughResource(int i) {
        if (avlWater < coffeeDetails[i - 1][0]) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (avlMilk < coffeeDetails[i - 1][1]) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (avlCoffeeBeans < coffeeDetails[i - 1][2]) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (avlCups < 1) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        return true;
    }

    public static void coffeeMachineQuantityDetails() {
        String quantity = "The coffee machine has:\n"
                        + avlWater + " ml of water\n"
                        + avlMilk + " ml of milk\n"
                        + avlCoffeeBeans + " g of coffee beans\n"
                        + avlCups + " disposable cups\n"
                        + "$" + avlMoney + " of money";
        System.out.println(quantity);
    }
}
