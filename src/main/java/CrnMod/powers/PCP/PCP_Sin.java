package CrnMod.powers.PCP;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiPower;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class PCP_Sin extends NaiPower {
    public static final String PID = Helper.avoidDup(PCP_Sin.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public PCP_Sin(int amount) {
        super(P, amount, PID, STS,"img/powers/Sin.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    public void onExhaust(AbstractCard c) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            onSpecificTrigger();
        }
    }
    public void onSpecificTrigger(){
        this.flash();
        NaiCard.gainPower(new P_Comb(amount));
        NaiCard.gainPower(new P_Aim(amount));
    }

}