package CrnMod.powers.PCP;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;

public class PCP_KNN extends NaiPower {
    public static final String PID = Helper.avoidDup(PCP_KNN.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int heal = 20;

    public PCP_KNN(int amount) {
        super(P, amount, PID, STS,"img/powers/PCP_KNN.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount,heal);
    }

    public void onVictory(){
        for (int i = 0; i < amount; i++) {
            onSpecificTrigger();
        }
        reducePower(amount);
    }
    public void onSpecificTrigger(){
        talkSound("KNN生き返る",1.25F);
        flash();
        addToTop(new HealAction(P,P,P.maxHealth*heal/100));
    }

//    @Override
//    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
//        if(amount>0){
//            damageInfo.base=0;
//            onSpecificTrigger();
//            addToTop(new ReducePowerAction(P,P,ID,1));
//        }
//        return false;
//    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount >= P.currentHealth) {
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            onSpecificTrigger();
            return 0;
        }
        return damageAmount;
    }
}