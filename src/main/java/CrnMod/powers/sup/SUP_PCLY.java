package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;
import static CrnMod.father.NaiCard.givePower;

public class SUP_PCLY extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_PCLY.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int poison=5;

    public SUP_PCLY(int amount) {
        super( amount, PID, STS,"img/powers/PCLY.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),poison);
    }


    public void onInitialApplication2() {
        addToBot(new GainEnergyAction(1));
    }

    public void stackPower2(int stackAmount) {
        this.onInitialApplication2();
    }


    public void triggerAtGainSup2() {
        talkSound("PCLY",1.25F,3);
    }
}