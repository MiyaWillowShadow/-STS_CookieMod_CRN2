package CrnMod.cards.attack;

import CrnMod.CRN;
import CrnMod.Helper;
import CrnMod.father.CrnCard;
import CrnMod.relics.crn.R_Basic2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.NJson.descriptionMakeForCard;
import static CrnMod.Thmod.talkSound;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.BOSS;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType.ELITE;

public class Both_HNS extends CrnCard {
    public static final String CID = Helper.avoidDup(Both_HNS.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public Both_HNS() {
        super(CID, STS,"img/cards/Both_HNS.png",CardType.ATTACK,  CardRarity.RARE, CardTarget.ALL_ENEMY,
                6, 0, 0, 0, 2, 1, 2,0);
        this.tags.add(CRN.CustomTags.BOTH);
        this.tags.add(CardTags.HEALING);
        this.exhaust =true;
        this.isMultiDamage=true;
        desFix();
    }

    public void use2(AbstractPlayer p, AbstractMonster m) {
        talkSound("HNS2",1.25F);
        ArrayList<AbstractMonster> ms=new ArrayList<>();
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()) {
                ms.add(m0);
            }
        }
        for (int i = 0; i < magicNumber; i++) {
            attackAoe( AbstractGameAction.AttackEffect.SMASH);
        }
        if(p.hasRelic(R_Basic2.RID)){
            AbstractRelic r=p.getRelic(R_Basic2.RID);
            addFunction(()->{
                for(AbstractMonster m0: ms){
                    if( (m0.isDeadOrEscaped() || m0.currentHealth <= 0) && !m0.hasPower("Minion")){
                        r.counter+=1+(m0.type==ELITE? 2:0)+(m0.type==BOSS? 5:0);
                    }
                }
            });
        }
    }

    public void upgrade2() {
    }

    public AbstractCard makeCopy() {
        return new Both_HNS();
    }


}
