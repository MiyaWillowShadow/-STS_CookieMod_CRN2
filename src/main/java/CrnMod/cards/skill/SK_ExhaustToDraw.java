package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.specific.SK_ExhaustToDraw_Action;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_ExhaustToDraw extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_ExhaustToDraw.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_ExhaustToDraw() {
        super(CID, STS,"img/cards/SK_ExhaustToDraw.png",CardType.SKILL,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 1, 0, 1,-1);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("削除",1.25F);
        addToBot(new SK_ExhaustToDraw_Action(1));
    }

    @Override
    public void upgrade2() {

    }

    public AbstractCard makeCopy() {
        return new SK_ExhaustToDraw();
    }


}
