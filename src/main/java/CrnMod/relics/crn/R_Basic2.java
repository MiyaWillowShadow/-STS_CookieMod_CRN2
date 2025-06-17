package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.cards.Basic_Call;
import CrnMod.father.NaiRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class R_Basic2 extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Basic2.class.getSimpleName());

    public R_Basic2() {
        super(RID, ImageMaster.loadImage("img/relics/Radio.png"),ImageMaster.loadImage("img/relics/outline/Radio.png"), RelicTier.STARTER, LandingSound.FLAT);
        this.counter=3;
    }

    public void atBattleStartPreDraw() {
        Basic_Call c = new Basic_Call(RID);
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(c,true));
        this.triggerEffect();
    }

    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public void use(int counter) {
        this.counter-=counter;
        this.triggerEffect();
    }

    public void onVictory() {
        counter+=1;
        this.triggerEffect();
    }

    public AbstractRelic makeCopy() {
        return new R_Basic2();
    }

}
