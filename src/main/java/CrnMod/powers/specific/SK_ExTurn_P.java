package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class SK_ExTurn_P extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_ExTurn_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    private boolean justApplied;


    public SK_ExTurn_P() {
        super(P, -1, PID, STS,"img/powers/SK_ExTurn_PW.png", PowerType.DEBUFF);
        justApplied=true;
    }

    public void updateDescription() {
            this.description = String.format(STS.get(1));
    }

    public void atStartOfTurn() {
        if (justApplied) {
            justApplied=false;
        } else {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        }
    }
    public boolean canPlayCard(AbstractCard card) {
        card.cantUseMessage=AbstractCard.TEXT[13];
        return false;
    }
}