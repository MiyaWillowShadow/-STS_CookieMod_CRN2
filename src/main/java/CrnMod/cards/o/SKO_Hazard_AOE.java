package CrnMod.cards.o;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class SKO_Hazard_AOE extends NaiCard {
    public static final String CID = Helper.avoidDup(SKO_Hazard_AOE.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SKO_Hazard_AOE() {
        super(CID, STS,"img/cards/SKO_Hazard_AOE.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        magicNumber=baseMagicNumber=20;
        desFix();
    }

    @Override
    public void onChoseThisOption() {
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m.isDeadOrEscaped()){
                addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
                addAction(new DamageAction(m, new DamageInfo(P, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new SKO_Hazard_AOE();
    }


}
