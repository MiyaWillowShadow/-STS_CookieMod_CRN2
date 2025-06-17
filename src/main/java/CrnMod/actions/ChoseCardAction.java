package CrnMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;
import java.util.function.Function;

public class ChoseCardAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> cards;
    private final Function<AbstractCard,AbstractCard> func;
    private boolean retrieveCard = false;
    public ChoseCardAction(ArrayList<AbstractCard> cards, Function<AbstractCard,AbstractCard> func) {
        this.cards=cards;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.func=func; //apply to the card after adding to hand
    }
    public ChoseCardAction(ArrayList<AbstractCard> cards) {
        this(cards,null);
    }
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(cards, CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard c = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    c.current_x = -1000.0F * Settings.xScale;
                    if(func!=null){
                        func.apply(c);
                    }
                }
                this.retrieveCard = true;
            }
            this.tickDuration();
        }
    }
}
