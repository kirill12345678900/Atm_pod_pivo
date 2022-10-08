package atm;

public class Client {
    String name;
    int accountNumber;
    String password;
    int accountBalance;

    int banAccount;



    public Client(String name, String password, int accountBalance,  int accountNumber, int banAccount) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.accountBalance = accountBalance;
        this.banAccount = banAccount;
    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", accountNumber=" + accountNumber +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                ", banAccount=" + banAccount +
                '}';
    }
}

