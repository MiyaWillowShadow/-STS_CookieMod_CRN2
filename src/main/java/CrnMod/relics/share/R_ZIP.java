package CrnMod.relics.share;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static CrnMod.Helper.P;

public class R_ZIP extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_ZIP.class.getSimpleName());
    public static final int count=3;

    public R_ZIP() {
        super(RID, ImageMaster.loadImage("img/relics/ZIP.png"),ImageMaster.loadImage("img/relics/outline/ZIP.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_ZIP();
    }

    public void atBattleStart() {
        counter=0;
    }
    public void atTurnStart() {
        if(counter<count){
            counter++;
            this.triggerEffect();
            this.addToBot(new DrawCardAction(1));
        }else{
            counter=-2;
        }
    }

}
