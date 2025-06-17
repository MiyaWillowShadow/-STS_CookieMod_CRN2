package CrnMod.cards;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.father.CrnCard.tryDropCombAim;

public class ST_SK_Block_DropCombAim extends NaiCard {
    public static final String CID = Helper.avoidDup(ST_SK_Block_DropCombAim.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public ST_SK_Block_DropCombAim() {
        super(CID, STS,"img/cards/ST_SK_Block_DropCombAim.png",CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE,
                0, 0, 0, 0, 0, 0, 0,0);
        this.exhaust =true;
        this.isEthereal=true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        tryDropCombAim();
    }


    @Override
    public boolean canUpgrade(){
        return !upgraded;
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new ST_SK_Block_DropCombAim();
    }


}
