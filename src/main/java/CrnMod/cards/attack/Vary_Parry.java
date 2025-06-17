package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.specific.Vary_Parry_P;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Vary_Parry extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Parry.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Parry() {
        super(CID, STS,"img/cards/Vary_Parry.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                10, 2, 0, 0, 1, 1, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("DIYUSIBell",1.25F);
        attack(m, AbstractGameAction.AttackEffect.SMASH);
        gainPower(new Vary_Parry_P(magicNumber));
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Parry();
    }


}
