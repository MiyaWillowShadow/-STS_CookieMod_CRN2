package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;

public class SUP_NYN extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_NYN.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public SUP_NYN(int amount) {
        super( amount, PID, STS,"img/powers/NYN.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        addToBot(new DrawCardAction(amount));
    }
    public void triggerAtGainSup2() {
        talkSound("NYN",1.25F,3);
    }
}