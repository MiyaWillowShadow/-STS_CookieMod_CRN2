package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.specific.SK_Upgrade_Action;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Upgrade extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Upgrade.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Upgrade() {
        super(CID, STS,"img/cards/SK_Upgrade.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 2, 0, 0,0);
        stack=2;
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            draw(1);
        }
        addToBot(new SK_Upgrade_Action(magicNumber));
        addFunction(this::stackUse);
    }


    @Override
    public void upgrade2() {
    }


    public AbstractCard makeCopy() {
        return new SK_Upgrade();
    }

}
