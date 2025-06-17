package CrnMod.cards.powerCard;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.PCP.PCP_DeathMathch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class PC_DeathMatch extends CrnCard {
    public static final String CID = Helper.avoidDup(PC_DeathMatch.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));


    public PC_DeathMatch() {
        super(CID, STS,"img/cards/PC_DeathMatch.png",CardType.POWER,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 0, 0, 1,-1);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainPower(new PCP_DeathMathch(1));
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new PC_DeathMatch();
    }


}
