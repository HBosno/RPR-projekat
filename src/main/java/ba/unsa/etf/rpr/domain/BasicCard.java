package ba.unsa.etf.rpr.domain;

public class BasicCard extends Card {

    public BasicCard(int id, int serialNumber, CardType cardType, double balance, boolean monthlyCoupon, Profile profile) {
        super(id, serialNumber, cardType, balance, monthlyCoupon, profile);
    }

    @Override
    public double getMonthlyCost() {
        switch(getCardType()) {
            case STUDENT:
            case PENSIONER:
                return 20;
            case HIGH_SCHOOL:
            case ELEMENTARY:
                return 16;
            case WORKER:
                return 25;
            default:
                return 23;
        }
    }

    @Override
    public String getDescription() {
        switch(getCardType()) {
            case STUDENT:
                return "Studentska";
            case PENSIONER:
                return "Penzionerska";
            case HIGH_SCHOOL:
                return "Srednja škola";
            case ELEMENTARY:
                return "Osnovna škola";
            case WORKER:
                return "Radnička";
            default:
                return "Ostali";
        }
    }

    @Override
    public boolean balanceNegative() {
        return getBalance() < getMonthlyCost();
    }

    @Override
    public double newBalance() {
        return getBalance() - getMonthlyCost();
    }
}
