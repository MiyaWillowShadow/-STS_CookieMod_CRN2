package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.NJson.descriptionMakeForCard;

public class Search_Shot extends NaiCard {
    public static final String CID = Helper.avoidDup(Search_Shot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public Search sk_search;

    public Search_Shot(Search sk_search) {
        super(CID, STS,"img/cards/Shot_Critical.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        desFix();
        this.sk_search = sk_search;
    }

    public Search_Shot() {
        this(null);
    }

    @Override
    public void onChoseThisOption() {
        sk_search.mod=2;
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new Search_Shot();
    }


}
