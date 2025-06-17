package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Helper.P;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.BOSS;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.ELITE;

public class R_Basic extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Basic.class.getSimpleName());

    public R_Basic() {
        super(RID, ImageMaster.loadImage("img/relics/R_Basic.png"),ImageMaster.loadImage("img/relics/outline/R_Basic.png"), AbstractRelic.RelicTier.STARTER, LandingSound.FLAT);
    }

    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new R_Basic();
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (P.hasRelic(R_Basic2.RID) && (m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
            R_Basic2 r= (R_Basic2) P.getRelic(R_Basic2.RID);
            r.counter+=1+(m.type==ELITE? 2:0)+(m.type==BOSS? 5:0);
            r.flash();
            this.triggerEffect();
        }
    }


}
