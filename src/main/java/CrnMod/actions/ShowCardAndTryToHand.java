package CrnMod.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;

import java.util.Iterator;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.newCardInBattle;

public class ShowCardAndTryToHand extends AbstractGameEffect {
    private static final float PADDING=25.0F * Settings.scale;
    private final AbstractCard card;

    //newCard决定是否被操纵现实升级
    public ShowCardAndTryToHand(AbstractCard card,boolean wasCardCreated) {
        this.card = card;
        this.identifySpawnLocation((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F);
        this.duration = 0.8F;
        card.drawScale = 0.75F;
        card.targetDrawScale = 0.75F;
        card.transparency = 0.01F;
        card.targetTransparency = 1.0F;
        card.fadingOut = false;
        playCardObtainSfx();
        
        if(P.hand.size()<10){
            P.hand.addToHand(card);
        }else{
            P.discardPile.addToTop(card);
        }
        card.triggerWhenCopied();
        P.hand.refreshHandLayout();
        P.hand.applyPowers();
        P.onCardDrawOrDiscard();

        if(wasCardCreated){
            newCardInBattle(card);
        }



    }

    private void playCardObtainSfx() {
        int effectCount = 0;
        for (AbstractGameEffect e : AbstractDungeon.effectList) {
            if (e instanceof ShowCardAndAddToHandEffect) {
                ++effectCount;
            }
        }
        if (effectCount < 2) {
            CardCrawlGame.sound.play("CARD_OBTAIN");
        }

    }

    private void identifySpawnLocation(float x, float y) {
        int effectCount = 0;
        Iterator var4 = AbstractDungeon.effectList.iterator();

        while(var4.hasNext()) {
            AbstractGameEffect e = (AbstractGameEffect)var4.next();
            if (e instanceof ShowCardAndAddToHandEffect) {
                ++effectCount;
            }
        }

        this.card.target_y = (float)Settings.HEIGHT * 0.5F;
        switch (effectCount) {
            case 0:
                this.card.target_x = (float)Settings.WIDTH * 0.5F;
                break;
            case 1:
                this.card.target_x = (float)Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
                break;
            case 2:
                this.card.target_x = (float)Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
                break;
            case 3:
                this.card.target_x = (float)Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
                break;
            case 4:
                this.card.target_x = (float)Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
                break;
            default:
                this.card.target_x = MathUtils.random((float)Settings.WIDTH * 0.1F, (float)Settings.WIDTH * 0.9F);
                this.card.target_y = MathUtils.random((float)Settings.HEIGHT * 0.2F, (float)Settings.HEIGHT * 0.8F);
        }

        this.card.current_x = this.card.target_x;
        this.card.current_y = this.card.target_y - 200.0F * Settings.scale;
        AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.card.update();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            this.card.render(sb);
        }

    }

    public void dispose() {
    }
}
