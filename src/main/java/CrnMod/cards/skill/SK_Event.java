package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.relics.crn.R_Basic2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SK_Event extends CrnCard implements OnObtainCard {
    public static final String CID = Helper.avoidDup(SK_Event.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Event() {
        super(CID, STS,"img/cards/SK_Event.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 0, 0, 2, 2, -2,0);
        this.tags.add(CardTags.HEALING);
        desFix();
    }

    public void onObtainCard(){
        talkSound("SNNM",1.25F);
        if (P.hasRelic(R_Basic2.RID))  {
            AbstractRelic r=P.getRelic(R_Basic2.RID);
            r.counter+=magicNumber;
            r.flash();
        }else{
            AbstractDungeon.player.increaseMaxHp(magicNumber, true);
        }
        P.masterDeck.removeCard(this);
        //删除此卡
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new SK_Event();
    }


}
