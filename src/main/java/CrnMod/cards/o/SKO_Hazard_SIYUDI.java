package CrnMod.cards.o;

import CrnMod.Helper;
import CrnMod.father.NaiCard;
import CrnMod.powers.P_Call;
import CrnMod.powers.sup.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.CRN.PlayerColorEnum.CRN_DERIVATIONS;
import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForCard;

public class SKO_Hazard_SIYUDI extends NaiCard {
    public static final String CID = Helper.avoidDup(SKO_Hazard_SIYUDI.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(CID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(cardStrings.NAME, descriptionMakeForCard(cardStrings.DESCRIPTION), descriptionMakeForCard(cardStrings.UPGRADE_DESCRIPTION)));

    public SKO_Hazard_SIYUDI() {
        super(CID, STS,"img/cards/SKO_Hazard_SIYUDI.png",CardType.STATUS, CRN_DERIVATIONS, CardRarity.SPECIAL, CardTarget.NONE,-2);
        desFix();
    }

    @Override
    public void onChoseThisOption() {
        for(AbstractPower pw: P.powers){
            if(pw instanceof Abstract_SUP){
                Abstract_SUP sup= (Abstract_SUP) pw;
                if(sup.supLv!=4 && !Objects.equals(sup.ID, SUP_SIYUDI.PID)){
                    gainPower(new SUP_SIYUDI(sup.amount));
                    losePower(sup.ID,sup.amount);
                }
            }
        }
        for(int pos = 0; pos<P_Call.sups.size(); pos++){
            if(pos!=6){
                P_Call.sups.set(pos,SUP_SIYUDI.PID);
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
        return new SKO_Hazard_SIYUDI();
    }


}
