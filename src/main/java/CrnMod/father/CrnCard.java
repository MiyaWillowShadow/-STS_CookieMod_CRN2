package CrnMod.father;

import CrnMod.CRN;
import CrnMod.powers.PCP.PCP_DeathMathch;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Call;
import CrnMod.powers.P_Comb;
import CrnMod.powers.specific.SK_Block_DropCombAim_P;
import CrnMod.powers.specific.SK_Retain_P;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Objects;

import static CrnMod.CRN.PlayerColorEnum.CRN_COLOR;
import static CrnMod.Helper.P;

public abstract class CrnCard extends NaiCard {

    public CrnCard(String id, ArrayList<String> sts, String img, CardType type, CardRarity rarity, CardTarget target, int dmg, int dmgUp, int block, int blockUp, int mag, int magUp, int cost, int costUp) {
        super(id, sts, img, type, CRN_COLOR, rarity, target, dmg, dmgUp, block, blockUp, mag, magUp, cost, costUp);
        tags.add(CRN.CustomTags.CRNCARD);
    }

    public CrnCard(String id, ArrayList<String> sts, String img, CardType type, CardRarity rarity, CardTarget target, int cost) {
        super(id, sts, img, type, CRN_COLOR, rarity, target, cost);
        tags.add(CRN.CustomTags.CRNCARD);
    }

    public static int[] getCombAim(){
        int[] combAim=new int[]{0,0};
        for(AbstractPower pw: P.powers){
            if(Objects.equals(pw.ID, P_Comb.PID)){
                combAim[0]=pw.amount;
            }else if(Objects.equals(pw.ID, P_Aim.PID)){
                combAim[1]=pw.amount;
            }
        }
        return combAim;
    }

    public static boolean isVary(AbstractCard c) {
        return c.hasTag(CRN.CustomTags.VARY)|| (!c.hasTag(CRN.CustomTags.CRNCARD) && c.type==CardType.ATTACK);
    }

    public static boolean isSlain(AbstractCard c){
        int[] combAim=getCombAim();
        return c.hasTag(CRN.CustomTags.SLAIN )||c.hasTag(CRN.CustomTags.BOTH )|| (isVary(c) && combAim[0]>0 && combAim[0]>=combAim[1]);
    }

    public static boolean isShot(AbstractCard c){
        int[] combAim=getCombAim();
        return c.hasTag(CRN.CustomTags.SHOT )||c.hasTag(CRN.CustomTags.BOTH )|| (isVary(c) && combAim[1]>0 && combAim[1]>=combAim[0]);
    }

    public static void tryDropCombAim(){
        if(!P.hasPower(SK_Retain_P.PID)){
            int comb=0;
            int aim=0;
            for (AbstractPower pw:P.powers){
                if(Objects.equals(pw.ID, P_Comb.PID)){
                    comb=pw.amount;
                    losePower(pw.ID,comb);
                    PCP_DeathMathch.run(true);
                }
                if(Objects.equals(pw.ID, P_Aim.PID)){
                    aim=pw.amount;
                    losePower(pw.ID,aim);
                    PCP_DeathMathch.run(false);
                }
            }
            if(comb+aim>0){
                SK_Block_DropCombAim_P.run(comb+aim);
            }
        }
    }

    public String desFix2(String s){
        String add="";
        if(hasTag(CRN.CustomTags.SLAIN)){
//            add="crnmod:斩击";
            add="crnmod:"+ CardCrawlGame.languagePack.getUIString("CrnMod:SLAIN").TEXT[0];
        }
        else if(hasTag(CRN.CustomTags.SHOT)){
            add="crnmod:"+ CardCrawlGame.languagePack.getUIString("CrnMod:SHOT").TEXT[0];
        }
        else if(hasTag(CRN.CustomTags.VARY)){
            add="crnmod:"+ CardCrawlGame.languagePack.getUIString("CrnMod:VARY").TEXT[0];
        }
        else if(hasTag(CRN.CustomTags.BOTH)){
            add="crnmod:"+ CardCrawlGame.languagePack.getUIString("CrnMod:BOTH").TEXT[0];
        }
        if(!add.isEmpty()){
            add=add+ (isInnate || retain? " ， ":" NL ");
        }
        return add+s;
    }

    public  void changeTag(AbstractCard.CardTags tag){
        tags.remove(CRN.CustomTags.SLAIN);
        tags.remove(CRN.CustomTags.SHOT);
        tags.remove(CRN.CustomTags.RESET);
        tags.remove(CRN.CustomTags.BOTH);
        tags.add(tag);
    }

    public boolean tryUseCall(int amount){
        if(freeToPlayOnce || powerAmount(P, P_Call.PID)>=amount){
            if(!freeToPlayOnce && amount>0){
                losePower(P_Call.PID,amount);
            }
            return true;
        }
        return false;
    }

    public boolean callCheckFix(boolean baseCheck){
        return freeToPlayOnce || baseCheck;
    }
}
