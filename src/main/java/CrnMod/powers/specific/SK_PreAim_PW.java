package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Aim;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class SK_PreAim_PW extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_PreAim_PW.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));


    public SK_PreAim_PW(int amount) {
        super(P, amount, PID, STS,"img/powers/SK_PreAim_PW.png", PowerType.BUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new ApplyPowerAction(owner,owner,new P_Aim(amount)));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        }
    }

}