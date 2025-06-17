package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;

public class SUP_NEL extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_NEL.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public SUP_NEL(int amount) {
        super(amount, PID, STS,"img/powers/NEL.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    public void atStartOfTurn() {

    }

    public void atEndOfTurn(boolean isPlayer){

    }
    public void atEndOfRound() {

    }
    public void onSpecificTrigger(){
        addToBot(new DrawCardAction(amount));
    }
    public void triggerAtGainSup2() {
        talkSound("NEL1",1.25F);
    }
}