package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;
import static com.megacrit.cardcrawl.ui.panels.EnergyPanel.totalCount;

public class Vary_X extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_X.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));
    public int dmgPlus=0;

    public Vary_X() {
        super(CID, STS,"img/cards/Vary_X.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ALL_ENEMY,
                5, 0, 0, 0, 0, 0, -1,0);
        this.tags.add(CRN.CustomTags.VARY);
        this.isMultiDamage=true;
        desFix();
    }

    public int realBaseDamage(){
        int call=powerAmount(P, P_Call.PID);
        return baseDamage+( callCheckFix(call>=totalCount)?totalCount:0);
    }

    public void applyPowers() {
        applyPowersPreFix(realBaseDamage());
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        calculateCardDamagePreFix(m,realBaseDamage());
    }

    public void triggerOnGlowCheck() {
        if (powerAmount(P, P_Call.PID)>=totalCount) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("DIYUSIBell",1.25F);
        addFunction(()->{
            int en=totalCount;
            if (this.energyOnUse != -1) {
                en = this.energyOnUse;
            }
            int x=en;
            if (p.hasRelic("Chemical X")) {
                x += 2;
                p.getRelic("Chemical X").flash();
            }

            int attackTimes=x+(upgraded?1:0);
            for(int i = 0; i < attackTimes; ++i) {
                if (i == 0) {
                    addToBot(new SFXAction("ATTACK_WHIRLWIND"));
                    addToBot(new VFXAction(new WhirlwindEffect(), 0.0F));
                }
                addToBot(new SFXAction("ATTACK_HEAVY"));
                addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
                attackAoe( AbstractGameAction.AttackEffect.NONE);
            }
            if (!this.freeToPlayOnce) {
                p.energy.use(this.energyOnUse);
            }
            tryUseCall(en);
        });

    }

    public AbstractCard makeCopy() {
        return new Vary_X();
    }


    @Override
    public void upgrade2() {
    }

}
