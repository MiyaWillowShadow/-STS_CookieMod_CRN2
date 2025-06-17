package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.ChoseCardAction;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.CrnCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_NewCards extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_NewCards.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_NewCards() {
        super(CID, STS,"img/cards/SK_NewCards.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 0, 0, 1,0);
        exhaust =true;
        desFix();
        MultiCardPreview.add(this, new AbstractCard[]{new Cleave(), new Bane(), new Smite()});
    }

    @Override
    public void upgrade2() {
//        for(AbstractCard c:cardsPack){
//            c.upgrade();
//        }
        Iterator var1 = ((ArrayList)MultiCardPreview.multiCardPreview.get(this)).iterator();
        while(var1.hasNext()) {
            AbstractCard card = (AbstractCard)var1.next();
            card.upgrade();
        }
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
//        ArrayList<AbstractCard> cards = new ArrayList<>(Arrays.asList(cardsPack));
        talkSound("TIS",1.5F,3);
        ArrayList<AbstractCard> cards = new ArrayList<>(MultiCardPreview.multiCardPreview.get(this));
        addToBot(new ChoseCardAction(cards,(AbstractCard c)->{
            AbstractCard copy=c.makeStatEquivalentCopy();
            copy.freeToPlayOnce = true;
            AbstractDungeon.effectList.add(new ShowCardAndTryToHand(copy,true));
            return null;
        }));
    }


    public AbstractCard makeCopy() {
        return new SK_NewCards();
    }


}
