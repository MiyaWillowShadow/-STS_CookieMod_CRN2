package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Spot extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Spot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Spot() {
        super(CID, STS,"img/cards/SK_Spot.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                0, 0, 0, 0, 2, 1, 0,0);
        stack=2;
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        givePower(m,new P_Spot(m,magicNumber));
        givePower(m,new VulnerablePower(m,magicNumber,false));
        addFunction(this::stackUse);
    }

    public AbstractCard makeCopy() {
        return new SK_Spot();
    }


}
