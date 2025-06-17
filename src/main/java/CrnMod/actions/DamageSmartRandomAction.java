package CrnMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class DamageSmartRandomAction extends AbstractGameAction {
    private final int damage;
    private final AbstractGameAction.AttackEffect effect;
    private AbstractMonster m;

    public DamageSmartRandomAction(int damage, AbstractMonster m, AbstractGameAction.AttackEffect effect) {
        this.damage = damage;
        this.m=m;
        this.effect = effect;
    }

    public void update() {
        this.m = (m == null || m.isDeadOrEscaped()) ? AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng) : m;
        if (this.m != null) {
            if (AttackEffect.LIGHTNING == this.effect) {
                this.addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), AttackEffect.NONE));
                this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                this.addToTop(new VFXAction(new LightningEffect(m.hb.cX, m.hb.cY)));
            } else {
                this.addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), effect));
            }
        }
        this.isDone = true;
    }
}
