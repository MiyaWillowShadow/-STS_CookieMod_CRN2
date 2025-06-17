package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.powers.P_Call.countSupport;

public class SK_EnergyBySup extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_EnergyBySup.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_EnergyBySup() {
        super(CID, STS,"img/cards/SK_EnergyBySup.png",CardType.SKILL,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 2, 1, 0,0);
        exhaust =true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        int sup=countSupport();
        int times= Math.min(sup, magicNumber);
        gainEn(times);
        draw(times);
    }

    public void upgrade2() {
    }
    public AbstractCard makeCopy() {
        return new SK_EnergyBySup();
    }


}
