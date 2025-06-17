package CrnMod.powers;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;
import java.util.Arrays;

public class P_Spot extends NaiPower {
    public static final String PID = Helper.avoidDup(P_Spot.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    private boolean justApplied = false;

    public P_Spot(AbstractCreature m, int amount, boolean justApplied) {
        super(m, amount, PID, STS,"img/powers/Spot.png", PowerType.DEBUFF);
        if (justApplied) {
            this.justApplied=true;
        }
    }
    public P_Spot(AbstractCreature m, int amount){
        this(m,amount,false);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard c) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage+amount;
        } else {
            return damage;
        }
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (amount == 0) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
            } else {
                addToBot(new ReducePowerAction(owner, owner, ID, 1));
            }
        }
    }

    public void onInitialApplication() {
        powerOrder(owner,this.ID,VulnerablePower.POWER_ID);
    }


}