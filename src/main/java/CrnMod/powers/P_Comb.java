package CrnMod.powers;

import CrnMod.Helper;
import CrnMod.cards.attack.Slain_Call;
import CrnMod.cards.attack.Slain_OnlyBlock;
import CrnMod.father.CrnCard;
import CrnMod.father.NaiPower;
import CrnMod.powers.PCP.PCP_MDCN_PowerUp;
import CrnMod.powers.specific.SK_Retain_P;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.addAction;

public class P_Comb extends NaiPower {
    public static final String PID = Helper.avoidDup(P_Comb.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public P_Comb(int amount) {
        super(P, amount, PID, STS,"img/powers/Comb.png", PowerType.BUFF);
    }

    public static void slainMagicDamage(boolean toBot, AbstractCreature m, int dmg){
        float tmp=(float)dmg;
        for(AbstractPower pw: m.powers){
            if(Objects.equals(pw.ID, P_Spot.PID)){
                tmp+=pw.amount;
            }
        }
        for(AbstractPower pw: P.powers){
            if(Objects.equals(pw.ID, P_Comb.PID)){
                tmp*= (float) (1F+0.1*pw.amount);
            }
        }
        addAction(toBot,new DamageAction(m, new DamageInfo(P, (int) tmp, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if(P.hasPower(PCP_MDCN_PowerUp.PID)){
            PCP_MDCN_PowerUp.triggerPoison(m);
        }
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount*10);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard c) {
        if (!P.hasPower(SK_Retain_P.PID) && c.type == AbstractCard.CardType.ATTACK && !c.purgeOnUse && this.amount > 0 && CrnCard.isSlain(c)) {
            return  damage * (1 + (Objects.equals(c.cardID, Slain_Call. CID)||Objects.equals(c.cardID, Slain_OnlyBlock.CID) ?0.2F:0.1F) * amount);
        } else {
            return damage;
        }
    }
}