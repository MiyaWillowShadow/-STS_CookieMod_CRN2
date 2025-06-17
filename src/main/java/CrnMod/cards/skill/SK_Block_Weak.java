package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_Block_Weak extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_Weak.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Block_Weak() {
        super(CID, STS,"img/cards/SK_Block_Weak.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                0, 0, 12, 2, 2, 1, 2,0);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainBlock(block);
        if(!m.hasPower(WeakPower.POWER_ID)){
            givePower(m,new WeakPower(m,magicNumber,false));
        }else{
            gainEn(2);
        }
    }

    public AbstractCard makeCopy() {
        return new SK_Block_Weak();
    }


}
