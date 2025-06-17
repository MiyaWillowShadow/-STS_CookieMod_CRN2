package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class SK_FreeDoubleAttack extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_FreeDoubleAttack.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_FreeDoubleAttack() {
        super(CID, STS,"img/cards/SK_FreeDoubleAttack.png",CardType.SKILL,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 0, 0, 2,0);
        exhaust =true;
        exhaustChange=true;
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainPower(true,new DoubleTapPower(p,1));
        gainPower(true,new FreeAttackPower(p,1));
    }

    public AbstractCard makeCopy() {
        return new SK_FreeDoubleAttack();
    }


}
