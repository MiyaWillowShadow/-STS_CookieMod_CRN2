package CrnMod.actions;

import CrnMod.father.AbstractPickHand;
import CrnMod.potions.PO_Beer;
import CrnMod.potions.PO_BottleMilk;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.*;

import java.util.Iterator;

import static CrnMod.Helper.P;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.returnRandomPotion;

public class TryAddPotion extends AbstractPickHand {
    private final AbstractMonster m;
    private AbstractPotion potion;

    public TryAddPotion(AbstractMonster m) {
        super(CardCrawlGame.languagePack.getUIString("ExhaustAction").TEXT[0], 1,false );
        this.m = m;
    }

    public void run() {
        boolean succeed;
        if (AbstractDungeon.player.hasRelic("Sozu")) {
            AbstractDungeon.player.getRelic("Sozu").flash();
            succeed=false;
        } else {
            succeed=P.obtainPotion(potion);
        }
        if(!succeed){
            AbstractPotion.playPotionSound();
            potion.use(m);
        }
    }

    @Override
    public void applyFirst() {
        if(selectedCards.isEmpty()){
            potion=returnRandomPotion(true);
            run();
        }
    }

    @Override
    public void applyToSelectedCards(AbstractCard c) {
        if(c.type== AbstractCard.CardType.ATTACK){
            potion=new FirePotion();
        }else if(c.type== AbstractCard.CardType.SKILL){
            potion=new BlockPotion();
        }else if(c.type== AbstractCard.CardType.POWER){
            potion=new LiquidMemories();
        }else if(c.type== AbstractCard.CardType.STATUS){
            potion=new PO_Beer();
        }else if(c.type== AbstractCard.CardType.CURSE){
            potion=new PO_BottleMilk();
        }
        P.hand.moveToExhaustPile(c);
        run();
    }
}
