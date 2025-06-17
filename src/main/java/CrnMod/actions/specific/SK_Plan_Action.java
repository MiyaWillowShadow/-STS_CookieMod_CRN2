package CrnMod.actions.specific;

import CrnMod.Helper;
import CrnMod.father.AbstractPickPile;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ListIterator;

import static CrnMod.Helper.P;

public class SK_Plan_Action extends AbstractPickPile {
    public SK_Plan_Action(int amount) {
        super(CardCrawlGame.languagePack.getUIString(Helper.avoidDup("SetCardToTop")).TEXT[0],AbstractDungeon.player.drawPile,amount,true);
    }

    @Override
    public void applyFirst(){
        //cards.reversed(); //don't use reversed,or the game will crash
        ListIterator<AbstractCard> var3 = selectedCards.listIterator(selectedCards.size());
        while (var3.hasPrevious()) {
            AbstractCard c= var3.previous();
            P.drawPile.removeCard(c);
            P.hand.moveToDeck(c, false);
        }
        AbstractDungeon.player.hand.refreshHandLayout();
    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {

    }
}
