package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.actions.TryAddPotion;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class SK_Potion extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Potion.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Potion() {
        super(CID, STS,"img/cards/SK_Potion.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                0, 0, 0, 0, 0, 0, 2,-1);
        exhaust =true;
        desFix();
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TryAddPotion(m));
        talkSound("RU九番茶",1.25F);
    }

    @Override
    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new SK_Potion();
    }


}
