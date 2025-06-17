package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class Shot_ToBlock extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_ToBlock.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Shot_ToBlock() {
        super(CID, STS,"img/cards/Shot_ToBlock.png",CardType.ATTACK,  CardRarity.COMMON, CardTarget.ENEMY,
                6, 2, 0, 0, 0, 1, 1,0);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.getIntentBaseDmg() >= 0) {
            addToBot(new WallopAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        }else{
            attack(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Shot_ToBlock();
    }


}
