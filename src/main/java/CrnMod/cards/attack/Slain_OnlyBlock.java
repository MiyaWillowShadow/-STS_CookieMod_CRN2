package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.powers.P_Comb;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;

public class Slain_OnlyBlock extends CrnCard {
    public static final String CID = Helper.avoidDup(Slain_OnlyBlock.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Slain_OnlyBlock() {
        super(CID, STS,"img/cards/Slain_OnlyBlock.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                0, 0, 10, 4, 1, 0, 2,0);
        this.tags.add(CRN.CustomTags.SLAIN);
        desFix();
    }

    @Override
    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = (float)this.baseBlock;

        int comb=powerAmount(P, P_Comb.PID);
        if(comb>0){
            tmp*= 1F+0.2F*comb;
            magicNumber= (int) (baseMagicNumber*(1F+0.2F*comb));
        }

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
        talkSound("DIYUSIBell",1.25F);
        gainBlock(block);
        gainPower(new BlurPower(p,magicNumber));
        attack(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Slain_OnlyBlock();
    }


}
