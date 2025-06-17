package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.specific.SK_Block_ToAoe_P;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_Block_ToAoe extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_ToAoe.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Block_ToAoe() {
        super(CID, STS,"img/cards/SK_Block_ToAoe.png",CardType.SKILL,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 4, 0, 1, 1, 1,0);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("DIYUSIBell",1.25F);
        gainPower(new SK_Block_ToAoe_P(1));
        for (int i = 0; i < magicNumber; i++) {
            gainBlock(block);
        }
    }

    @Override
    public void upgrade2() {

    }

    public AbstractCard makeCopy() {
        return new SK_Block_ToAoe();
    }


}
