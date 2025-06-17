package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;

public class SUP_SIK extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_SIK.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int dmg=2;

    public SUP_SIK(int amount) {
        super( amount, PID, STS,"img/powers/SIK.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),dmg*amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount*dmg : damage;
    }
    public void triggerAtGainSup2() {
        talkSound("SIK",1.25F,3);
    }
}