package CrnMod.powers.PCP;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class PCP_OFFside extends NaiPower {
    public static final String PID = Helper.avoidDup(PCP_OFFside.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public PCP_OFFside(int amount) {
        super(P, amount, PID, STS,"img/powers/OFFside.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    public void atStartOfTurn(){
        addToBot(new ApplyPowerAction(P,P,new P_Call(amount)));
    }
}