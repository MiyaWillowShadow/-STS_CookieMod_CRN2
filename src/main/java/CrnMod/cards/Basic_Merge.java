package CrnMod.cards;

import CrnMod.Helper;
import CrnMod.cards.o.*;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Basic_Merge extends CrnCard {
    public static final String CID = Helper.avoidDup(Basic_Merge.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Basic_Merge() {
        super(CID, STS,"img/cards/Block_Merge.png",CardType.SKILL,  CardRarity.BASIC, CardTarget.SELF,
                0, 0, 8, 3, 0, 0, 1,0);
        desFix();
    }

    public static void merge(int mod){
        if(mod>0){
            if(mod==1){
                addFunction(()->{
                    int aim=powerAmount(P, P_Aim.PID);
                    if(aim>0){
                        losePower(P_Aim.PID,aim);
                        gainPower(new P_Comb(aim));
                    }
                });
            }else if(mod==2){
                addFunction(()->{
                    int comb=powerAmount(P, P_Comb.PID);
                    if(comb>0){
                        losePower(P_Comb.PID,comb);
                        gainPower(new P_Aim(comb));
                    }
                });
            }
        }
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainBlock(block);
        if(p.hasPower(P_Comb.PID)||p.hasPower(P_Aim.PID)){
            ArrayList<AbstractCard> cards = new ArrayList<>();
            cards.add(new Merge_Comb(this));
            cards.add(new Merge_Aim(this));
            if(upgraded){
                cards.add(new Merge_Nothing(this));
            }
            addToBot(new ChooseOneAction(cards));
            addFunction(()->{
                merge(magicNumber);
            });
        }
    }

    public AbstractCard makeCopy() {
        return new Basic_Merge();
    }
}
