package CrnMod.cards.o;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class SKO_Hazard_Exhaust extends NaiCard {
    public static final String CID = Helper.avoidDup(SKO_Hazard_Exhaust.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SKO_Hazard_Exhaust() {
        super(CID, STS,"img/cards/SKO_Hazard_Exhaust.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        desFix();
    }

    @Override
    public void onChoseThisOption() {
        ArrayList<AbstractCard> cards = new ArrayList<>(P.hand.group);
        int counts = cards.size();
        for(AbstractCard c: cards){
            P.hand.moveToExhaustPile(c);
        }
        gainPower(new P_Call(counts));
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
        return new SKO_Hazard_Exhaust();
    }


}
