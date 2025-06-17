package CrnMod.cards.o;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.NJson.descriptionMakeForCard;

public class SKO_Hazard_Debuff extends NaiCard {
    public static final String CID = Helper.avoidDup(SKO_Hazard_Debuff.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SKO_Hazard_Debuff() {
        super(CID, STS,"img/cards/SKO_Hazard_Debuff.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        desFix();
    }

    @Override
    public void onChoseThisOption() {
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m.isDeadOrEscaped()){
                for(AbstractPower pw: m.powers){
                    if(pw.type== AbstractPower.PowerType.DEBUFF && pw.amount>0){
                        pw.flash();
                        pw.amount*=2;
                    }
                }
            }
        }
    }

    @Override
    public void use2(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade2() {
    }

    @Override
    public String desFix2(String s) {
        return s;
    }

    public AbstractCard makeCopy() {
        return new SKO_Hazard_Debuff();
    }


}
