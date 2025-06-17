package CrnMod.relics.share;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import static CrnMod.Thmod.talkSound;

public class R_Shop extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Shop.class.getSimpleName());
    public static final int gold=150;

    public R_Shop() {
        super(RID, ImageMaster.loadImage("img/relics/Shop.png"),ImageMaster.loadImage("img/relics/outline/Shop.png"), RelicTier.COMMON, LandingSound.FLAT);
        counter=3;
    }
    public String getUpdatedDescription2() {
        return this.DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new R_Shop();
    }

    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof ShopRoom) {
            if(!usedUp){
                talkSound("NYN SHOP2",1.25F);
                this.flash();
                AbstractDungeon.player.gainGold(gold);
                if(--counter<=0){
                    this.usedUp();
                    this.counter = -2;
                }
            }
        }

    }
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 33;
    }
    public void onEquip() {
        talkSound("NYN SHOP1",1.25F);
    }
}
