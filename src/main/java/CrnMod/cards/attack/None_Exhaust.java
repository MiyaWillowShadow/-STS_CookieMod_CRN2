package CrnMod.cards.attack;

import CrnMod.Helper;
import CrnMod.actions.specific.Vary_Exhaust_Action;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class None_Exhaust extends CrnCard {
    public static final String CID = Helper.avoidDup(None_Exhaust.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public None_Exhaust() {
        super(CID, STS,"img/cards/Vary_Exhaust.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                9, 2, 0, 0, 1, 1, 1,0);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new Vary_Exhaust_Action(magicNumber));
        talkSound("NDKいたたたた",1.25F);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new None_Exhaust();
    }


}
