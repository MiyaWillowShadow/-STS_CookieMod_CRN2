package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class Slains extends CrnCard {
    public static final String CID = Helper.avoidDup(Slains.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slains() {
        super(CID, STS,"img/cards/Slains.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                4, 0, 0, 0, 2, 1, 1,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
        gainPower(new P_Comb(magicNumber-1));
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slains();
    }


}
