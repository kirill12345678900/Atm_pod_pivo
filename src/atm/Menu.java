package atm;

import java.util.Random;
import java.util.Scanner;

import static atm.Atm.*;

public class Menu {

    static int count = 2;
    static Scanner scanner = new Scanner(System.in);
    static Client actualClient;
    static void startMenu() {
        System.out.println("Введите 1, чтобы создать счет");
        System.out.println("Введите 2, чтобы войти в аккаунт");
        System.out.println("Введите 3, чтобы положить деньги на счет");
        System.out.println("Введите 4, чтобы просмотреть баланс");
        System.out.println("Введите 5, чтобы обналичить счет");
        System.out.println("Введите 6, чтобы посмотреть базу данных");
        System.out.println("Введите 7, чтобы выйти из программы");
        switch (chooseNumber()) {
            case 1:
                DataBase.writeToDataBase(createAccount());
                System.out.println("Аккаунт сохранён");
                break;
            case 2:

                actualClient = login();
                if (actualClient != null) {
                    if(actualClient.banAccount != 3){
                        System.out.println("Здравствуйте " + actualClient.name);
                    } else if (actualClient.banAccount == 3) {
                        startMenu();
                    }
                }
                break;
            case 3:
                if(actualClient.banAccount == 3){
                    System.out.println("Ваш аккаунт заблокирован");
                    break;
                }
                addBalance();
                System.out.println("Баланс пополнен");
                break;
            case 4:
                if(actualClient.banAccount == 3){
                    System.out.println("Ваш аккаунт заблокирован");
                    break;
                }
                System.out.println("Баланс счета: " + showBalance());
                break;
            case 5:
                if(actualClient.banAccount == 3){
                    System.out.println("Ваш аккаунт заблокирован");
                    break;
                }
                System.out.println("Введите сумму для снятия денег со счёта: ");
                chooseNodes();
                break;
            case 6:
                DataBase.printAllClients();
                break;
            case 7:
                return;
            default:
                System.out.println("Error");
                startMenu();
        }
        startMenu();
    }

    static int chooseNumber() {
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        return choose;
    }

    static Client createAccount() {
        System.out.println("Введите своё имя: ");
        String name = scannerSrt();
        System.out.println("Введите пароль: ");
        String password = scannerSrt();
        Client client = new Client(name, password, 0, (int) (Math.random() * 10000), 0);

        return client;
    }

    static void addBalance() {
        System.out.println("Пополните баланс: ");
        while (true) {

            int accountBalance = Integer.parseInt(scannerSrt());
            if (accountBalance < 0) {
                System.out.println("Неверное значение, повторите попытку: ");
            } else {
                actualClient.accountBalance += accountBalance;
                break;
            }

        }
    }

    static int showBalance() {
        return actualClient.accountBalance;
    }

    static String scannerSrt(){
        String str = scanner.nextLine();
        return str;
    }

