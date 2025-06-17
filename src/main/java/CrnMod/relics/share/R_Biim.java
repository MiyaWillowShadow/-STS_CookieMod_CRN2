package CrnMod.relics.share;

import CrnMod.Helper;
import CrnMod.father.NaiRelic;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static CrnMod.Helper.P;

public class R_Biim extends NaiRelic {
    public static final String RID = Helper.avoidDup(R_Biim.class.getSimpleName());
    public static final int block =3;

    public R_Biim() {
        super(RID, ImageMaster.loadImage("img/relics/Biim.png"),ImageMaster.loadImage("img/relics/outline/Biim.png"), RelicTier.RARE, LandingSound.FLAT);
    }
    public String getUpdatedDescription2() {
        return String.format(this.DESCRIPTIONS[0],block);
    }
    public AbstractRelic makeCopy() {
        return new R_Biim();
    }

    public void onPlayerEndTurn(){
        int debuff=0;
        for(AbstractPower pw:P.powers){
            if(pw.type== AbstractPower.PowerType.DEBUFF){
                debuff++;
            }
        }
        if(debuff>0){
            this.triggerEffect();
            addToBot(new GainBlockAction(P,debuff* block));
        }
    }
}
