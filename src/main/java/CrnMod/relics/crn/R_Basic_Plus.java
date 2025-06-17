package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Helper.P;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.BOSS;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.ELITE;

public class R_Basic_Plus extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Basic_Plus.class.getSimpleName());

    public R_Basic_Plus() {
        super(RID, ImageMaster.loadImage("img/relics/tel.png"),ImageMaster.loadImage("img/relics/outline/tel.png"), RelicTier.BOSS, LandingSound.FLAT);
    }

    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new R_Basic_Plus();
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (P.hasRelic(R_Basic2.RID) && (m.isDying || m.currentHealth <= 0) && !m.halfDead && !m.hasPower("Minion")) {
            R_Basic2 r= (R_Basic2) P.getRelic(R_Basic2.RID);
            r.counter+=2+(m.type==ELITE? 4:0)+(m.type==BOSS? 10:0);
            r.flash();
            this.triggerEffect();
        }
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(R_Basic.RID);
    }

    public void obtain() {
        if (P.hasRelic(R_Basic.RID)) {
            for(int i = 0; i < P.relics.size(); ++i) {
                if (P.relics.get(i).relicId.equals(R_Basic.RID)) {
                    this.instantObtain(P, i, false);
                    break;
                }
            }
        }
        else {
            super.obtain();
        }
    }
}
