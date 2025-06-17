package CrnMod.potions;

import CrnMod.Helper;
import CrnMod.powers.P_Aim;
import CrnMod.powers.P_Comb;
import CrnMod.powers.sup.SUP_Beer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Arrays;

import static CrnMod.Helper.P;
import static CrnMod.NJson.descriptionMakeForRelic;

public class PO_Ration extends AbstractPotion {
    public static final String POTION_ID = Helper.avoidDup(PO_Ration.class.getSimpleName());
    public static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static ArrayList<String> STS=new ArrayList<>(Arrays.asList(potionStrings.NAME, (potionStrings.DESCRIPTIONS[0])));

    public PO_Ration() {
        super(potionStrings.NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SNECKO, PotionColor.GREEN);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = String.format(STS.get(1), potency);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.ARTIFACT.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.ARTIFACT.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(P, P, new P_Comb(4), 4));
            this.addToBot(new ApplyPowerAction(P, P, new P_Aim(4), 4));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 4;
    }


    @Override
    public AbstractPotion makeCopy() {
        return new PO_Ration();
    }
}
