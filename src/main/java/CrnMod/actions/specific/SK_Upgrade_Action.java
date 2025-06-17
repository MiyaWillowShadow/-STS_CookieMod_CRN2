package CrnMod.actions.specific;

import CrnMod.actions.ShowCardAndTryToHand;
import CrnMod.father.AbstractPickHand;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import static CrnMod.Helper.P;

public class SK_Upgrade_Action extends AbstractPickHand {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
        TEXT = uiStrings.TEXT;
    }
    public SK_Upgrade_Action(int amount) {
        super(TEXT[0], amount, true);
        this.filter=(AbstractCard c)->{
            return !c.canUpgrade();
        };
    }

    @Override
    public void applyFirst() {

    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        c.upgrade();
        AbstractDungeon.effectList.add(new ShowCardAndTryToHand(c,false));
    }
}
