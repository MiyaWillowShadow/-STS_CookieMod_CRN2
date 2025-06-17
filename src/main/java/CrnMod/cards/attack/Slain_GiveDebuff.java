package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Comb;
import CrnMod.powers.P_Spot;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Slain_GiveDebuff extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_GiveDebuff.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_GiveDebuff() {
        super(CID, STS,"img/cards/Slain_GiveDebuff.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ENEMY,
                10, 0, 0, 0, 0, 0, 2,-1);
        this.tags.add(CRN.CustomTags.SLAIN);
        this.exhaust =true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("YJじゃけん夜行きましょうね",1.25F);
        attack(m,AbstractGameAction.AttackEffect.SLASH_HEAVY);
        int comb=powerAmount(p, P_Comb.PID);
        if(comb>0){
            givePower(m, new PoisonPower(m,p,comb));
            givePower(m, new WeakPower(m,comb,false));
            givePower(m, new VulnerablePower(m,comb,false));
            givePower(m,new P_Spot(m,comb));
        }

    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_GiveDebuff();
    }


}
