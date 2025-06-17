package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class Slain_Aoe extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_Aoe.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_Aoe() {
        super(CID, STS,"img/cards/Slain_Aoe.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ALL_ENEMY,
                7, 3, 0, 0, 1, 0, 1,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        this.isMultiDamage=true;
        desFix();
    }

    public int realBaseDamage(){
        return baseDamage+magicNumber*powerAmount(P, P_Comb.PID);
    }

    public void applyPowers() {
        applyPowersPreFix(realBaseDamage());
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        calculateCardDamagePreFix(m,realBaseDamage());
    }
    public void use2(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        attackAoe( AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_Aoe();
    }


}
