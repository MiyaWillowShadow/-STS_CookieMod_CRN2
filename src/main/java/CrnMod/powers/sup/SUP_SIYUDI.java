package CrnMod.powers.sup;

import CrnMod.Helper;
import CrnMod.actions.specific.SUP_SIYUDI_Action;
import CrnMod.powers.P_Aim;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;
import static CrnMod.father.NaiCard.countMonster;
import static CrnMod.father.NaiCard.powerAmount;

public class SUP_SIYUDI extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_SIYUDI.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0],powerStrings.DESCRIPTIONS[1]));
    public static final int baseDmg =6;
    public static int dmg =baseDmg;
    public static boolean powerUping=false;
    public int critical=0;

    public SUP_SIYUDI(int amount) {
        super( amount, PID, STS,"img/powers/SIYUDI.png");
        this.ladyList.add(SUP_DIYUSI.PID);
    }

    public void updateDescription() {
        if(powerUping){
            this.description = String.format(STS.get(1),dmg, amount)+  String.format(STS.get(2),critical);
        }else{
            this.description = String.format(STS.get(1),dmg, amount);
        }
    }


    public void atStartOfTurn(){
        critical=0;
    }
    public void atEndOfTurn(boolean isPlayer){
        onSpecificTrigger();
    }

    @Override
    public void onSpecificTrigger(){
        if(countMonster()>0){
            flash();
            int realCritical=critical+powerAmount(P, P_Aim.PID)/4;
            dmgCount();
            for(int i = 0; i < amount; ++i) {
                addToBot(new SUP_SIYUDI_Action(dmg,realCritical));
            }
        }
    }

    public void stackPower2(int stackAmount){
        dmgCount();
        talkSound("SIYUDI2",1.50F);
    }

    public void onInitialApplication2(){
        dmgCount();
        powerOrder(owner,this.ID, LoseStrengthPower.POWER_ID);
        talkSound("SIYUDI",1.50F);
    }

    public void dmgCount(){
        dmg=baseDmg+powerAmount(P, StrengthPower.POWER_ID)+powerAmount(P, SUP_SIK.PID)*SUP_SIK.dmg;
        updateDescription();
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        this.dmgCount();
    }
}