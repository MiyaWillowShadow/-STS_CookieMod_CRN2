package CrnMod.father;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Call;
import CrnMod.powers.P_Comb;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static CrnMod.Helper.P;

@Deprecated
public class _CardLibrary extends CrnCard {
    public static final String CID = Helper.avoidDup(_CardLibrary.class.getSimpleName());
    public static ArrayList<String> STS;

    public _CardLibrary() {
        super(CID, STS,"img/cards/Accuration.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ENEMY,
                10, 4, 3, 2, 2, 2, 1,0);
        this.exhaustChange= exhaust = true;
        this.isEtherealChange=isEthereal=true;
        this.isInnateChange=isInnate=true;
        this.retainChange=retain=true;
        this.tags.add(CRN.CustomTags.SLAIN);
        this.tags.add(CRN.CustomTags.SHOT);
        this.tags.add(CRN.CustomTags.RESET);
        this.tags.add(CRN.CustomTags.BOTH);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.HEALING);
        desFix();
    }

    public void upgrade2() {
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        attack(m, magicNumber,AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        attackRandom(magicNumber,AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        attackAoe(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        attackAoe(magicNumber,AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

        gainBlock(block);
        draw(magicNumber);
        gainEn(magicNumber);

        int comb=powerAmount(p, P_Comb.PID);
        int aim=powerAmount(p, P_Aim.PID);

        gainPower(true,new P_Call(magicNumber));
        losePower(true, P_Call.PID,magicNumber);

        givePower(true, m , new P_Spot(m,magicNumber));
        removePower(true,m, P_Spot.PID);

        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()){
            }
        }

        for(AbstractPower pw: P.powers){
            if(pw.ID== P_Call.PID){
            }
        }
        for(AbstractCard c: P.hand.group){
        }

        addFunction(()->{

        });

        if(powerAmount(p, P_Call.PID)>=magicNumber){
            losePower(P_Call.PID,magicNumber);

        }

        AbstractCard copy =  this.makeStatEquivalentCopy();
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(copy,true));
    }

    //肚皮
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();
    }

    //粉碎关节提示
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1)).type == CardType.SKILL) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    //交锋
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        } else {
            return true;
        }
    }

    public AbstractCard makeCopy() {
        return new _CardLibrary();
    }


}
