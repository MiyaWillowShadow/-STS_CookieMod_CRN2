package CrnMod.cards.powerCard;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.PCP.PCP_KNN;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class PC_KNN extends CrnCard {
    public static final String CID = Helper.avoidDup(PC_KNN.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public PC_KNN() {
        super(CID, STS,"img/cards/PC_KNN.png",CardType.POWER,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 20, 0, 3,-1);
        this.tags.add(CardTags.HEALING);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("KNN息してる",1.25F);
        gainPower(new PCP_KNN(1));
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new PC_KNN();
    }


}
