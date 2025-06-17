package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.specific.SK_Retain_P;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Shot_Cost extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_Cost.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public boolean powerUping=false;

    public Shot_Cost() {
        super(CID, STS,"img/cards/Shot_Cost.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                18, 6, 0, 0, 0, 0, 2,0);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    @Override
    public void applyPowers(){
        boolean cri=false;
        if(P.hasPower(P_Aim.PID) ){
            P_Aim pw= (P_Aim) P.getPower(P_Aim.PID);
            cri=!P.hasPower(SK_Retain_P.PID) && pw.amount>=4;
        }
        if(cri && !powerUping && costForTurn!=0){ //干手造成的0费状态下不会切换，避免暴击被抢时丢失的干手0费
            powerUping=true;
        }else if (!cri && powerUping){
            powerUping=false;
            setCostForTurn(cost);
            isCostModifiedForTurn=false;
        }
        if(powerUping && costForTurn!=0){
            setCostForTurn(0);
        }
    }

    public void triggerOnGlowCheck() {
        if (powerUping) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public AbstractCard makeCopy() {
        return new Shot_Cost();
    }


}
