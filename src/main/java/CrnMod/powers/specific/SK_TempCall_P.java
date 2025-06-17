package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class SK_TempCall_P extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_TempCall_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0],powerStrings.DESCRIPTIONS[1]));
    private static int AVOIDSTACK = 0;


    public SK_TempCall_P(int amount) {
        super(P, amount, PID + AVOIDSTACK++, STS,"img/powers/SK_TempCall_PW.png", PowerType.DEBUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(amount>1?1:2));
    }


    public void atEndOfRound(){
        if (this.amount == 1) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, P_Call.PID, 3));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, ID, 1));
        }
    }
}