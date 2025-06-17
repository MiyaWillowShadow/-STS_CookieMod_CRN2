package CrnMod.powers.sup;

import CrnMod.Helper;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;
import static CrnMod.father.NaiCard.addFunction;
import static CrnMod.father.NaiCard.powerAmount;

public class SUP_DIYUSI extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_DIYUSI.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0],powerStrings.DESCRIPTIONS[1]));
    public static final int baseBlock=3;
    public static boolean powerUping=false;
    public float block=baseBlock;

    public SUP_DIYUSI(int amount) {
        super( amount, PID, STS,"img/powers/DIYUSI.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),(int)block,amount)+STS.get(2);
    }

    public void blockCount(){
        block= baseBlock+powerAmount(P, DexterityPower.POWER_ID)+powerAmount(P, SUP_ICG.PID)*SUP_ICG.block ;
        block= block* (P.hasPower(FrailPower.POWER_ID)?0.75F:1F) *(100+(powerUping?10:0)*powerAmount(P, P_Comb.PID))/100 ;
        updateDescription();
    }

    public void run(){
        this.flash();
        blockCount();
        for(int i=0;i<amount;i++){
            addToTop(new GainBlockAction(P,(int)block));
        }
    }

    public void atEndOfTurn(boolean isPlayer){
        run();
    }

    @Override
    public void onSpecificTrigger(){
        run();
    }


    public void stackPower2(int stackAmount){
        blockCount();
    }

    public void onInitialApplication2(){
        blockCount();
        powerOrder(owner,this.ID, LoseDexterityPower.POWER_ID);
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        addFunction(this::blockCount);
    }


    public void triggerAtGainSup2() {
        talkSound("DIYUSI",1.25F,2);
    }
}