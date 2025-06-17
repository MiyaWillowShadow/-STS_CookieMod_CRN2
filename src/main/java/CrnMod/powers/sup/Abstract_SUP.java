package CrnMod.powers.sup;

import CrnMod.cards.attack.Vary_Cost;
import CrnMod.father.NaiPower;
import CrnMod.powers.PCP.PCP_Sin;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.*;

public abstract class Abstract_SUP extends NaiPower {
    public int supLv=0;
    public Abstract_SUP(int amount, String pid, ArrayList<String> nameAndDes, String img) {
        super(P, amount, pid, nameAndDes, img);
        this.tags.add(PTags.SUP);
    }

    public void triggerAtGainSup(){
        if(P.hasPower(SUP_NEL.PID)){
            addFunction(()->{
                powerTrigger(P, SUP_NEL.PID);
            });
        }
        if(P.hasPower(PCP_Sin.PID)){
            addFunction(()->{
                powerTrigger(P, PCP_Sin.PID);
            });
        }
        cardsCheck(true,true,true,(AbstractCard c)->{
            if(Objects.equals(c.cardID, Vary_Cost.CID)){
                c.updateCost(-1);
            }
            return null;
        });
        triggerAtGainSup2();
    }
    public void triggerAtGainSup2(){

    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        triggerAtGainSup();
        stackPower2(stackAmount);
    }

    public void stackPower2(int stackAmount) {
    }

    public void onInitialApplication() {
        triggerAtGainSup();
        onInitialApplication2();
    }
    public void onInitialApplication2(){}

}