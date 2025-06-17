package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.cards.ST_LoseCall;
import CrnMod.father.NaiRelic;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class R_Moon extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Moon.class.getSimpleName());

    public R_Moon() {
        super(RID, ImageMaster.loadImage("img/relics/yueyao.png"),ImageMaster.loadImage("img/relics/outline/yueyao.png"), RelicTier.BOSS, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Moon();
    }

    public void atBattleStart() {
        this.triggerEffect();
        this.addToBot(new MakeTempCardInDrawPileAction(new ST_LoseCall(), 1, true, true));
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
}
