package CrnMod.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import java.util.ArrayList;
import java.util.Arrays;

@SpirePatch(
        clz = AbstractCard.class,
        method = "<class>"
)
public class MultiCardPreview {
    public static SpireField<ArrayList<AbstractCard>> multiCardPreview = new SpireField(ArrayList::new);
    public static SpireField<Boolean> horizontal = new SpireField(() -> {
        return false;
    });

    public MultiCardPreview() {
    }

    public static void add(AbstractCard card, AbstractCard... previews) {
        add(card, false, previews);
    }

    public static void add(AbstractCard card, boolean horizontalOnly, AbstractCard... previews) {
        ((ArrayList)multiCardPreview.get(card)).addAll(Arrays.asList(previews));
        horizontal.set(card, horizontalOnly);
    }

    public static void remove(AbstractCard card, AbstractCard preview) {
        ((ArrayList)multiCardPreview.get(card)).remove(preview);
    }

    public static void clear(AbstractCard card) {
        ((ArrayList)multiCardPreview.get(card)).clear();
    }

    public static void horizontalOnly(AbstractCard card) {
        horizontalOnly(card, true);
    }

    public static void horizontalOnly(AbstractCard card, boolean horizontal) {
        basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal.set(card, horizontal);
    }
}