package CrnMod.actions.specific;

import CrnMod.powers.PCP.PCP_MDCN_PowerUp;
import CrnMod.powers.P_Spot;
import CrnMod.powers.sup.SUP_JGN;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.effectSound;
import static CrnMod.father.NaiCard.powerAmount;

public class SUP_SIYUDI_Action extends AbstractGameAction {
    private final int baseDamage;
    private int critical =0;

    public SUP_SIYUDI_Action(int baseDamage,int critical) {
        this.baseDamage = baseDamage;
        this.critical =critical;
    }

    public int dmgFix(int dmg, AbstractMonster m){
        dmg=dmg+ powerAmount(m,P_Spot.PID);
        dmg=dmg*( 100+(100+SUP_JGN.damageUp*powerAmount(P, SUP_JGN.PID))*critical )/100;
        if(m.hasPower(VulnerablePower.POWER_ID)){
            dmg=dmg*(100+50+(P.hasRelic("Paper Frog")?25:0))/100;
        }
        return dmg;
    }

    public AbstractMonster getWeakestAttackingMonster(){
        AbstractMonster weakestMonster = null;
        Iterator<AbstractMonster> var = AbstractDungeon.getMonsters().monsters.iterator();
        while(var.hasNext()) {
            AbstractMonster m = (AbstractMonster)var.next();
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                if (weakestMonster == null) {
                    weakestMonster = m;
                } else if (m.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = m;
                }
            }
        }
        if(weakestMonster == null && !AbstractDungeon.getMonsters().monsters.isEmpty()){
            var = AbstractDungeon.getMonsters().monsters.iterator();
            while(var.hasNext()) {
                AbstractMonster m = (AbstractMonster)var.next();
                if (!m.isDeadOrEscaped() ) {
                    if (weakestMonster == null) {
                        weakestMonster = m;
                    } else if (m.currentHealth < weakestMonster.currentHealth) {
                        weakestMonster = m;
                    }
                }
            }
        }
        return weakestMonster;
    }

    public void update() {
        if (!AbstractDungeon.getCurrRoom().monsters.monsters.isEmpty()) {
            AbstractMonster m = getWeakestAttackingMonster();
            int tmp= dmgFix(baseDamage,m);
            if(critical >0){
                addToBot(new DamageAction(m, new DamageInfo(P, tmp, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
                effectSound("CriticalShot",1.05F);
            }else{
                addToBot(new DamageAction(m, new DamageInfo(P, tmp, DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_LIGHT));
            }
            if(P.hasPower(PCP_MDCN_PowerUp.PID)){
                PCP_MDCN_PowerUp.triggerPoison(m);
            }
        }
        isDone = true;
    }
}
