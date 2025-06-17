package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.*;

public class SUP_Milk extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_Milk.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public SUP_Milk(int amount) {
        super( amount, PID, STS,"img/powers/Milk.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }


    public void onInitialApplication2() {
        for(AbstractPower pw: P.powers){
            if(pw.type== PowerType.DEBUFF){
                removePower(false,P,pw.ID);
            }
        }
    }

    public void stackPower2(int stackAmount) {
        this.onInitialApplication2();
    }
}