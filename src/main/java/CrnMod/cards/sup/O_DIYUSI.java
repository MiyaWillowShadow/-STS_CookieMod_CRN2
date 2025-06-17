package CrnMod.cards.sup;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.powers.P_Call;
import CrnMod.powers.sup.SUP_DIYUSI;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.NJson.descriptionMakeForCard;

public class O_DIYUSI extends NaiCard {
    public static final String CID = Helper.avoidDup(O_DIYUSI.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public O_DIYUSI() {
        super(CID, STS,"img/cards/DIYUSI.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,1);
        desFix();
    }

    @Override
    public void onChoseThisOption() {
        gainPower(new SUP_DIYUSI(1));
        P_Call.sups.set(baseMagicNumber, SUP_DIYUSI.PID);
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
        return new O_DIYUSI();
    }


}
