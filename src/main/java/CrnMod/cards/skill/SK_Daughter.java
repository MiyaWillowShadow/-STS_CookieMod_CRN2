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

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Daughter extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Daughter.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Daughter(int upgrades) {
        super(CID, STS,"img/cards/SK_Daughter.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 1, 1, 0,0);
        this.isInnate=true;
        this.exhaust =true;
        stsPosToDescription=1;
        desFix();
        for (int i = 0; i < upgrades; i++) {
            upgrade();
        }
    }

    public SK_Daughter(){
        this(0);
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        if(timesUpgraded<3){
            magicNumber=baseMagicNumber=1+timesUpgraded;
        }else{
            magicNumber=baseMagicNumber=3;
        }
        gainPower(true,new P_Call(magicNumber));
        if(timesUpgraded>=3){
            draw(1);
        }
    }

    public void upgrade() {
        upgraded = true;
        ++timesUpgraded;
        if(timesUpgraded<3){
            upgradeMagicNumber(magUp);
        }
        name = STS.get(0) + "+" + this.timesUpgraded;
        this.initializeTitle();
        stsPosToDescription=timesUpgraded==3?2:1;
        desFix(stsPosToDescription);
    }

    @Override
    public void upgrade2() {
    }

    public boolean canUpgrade() {
        return timesUpgraded<3;
    }

    public AbstractCard makeCopy() {
        return new SK_Daughter(this.timesUpgraded);
    }

}
