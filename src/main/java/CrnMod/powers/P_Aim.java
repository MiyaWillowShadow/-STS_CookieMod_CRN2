package CrnMod.powers;

import CrnMod.Helper;
import CrnMod.cards.attack.Shot_Critical;
import CrnMod.father.CrnCard;
import CrnMod.father.NaiPower;
import CrnMod.powers.sup.SUP_JGN;
import CrnMod.powers.specific.SK_Retain_P;
import CrnMod.powers.sup.SUP_SIYUDI;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.Thmod.talkSound;
import static CrnMod.father.NaiCard.powerAmount;

public class P_Aim extends NaiPower {
    public static final String PID = Helper.avoidDup(P_Aim.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public int critical=0;

    public P_Aim(int amount) {
        super(P, amount, PID, STS,"img/powers/Aim.png", PowerType.BUFF);
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1), critical);
    }

    public void countSelfCritical(){
        critical=amount/4;
    }
    public void onSpecificTrigger(){
        countSelfCritical();
    }

    public void onInitialApplication() {
        if(amount>=4){
            flash();
            talkSound("CriticalReady",1.5F);
        }
        countSelfCritical();
    }

    public void stackPower(int stackAmount) {
        if(stackAmount>=4 || (stackAmount+amount%4)>=4){
            flash();
            talkSound("CriticalReady",1.5F);
        }
        super.stackPower(stackAmount);
        countSelfCritical();
    }

    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        countSelfCritical();
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (amount > 0 && CrnCard.isShot(card) && !P.hasPower(SK_Retain_P.PID)) {
            if(Objects.equals(card.cardID, Shot_Critical.CID)){
                critical=amount/3;
            }else{
                countSelfCritical();
            }
            if(critical>0){
                return damage* ( 100+(powerAmount(P, SUP_JGN.PID)*SUP_JGN.damageUp+100)*critical) /100;
            }
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (critical>0 && !P.hasPower(SK_Retain_P.PID)
                && card.type == AbstractCard.CardType.ATTACK && CrnCard.isShot(card)) {
            if(Objects.equals(card.cardID, Shot_Critical.CID)){
                this.addToBot(new ReducePowerAction(owner, owner, PID,critical*3));
            }else{
                this.addToBot(new ReducePowerAction(owner, owner, PID,critical*4));
            }
            for(AbstractPower pw: P.powers){
                if(pw instanceof SUP_SIYUDI){
                    if (SUP_SIYUDI.powerUping){
                        SUP_SIYUDI siyudi= (SUP_SIYUDI) pw;
                        siyudi.critical+=critical;
                    }
                    break;
                }
            }
        }
    }
}