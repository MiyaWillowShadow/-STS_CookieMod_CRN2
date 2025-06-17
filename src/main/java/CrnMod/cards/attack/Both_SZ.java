package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Both_SZ extends CrnCard {
    public static final String CID = Helper.avoidDup(Both_SZ.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Both_SZ() {
        super(CID, STS,"img/cards/Both_SZ.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ENEMY,
                8, 2, 0, 0, 30, 30, 1,0);
        this.exhaust =true;
        this.tags.add(CRN.CustomTags.BOTH);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("SZ3",1.25F);
        attack(m, AbstractGameAction.AttackEffect.SMASH);
        if(damage<magicNumber){
            Both_SZ c = (Both_SZ) this.makeStatEquivalentCopy();
            c.baseDamage++;
            AbstractDungeon.effectList.add(new ShowCardAndTryToHand(c,true));
        }
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Both_SZ();
    }


}
