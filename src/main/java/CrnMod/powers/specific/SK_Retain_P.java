package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static CrnMod.Helper.P;

public class SK_Retain_P extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_Retain_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));


    public SK_Retain_P(int amount) {
        super(P, amount, PID, STS,"img/powers/SK_Retain_PW.png", PowerType.BUFF);
    }

    public void updateDescription() {
            this.description = String.format(STS.get(1), amount);
    }


    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            Iterator var2 = AbstractDungeon.player.hand.group.iterator();

            while(var2.hasNext()) {
                AbstractCard c = (AbstractCard)var2.next();
                if (!c.isEthereal) {
                    c.retain = true;
                }
            }
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, ID, 1));
            }
        }
    }
}