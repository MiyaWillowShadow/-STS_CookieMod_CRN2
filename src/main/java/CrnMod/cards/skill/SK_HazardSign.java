package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.cards.o.SKO_Hazard_AOE;
import CrnMod.cards.o.SKO_Hazard_Debuff;
import CrnMod.cards.o.SKO_Hazard_Exhaust;
import CrnMod.cards.o.SKO_Hazard_SIYUDI;
import CrnMod.father.CrnCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_HazardSign extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_HazardSign.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_HazardSign() {
        super(CID, STS,"img/cards/SK_HazardSign.png",CardType.SKILL,  CardRarity.RARE, CardTarget.SELF,
                0, 0, 0, 0, 0, 0, 2,-1);
        exhaust =true;
        selfRetain=true;
        desFix();
        MultiCardPreview.add(this, new AbstractCard[]{new SKO_Hazard_AOE(), new SKO_Hazard_Exhaust(), new SKO_Hazard_SIYUDI(),new SKO_Hazard_Debuff()});
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("HNS4",2F);
        ArrayList<AbstractCard> cards = new ArrayList<>(MultiCardPreview.multiCardPreview.get(this));
//        ArrayList<AbstractCard> cards = new ArrayList<>();
//        cards.add(new SKO_Hazard_AOE());
//        cards.add(new SKO_Hazard_Exhaust());
//        cards.add(new SKO_Hazard_SIYUDI());
//        cards.add(new SKO_Hazard_Debuff());
        this.addToBot(new ChooseOneAction(cards));
    }

    @Override
    public void upgrade2() {

    }

    public AbstractCard makeCopy() {
        return new SK_HazardSign();
    }


}
