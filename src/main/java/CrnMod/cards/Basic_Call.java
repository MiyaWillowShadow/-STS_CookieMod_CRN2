package CrnMod.cards;

import CrnMod.Helper;
import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.NaiCard;
import CrnMod.powers.P_Call;
import CrnMod.relics.crn.R_Basic2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Basic_Call extends NaiCard {
    public static final String CID = Helper.avoidDup(Basic_Call.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public String rid="";

    public Basic_Call(String rid) {
        super(CID, STS,"img/cards/SK_BasicCall.png",CardType.SKILL,CardColor.COLORLESS,  CardRarity.SPECIAL, CardTarget.SELF,
                0, 0, 0, 0, 3, 0, 0,0);
        this.isEthereal=true;
        this.exhaust =true;
        this.rid=rid;
        desFix();
    }
    public Basic_Call() {
        this("");
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        R_Basic2 r = (R_Basic2) P.getRelic(rid);
        int useAmount=Math.min(r.counter, magicNumber);
        if(useAmount>0){
            r.use(useAmount);
            gainPower(new P_Call(useAmount+(upgraded?1:0)));
        }
        if(r.counter>0){
            addFunction(()->{
                Basic_Call copy = (Basic_Call) this.makeStatEquivalentCopy();
                copy.rid=rid;
                AbstractDungeon.effectList.add(new ShowCardAndTryToHand(copy,true));
            });
        }
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new Basic_Call();
    }


}
