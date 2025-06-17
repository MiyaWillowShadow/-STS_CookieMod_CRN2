package CrnMod.father;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.ArrayList;

import static CrnMod.Helper.P;

@Deprecated
public class _PowerLibrary extends NaiPower {
    public static final String PID = Helper.avoidDup(_PowerLibrary.class.getSimpleName());
    public static ArrayList<String> STS;
    private static int AVOIDSTACK = 0;

    public _PowerLibrary(int amount, ArrayList<String> strings) {
        super(P, amount, PID + AVOIDSTACK++, STS,"img/powers/Comb.png", PowerType.BUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), amount);
    }

    //残影等回合开始时衰减的 或战歌狂暴
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new Smite(), this.amount, false));
        }
    }

    //肌肉等 自身回合衰减
    public void atEndOfTurn(boolean isPlayer){
        if (isPlayer) {
            if (amount == 0) {
                addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
            } else {
                addToBot(new ReducePowerAction(owner, owner, ID, 1));
            }
        }
    }

    //易伤 或无实体等要在敌人回合结束才衰减的
    public void atEndOfRound() {
        if (amount == 0) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        } else {
            addToBot(new ReducePowerAction(owner, owner, ID, 1));
        }
    }


    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }

    }
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return damage;
    }

    //涂毒  看样子可以是非攻击伤害 但貌似必须是未被格挡的
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToTop(new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount), this.amount, true));
        }

    }

    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(this.amount); //修车
        }

    }

    public void onSpecificTrigger(){
        this.flash();
    }
}