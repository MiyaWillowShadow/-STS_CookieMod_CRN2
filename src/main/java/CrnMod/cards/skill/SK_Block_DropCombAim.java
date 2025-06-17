package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.cards.ST_SK_Block_DropCombAim;
import CrnMod.father.CrnCard;
import CrnMod.powers.specific.SK_Block_DropCombAim_P;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Block_DropCombAim extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_DropCombAim.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public SK_Block_DropCombAim() {
        super(CID, STS,"img/cards/SK_Block_DropCombAim.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 3, 2, 0,0);
        desFix();
        this.cardsToPreview = new ST_SK_Block_DropCombAim();
    }


    @Override
    public void upgrade2() {
    }


    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainPower(new SK_Block_DropCombAim_P(magicNumber));
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(new ST_SK_Block_DropCombAim(),true));
    }

    public AbstractCard makeCopy() {
        return new SK_Block_DropCombAim();
    }


}
