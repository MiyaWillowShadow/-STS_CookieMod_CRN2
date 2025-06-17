package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiRelic;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Thmod.talkSound;

public class R_EroBook extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_EroBook.class.getSimpleName());

    public R_EroBook() {
        super(RID, ImageMaster.loadImage("img/relics/erohon.png"),ImageMaster.loadImage("img/relics/outline/erohon.png"), RelicTier.RARE, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_EroBook();
    }

    public void atTurnStart() {
        this.triggerEffect();
        NaiCard.gainPower(new P_Comb(1));
        NaiCard.gainPower(new P_Aim(1));
    }

    public void onEquip() {
        talkSound("BN",1.5F);
    }
}
