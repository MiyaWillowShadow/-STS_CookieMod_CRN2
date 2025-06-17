package CrnMod.cards;

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

public class Basic_Shot extends CrnCard {
    public static final String CID = Helper.avoidDup(Basic_Shot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Basic_Shot() {
        super(CID, STS,"img/cards/Strike_Shot.png",CardType.ATTACK,  CardRarity.BASIC, CardTarget.ENEMY,
                6, 3, 0, 0, 0, 0, 1,0);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public AbstractCard makeCopy() {
        return new Basic_Shot();
    }


}
