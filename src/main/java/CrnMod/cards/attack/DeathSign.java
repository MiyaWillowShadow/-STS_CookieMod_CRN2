package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DeathSign extends CrnCard {
    public static final String CID = Helper.avoidDup(DeathSign.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public DeathSign() {
        super(CID, STS,"img/cards/Vary_Repeat.png",CardType.ATTACK,  CardRarity.UNCOMMON, CardTarget.ENEMY,
                18, 6, 0, 0, 0, 0, 2,0);
        this.isEthereal=true;
        this.tags.add(CRN.CustomTags.BOTH);
        desFix();
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
        run(p,m,damage);
    }

    public void run(AbstractPlayer p, AbstractMonster m,int dmg){
        addAction(new DamageAction(m, new DamageInfo(player, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addFunction(()->{
            if(m.isDeadOrEscaped() && AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng)!=null){
                AbstractMonster m1=null;
                for (AbstractMonster m2 : AbstractDungeon.getMonsters().monsters) {
                    if (!m2.isDeadOrEscaped()) {
                        if (m1 == null || (m2.currentHealth < m1.currentHealth)) {
                            m1 = m2;
                        }
                    }
                }
                if (m1 != null) {
                    run(p,m1,dmg);
                }
            }
        });
    }

    public AbstractCard makeCopy() {
        return new DeathSign();
    }


}
