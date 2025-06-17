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

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Slain_ByDebuff extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_ByDebuff.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public static final int attackTimes=2;


    public Slain_ByDebuff() {
        super(CID, STS,"img/cards/Slain_ByDebuff.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                3, 2, 0, 0, attackTimes, 0, 1,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        desFix();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        if (!(m==null)){
            int debuffAmount=0;
            for(AbstractPower pw:m.powers){
                if(pw.type== AbstractPower.PowerType.DEBUFF){
                    debuffAmount++;
                }
            }
            if(debuffAmount>0) {
                magicNumber=baseMagicNumber=attackTimes+debuffAmount;
                isMagicNumberModified=true;
            }else{
                magicNumber=baseMagicNumber=attackTimes;
                isMagicNumberModified=false;
            }
        }
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, magicNumber,AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        magicNumber=baseMagicNumber=attackTimes;
        isMagicNumberModified=false;
        talkSound("YMNINM",1.25F);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_ByDebuff();
    }


}
