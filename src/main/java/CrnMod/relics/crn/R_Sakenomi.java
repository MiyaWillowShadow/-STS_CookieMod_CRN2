package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiRelic;
import CrnMod.powers.sup.SUP_Beer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Thmod.talkSound;

public class R_Sakenomi extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Sakenomi.class.getSimpleName());
    public static final int COUNT =3;

    public R_Sakenomi() {
        super(RID, ImageMaster.loadImage("img/relics/Sakenomi.png"),ImageMaster.loadImage("img/relics/outline/Sakenomi.png"), RelicTier.COMMON, LandingSound.FLAT);
        this.counter=0;
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Sakenomi();
    }
    public void onEquip() {
        talkSound("酒飲",1.5F);
    }

    public void onMonsterDeath(AbstractMonster m) {
        if ((m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
            this.triggerEffect();
            if(++counter>= COUNT){
                counter-= COUNT;
                NaiCard.gainPower(new SUP_Beer(1));
            }
        }
    }

}
