package CrnMod.actions.specific;

import CrnMod.father.AbstractPickPile;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static CrnMod.Helper.P;
import static basemod.BaseMod.logger;

public class SK_ExhaustToDraw_Action extends AbstractPickPile {

    public SK_ExhaustToDraw_Action(int amount) {
        super(CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0],AbstractDungeon.player.drawPile,amount,false);
    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        P.drawPile.moveToExhaustPile(c);
        int draw=0;
        if(c.type== AbstractCard.CardType.CURSE ||c.rarity== AbstractCard.CardRarity.RARE){
            draw=3;
        }else if(c.type== AbstractCard.CardType.STATUS ||c.rarity== AbstractCard.CardRarity.UNCOMMON){
            draw=2;
        }else {
            draw=1;
        }
        addToTop(new DrawCardAction(draw));
    }

    @Override
    public void applyFirst(){
    }
}
