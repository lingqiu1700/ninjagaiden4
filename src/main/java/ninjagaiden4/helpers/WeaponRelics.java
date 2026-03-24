package ninjagaiden4.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import ninjagaiden4.modcore.WeaponTags;

import java.util.ArrayList;

public class WeaponRelics {

    private static final AbstractCard.CardTags[] ALL = {
            WeaponTags.W_S,WeaponTags.BLOOD_W_S,
            WeaponTags.F,WeaponTags.BLOOD_W_S,
            WeaponTags.BLOOD_C,WeaponTags.BLOOD_SPACE,
            WeaponTags.X,WeaponTags.Y
    };

    public static AbstractCard getReplacementCard(String tagWeaponID,AbstractCard oldCard){
        AbstractCard.CardTags targeWeaponTag = null;
        if (tagWeaponID.equals("ninjagaiden4:Yatousen")) targeWeaponTag = WeaponTags.WEAPON_YATOUSEN;
        else if (tagWeaponID.equals("ninjagaiden4:Takeminakata")) targeWeaponTag = WeaponTags.WEAPON_TAKEMINAKATA;

        if (targeWeaponTag == null) return null;

        ArrayList<AbstractCard.CardTags> oldCommond = new ArrayList<>();
        for (AbstractCard.CardTags c : ALL) {
            if (oldCard.hasTag(c)) {
                oldCommond.add(c);
            }
        }

        if (oldCommond.isEmpty()) return null;

        for (AbstractCard libraryCard : CardLibrary.cards.values()) {

            if (libraryCard.hasTag(targeWeaponTag) && libraryCard.tags.contains(oldCommond)) {

                return libraryCard.makeCopy();
            }
        }

        return null;
    }
}
