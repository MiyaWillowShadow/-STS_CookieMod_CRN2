package CrnMod.actions.specific;

import CrnMod.father.AbstractPickHand;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static CrnMod.Helper.P;

public class Vary_Exhaust_Action extends AbstractPickHand {
    public Vary_Exhaust_Action(int amount) {
        super(CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0], amount, true);
    }

    @Override
    public void applyFirst() {

    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        int cost= c.costForTurn==-1? EnergyPanel.totalCount:c.costForTurn;
        cost= Math.max(cost, 0);
        if(cost>0){
            addToTop(new ApplyPowerAction(P, P, new P_Comb(cost),cost));
            addToTop(new ApplyPowerAction(P, P, new P_Aim(cost),cost));
        }
        P.hand.moveToExhaustPile(c);
    }
}
