package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Search extends CrnCard {
    public static final String CID = Helper.avoidDup(Search.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public int mod=0;

    public Search() {
        super(CID, STS,"img/cards/SK_Search.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 2, 0, 0,0);
        exhaust =exhaustChange=true;
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        boolean available=false;
        for(AbstractCard c:P.drawPile.group){
            if (CrnCard.isSlain(c) || CrnCard.isShot(c)) {
                available=true; //need at least one available card to run the action
                break;
            }
        }
        if (available) {
            mod=0;
            ArrayList<AbstractCard> cards = new ArrayList<>();
            cards.add(new Search_Slain(this));
            cards.add(new Search_Shot(this));
            addToBot(new ChooseOneAction(cards));
            addFunction(()->{
                    CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for(AbstractCard c:P.drawPile.group){
                        if ((mod==1 && CrnCard.isSlain(c)) || (mod==2 && CrnCard.isShot(c))) {
                            tmpGroup.addToRandomSpot(c);
                        }
                    }
                    if(!tmpGroup.isEmpty()){
                        int vacancy=10-p.hand.size();
                        int count= Math.min(vacancy, magicNumber);
                        for(AbstractCard c:tmpGroup.group){
                            p.drawPile.moveToHand(c);
                            if(--count<=0)break;
                        }
                    }
            });
        }
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Search();
    }


}
