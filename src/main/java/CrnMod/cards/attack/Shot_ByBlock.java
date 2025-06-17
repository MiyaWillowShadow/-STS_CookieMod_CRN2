package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;

public class Shot_ByBlock extends CrnCard {
    public static final String CID = Helper.avoidDup(Shot_ByBlock.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Shot_ByBlock() {
        super(CID, STS,"img/cards/Shot_ByBlock.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                10, 4, 0, 0, 1, 0, 1,0);
        this.tags.add(CRN.CustomTags.SHOT);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int block=0;
        if(!(m ==null)){
            block=m.currentBlock;
        }
        calculateCardDamagePreFix(m,baseDamage+block);
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        attack(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void triggerOnGlowCheck() {
        boolean blockExist=false;
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(m0.currentBlock>0){
                blockExist=true;
                break;
            }
        }
        if (blockExist) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public AbstractCard makeCopy() {
        return new Shot_ByBlock();
    }


}
