package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
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
import static CrnMod.powers.P_Call.countSupport;

public class Slain_BySup extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_BySup.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_BySup() {
        super(CID, STS,"img/cards/Slain_BySup.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                12, 4, 0, 0, 0, 0, 2,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        int sup=countSupport();
        attackRandom(1+sup,AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        talkSound("YMN",1.25F,3);
    }

    public AbstractCard makeCopy() {
        return new Slain_BySup();
    }


}
