package CrnMod.actions.specific;

import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.cards.attack.Shot_Copy;
import CrnMod.father.AbstractPickHand;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static CrnMod.Helper.P;

public class ATK_Shot_Copy_Action extends AbstractPickHand {
    private final boolean upgraded;
    AbstractCard speller;
    public ATK_Shot_Copy_Action(Shot_Copy speller, boolean upgraded) {
        super(CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0], 1, upgraded);
        this.speller=speller;
        this.upgraded=upgraded;
    }

    @Override
    public void applyFirst() {

    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        P.hand.moveToExhaustPile(c);
        if(c.costForTurn<=0){
            Shot_Copy copy = (Shot_Copy) speller.makeStatEquivalentCopy();
            copy.desFix();
            AbstractDungeon.effectList.add(new ShowCardAndTryToHand(copy,true));
        }
        if(upgraded){
            P.hand.moveToExhaustPile(speller);
        }
    }
}
