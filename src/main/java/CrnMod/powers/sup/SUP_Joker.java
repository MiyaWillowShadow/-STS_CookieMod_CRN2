package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;
import static com.megacrit.cardcrawl.ui.panels.EnergyPanel.totalCount;

public class SUP_Joker extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_Joker.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    private int energy=0;

    public SUP_Joker(int amount) {
        super(amount, PID, STS,"img/powers/Joker.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount*2);
    }

    public void atStartOfTurn() {
        if(energy>0){
            flash();
            addToBot(new DrawCardAction(energy*amount*2));
            energy=0;
        }
    }

    public void atEndOfTurn(boolean isPlayer){
        energy=totalCount;
    }

    public void triggerAtGainSup2() {
        talkSound("JOKER",1.25F,3);
    }
}