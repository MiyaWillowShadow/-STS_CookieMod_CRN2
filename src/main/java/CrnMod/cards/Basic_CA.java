package CrnMod.cards;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.effectSound;
import static CrnMod.Thmod.talkSound;

public class Basic_CA extends CrnCard {
    public static final String CID = Helper.avoidDup(Basic_CA.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Basic_CA() {
        super(CID, STS,"img/cards/Vary_CombAim.png",CardType.ATTACK,  CardRarity.BASIC, CardTarget.ENEMY,
                4, 2, 0, 0, 1, 1, 0,0);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.getIntentBaseDmg() >= 0) {
            attack(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            gainPower(true,new P_Aim(magicNumber));
            effectSound("GUN",1.25F,3);
        }else{
            attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            gainPower(true,new P_Comb(magicNumber));
        }

    }

    public AbstractCard makeCopy() {
        return new Basic_CA();
    }


}
