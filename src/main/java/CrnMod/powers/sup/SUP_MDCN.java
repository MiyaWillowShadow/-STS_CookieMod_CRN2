package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.givePower;

public class SUP_MDCN extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_MDCN.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static final int poison=5;
    public static boolean powerUping=false;

    public SUP_MDCN(int amount) {
        super( amount, PID, STS,"img/powers/MDCN.png");
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1),poison);
    }

    public void run(int count){
        for(AbstractMonster m0: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m0.isDeadOrEscaped()){
                givePower(false,m0,new PoisonPower(m0,P,count*poison));
            }
        }
    }

    public void onInitialApplication2() {
        run(amount);
    }

    public void stackPower2(int stackAmount) {
        run(stackAmount);
    }

    @Override
    public void onSpecificTrigger(){
        run(amount);
    }
}