    static void chooseNodes() {
        int minusAccountBalance = Integer.parseInt(scannerSrt());
        if (actualClient.accountBalance == 0) {
            System.out.println("Нельзя снять деньги потому что баланс равен нулю, иди проспись");
            chooseNodes();
            return;
        }
        if (minusAccountBalance > 0 && minusAccountBalance % 5 == 0 || minusAccountBalance % 10 == 0) {
            if (actualClient.accountBalance < minusAccountBalance) {
                System.out.println("Недостаточно средств: ");
            } else {
                System.out.println("1. Снять деньги по 5 рублей");
                System.out.println("2. Снять деньги по 10 рублей");
                System.out.println("3. Снять деньги по 20 рублей");
                System.out.println("4. Снять деньги по 50 рублей");
                System.out.println("5. Снять деньги по 100 рублей");
                System.out.println("6. Вернуться в меню");
                int n = scanner.nextInt();
                switch (n) {
                    case 1:
                        if (minusAccountBalance % 5 == 0 && minusAccountBalance % 10 == 0) {
                            actualClient.accountBalance -= minusAccountBalance;
                            if (fiveByn < minusAccountBalance / 5) {
                                System.out.println("Вызываем инкасацию для пополнения банкомата купюрами");
                                fiveByn += 100;
                            }
                            System.out.println("Получены купюры по 5 рублей в количестве " + minusAccountBalance / 5);
                            fiveByn -= minusAccountBalance / 5;
                            break;
                        }
                    case 2:
                        if (minusAccountBalance % 10 == 5) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 5 рублей, так как банкомат не может выдать текущую сумму купюрами по 10 рублей" +
                                    " после банкомат выдаст купюры по 10 рублей");
                            actualClient.accountBalance -= 5;
                            minusAccountBalance -= 5;
                            fiveByn -= 1;
                            System.out.println("Получена купюра номиналом 5 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 10 == 0) {
                            actualClient.accountBalance -= minusAccountBalance;
                            if (tenByn < minusAccountBalance / 10) {
                                System.out.println("Вызываем инкасацию для пополнения банкомата купюрами");
                                tenByn += 100;
                            }
                            System.out.println("Получены купюры по 10 рублей в количестве " + minusAccountBalance / 10);
                            tenByn -= minusAccountBalance / 10;
                            break;
                        }

                    case 3:

                        if (minusAccountBalance % 10 == 5) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 5 рублей, так как банкомат не может выдать текущую сумму купюрами по 20 рублей" +
                                    " после банкомат выдаст купюры по 20 рублей");
                            actualClient.accountBalance -= 5;
                            minusAccountBalance -= 5;
                            fiveByn -= 1;
                            System.out.println("Получена купюра номиналом 5 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 20 == 10) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 20 рублей" +
                                    " после банкомат выдаст купюры по 20 рублей");
                            actualClient.accountBalance -= 10;
                            minusAccountBalance -= 10;
                            tenByn -= 1;
                            System.out.println("Получена купюра номиналом 10 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 20 == 0) {
                            actualClient.accountBalance -= minusAccountBalance;
                            if (twentyByn < minusAccountBalance / 20) {
                                System.out.println("Вызываем инкасацию для пополнения банкомата купюрами");
                                twentyByn += 100;
                            }
                            System.out.println("Получены купюры по 20 рублей в количестве " + minusAccountBalance / 20);
                            twentyByn -= minusAccountBalance / 20;
                            break;
                        }

                    case 4:
                        if (minusAccountBalance % 10 == 5) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 5 рублей, так как банкомат не может выдать текущую сумму купюрами по 10 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 5;
                            minusAccountBalance -= 5;
                            fiveByn -= 1;
                            System.out.println("Получена купюра номиналом 5 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 50 == 10) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 50 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 10;
                            minusAccountBalance -= 10;
                            tenByn -= 1;
                            System.out.println("Получена купюра номиналом 10 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 50 == 20) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 50 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 20;
                            minusAccountBalance -= 20;
                            twentyByn -= 1;
                            System.out.println("Получена купюра номиналом 20 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 50 == 30) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 20 рублей и одну кумюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 50 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 30;
                            minusAccountBalance -= 30;
                            tenByn -= 1;
                            twentyByn -= 1;
                            System.out.println("Получена купюра номиналом 20 рублей и 10 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 50 == 40) {
                            System.out.println("Банкомат выдаст вам две купюры номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 50 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 40;
                            minusAccountBalance -= 40;
                            twentyByn -= 2;
                            System.out.println("Получена купюра номиналом 20 рублей в количестве 2 штук");
                        }

                        if (minusAccountBalance % 50 == 0) {
                            actualClient.accountBalance -= minusAccountBalance;
                            if (fiftyByn < minusAccountBalance / 50) {
                                System.out.println("Вызываем инкасацию для пополнения банкомата купюрами");
                                fiftyByn += 100;
                            }
                            System.out.println("Получены купюры по 50 рублей в количестве " + minusAccountBalance / 50);
                            fiftyByn -= minusAccountBalance / 50;
                            break;
                        }

                    case 5:

                        if (minusAccountBalance % 10 == 5) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 5 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 10 рублей");
                            actualClient.accountBalance -= 5;
                            minusAccountBalance -= 5;
                            fiveByn -= 1;
                            System.out.println("Получена купюра номиналом 5 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 100 == 10) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 10;
                            minusAccountBalance -= 10;
                            tenByn -= 1;
                            System.out.println("Получена купюра номиналом 10 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 100 == 20) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 20;
                            minusAccountBalance -= 20;
                            twentyByn -= 1;
                            System.out.println("Получена купюра номиналом 20 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 100 == 30) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 20 рублей и одну кумюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 30;
                            minusAccountBalance -= 30;
                            tenByn -= 1;
                            twentyByn -= 1;
                            System.out.println("Получена купюра номиналом 20 рублей и 10 рублей в количестве 1 штуки");
                        }

                        if (minusAccountBalance % 100 == 40) {
                            System.out.println("Банкомат выдаст вам две купюры номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 40;
                            minusAccountBalance -= 40;
                            twentyByn -= 2;
                            System.out.println("Получена купюра номиналом 20 рублей в количестве 2 штук");
                        }

                        if (minusAccountBalance % 100 == 50) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 50 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 50;
                            minusAccountBalance -= 50;
                            fiftyByn -= 1;
                            System.out.println("Получена купюра номиналом 50 рублей в количестве 1 штук");
                        }

                        if (minusAccountBalance % 100 == 60) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 50 рублей и одну купюру номиналом в 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 60;
                            minusAccountBalance -= 60;
                            fiftyByn -= 1;
                            tenByn -= 1;
                            System.out.println("Получена купюра номиналом 50 и 10 рублей в количестве 1 штук");
                        }

                        if (minusAccountBalance % 100 == 70) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 50 рублей и одну купюру номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 70;
                            minusAccountBalance -= 70;
                            fiftyByn -= 1;
                            twentyByn -= 1;
                            System.out.println("Получена купюра номиналом 50 и 20 рублей в количестве 1 штук");
                        }

                        if (minusAccountBalance % 100 == 80) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 50 рублей и одну купюру номиналом в 20 рублей и 1 купюру 10 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 80;
                            minusAccountBalance -= 80;
                            fiftyByn -= 1;
                            twentyByn -= 1;
                            tenByn -= 1;
                            System.out.println("Получена купюра номиналом 50 и 20 и 10 рублей в количестве 1 штук");
                        }

                        if (minusAccountBalance % 100 == 90) {
                            System.out.println("Банкомат выдаст вам одну купюру номиналом в 50 рублей и две купюры номиналом в 20 рублей, так как банкомат не может выдать текущую сумму купюрами по 100 рублей" +
                                    " после банкомат выдаст купюры по 50 рублей");
                            actualClient.accountBalance -= 90;
                            minusAccountBalance -= 90;
                            fiftyByn -= 1;
                            twentyByn -= 2;
                            System.out.println("Получена купюра номиналом 50 1 штуку и 20 рублей 2 штуки");
                        }

                        if (minusAccountBalance % 100 == 0) {
                            actualClient.accountBalance -= minusAccountBalance;
                            if (hundredByn < minusAccountBalance / 100) {
                                System.out.println("Вызываем инкасацию для пополнения банкомата купюрами");
                                hundredByn += 100;
                            }
                            System.out.println("Получены купюры по 100 рублей в количестве " + minusAccountBalance / 100);
                            hundredByn -= minusAccountBalance / 100;
                            break;
                        }
                    case 6:
                    default:
                        startMenu();
                }
            }
        } else {
            System.out.println("Неправильно введено число");
        }

    }
//SOLID - посмотреть

    static Client login() {

        System.out.println("Введите своё имя: ");
        String name = scannerSrt();
        System.out.println("Введите пароль: ");
        String password = scannerSrt();

        return DataBase.login(name, password);
    }

}
