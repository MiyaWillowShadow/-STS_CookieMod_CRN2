package CrnMod.relics.crn;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;

public class R_Sword extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Sword.class.getSimpleName());
    public static final int COUNT = 6;

    public R_Sword() {
        super(RID, ImageMaster.loadImage("img/relics/Sword.png"),ImageMaster.loadImage("img/relics/outline/Sword.png"), RelicTier.RARE, LandingSound.FLAT);
        counter=0;
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Sword();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++this.counter;
            if (this.counter == COUNT) {
                this.counter = 0;
                this.flash();
                this.pulse = false;
            } else if (this.counter == COUNT-1) {
                this.beginPulse();
                this.pulse = true;
                AbstractDungeon.player.hand.refreshHandLayout();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
        }
    }

    public float atDamageModify(float damage, AbstractCard c) {
        if (this.counter == COUNT-1) {
            int d=0;
            for(AbstractPower pw:P.powers){
                d++;
            }
            return damage + (float)d;
        }else{
            return damage;
        }
    }

    public void atBattleStart() {
        if (this.counter == COUNT-1) {
            this.beginPulse();
            this.pulse = true;
            AbstractDungeon.player.hand.refreshHandLayout();
        }

    }

    public void onEquip() {
        talkSound("YMNINM",1.25F);
    }
}
