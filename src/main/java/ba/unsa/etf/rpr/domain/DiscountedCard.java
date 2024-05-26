package ba.unsa.etf.rpr.domain;

public class DiscountedCard extends CardDecorator {
    private double discountRate;

    public DiscountedCard(Card decoratedCard, double discountRate) {
        super(decoratedCard);
        this.discountRate = discountRate;
    }

    @Override
    public double getMonthlyCost() {
        return decoratedCard.getMonthlyCost() * (1 - discountRate);
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription() + " (sa popustom)";
    }

    @Override
    public boolean balanceNegative() {
        return decoratedCard.getBalance() < getMonthlyCost();
    }

    @Override
    public double newBalance() {
        return decoratedCard.getBalance() - getMonthlyCost();
    }
}
