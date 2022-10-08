package atm;

public class Atm {
    static int atmBalance;
    static int fiveByn = 60;
    static int tenByn = 50;
    static int twentyByn = 40;
    static int fiftyByn = 30;
    static int hundredByn = 20;


    public Atm(int atmBalance, int fiveByn, int tenByn, int twentyByn, int fiftyByn, int hundredByn, int twoHundredByn, int fiveHundredByn) {
        Atm.atmBalance = atmBalance;
        Atm.fiveByn = fiveByn;
        Atm.tenByn = tenByn;
        Atm.twentyByn = twentyByn;
        Atm.fiftyByn = fiftyByn;
        Atm.hundredByn = hundredByn;

    }

    @Override
    public String toString() {
        return "Atm{" +
                "atmBalance=" + atmBalance +
                ", fiveByn=" + fiveByn +
                ", tenByn=" + tenByn +
                ", twentyByn=" + twentyByn +
                ", fiftyByn=" + fiftyByn +
                ", hundredByn=" + hundredByn +
                '}';
    }
}
