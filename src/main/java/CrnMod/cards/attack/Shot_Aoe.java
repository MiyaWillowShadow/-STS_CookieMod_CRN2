package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Shot_Aoe extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_Aoe.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Shot_Aoe() {
        super(CID, STS,"img/cards/Shot_Aoe.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ALL_ENEMY,
                12, 4, 0, 0, 2, -1, 2,0);
        this.tags.add(CRN.CustomTags.SHOT);
        this.isMultiDamage=true;
        desFix();
    }

    public int realBaseDamage(){
        int call=powerAmount(P, P_Call.PID);
        return baseDamage+(callCheckFix(call>=magicNumber)?call:0);
    }

    public void applyPowers() {
        applyPowersPreFix(realBaseDamage());
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        calculateCardDamagePreFix(m, realBaseDamage());
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attackAoe(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        tryUseCall(magicNumber);
        talkSound("NDKNDK知らなーい",1.25F);
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Shot_Aoe();
    }


}
