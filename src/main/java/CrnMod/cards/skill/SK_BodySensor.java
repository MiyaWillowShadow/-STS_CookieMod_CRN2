package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_BodySensor extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_BodySensor.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_BodySensor() {
        super(CID, STS,"img/cards/SK_BodySensor.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.ALL_ENEMY,
                0, 0, 0, 0, 4, 2, 0,0);
        exhaust =true;
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("YJ戊辰戦争",1.25F);
        gainPower(new MetallicizePower(p,magicNumber));
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()){
                givePower(m0,new MetallicizePower(m0,magicNumber));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new SK_BodySensor();
    }


}
