package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_StrDown extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_StrDown.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_StrDown() {
        super(CID, STS,"img/cards/SK_StrDown.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                0, 0, 0, 0, 0, 0, 1,-1);
        exhaust =true;
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("NEL2",1.25F);
        addFunction(()->{
            int comb=powerAmount(p, P_Comb.PID);
            int aim=powerAmount(p, P_Aim.PID);
            int count= Math.max(comb, aim);
            if(count>0){
                givePower(m,new StrengthPower(m,-count));
            }
        });
    }

    public AbstractCard makeCopy() {
        return new SK_StrDown();
    }


}
