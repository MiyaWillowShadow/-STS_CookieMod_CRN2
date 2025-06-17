package CrnMod.cards;

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

public class Basic_Defend extends CrnCard {
    public static final String CID = Helper.avoidDup(Basic_Defend.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Basic_Defend() {
        super(CID, STS,"img/cards/Defend.png",CardType.SKILL,  CardRarity.BASIC, CardTarget.SELF,
                0, 0, 5, 3, 0, 0, 1,0);
        this.tags.add(CardTags.STARTER_DEFEND);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainBlock(block);
    }

    public AbstractCard makeCopy() {
        return new Basic_Defend();
    }


}
