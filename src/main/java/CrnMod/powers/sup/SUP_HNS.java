package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Thmod.talkSound;

public class SUP_HNS extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_HNS.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int dmgUp=50;

    public SUP_HNS() {
        super( -1, PID, STS,"img/powers/HNS.png");
        supLv=4;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),dmgUp);
    }

    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK && !c.purgeOnUse ) {
            return  damage *(100+dmgUp)/100;
        } else {
            return damage;
        }
    }

    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (!(info.type== DamageInfo.DamageType.NORMAL)) {
            return damageAmount*(100+dmgUp)/100;
        }
        return damageAmount;
    }
    public void triggerAtGainSup2() {
        talkSound("HNS1",1.25F);
    }
}