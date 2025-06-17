package CrnMod.cards.attack;

import CrnMod.CRN;
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
import static CrnMod.Thmod.talkSound;

public class Vary_Draw extends CrnCard {
    public static final String CID = Helper.avoidDup(Vary_Draw.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Vary_Draw() {
        super(CID, STS,"img/cards/Vary_Draw.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                9, 2, 0, 0, 2, 1, 1,0);
        this.tags.add(CRN.CustomTags.VARY);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        int draw=powerAmount(p, P_Comb.PID)+powerAmount(p, P_Aim.PID);
        if(draw>0){
            draw(Math.min(draw, magicNumber));
        }
        talkSound("893",1.25F);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Vary_Draw();
    }


}
