package CrnMod.relics.share;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class R_Grape extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Grape.class.getSimpleName());

    public R_Grape() {
        super(RID, ImageMaster.loadImage("img/relics/Grape.png"),ImageMaster.loadImage("img/relics/outline/Grape.png"), RelicTier.SPECIAL, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Grape();
    }
    public void onEquip() {
        AbstractDungeon.player.increaseMaxHp(21, true);
    }

}
