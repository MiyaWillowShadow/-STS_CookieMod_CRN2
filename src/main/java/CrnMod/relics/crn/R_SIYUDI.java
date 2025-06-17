package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiRelic;
import CrnMod.powers.sup.SUP_SIYUDI;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static CrnMod.Thmod.talkSound;

public class R_SIYUDI extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_SIYUDI.class.getSimpleName());

    public R_SIYUDI() {
        super(RID, ImageMaster.loadImage("img/relics/Siyudi.png"),ImageMaster.loadImage("img/relics/outline/Siyudi.png"), RelicTier.SHOP, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_SIYUDI();
    }

    public void atBattleStart() {
        this.triggerEffect();
        NaiCard.gainPower(new SUP_SIYUDI(1));
    }
    public void onEquip() {
        talkSound("SIYUDI",1.50F);
    }

}
