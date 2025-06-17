package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Comb;
import CrnMod.powers.specific.Slain_Copy_P;
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

public class Slain_Copy extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_Copy.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_Copy() {
        super(CID, STS,"img/cards/Slain_Copy.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                8, 2, 0, 0, 0, 0, 1,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        this.selfRetain = true;
        this.exhaust =true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("RRM",1.5F);
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if(p.hasPower(P_Comb.PID)){
            AbstractCard c= this.makeStatEquivalentCopy();
            c.upgrade();
            gainPower(new Slain_Copy_P(c));
        }
    }

    @Override
    public void upgrade() {
        this.upgradeDamage(dmgUp);
        ++timesUpgraded;
        this.upgraded = true;
        this.name = STS.get(0) + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }


    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_Copy();
    }


}
