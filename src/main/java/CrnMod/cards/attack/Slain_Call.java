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

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Slain_Call extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_Call.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_Call() {
        super(CID, STS,"img/cards/Slain_Call.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                9, 3, 0, 0, 1, 1, 1,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("宣伝",1.25F);
        attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        gainPower(new P_Call(magicNumber));
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_Call();
    }


}
