package encapsulation.inheritance.activity;

import java.util.Scanner;
import java.util.InputMismatchException;

// Galit ako sa typing
// kaya po ganyarn sya, tinks

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
// Create helpers
        productScanners scannerHelper = new productScanners();
        displayProducts displayHelper = new displayProducts();

// Get product info from user
        DiscountedProduct product = scannerHelper.productScanner(scanner);

// Display product details
        displayHelper.displayProductDetails(product);
    }
}

 
class DiscountedProduct extends Product {
    private double discountRate;

// Call parent class values before discounting
    public DiscountedProduct(String productName, String productCode, double price, double discountRate) {
        super(productName, productCode, price);
        this.discountRate = discountRate;
    }

// Getter
    public double getDiscountRate() {
        return discountRate;
    }

// Setter
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

// Actual discounting and updating price
    @Override
    public double getPrice() {
        double originalPrice = super.getPrice();
        return originalPrice - (originalPrice * discountRate / 100);
    }
}


class Product {
    private String productName;
    private String productCode;
    private double price;

// Constructor
    public Product(String productName, String productCode, double price) {
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
    }



// Getters
    public String getProductName() {
        return productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public double getPrice() {
        return price;
    }



// Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class productScanners {
    public DiscountedProduct productScanner(Scanner scanner) {
        // Variable initializations
        String name = "";
        String code = "";
        double price = 0.0;
        double discount = 0.0;

        System.out.print("Enter product name: ");
        name = scanner.nextLine();

        System.out.print("Enter product code: ");
        code = scanner.nextLine();

        price = getPrice(scanner);

        discount = getDiscount(scanner);

        return new DiscountedProduct(name, code, price, discount);
    }

// Method to get a valid price
    private double getPrice(Scanner scanner) {
        double price = 0.0;
        while (true) {
            try {
                System.out.print("Enter product price: ");
                price = scanner.nextDouble();

                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
                scanner.nextLine();
            }
        }
        return price;
    }
    
// Method to get a valid discount
    private double getDiscount(Scanner scanner) {
        double discount = 0.0;
        while (true) {
            try {
                System.out.print("Enter discount rate (in percentage): ");
                discount = scanner.nextDouble(); 

                if (discount < 0 || discount > 100) {
                    System.out.println("Discount rate must be between 0 and 100. Please enter a valid discount rate.");
                    continue; 
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for discount rate.");
                scanner.nextLine();
            }
        }
        return discount;
    }
}


class displayProducts {
    public void displayProductDetails(DiscountedProduct product) {
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Product Code: " + product.getProductCode());
        System.out.println("Original Price: " + product.getPrice());
        System.out.println("Discounted Price: " + product.getPrice());
    }
}