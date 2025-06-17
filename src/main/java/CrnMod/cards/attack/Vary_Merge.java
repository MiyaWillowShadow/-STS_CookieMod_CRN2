package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.cards.o.Merge_Aim;
import CrnMod.cards.o.Merge_Comb;
import CrnMod.cards.o.Merge_Nothing;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.cards.Basic_Merge.merge;

public class Vary_Merge extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Merge.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Merge() {
        super(CID, STS,"img/cards/Vary_Merge.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                10, 4, 0, 0, 0, 0, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SMASH);
        if(p.hasPower(P_Comb.PID)||p.hasPower(P_Aim.PID)){
            ArrayList<AbstractCard> cards = new ArrayList<>();
            cards.add(new Merge_Comb(this));
            cards.add(new Merge_Aim(this));
            cards.add(new Merge_Nothing(this));
            addToBot(new ChooseOneAction(cards));
            addFunction(()->{
                merge(magicNumber);
            });
        }
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Merge();
    }


}
