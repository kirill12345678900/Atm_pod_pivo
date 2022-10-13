package atm;

import javax.xml.namespace.QName;
import java.util.Arrays;

import static atm.Menu.createAccount;
import static atm.Menu.startMenu;


public class DataBase {
    static Client[] accounts = new Client[10];

    /*
    обязательно записать первого клиента
    далее проверка на вторую ячейку, если она null и такого никнейма нету в массиве клиентов, то мы записываем клиента
    если нет, то повторить попытку
     */

    static void writeToDataBase(Client client) {

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                accounts[i] = client;
                System.out.println("Аккаунт сохранён");
                break;
            } else if (!accounts[i].name.equals(client.name)) {
                continue;
            }else {
                System.out.println("Такой логин уже существует, повторите попытку");
                break;
            }

        }

    }

    static void printAllClients() {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                System.out.println(accounts[i]);
            }
        }
    }


    static Client login(String name, String password) {

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] != null) {
                if (accounts[i].name.equals(name) && accounts[i].password.equals(password)) {
                    if (accounts[i].banAccount == 3) {
                        System.out.println("Ваш аккаунт заблокирован, обратитесь в банк");
                        startMenu();
                    }
                    return accounts[i];
                } else if (accounts[i].name.equals(name)) {
                    accounts[i].banAccount += 1;
                    if (accounts[i].banAccount == 3) {
                        System.out.println("Ваш аккаунт заблокирован");
                        return accounts[i];
                    }
                }
            }
        }
        return null;
    }
}
//блок по номеру

