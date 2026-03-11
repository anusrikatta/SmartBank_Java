import java.io.*;
import java.util.*;

public class BankService{
    private List<BankAccount> accounts;
    private final String FILE_NAME="accounts.txt";

    public BankService(){
        accounts=new ArrayList<>();
        loadAccounts();
    }

    public void registerAccount(String name,int accNo,double bal,int pin){
        BankAccount account=new BankAccount(name,accNo,bal,pin);
        accounts.add(account);
        saveAccounts();
        System.out.println("Account Registered Successfully");
    }

    public BankAccount login(int accNo,int pin){
        for(BankAccount acc:accounts){
            if(acc.getAccountNumber()==accNo&&acc.validatePin(pin)){
                return acc;
            }
        }
        return null;
    }

    private void saveAccounts(){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME));
            for(BankAccount acc:accounts){
                StringBuilder sb=new StringBuilder();
                for(String t:acc.getTransactions()){
                    sb.append(t).append(";");
                }

                bw.write(acc.getAccountHolder()+","+acc.getAccountNumber()+","+acc.getBalance()+","+acc.getPin()+"|"+sb.toString());
                bw.newLine();
            }
            bw.close();
        }catch(IOException e){
            System.out.println("Error Saving Accounts");
        }
    }
    private void loadAccounts(){
        try{
            File file=new File(FILE_NAME);
            if(!file.exists())return;

            BufferedReader br=new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while((line=br.readLine())!=null){

                String[] parts=line.split("\\|");
                String[] data=parts[0].split(",");

                String name=data[0];
                int accNo=Integer.parseInt(data[1]);
                double bal=Double.parseDouble(data[2]);
                int pin=Integer.parseInt(data[3]);

                List<String> trans=new ArrayList<>();
                if(parts.length>1&&!parts[1].isEmpty()){
                    String[] tData=parts[1].split(";");
                    for(String t:tData){
                        if(!t.isEmpty()){
                            trans.add(t);
                        }
                    }
                }

                accounts.add(new BankAccount(name,accNo,bal,pin,trans));
            }
            br.close();
        }catch(IOException e){
            System.out.println("Error Loading Accounts");
        }
    }

    public void updateData(){
        saveAccounts();
    }
}