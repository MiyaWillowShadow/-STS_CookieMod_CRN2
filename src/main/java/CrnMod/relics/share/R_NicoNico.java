package CrnMod.relics.share;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class R_NicoNico extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_NicoNico.class.getSimpleName());
    public static final int dmg=12;
    public boolean used=false;

    public R_NicoNico() {
        super(RID, ImageMaster.loadImage("img/relics/Nico.png"),ImageMaster.loadImage("img/relics/outline/Nico.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return String.format(this.DESCRIPTIONS[0],dmg);
    }
    public AbstractRelic makeCopy() {
        return new R_NicoNico();
    }

    public void atTurnStart() {
        if(!used){
            used=true;
            this.triggerEffect();
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(dmg, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
    }

}
