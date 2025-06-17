package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Vary_Debuff extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Debuff.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Debuff() {
        super(CID, STS,"img/cards/Vary_Debuff.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                8, 2, 0, 0, 1, 1, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("MGR",1.25F);
        attack(m, AbstractGameAction.AttackEffect.SMASH);
        if(p.hasPower(P_Comb.PID)){
            givePower(m,new WeakPower(m,magicNumber,false));
        }
        if(p.hasPower(P_Aim.PID)){
            givePower(m,new VulnerablePower(m,magicNumber,false));
        }
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Debuff();
    }


}
