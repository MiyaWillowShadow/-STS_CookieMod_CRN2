package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
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

public class Vary_Cost extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Cost.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Cost() {
        super(CID, STS,"img/cards/Vary_Cost.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                21, 6, 0, 0, 0, 0, 3,0);
        this.tags.add(CRN.CustomTags.VARY);
        this.selfRetain=true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SMASH);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Cost();
    }


}
