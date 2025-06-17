package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Shot_Along extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_Along.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public static final int attackTimes=2;

    public Shot_Along() {
        super(CID, STS,"img/cards/Shot_Along.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                4, 2, 0, 0, attackTimes, 0, 1,0);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if(damage>=powerAmount(P, P_Call.PID)) {
            magicNumber=baseMagicNumber=attackTimes+1;
            isMagicNumberModified=true;
        }else{
            magicNumber=baseMagicNumber=attackTimes;
            isMagicNumberModified=false;
        }
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, magicNumber,AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Shot_Along();
    }


}
