package CrnMod.powers;

import CrnMod.Helper;
import CrnMod.cards.sup.*;
import CrnMod.father.NaiPower;
import CrnMod.powers.sup.SUP_MDCN;
import CrnMod.powers.sup.SUP_Milk;
import CrnMod.powers.sup.SUP_PCLY;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static CrnMod.Helper.P;
import static CrnMod.father.NaiCard.gainPower;

public class P_Call extends NaiPower {
    public static final String PID = Helper.avoidDup(P_Call.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));
    public static ArrayList<AbstractCard> supOptions=new ArrayList<>();
    public static ArrayList<String> sups=new ArrayList<>();

    public P_Call(int amount) {
        super(P, amount, PID, STS,"img/powers/Call.png", PowerType.BUFF);
        supOptionsInit();
    }

    public static void supOptionsInit(){
        if(supOptions.isEmpty()){
            supOptions.addAll(Arrays.asList(new O_DIYUSI(),new O_SIYUDI(),new O_MDCN(),
                    new O_SIK(),new O_ICG(),new O_NYN(),
                    new O_RMA(),new O_Flour(),new O_Milk(),
                    new O_HNS(),new O_KNN(),new O_SZ()));
        }
    }

    public static int countSupport(){
        int i=0;
        for(AbstractPower pw: P.powers){
            if(pw instanceof NaiPower && ((NaiPower)pw).hasTag(PTags.SUP)){
                ++i;
            }
        }
        return i;
    }

    public void updateDescription() {
        this.description = String.format(STS.get(1));
    }

    public void update(){
        int maxPos= Math.max(sups.size(), amount/3);
        for(int pos = 0; pos<maxPos; pos++){
            if(sups.size()<=pos){
                sups.add(""); //长度不够自动补1个空的
            }
            if(amount>=(pos+1)*3 && Objects.equals(sups.get(pos), "") ){
                if(pos<7){
                    addToBot(new ChooseOneAction(optionsMake(pos)));
                }else{
                    gainPower(new SUP_PCLY(1));
                    sups.set(pos, SUP_PCLY.PID);
                }
            }else if(amount<(pos+1)*3 && !Objects.equals(sups.get(pos), "")){
                if(Objects.equals(sups.get(pos), SUP_MDCN.PID)
                        ||Objects.equals(sups.get(pos), SUP_Milk.PID)
                        ||Objects.equals(sups.get(pos), SUP_PCLY.PID)){
                    addToBot(new ReducePowerAction(P,P,sups.get(pos),1));
                    sups.set(pos,"");
                    break;
                }
            }
        }
    }

    public void onInitialApplication() {
        this.update();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.update();
    }

    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        this.update();
    }

    public void onRemove() {
        this.amount = 0;
        this.update();
    }

    public ArrayList<AbstractCard> optionsMake(int pos){
        ArrayList<AbstractCard> cards = new ArrayList<>();
        int lv=pos/2*3;
        for(int i=0;i<3;i++){
            AbstractCard c=supOptions.get(lv).makeCopy();
            c.baseMagicNumber=pos;
            cards.add(c);
            lv++;
        }
        return cards;
    }
}