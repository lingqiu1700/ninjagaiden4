package ninjagaiden4.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.modcards.Bloodbath_Slaughter;

// 继承CustomRelic
public class hitogoroshi extends CustomRelic {
    // 遗物ID（此处的ModHelper在“04 - 本地化”的进阶中提到）
    public static final String ID = ModHelper.makeID("hitogoroshi");
    // 图片路径（大小128x128，可参考同目录的图片）
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/relics/hitogoroshi.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public hitogoroshi() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
        this.counter = 0;
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth ==0 && !m.halfDead) {
            this.counter++;
            this.flash();

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof Bloodbath_Slaughter) {
                    c.applyPowers();
                }
            }
        }
    }

    public boolean hasCharge() {
        return this.counter > 0;
    }

    public void consumeCharge() {
        if (this.counter > 0) {
            this.counter--;
            this.flash();
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Bloodbath_Slaughter) {
                c.applyPowers();
            }
        }
    }

    public boolean bloodhasCharge() {
        return this.counter >= 10;
    }

    public void bloodbathCharge() {
        if (this.counter > 9) {
            this.counter = this.counter - 10;
            this.flash();
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof Bloodbath_Slaughter) {
                c.applyPowers();
            }
        }
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new hitogoroshi();
    }
}
