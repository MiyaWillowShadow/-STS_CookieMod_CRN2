package CrnMod.powers.sup;

import CrnMod.Helper;
import com.megacrit.cardcrawl.actions.watcher.MeditateAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class SUP_Flour extends Abstract_SUP {
    public static final String PID = Helper.avoidDup(SUP_Flour.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(PID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(powerStrings.NAME, powerStrings.DESCRIPTIONS[0]));

    public SUP_Flour(int amount) {
        super( amount, PID, STS,"img/powers/Flour.png");
    }



    public void updateDescription() {
        this.description = String.format(STS.get(1),amount);
    }

    public void atEndOfTurn(boolean isPlayer){
        flash();
        addToTop(new MeditateAction(amount));
    }

}