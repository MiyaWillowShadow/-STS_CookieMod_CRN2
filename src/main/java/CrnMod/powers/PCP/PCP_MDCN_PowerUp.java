package CrnMod.powers.PCP;

import CrnMod.Helper;
import CrnMod.father.NaiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.givePower;

public class PCP_MDCN_PowerUp extends NaiPower {
    public static final String PID = Helper.avoidDup(PCP_MDCN_PowerUp.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public PCP_MDCN_PowerUp() {
        super(P, -1, PID, STS,"img/powers/PCP_MDCN_PowerUp.png");
    }

    public static void triggerPoison(AbstractCreature m){
        for(AbstractPower pw: m.powers){
            if(Objects.equals(pw.ID, PoisonPower.POWER_ID) && pw.amount>0){
                AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(m, P, pw.amount, AbstractGameAction.AttackEffect.POISON));
                break;
            }
        }
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1));
    }

//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
//            this.flash();
//            this.addToTop(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount), this.amount, true));
//        }
//    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//      if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
        if (target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
//            this.addToTop(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, 1), 1, true));
            triggerPoison(target);
        }
    }
}