package CrnMod.actions.specific;

import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.AbstractPickHand;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static CrnMod.Helper.P;

@Deprecated
public class _SK_NewCards_Action extends AbstractPickHand {
    public _SK_NewCards_Action() {
        super(CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0], 10, true);
    }

    @Override
    public void applyFirst() {

    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        P.hand.moveToExhaustPile(c);
        AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();;
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(card,true));
    }
}
