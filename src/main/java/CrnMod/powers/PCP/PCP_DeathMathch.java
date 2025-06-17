package CrnMod.powers.PCP;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class PCP_DeathMathch extends NaiPower {
    public static final String PID = Helper.avoidDup(PCP_DeathMathch.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public PCP_DeathMathch(int amount) {
        super(P, amount, PID, STS,"img/powers/DeathMathch.png");
    }

    public static void run(boolean loseComb){
        for(AbstractPower pw:P.powers){
            if(pw instanceof PCP_DeathMathch){
                PCP_DeathMathch PCP_DeathMathch = (PCP_DeathMathch) pw;
                PCP_DeathMathch.gain(loseComb);
            }
        }
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount,amount);
    }

    public void gain(boolean loseComb) {
        if(loseComb){
            NaiCard.gainPower(new P_Aim(amount));
        }else{
            NaiCard.gainPower(new P_Comb(amount));
        }
    }
}