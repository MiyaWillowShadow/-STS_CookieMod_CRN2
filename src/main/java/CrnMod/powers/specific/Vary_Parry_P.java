package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class Vary_Parry_P extends NaiPower {
    public static final String PID = Helper.avoidDup(Vary_Parry_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));


    public Vary_Parry_P(int amount) {
        super(P, amount, PID, STS,"img/powers/Parry_PW.png", PowerType.BUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            addToBot(new ApplyPowerAction(info.owner,P,new P_Spot(info.owner,amount,true),amount));
        }
        return damageAmount;
    }

    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(P, P, ID));
    }

}