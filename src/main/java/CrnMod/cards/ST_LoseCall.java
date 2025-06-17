package CrnMod.cards;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class ST_LoseCall extends NaiCard {
    public static final String CID = Helper.avoidDup(ST_LoseCall.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public ST_LoseCall() {
        super(CID, STS,"img/cards/ST_LoseCall.png",CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE,
                0, 0, 0, 0, 2, 0, -2,2);
        this.exhaustChange=true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            gainPower(new P_Call(magicNumber));
        }else if (this.dontTriggerOnUseCard) {
            losePower(P_Call.PID,magicNumber);
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public boolean canUpgrade(){
        return !upgraded;
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new ST_LoseCall();
    }


}
