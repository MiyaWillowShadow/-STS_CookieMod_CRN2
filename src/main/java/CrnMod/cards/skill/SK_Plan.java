package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.specific.SK_Plan_Action;
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

public class SK_Plan extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Plan.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Plan() {
        super(CID, STS,"img/cards/SK_Plan.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 2, 0, 0,0);
        this.exhaust =true;
        desFix();
    }

    @Override
    public void upgrade2() {}

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        if (p.drawPile.size()>1) {
            addToBot(new SK_Plan_Action(magicNumber));
        }
        if(upgraded){
            draw(1);
        }
        talkSound("RUぷはー",1.25F);
    }

    public AbstractCard makeCopy() {
        return new SK_Plan();
    }


}
