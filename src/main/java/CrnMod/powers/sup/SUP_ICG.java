package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;

public class SUP_ICG extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_ICG.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int block=2;

    public SUP_ICG(int amount) {
        super( amount, PID, STS,"img/powers/ICG.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount*block);
    }

    public float modifyBlock(float blockAmount) {
        return (blockAmount += (float)this.amount*block) < 0.0F ? 0.0F : blockAmount;
    }
    public void triggerAtGainSup2() {
        talkSound("ICG",1.25F,3);
    }
}