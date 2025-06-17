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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Vary_ByBuff extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_ByBuff.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_ByBuff() {
        super(CID, STS,"img/cards/Vary_ByBuff.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                9, 3, 0, 0, 1, 0, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int debuff=0;
        for(AbstractPower pw:m.powers){
            if(pw.amount<0 && (Objects.equals(pw.ID, StrengthPower.POWER_ID)|| Objects.equals(pw.ID, DexterityPower.POWER_ID))){
                debuff-=pw.amount;
            }else if( pw.type== AbstractPower.PowerType.DEBUFF){
                debuff+=(pw.amount>0?pw.amount:1);
            }
        }
        calculateCardDamagePreFix(m,baseDamage+debuff*magicNumber);
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("AKYS",2.2F,3);
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_ByBuff();
    }


}
