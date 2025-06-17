package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.father.NaiRelic;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Thmod.talkSound;

public class R_BlackCar extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_BlackCar.class.getSimpleName());

    public R_BlackCar() {
        super(RID, ImageMaster.loadImage("img/relics/Kururu.png"),ImageMaster.loadImage("img/relics/outline/Kururu.png"), AbstractRelic.RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_BlackCar();
    }

    public void atBattleStart() {
        this.triggerEffect();
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()){
                NaiCard.givePower(m0,new P_Spot(m0,3));
            }
        }
    }
    public void onEquip() {
        talkSound("BlackCar",1.5F);
    }
}
