import java.util.*;

public class BankingApp{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BankService service=new BankService();
        int choice;

        do{
            System.out.println("\n1.Register");
            System.out.println("2.Login");
            System.out.println("3.Exit");
            System.out.println("Enter your choice:");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter Name:");
                    sc.nextLine();
                    String name=sc.nextLine();

                    System.out.println("Enter Account Number:");
                    int accNo=sc.nextInt();

                    System.out.println("Enter Initial Balance:");
                    double bal=sc.nextDouble();

                    System.out.println("Set PIN:");
                    int pin=sc.nextInt();

                    service.registerAccount(name,accNo,bal,pin);
                    break;

                case 2:
                    System.out.println("Enter Account Number:");
                    int loginAcc=sc.nextInt();

                    System.out.println("Enter PIN:");
                    int loginPin=sc.nextInt();

                    BankAccount account=service.login(loginAcc,loginPin);

                    if(account!=null){
                        System.out.println("Login Successful");
                        int opt;
                        do{
                        	System.out.println("\n1.Deposit");
                        	System.out.println("2.Withdraw");
                        	System.out.println("3.Check Balance");
                        	System.out.println("4.Transaction History");
                        	System.out.println("5.Logout");
                        	System.out.println("Enter your choice:");
                        	opt=sc.nextInt();

                            switch(opt){
                                case 1:
                                    System.out.println("Enter amount:");
                                    account.deposit(sc.nextDouble());
                                    service.updateData();
                                    break;
                                case 2:
                                    System.out.println("Enter amount:");
                                    account.withdraw(sc.nextDouble());
                                    service.updateData();
                                    break;
                                case 3:
                                    account.checkBalance();
                                    break;
                                case 4:
                                    account.showTransactions();
                                    break;

                                case 5:
                                    System.out.println("Logged Out Successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
                        }while(opt!=5);
                    }else{
                        System.out.println("Invalid Credentials");
                    }
                    break;

                case 3:
                    System.out.println("Thank You for Using Banking System");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice!=3);

        sc.close();
    }
}