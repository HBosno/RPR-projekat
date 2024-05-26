package ba.unsa.etf.rpr.domain;

public abstract class CardDecorator extends Card {
    protected Card decoratedCard;

    public CardDecorator(Card decoratedCard) {
        super(decoratedCard.getId(), decoratedCard.getSerialNumber(),
                decoratedCard.getCardType(), decoratedCard.getBalance(),
                decoratedCard.isMonthlyCoupon(), decoratedCard.getProfile());
        this.decoratedCard = decoratedCard;
    }

    @Override
    public double getMonthlyCost() {
        return decoratedCard.getMonthlyCost();
    }

    @Override
    public String getDescription() {
        return decoratedCard.getDescription();
    }

    @Override
    public boolean balanceNegative() {
        return decoratedCard.balanceNegative();
    }

    @Override
    public double newBalance() {
        return decoratedCard.newBalance();
    }

}
