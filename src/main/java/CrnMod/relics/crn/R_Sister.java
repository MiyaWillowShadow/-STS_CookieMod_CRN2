package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import CrnMod.powers.sup.SUP_DIYUSI;
import CrnMod.powers.sup.SUP_SIYUDI;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static CrnMod.Helper.P;

public class R_Sister extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Sister.class.getSimpleName());

    public R_Sister() {
        super(RID, ImageMaster.loadImage("img/relics/CiXing.png"),ImageMaster.loadImage("img/relics/outline/CiXing.png"), RelicTier.RARE, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Sister();
    }

    public void atTurnStart() {
        if(( P.hasRelic(R_DIYUSI.RID)&&P.hasRelic(R_SIYUDI.RID) ) || P.hasPower(SUP_DIYUSI.PID) && P.hasPower(SUP_SIYUDI.PID)){
            this.triggerEffect();
            addToBot(new DrawCardAction(1));
        }
    }

}
