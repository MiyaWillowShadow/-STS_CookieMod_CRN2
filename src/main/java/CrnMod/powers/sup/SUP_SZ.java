package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;
import static CrnMod.father.NaiCard.givePower;

public class SUP_SZ extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_SZ.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int max=5;

    public SUP_SZ() {
        super( 0, PID, STS,"img/powers/SZ.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),max,amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if(++amount>=max){
                amount-=max;
                addToBot(new GainEnergyAction(1));
            }
            updateDescription();
        }
    }
    public void triggerAtGainSup2() {
        talkSound("SZ",1.25F,3);
    }
}