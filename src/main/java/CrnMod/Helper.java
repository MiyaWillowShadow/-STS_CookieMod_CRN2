package CrnMod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Function;

import static com.megacrit.cardcrawl.helpers.CardLibrary.getCardList;
import static CrnMod.CRN.PlayerLibraryEnum.CRN_COLOR;
import static java.lang.Math.max;



public class Helper {
    public static AbstractPlayer P;
    public static String modId="CRNmod2";//在ModTheSpire.json中写的id

    public static void initialGlobalP(){
        P =AbstractDungeon.player;
    }

    public static String avoidDup(String id) {
        return "CrnMod:" + id; //防止和其他MOD的实例冲突
    }

    //apply a func to all card
    public static void cardsCheck(Function<AbstractCard,AbstractCard>  func) {
        cardsCheck(true,true,true,func);
    }
    public static void cardsCheck(boolean drawPile, boolean hand, boolean discardPile, Function<AbstractCard,AbstractCard>  func) {
        for(AbstractCard c:AbstractDungeon.player.drawPile.group){func.apply(c);}
        for(AbstractCard c:AbstractDungeon.player.hand.group){func.apply(c);}
        for(AbstractCard c:AbstractDungeon.player.discardPile.group){func.apply(c);}
    }
    //a jocard group which can make the func return true
    public static ArrayList<AbstractCard> jocardsMake(Function<AbstractCard,Boolean>  func) {
        ArrayList<AbstractCard> cards =new ArrayList<>();
        ArrayList<AbstractCard> playerLib =getCardList(CRN_COLOR);
        Iterator<AbstractCard> var = playerLib.iterator();
        AbstractCard c;
        while(var.hasNext()) {
            c = var.next();
            if(func.apply(c)){
                cards.add(c);
            }
        }
        return cards;
    }
    //no duplicate
    public static ArrayList<AbstractCard> randomCardsFromCards(ArrayList<AbstractCard> cards,int amount) {
        ArrayList<AbstractCard> returnCards =new ArrayList<>();
        AbstractCard c;
        for(int i = 0; i < amount; ++i) {
            c=cards.get(AbstractDungeon.cardRng.random(0, cards.size() - 1));
            returnCards.add(c);
            cards.remove(c);
        }
        return returnCards;
    }
    public static AbstractMonster targetAlive(AbstractMonster m){
        return (m == null || m.isDeadOrEscaped()) ? AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng) : m;
    }
    public static void setBaseCostWithOutXB(AbstractCard c,int newBaseCost) {
        if(c.cost>=0){
            int diff = c.costForTurn - c.cost;
            c.cost = newBaseCost;
            c.costForTurn = max(0,c.cost + diff);
            c.upgradedCost = true;
        }
    }
    public static void addBaseCostWithOutXB(AbstractCard c,int add) {
        if(c.cost>=0){
            c.cost = max(0,c.cost+add);
            c.costForTurn = max(0,c.costForTurn +add);
            c.upgradedCost = true;
        }
    }

    public static int countMonsters(){
        int count=0;
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()){
                ++count;
            }
        }
        return count;
    }
    public interface Lambda extends Runnable {}
}


