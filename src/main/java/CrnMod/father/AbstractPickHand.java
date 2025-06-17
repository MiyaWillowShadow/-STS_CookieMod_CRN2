package CrnMod.father;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Function;

import static CrnMod.Helper.P;
import static basemod.BaseMod.logger;

public abstract class AbstractPickHand extends AbstractGameAction {
    public final String message;
    public final int selectAmount;
    public final boolean anyNumber;
    public ArrayList<AbstractCard> banCards=new ArrayList<>();
    public ArrayList<AbstractCard> selectedCards=new ArrayList<>();
    public Function  <AbstractCard , Boolean> filter;

    public AbstractPickHand(String message, int selectAmount, boolean anyNumber) {
        this.message = message;
        this.selectAmount = selectAmount;
        this.anyNumber=anyNumber;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == startDuration) { //此if内的动作仅运行一次,从而避免handCardSelectScreen改变手牌而误触发其他case
            if (P.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if(filter != null){
                for (AbstractCard c : P.hand.group) {
                    if (filter.apply(c)) {
                        banCards.add(c);
                    }
                }
            }
            if(!anyNumber){
                if (banCards.size() == P.hand.group.size()) {
                    isDone = true;
                    return;
                }
                if (P.hand.group.size() - banCards.size() <= selectAmount) {
                    selectedCards.addAll(P.hand.group); //白名单卡牌少于抽取量 直接全选
                    selectedCards.removeAll(banCards);
                    apply();
                    isDone = true;
                    return;
                }
            }
            P.hand.group.removeAll(banCards);
            AbstractDungeon.handCardSelectScreen.open(message, selectAmount, anyNumber, anyNumber);
            this.tickDuration();
            return; //必须return中断过程 才能保证if运行前已经跑完了选牌窗口?
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
//                logger.info("1被选择的selectedCards为" + c.name);
                selectedCards.add(c);
            }
            returnCards();
            apply();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;//使if的条件为false
        }

        this.tickDuration();
    }

    public void apply(){
        applyFirst();
        for (AbstractCard c : selectedCards) {
//            logger.info("2被选择的selectedCards为" + c.name);
            applyToSelectedCards(c);
        }
    }
    public abstract void applyFirst();
    public abstract void applyToSelectedCards(AbstractCard c);

    private void returnCards() {
        if(!banCards.isEmpty()){
            for (AbstractCard c : banCards) {
                P.hand.addToTop(c);
            }
            P.hand.refreshHandLayout();
        }
    }
}
