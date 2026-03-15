package ninjagaiden4.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ninjagaiden4.modcards.Takeminakata.Spiral_Shadow;
import ninjagaiden4.modcards.Takeminakata.Blood.Takumo_Drop;
import ninjagaiden4.modcards.Yatousen.Blood.Eagle_Claw;
import ninjagaiden4.modcards.Yatousen.Hollow_Thrust;
import ninjagaiden4.relics.*;

public class SwapWeaponAction extends AbstractGameAction {
    private String targetWeaponRelicId; // 你想要切换到的新武器ID

    public SwapWeaponAction(String targetWeaponRelicId) {
        this.targetWeaponRelicId = targetWeaponRelicId;
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            // 1. 移除旧武器，获得新武器
            // 假设当前拿着 Takeminakata，要切成 Yatousen
            if (AbstractDungeon.player.hasRelic(Yatousen.ID) && targetWeaponRelicId.equals("ninjagaiden4:Yatousen")) {
                AbstractDungeon.player.loseRelic(Yatousen.ID); // 卸下旧武器
                // 获得新武器 (这里假设你有一个名为 Yatousen 的遗物类)
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Yatousen());
            }
            // 反过来：当前拿着 Yatousen，要切成 Takeminakata
            else if (AbstractDungeon.player.hasRelic("ninjagaiden4:Yatousen") && targetWeaponRelicId.equals(Yatousen.ID)) {
                AbstractDungeon.player.loseRelic("ninjagaiden4:Yatousen");
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Yatousen());
            }

            // 2. 替换所有位置的卡牌 (并且继承升级状态)
            replaceCardsInGroup(AbstractDungeon.player.masterDeck); // 替换总牌组（确保战斗结束后也是新卡）
            replaceCardsInGroup(AbstractDungeon.player.hand);       // 替换手牌
            replaceCardsInGroup(AbstractDungeon.player.drawPile);   // 替换抽牌堆
            replaceCardsInGroup(AbstractDungeon.player.discardPile);// 替换弃牌堆
            replaceCardsInGroup(AbstractDungeon.player.exhaustPile);// 替换消耗堆

            // 刷新手牌排版，防止手牌显示错位
            AbstractDungeon.player.hand.refreshHandLayout();

            this.isDone = true;
        }
    }

    // 遍历牌堆并替换卡牌的方法
    private void replaceCardsInGroup(CardGroup group) {
        for (int i = 0; i < group.group.size(); i++) {
            AbstractCard oldCard = group.group.get(i);
            // 获取对应的替换卡牌
            AbstractCard newCard = getCorrespondingCard(oldCard, targetWeaponRelicId);

            if (newCard != null) {
                // 如果旧卡牌升过级，新卡牌自动升级！
                if (oldCard.upgraded) {
                    newCard.upgrade();
                }
                // 执行替换
                group.group.set(i, newCard);
            }
        }
    }

    // 核心映射库：定义哪张卡对应哪张卡
    private AbstractCard getCorrespondingCard(AbstractCard oldCard, String newWeaponId) {
        // 如果切成 Yatousen（夜叉王）
        if (newWeaponId.equals("ninjagaiden4:Yatousen")) {
            // Takeminakata的卡 -> 变成 Yatousen的卡
            if (oldCard.cardID.equals(Spiral_Shadow.ID)) return new Eagle_Claw(); // 替换为你实际的卡
            if (oldCard.cardID.equals(Takumo_Drop.ID)) return new Hollow_Thrust();  // 替换为你实际的卡
        }
        // 如果切成 Takeminakata（建御名方）
        else if (newWeaponId.equals(Yatousen.ID)) {
            // Yatousen的卡 -> 变成 Takeminakata的卡
            if (oldCard.cardID.equals("ninjagaiden4:Yatousen_Attack_1")) return new Spiral_Shadow();
            if (oldCard.cardID.equals("ninjagaiden4:Yatousen_Attack_2")) return new Takumo_Drop();
        }

        return null; // 如果不是武器专属卡（比如基础打击、防御），就不替换
    }
}