// Interface
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

// Encapsulation
class BankAccount implements BankOperations {

    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

// Inheritance
class SavingAccount extends BankAccount {

    private double interestRate;

    public SavingAccount(String accountHolder, double balance, double interestRate) {
        super(accountHolder, balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("Interest added: " + interest);
    }

    // Polymorphism
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= getBalance() - 500) {
            super.withdraw(amount);
        } else {
            System.out.println("Minimum balance of 500 must be maintained");
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        BankAccount acc1 = new BankAccount("Kowshik", 5000);
        acc1.deposit(1000);
        acc1.withdraw(2000);
        System.out.println("Balance: " + acc1.getBalance());

        System.out.println("----------------");

        BankAccount acc2 = new SavingAccount("Sai Suraj", 1000, 5);
        acc2.deposit(2000);
        acc2.withdraw(11500);
        System.out.println("Balance: " + acc2.getBalance());
    }
}
