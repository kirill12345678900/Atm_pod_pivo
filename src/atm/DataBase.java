package atm;

//Счет банкомата(), снимает деньги со своего счета(--) и банкомата(--). После каждого снятия проверяет хватает ли денег на выплату.
//Если осталось мало, то он вызывает инкасацию и счет банкомата ++

import java.util.Arrays;

import static atm.Menu.startMenu;



public class DataBase {
    static Client[] accounts = new Client[10];


    static void writeToDataBase(Client client) {

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = client;
                break;
            }
        }
    }

    static void printAllClients() {
        for (int i = 0; i < accounts.length; i++) {
            if(accounts[i] != null){
                System.out.println(accounts[i]);
            }
        }
    }


    static Client login(String name, String password) {

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] != null) {
                if (accounts[i].name.equals(name) && accounts[i].password.equals(password)) {
                    if(accounts[i].banAccount == 3){
                        System.out.println("Ваш аккаунт заблокирован, обратитесь в банк");
                        startMenu();
                    }
                    return accounts[i];
                } else if (accounts[i].name.equals(name)) {
                    accounts[i].banAccount += 1;
                    if (accounts[i].banAccount == 3){
                        System.out.println("Ваш аккаунт заблокирован");
                        return accounts[i];
                    }
                }
            }
        }
        return null;
    }
}


