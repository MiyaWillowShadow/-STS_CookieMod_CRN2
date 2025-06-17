package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;

public class SUP_Beer extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_Beer.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int heal = 10;

    public SUP_Beer(int amount) {
        super( amount, PID, STS,"img/powers/Beer.png");
        staticTrigger();
    }

    public static void staticTrigger(){
        P.heal(P.maxHealth*heal/100);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),heal);
    }

    public void triggerAtGainSup2() {
        talkSound("YJ BEER",1.25F);
    }

}