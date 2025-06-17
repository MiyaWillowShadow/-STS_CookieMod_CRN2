package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.powers.P_Comb.slainMagicDamage;

public class SK_Block_ToAoe_P extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_Block_ToAoe_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    private static int AVOIDSTACK = 0;


    public SK_Block_ToAoe_P(int amount) {
        super(P, amount, PID+AVOIDSTACK++, STS,"img/powers/SK_Block_ToAoe_PW.png", PowerType.BUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public void onGainedBlock(float blockAmount) {
        if (blockAmount > 0.0F) {
            this.flash();
            for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
                if(!m0.isDeadOrEscaped()){
                    slainMagicDamage(true,m0,(int)blockAmount);
                }
            }
        }

    }

    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(P, P, ID));
    }
}