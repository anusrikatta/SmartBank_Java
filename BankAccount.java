import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount{
    private String accountHolder;
    private int accountNumber;
    private double balance;
    private int pin;
    private List<String> transactions;

    public BankAccount(String name,int accNo,double bal,int pin){
        this.accountHolder=name;
        this.accountNumber=accNo;
        this.balance=bal;
        this.pin=pin;
        this.transactions=new ArrayList<>();
    }
    public BankAccount(String name,int accNo,double bal,int pin,List<String> trans){
        this.accountHolder=name;
        this.accountNumber=accNo;
        this.balance=bal;
        this.pin=pin;
        this.transactions=trans;
    }

    public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}

	public boolean validatePin(int enteredPin){
        return this.pin==enteredPin;
    }

	public void deposit(double amount){
	    balance+=amount;

	    LocalDateTime now=LocalDateTime.now();
	    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String time=now.format(formatter);

	    transactions.add("Deposited: "+amount+" on "+time);
	    System.out.println("Amount Deposited Successfully");
	}

	public void withdraw(double amount){
	    if(amount<=balance){
	        balance-=amount;

	        LocalDateTime now=LocalDateTime.now();
	        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String time=now.format(formatter);

	        transactions.add("Withdrawn: "+amount+" on "+time);
	        System.out.println("Withdrawal Successful");
	    }else{
	        System.out.println("Insufficient Balance");
	    }
	}
    public void checkBalance(){
        System.out.println("Current Balance: "+balance);
    }

    public void showTransactions(){
        System.out.println("Transaction History:");
        for(String t:transactions){
            System.out.println(t);
        }
    }
}