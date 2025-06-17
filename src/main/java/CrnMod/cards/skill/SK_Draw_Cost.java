package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_Draw_Cost extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Draw_Cost.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public boolean enhance =false;
    public boolean freeWhenNotEnhance =false;

    public SK_Draw_Cost() {
        super(CID, STS,"img/cards/SK_Draw_Cost.png",CardType.SKILL,  CardRarity.COMMON, CardTarget.SELF,
                0, 0, 0, 0, 3, 1, 1,0);
        desFix();
    }

    @Override
    public void triggerWhenDrawn(){
        freeWhenNotEnhance =false;
        enhance=false;
    }

    @Override
    public void applyPowers(){
        boolean condition =false;
        int call=powerAmount(P, P_Call.PID);
        condition =call <= EnergyPanel.totalCount;
        if(condition && !enhance ){
            enhance =true;
            if( costForTurn==0){
                freeWhenNotEnhance=true;
            }else{
                setCostForTurn(0);
            }
        }else if (!condition && enhance){
            enhance =false;
            if(costForTurn==0){
                if( freeWhenNotEnhance){
                    setCostForTurn(0);
                }else{
                    setCostForTurn(cost);
                    isCostModifiedForTurn=false;
                }
            }
            freeWhenNotEnhance=false;
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
        talkSound("KNN背水",1.25F);
        if(enhance){
            draw(magicNumber-1);
            gainPower(new P_Call(1));
//            tryDropCombAim();
        }else{
            draw(magicNumber);
        }
    }

    public AbstractCard makeCopy() {
        return new SK_Draw_Cost();
    }


}
