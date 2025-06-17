package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class Both_Stone extends CrnCard {
    public static final String CID = Helper.avoidDup(Both_Stone.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Both_Stone() {
        super(CID, STS,"img/cards/Both_Stone.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ENEMY,
                20, 8, 0, 0, 0, 0, 2,0);
        this.tags.add(CRN.CustomTags.BOTH);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SMASH);
        if(powerAmount(p, P_Comb.PID)>=4 ||powerAmount(p, P_Aim.PID)>=4){
            addToBot(new StunMonsterAction(m,p));
            this.exhaust =true;
        }else{
            this.exhaust =false;
        }

//                addToBot(new ExhaustSpecificCardAction(this,p.hand));
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Both_Stone();
    }


}
