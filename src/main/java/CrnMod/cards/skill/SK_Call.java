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

public class SK_Call extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Call.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Call() {
        super(CID, STS,"img/cards/SK_Call.png",CardType.SKILL,  CardRarity.COMMON, CardTarget.SELF,
                0, 0, 0, 0, 3, 1, 1,0);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainPower(true,new P_Call(magicNumber));
    }

    @Override
    public void upgrade2() {

    }

    public AbstractCard makeCopy() {
        return new SK_Call();
    }


}
