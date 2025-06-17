package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiRelic;
import CrnMod.powers.sup.SUP_DIYUSI;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Thmod.talkSound;

public class R_DIYUSI extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_DIYUSI.class.getSimpleName());

    public R_DIYUSI() {
        super(RID, ImageMaster.loadImage("img/relics/Diyusi.png"),ImageMaster.loadImage("img/relics/outline/Diyusi.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_DIYUSI();
    }

    public void atBattleStart() {
        this.triggerEffect();
        NaiCard.gainPower(new SUP_DIYUSI(1));
    }
    public void onEquip() {
        talkSound("DIYUSIBell",1.5F);
    }
}
