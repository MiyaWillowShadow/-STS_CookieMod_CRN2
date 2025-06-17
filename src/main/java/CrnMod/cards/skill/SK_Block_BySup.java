package CrnMod.cards.skill;

import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.powers.P_Call.countSupport;

public class SK_Block_BySup extends CrnCard {
    public static final String CID = Helper.avoidDup(SK_Block_BySup.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SK_Block_BySup() {
        super(CID, STS,"img/cards/SK_Block_BySup.png",CardType.SKILL,  CardRarity.UNCOMMON, CardTarget.SELF,
                0, 0, 8, 3, 2, 0, 1,0);
        desFix();
    }

    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        int sup=countSupport();
        float tmp = (float)this.baseBlock+sup*magicNumber;

        Iterator var2;
        AbstractPower p;
        for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlock(tmp, this)) {
            p = (AbstractPower)var2.next();
        }

        for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlockLast(tmp)) {
            p = (AbstractPower)var2.next();
        }

        if (this.baseBlock != MathUtils.floor(tmp)) {
            this.isBlockModified = true;
        }

        if (tmp < 0.0F) {
            tmp = 0.0F;
        }

        this.block = MathUtils.floor(tmp);
    }


    public void use2(AbstractPlayer p, AbstractMonster m) {
        gainBlock(block);
    }

    public void upgrade2() {
    }
    public AbstractCard makeCopy() {
        return new SK_Block_BySup();
    }


}
