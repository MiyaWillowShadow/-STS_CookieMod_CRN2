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

public class Vary_Free extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Free.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Free() {
        super(CID, STS,"img/cards/Vary_Free.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                8, 4, 0, 0, 1, 0, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    public void triggerOnGlowCheck() {
        if (powerAmount(P, P_Call.PID)>=magicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if(tryUseCall(magicNumber)){
            gainEn(1);
            draw(1);
        }
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Free();
    }


}
