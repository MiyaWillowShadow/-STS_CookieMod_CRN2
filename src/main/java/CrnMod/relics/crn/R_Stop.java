package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.BOSS;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.ELITE;

public class R_Stop extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Stop.class.getSimpleName());

    public R_Stop() {
        super(RID, ImageMaster.loadImage("img/relics/Stop.png"),ImageMaster.loadImage("img/relics/outline/Stop.png"), RelicTier.BOSS, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Stop();
    }

    public void atBattleStart() {
//        this.addToBot(new SkipEnemiesTurnAction());
        this.triggerEffect();
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped() && m0.type!=ELITE && m0.type!=BOSS ){
                addToBot(new StunMonsterAction(m0,P));
            }
        }
    }
    public void onEquip() {
        talkSound("DIYUSIBell",1.5F);
    }
//    public void onPlayerEndTurn() {
//        if (!used) {
//            used=true;
//            this.flash();
//            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//            this.stopPulse();
//        }
//    }

}
