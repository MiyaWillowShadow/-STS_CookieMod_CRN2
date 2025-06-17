package CrnMod.powers.sup;

import CrnMod.Helper;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;

public class SUP_KNN extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_KNN.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int tempHP=3;

    public SUP_KNN() {
        super( -1, PID, STS,"img/powers/KNN.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),tempHP);
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return damageAmount/2;
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        flash();
        addToTop(new AddTemporaryHPAction(P,P,tempHP));
    }
    public void triggerAtGainSup2() {
        talkSound("KNN息してる",1.25F);
    }
}