package CrnMod.father;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class AbstractPickPile extends AbstractGameAction {
    public final String message;
    public final CardGroup cardGroup;
    public final int selectAmount;
    public final boolean anyNumber;
    public ArrayList<AbstractCard> selectedCards=new ArrayList<>();
    public Function<AbstractCard , Boolean> filter;

    public AbstractPickPile(String message, CardGroup cardGroup, int selectAmount, boolean anyNumber) {
        this.message = message;
        this.cardGroup = cardGroup;
        this.selectAmount = selectAmount;
        this.anyNumber=anyNumber;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration=Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == startDuration) { //此if内的动作仅运行一次,从而避免handCardSelectScreen改变手牌而误触发其他case
            CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for(AbstractCard c:cardGroup.group){
                if (filter==null || filter.apply(c)) {
                    tmpGroup.addToRandomSpot(c); //打乱顺序,使选牌窗口不暴露牌序
                }
            }
            if (tmpGroup.isEmpty()) {
                isDone = true;
                return;
            }
            if(!anyNumber && tmpGroup.size() <= selectAmount){
                selectedCards.addAll(tmpGroup.group);
                apply();
                isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(tmpGroup, selectAmount, anyNumber, message);
            this.tickDuration();
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            selectedCards.addAll(AbstractDungeon.gridSelectScreen.selectedCards);
            apply();
            AbstractDungeon.gridSelectScreen.selectedCards.clear(); //使if为false
        }
        this.tickDuration();
    }

    public void apply(){
        applyFirst();
        for (AbstractCard c : selectedCards) {
            applyToSelectedCards(c);
        }
    }
    public abstract void applyFirst();
    public abstract void applyToSelectedCards(AbstractCard c);
}
