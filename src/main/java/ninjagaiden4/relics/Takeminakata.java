package ninjagaiden4.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Takeminakata extends CustomRelic {
    public static final String ID = "Takeminakata";
    private static final String IMG = "lingqiu1700/ninjagaiden4/images/relics/Takeminakata.png";

    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Takeminakata() {
        super(ID, IMG, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Takeminakata();
    }
}
