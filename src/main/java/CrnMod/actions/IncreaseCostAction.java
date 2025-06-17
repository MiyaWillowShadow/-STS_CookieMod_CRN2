package CrnMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class IncreaseCostAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card = null;

    public IncreaseCostAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public IncreaseCostAction(UUID targetUUID, int amount) {
        this.uuid = targetUUID; //若使用uuid则所有同id卡牌的费用都会改变
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }
    @Override
    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                c.modifyCostForCombat(amount);
            }
        } else {
            this.card.modifyCostForCombat(amount);
        }
        this.isDone = true;
    }
}
