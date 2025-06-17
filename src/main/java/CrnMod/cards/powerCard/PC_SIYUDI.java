package CrnMod.cards.powerCard;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.sup.SUP_SIYUDI;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class PC_SIYUDI extends CrnCard {
    public static final String CID = Helper.avoidDup(PC_SIYUDI.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public PC_SIYUDI() {
        super(CID, STS,"img/cards/SIYUDI.png",CardType.POWER,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 0, 0, 1,0);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainPower(new SUP_SIYUDI(1));
        if(upgraded){
            addFunction(()-> powerTrigger(P,SUP_SIYUDI.PID));
        }
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new PC_SIYUDI();
    }


}
