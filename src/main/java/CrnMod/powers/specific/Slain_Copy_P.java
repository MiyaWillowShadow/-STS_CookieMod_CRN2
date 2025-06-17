package CrnMod.powers.specific;

import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;

public class Slain_Copy_P extends NaiPower {
    public static final String PID = Helper.avoidDup(Slain_Copy_P.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    private static int AVOIDSTACK = 0;
    public AbstractCard c;

    public Slain_Copy_P(AbstractCard c) {
        super(P, c.timesUpgraded, PID + AVOIDSTACK++, STS,"img/powers/Slain_Copy_P.png", PowerType.BUFF);
        this.c=c;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public void atStartOfTurn() {
        flash();
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(c,true));
        addToBot(new RemoveSpecificPowerAction(P, P,ID));
    }
}