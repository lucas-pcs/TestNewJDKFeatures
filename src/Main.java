import model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        displayWelcomeMessage();

        double sumValueOfCartItens = 0;
        double cardLimit = getCardLimit();
        boolean userHasLimit = true;

        // create the item list
        List<Product> listOfProducts = new ArrayList<>();

        do {
            // create a new item
            Product product = loopOfBuying();

            // get the value of sum that will be used further to check if overflow the cardLimit
            sumValueOfCartItens += product.getValue();

            // check card limit
            userHasLimit = checkCardLimit(cardLimit, sumValueOfCartItens);
             if(!userHasLimit){
                 break;
             }

            // add the new item on list
            listOfProducts.add(product);

            // sort the list
            listOfProducts.sort(Comparator.comparing(Product::getValue));

            displayResumeOfCart(listOfProducts);
        } while ((userWantToContinue()));

        displayResumeOfCart(listOfProducts);
        displayFinalMessage();
    }

    private static void displayWelcomeMessage() {
        System.out.print("""
                #############################################
                #               Seja bem vindo              #
                #############################################
                """);
    }

    private static double getCardLimit() {
        System.out.print("""
                #############################################
                #         Digite o limite do cartão         #
                #############################################
                """);

        // get the card limit
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private static void displayFinalMessage() {
        System.out.print("""
                #############################################
                #         Obrigado pela preferência         #
                #############################################
                """);
    }

    private static boolean userWantToContinue() {

        // check if the user wants to continue shopping
        System.out.print("""
                #############################################
                # Deseja continuar comprando ?              #
                # 0 - Sair                                  #
                # 1 - Continuar                             #
                #############################################
                    """);
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            return true;
        }
        return false;
    }

    private static boolean checkCardLimit(double cardLimit, double sumValueOfCartItens) {
        // check if the user has any card limit available
        if (sumValueOfCartItens > cardLimit) {
            System.out.print("""
                #############################################
                # Você não possui limite para novas compras #
                #                                           #
                #          Finalizando carrinho             #
                #############################################
                    """);
            return false;
        }
        return true;
    }

    private static void displayResumeOfCart(List<Product> listOfProducts) {

        System.out.print("""
                #############################################
                #                                           #
                #       Resumo do carrinho de compras       #   
                #                                           #
                ############################################# 
                """);
        for (int i = 0; i < listOfProducts.size(); i++) {
            System.out.print("""
                        #############################################
                          Item %d: %s                               
                          Valor:   %.2f                                 
                        ############################################# 
                        """.formatted(i+1,listOfProducts.get(i).getName(), listOfProducts.get(i).getValue()));
        }
    }

    private static Product loopOfBuying() {
        String name = displayItemMessage();
        double value = displayValueMessage();
        Product product = new Product(value, name);
        return product;
    }

    private static double displayValueMessage() {
        System.out.print("""
                #############################################
                #          Digite o valor do item:          #
                #############################################    
                """);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private static String displayItemMessage() {
        System.out.print("""
                #############################################
                #          Digite o nome do item:           #
                #############################################    
                """);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}