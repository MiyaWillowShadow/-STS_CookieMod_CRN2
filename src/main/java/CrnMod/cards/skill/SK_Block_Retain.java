package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Call;
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

public class SK_Block_Retain extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_Retain.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Block_Retain() {
        super(CID, STS,"img/cards/SK_Block_Retain.png",CardType.SKILL,  CardRarity.COMMON, CardTarget.SELF,
                0, 0, 10, 4, 1, 0, 1,0);
        this.selfRetain=true;
        desFix();
    }

    public void triggerOnGlowCheck() {
        if (powerAmount(P, P_Call.PID)>=magicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        if(tryUseCall(magicNumber)){
            talkSound("MCTDefend",1.25F,2);
            gainBlock(block);
        }
    }

    public void upgrade2() {
    }
    public AbstractCard makeCopy() {
        return new SK_Block_Retain();
    }


}
