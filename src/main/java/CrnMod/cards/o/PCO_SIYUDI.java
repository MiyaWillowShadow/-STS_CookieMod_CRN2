package CrnMod.cards.o;

import CrnMod.Helper;
import CrnMod.cards.powerCard.PC_Train;
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

public class PCO_SIYUDI extends NaiCard {
    public static final String CID = Helper.avoidDup(PCO_SIYUDI.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public PC_Train speller;

    public PCO_SIYUDI(PC_Train speller) {
        super(CID, STS,"img/cards/SIYUDI.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        this.speller=speller;
        desFix();
    }

    public PCO_SIYUDI(){
        this(null);
    }

    @Override
    public void onChoseThisOption() {
        speller.mod=2;
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
        return new PCO_SIYUDI();
    }


}
