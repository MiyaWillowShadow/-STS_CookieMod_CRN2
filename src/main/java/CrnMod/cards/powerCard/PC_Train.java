package CrnMod.cards.powerCard;

import CrnMod.Helper;
import CrnMod.cards.o.*;
import CrnMod.father.CrnCard;
import CrnMod.powers.PCP.PCP_MDCN_PowerUp;
import CrnMod.powers.sup.SUP_DIYUSI;
import CrnMod.powers.sup.SUP_MDCN;
import CrnMod.powers.sup.SUP_SIYUDI;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class PC_Train extends CrnCard {
    public static final String CID = Helper.avoidDup(PC_Train.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public int mod=0;

    public PC_Train() {
        super(CID, STS,"img/cards/PC_Train.png",CardType.POWER,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 1, 1, 1,0);
        desFix();
        MultiCardPreview.add(this, new AbstractCard[]{new PCO_DIYUSI(), new PCO_SIYUDI(), new PCO_MDCN()});
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        if(!SUP_DIYUSI.powerUping)cards.add(new PCO_DIYUSI(this));
        if(!SUP_DIYUSI.powerUping)cards.add(new PCO_SIYUDI(this));
        if(!SUP_MDCN.powerUping)cards.add(new PCO_MDCN(this));
        if(!cards.isEmpty()){
            addToBot(new ChooseOneAction(cards));
            addFunction(this::run);
        }
    }

    public void run(){
        if(mod>0){
            if(mod==1){
                SUP_DIYUSI.powerUping=true;
                gainPower(new SUP_DIYUSI(magicNumber));
            }else if(mod==2){
                SUP_SIYUDI.powerUping=true;
                gainPower(new SUP_SIYUDI(magicNumber));
            }else if(mod==3){
                SUP_MDCN.powerUping=true;
                gainPower(new SUP_MDCN(magicNumber));
                gainPower(new PCP_MDCN_PowerUp());
            }
        }
    }


    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new PC_Train();
    }


}
