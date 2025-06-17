package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class SK_Block_DropCombAim_P extends NaiPower {
    public static final String PID = Helper.avoidDup(SK_Block_DropCombAim_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public SK_Block_DropCombAim_P(int amount) {
        super(P, amount, PID, STS,"img/powers/SK_Block_DropCombAim_PW.png", PowerType.BUFF);
    }

    public static void run(int drop){
        for(AbstractPower pw:P.powers){
            if(pw instanceof SK_Block_DropCombAim_P){
                SK_Block_DropCombAim_P sk_Block_ByCombAim_PW = (SK_Block_DropCombAim_P) pw;
                sk_Block_ByCombAim_PW.gainBlockByCombAim(drop);
            }
        }
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    public void gainBlockByCombAim(int loseStack){
        addToBot(new GainBlockAction(P,loseStack*amount));
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if(isPlayer){
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        }
    }
}