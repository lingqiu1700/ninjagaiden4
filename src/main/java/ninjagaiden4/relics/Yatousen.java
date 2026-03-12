package ninjagaiden4.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class Yatousen extends CustomRelic {
    public static final String ID = "Yatousen";
    private static final String IMG = "lingqiu1700/ninjagaiden4/images/relics/Yatousen.png";

    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public Yatousen() {
        super(ID, IMG, RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Yatousen();
    }
}
