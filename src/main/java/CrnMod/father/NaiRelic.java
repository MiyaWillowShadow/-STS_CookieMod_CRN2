package CrnMod.father;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static CrnMod.NJson.descriptionMakeForRelic;
import static CrnMod.Thmod.keywordPrefix;

public abstract class NaiRelic extends CustomRelic {
    public NaiRelic(String rid, Texture img, Texture imgOutline, RelicTier Tier,LandingSound rsound) {
        super(rid, img,imgOutline, Tier, rsound);

    }
    public void triggerEffect(){
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
    public abstract String getUpdatedDescription2();
    public String getUpdatedDescription(){
        if(!DESCRIPTIONS[0].contains(keywordPrefix)){
            DESCRIPTIONS[0]= descriptionMakeForRelic(DESCRIPTIONS[0]);
        }
        return getUpdatedDescription2();
    }
}
