package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class Shot_Spot extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_Spot.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Shot_Spot() {
        super(CID, STS,"img/cards/Shot_Spot.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                6, 2, 0, 0, 1, 1, 0,0);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        givePower(m,new P_Spot(m,magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Shot_Spot();
    }


}
