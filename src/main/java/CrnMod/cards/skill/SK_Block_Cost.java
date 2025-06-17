package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Block_Cost extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_Cost.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public boolean enhance =false;

    public SK_Block_Cost() {
        super(CID, STS,"img/cards/SK_Block_Cost.png",CardType.SKILL,  CardRarity.COMMON, CardTarget.SELF,
                0, 0, 8, 3, 0, 0, 1,0);
        desFix();
    }
    @Override
    public void applyPowers(){
        boolean condition =false;
        int call=powerAmount(P, P_Call.PID);
        condition =call < P.hand.size();

        if(condition && !enhance && costForTurn!=0){ //已经0费则不会切换
            enhance =true;
        }else if (!condition && enhance){
            enhance =false;
            setCostForTurn(cost);
            isCostModifiedForTurn=false;
        }
        if(enhance && costForTurn!=0){
            setCostForTurn(0);
        }
    }

    public void triggerOnGlowCheck() {
        if (enhance) {
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
        gainBlock(block);
//        if(enhance){
//            tryDropCombAim();
//        }
    }

    public AbstractCard makeCopy() {
        return new SK_Block_Cost();
    }


}
